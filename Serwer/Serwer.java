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
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }        
    }
}
