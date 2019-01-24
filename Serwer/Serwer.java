/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allianceserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lenovo
 */
//Potrzebuje 3 watkow. Od tego czytania tego co gracze przysylaja,
//Od wysylania do graczy wiadomosci
//Od licznika
//Od serwera potrzebuje zeby odbieral od klienta informcje - pojedynczo
//Wysylal do klienta informacje pojedynczo
//Byl za zegarek
//Aktualizowal Baze Danych
public class Serwer{
    private Map <String,PrintWriter> listaGraczy;
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    private Socket graczSocket;
    private String nazwa = "";
    private BazaDanych db;
    
    public void polacz(BazaDanych db){
        this.db = db;
         listaGraczy = new HashMap <String,PrintWriter>(); 
         try{
             ServerSocket serverSocket = new ServerSocket(1234);
             while(true){
                graczSocket = serverSocket.accept();// W tym miejscu akceptujemy gracza    
                wyslij(graczSocket);
                Thread thread = new Thread(new Odbierz(graczSocket));
                thread.start();
             }
         }
         catch(IOException e){
             e.printStackTrace();
         }         
    }   
    public void wyslij(Socket graczSocket){
        try{
            wyslij = new PrintWriter(graczSocket.getOutputStream());
            listaGraczy.put(nazwa, wyslij);
            //Teraz moge wysylac informacj do konkretnego gracza
            //Odnajduje go po nazwie.
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    class Odbierz implements Runnable{
        Socket graczSocket;
        public Odbierz(Socket graczSocket){
            this.graczSocket = graczSocket;
            try{                
                InputStreamReader reader = new InputStreamReader(graczSocket.getInputStream());
                odbierz = new BufferedReader(reader);               
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            try{  
                String linia;                
                while((linia=odbierz.readLine())!=null){                    
                    String [] tmp = linia.split("@");
                    //Zrob cos z baza danych
                    if(tmp[0].equals("Logowanie")){
                        nazwa = tmp[1];
                        String sql = "SELECT COUNT(*) FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+tmp[1]+"' and HASLO = '"+tmp[2]+"';";
                        int licznik = db.licznik(sql);
                        //System.out.println(licznik);
                        if(licznik == 0){
                            wyslij.println("Brak");
                            wyslij.flush();
                        }
                        else{
                            String cos = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+tmp[1]+"';";
                            wyslij.println(db.zwroc(cos));
                            wyslij.flush();
                        }
                    }
                    if(tmp[0].equals("Rejestracja")){                        
                        nazwa = tmp[1];
                        String sql = "SELECT COUNT (*) FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN ='"+tmp[1]+"';";
                        int licznik = db.licznik(sql);
                        if(licznik >= 1){
                            wyslij.println("Uzytkownik istnieje");
                            wyslij.flush();
                        }
                        else{
                            db.zarejestruj(tmp[1], tmp[2]);
                            wyslij.println("Wszystko ok");
                            wyslij.flush();
                        }
                    } 
                    if(tmp[0].equals("IleKosztuje")){                        
                        String sql = "SELECT * FROM ALLIANCE."+tmp[1]+" WHERE ID = '"+tmp[2]+"';";
                        wyslij.println(db.surowce(sql,tmp[1]));
                        wyslij.flush();
                    }
                    if(tmp[0].equals("Upload")){
                        //Uaktualnia ile mam surowcow w magazynie
                        //Ile mam teraz na godzine
                        //I gdzie znajduje sie pole
                        //System.out.println("Upload");
                        db.updatePole(Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]), Integer.parseInt(tmp[4]), tmp[6], tmp[5], nazwa);
                        wyslij.println(db.zwroc("SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN ='"+nazwa+"';"));
                        wyslij.flush();
                    }
                    if(tmp[0].equals("Informacja")){
                        String sql = "SELECT * FROM ALLIANCE."+tmp[1].toUpperCase()+" WHERE ID = "+tmp[2];
                        wyslij.println(db.opis(sql));
                        wyslij.flush();
                    }
                    if(tmp[0].equals("ListaBudynkow")){
                        wyslij.println(db.listaBudynkow());
                        wyslij.flush();
                    }
                    if(tmp[0].equals("UploadBudynek")){
                        db.updateBudynek(Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]), Integer.parseInt(tmp[4]), tmp[5], Integer.parseInt(tmp[6]), tmp[7], nazwa);
                        wyslij.println(db.zwroc("SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN ='"+nazwa+"';"));
                        wyslij.flush();
                    }
                    if(tmp[0].equals("Kryjowka")){
                        wyslij.println(db.kryjowka(Integer.parseInt(tmp[1])));
                        wyslij.flush();
                    }
                    if(tmp[0].equals("Magazyn")){
                        wyslij.println(db.magazyn(Integer.parseInt(tmp[1])));
                        wyslij.flush();
                    }
                    if(tmp[0].equals("UploadMuryObronne")){
                        db.updateMuryObronne(Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]), Integer.parseInt(tmp[4]),Integer.parseInt(tmp[5]), nazwa);
                        wyslij.println(db.zwroc("SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN ='"+nazwa+"';"));
                        wyslij.flush();
                    }
                    if(tmp[0].equals("MuryObronne")){
                        wyslij.println(db.muryObronne(Integer.parseInt(tmp[1])));
                        wyslij.flush();
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }        
    }
}
