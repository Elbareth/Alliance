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
         Thread licznik = new Thread (new Licznik());
         licznik.start();
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
                        //Dodajemy naszego gracza do mapy
                        String tak = db.addMapa(tmp[1]);
                        if(tak.equals("false")){
                            wyslij.println("Brak wolnych miejsc");
                            wyslij.flush();
                        }
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
                    if(tmp[0].equals("Koszary")){                        
                        wyslij.println(db.koszary(Integer.parseInt(tmp[1])));
                        wyslij.flush();
                    }
                    if(tmp[0].equals("WojskoKoszary")){                        
                        wyslij.println(db.listaKoszary());
                        wyslij.flush();
                    }
                    if(tmp[0].equals("UploadJednostki")){
                        db.uploadJednostki(Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]), Integer.parseInt(tmp[4]), tmp[5], Integer.parseInt(tmp[6]), nazwa);
                        wyslij.println(db.zwroc("SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN ='"+nazwa+"';"));
                        wyslij.flush();
                    }
                    if(tmp[0].equals("Stajnia")){
                        wyslij.println(db.stajnia(Integer.parseInt(tmp[1])));
                        wyslij.flush();
                    }
                    if(tmp[0].equals("WojskoStajnia")){
                        wyslij.println(db.listaStajnia());
                        wyslij.flush();
                    }
                    if(tmp[0].equals("Close")){
                        listaGraczy.remove(nazwa);
                        graczSocket.close();
                    }
                    if(tmp[0].equals("Mapa")){
                        wyslij.println(db.mapa());
                        wyslij.flush();
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }        
    }
    class Licznik implements Runnable{
        //Miejsce gdzie przechowujemy ile mamy na godzine
        //To będzie dokładnie ten sam identyfikator co u uzytkownika
        private Float [] drewno;
        private Float [] glina;
        private Float [] zelazo;
        private Float [] zboze;
        private Float [] drewnoTmp;
        private Float [] glinaTmp;
        private Float [] zelazoTmp;
        private Float [] zbozeTmp;
        @Override
        public void run(){            
            //Pobieram liste wszystkich graczy z bazy danych
            String tmp = db.listaGraczy();
            String [] lista = tmp.split("@");
            drewno = new Float[lista.length];
            glina = new Float[lista.length];
            zelazo = new Float[lista.length];
            zboze = new Float[lista.length];
            drewnoTmp = new Float[lista.length];
            glinaTmp = new Float[lista.length];
            zelazoTmp = new Float[lista.length];
            zbozeTmp = new Float[lista.length];
            for(int i=0;i<lista.length;i++){                
                String cos = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+lista[i]+"';";
                String wszystko = db.zwroc(cos);
                String [] tniemy = wszystko.split("@");
                //Teraz już wiemy ile poszczegolny uzytkownik ma na godzine
                //Dziele na 3600 by wiedziec jaki jest przyrost co sekunde
                //drewno[i] = Float.parseFloat(tniemy[12]) / 3600;
                drewno[i] = Float.parseFloat(tniemy[12]) / 3600;
                glina[i] = Float.parseFloat(tniemy[13]) / 3600;
                zelazo[i] = Float.parseFloat(tniemy[14]) / 3600;
                zboze[i] = Float.parseFloat(tniemy[15]) / 3600; 
                drewnoTmp[i] = 0F;
                glinaTmp[i] = 0F;
                zelazoTmp[i] = 0F;
                zbozeTmp[i] = 0F;
                //System.out.println("Drewno "+drewno[i]+" Glina "+glina[i]+ " Żelazo "+zelazo[i]+" Zboże "+zboze[i]+" dla użytkownika "+lista[i]);
            }
            //liczymy ile czasu uplynelo odkad wlaczylismy serwer
            //Co z czasem? 
            long start = System.currentTimeMillis();
            while(true){
                long tmpSek = System.currentTimeMillis() - start;
                float sekundka = tmpSek/1000F;
                start = System.currentTimeMillis(); 
                //System.out.println("Sekundka " + sekundka);
                //Dla każdego gracza liczymy ile ma surowcow
                for(int i=0;i<lista.length;i++){
                    //Liczymy przyrost w ciagu sekundy dla kazdego gracza                     
                    drewnoTmp[i] = drewnoTmp[i] + drewno[i]*sekundka;
                    glinaTmp[i] = glinaTmp[i] + glina[i]*sekundka;
                    zelazoTmp[i] = zelazoTmp[i] + zelazo[i]*sekundka;
                    zbozeTmp[i] = zbozeTmp[i] + zboze[i]*sekundka;                                    
                    //System.out.println(" Po Drewno "+drewnoTmp[i]+" Glina "+glinaTmp[i]+ " Żelazo "+zelazoTmp[i]+" Zboże "+zbozeTmp[i]+" dla użytkownika "+lista[i]);                    
                    if(drewnoTmp[i]>1){                        
                        //tu wywowule funkcje dodajaca do bazy danych
                        db.addSurowce(Math.round(drewnoTmp[i]), "DREWNO", lista[i]);                        
                        drewnoTmp[i] = 0F; // zeruje by mozna bylo dalej liczyc                        
                        //wysylam do danego uzytkownika nowa wartosc surowcow
                        //String tmpSQL = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+lista[i]+"';";
                        //sprawdznie czy to ten user!!!
                        if(lista[i].equals(nazwa)){
                            //System.out.println("W if");
                            String tmpSQL = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+nazwa+"';";
                            wyslij.println(db.zwroc(tmpSQL));
                            wyslij.flush();
                        }
                    }
                    if(glinaTmp[i]>1){
                        //tu wywowule funkcje dodajaca do bazy danych
                        db.addSurowce(Math.round(glinaTmp[i]), "GLINA", lista[i]);
                        glinaTmp[i] = 0F; // zeruje by mozna bylo dalej liczyc
                        //wysylam do danego uzytkownika nowa wartosc surowcow
                        //String tmpSQL = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+lista[i]+"';";
                        if(lista[i].equals(nazwa)){
                            System.out.println("W if");
                            String tmpSQL = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+nazwa+"';";
                            wyslij.println(db.zwroc(tmpSQL));
                            wyslij.flush();
                        }
                    }
                    if(zelazoTmp[i]>1){
                        //tu wywowule funkcje dodajaca do bazy danych
                        db.addSurowce(Math.round(zelazoTmp[i]), "ZELAZO", lista[i]);
                        zelazoTmp[i] = 0F; // zeruje by mozna bylo dalej liczyc
                        //wysylam do danego uzytkownika nowa wartosc surowcow
                        //String tmpSQL = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+lista[i]+"';";
                        if(lista[i].equals(nazwa)){
                            System.out.println("W if");
                            String tmpSQL = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+nazwa+"';";
                            wyslij.println(db.zwroc(tmpSQL));
                            wyslij.flush();
                        }
                    }
                    if(zbozeTmp[i]>1){
                        //tu wywowule funkcje dodajaca do bazy danych
                        db.addSurowce(Math.round(zbozeTmp[i]), "ZBOZE", lista[i]);
                        zbozeTmp[i] = 0F; // zeruje by mozna bylo dalej liczyc
                        //wysylam do danego uzytkownika nowa wartosc surowcow
                        //String tmpSQL = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+lista[i]+"';";
                        if(lista[i].equals(nazwa)){
                            System.out.println("W if");
                            String tmpSQL = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+nazwa+"';";
                            wyslij.println(db.zwroc(tmpSQL));
                            wyslij.flush();
                        }
                    }
                    try{
                        Thread.sleep(20);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }                    
                }
            }
        }
    }
}
