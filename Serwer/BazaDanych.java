/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allianceserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lenovo
 */
public class BazaDanych {
    private Connection connect;
    public BazaDanych(){
        try{
            Class.forName("org.h2.Driver");
            connect = DriverManager.getConnection("jdbc:h2:file:~/test;USER=ROOT;PASSWORD=haslo");          
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void zarejestruj(String login, String haslo){
        System.out.println("Rejestruje z baza");
        try{
            String tmp = "SELECT COUNT(*) FROM ALLIANCE.UZYTKOWNIK";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(tmp);
            int licznik = 1;
            while(resultSet.next()){
                licznik = resultSet.getInt("count(*)");  
            }    
            String sql = "INSERT INTO ALLIANCE.UZYTKOWNIK (ID, LOGIN, HASLO, DREWNO, GLINA,"
                + "ZELAZO, ZBOZE, RATUSZ, MAGAZYN, KOSZARY, STAJNIA, KRYJOWKA, "
                + "MURYOBRONNE, RYNEK, TARTAK, KOPALNIAGLINY, KOPALNIAZELAZA, "
                + "POLEZBOZA, PALAC, PRZYCISK1, PRZYCISK2, PRZYCISK3, PRZYCISK4,"
                + "PRZYCISK5, PRZYCISK6, PRZYCISK7, PRZYCISK8, PRZYCISK9, PRZYCISK10,"
                + "PRZYCISK11, PRZYCISK12, PRZYCISK13, PRZYCISK14, PRZYCISK15,"
                + "POLE1, POLE2, POLE3, POLE4, POLE5, POLE6, POLE7, POLE8, POLE9,"
                + "POLE10, POLE11, POLE12, POLE13, POLE14, POLE15, POLE16, POLE17,"
                + "POLE18, POLE19, POLE20, POLE21, POLE22, POLE23, POLE24,"
                + "JEDNOSTKALEKKA, JEDNOSTKACIEZKA, LUCZNIK, TROPICIEL, KAWALERIA, "
                + "OSADNIK) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, licznik+1);
            prepare.setString(2,login);
            prepare.setString(3,haslo);
            prepare.setInt(4,500);//Drewno
            prepare.setInt(5,500);//Glina
            prepare.setInt(6,500);//Zelazo
            prepare.setInt(7,500);//Zboze
            prepare.setInt(8,1);//Ratusz
            prepare.setInt(9,1);//Magazyn
            prepare.setInt(10,0);//Koszary
            prepare.setInt(11,0);//Stajnia
            prepare.setInt(12,0);//Kryjowka
            prepare.setInt(13, 1);//Mury Obronne
            prepare.setInt(14,0);//Rynek
            prepare.setInt(15,0);//tartak
            prepare.setInt(16, 0);//kopania gliny
            prepare.setInt(17,0);//kopalnia żelazo
            prepare.setInt(18,0);//pole zboza
            prepare.setInt(19,0);//palac
            //Mur obronny nie musi byc w tej puli przyciskow
            prepare.setString(20,"Ratusz");//Przycisk1
            prepare.setString(21, "Magazyn");//Przycisk2
            prepare.setString(22, "brak");//Przycisk3
            prepare.setString(23,"brak");//Przycisk4
            prepare.setString(24,"brak");//Przycisk5
            prepare.setString(25,"brak");//Przycisk6
            prepare.setString(26,"brak");//Przycisk7
            prepare.setString(27,"brak");//Przycisk8
            prepare.setString(28,"brak");//Przycisk9
            prepare.setString(29,"brak");//Przycisk10
            prepare.setString(30,"brak");//Przycisk11
            prepare.setString(31,"brak");//Przycisk12
            prepare.setString(32,"brak");//Przycisk13
            prepare.setString(33,"brak");//Przycisk14
            prepare.setString(34,"brak");//Przycisk15
            prepare.setString(35,"brak");//Pole1
            prepare.setString(36,"brak");//Pole2
            prepare.setString(37,"brak");//Pole3
            prepare.setString(38,"brak");//Pole4
            prepare.setString(39,"brak");//Pole5
            prepare.setString(40,"brak");//Pole6
            prepare.setString(41,"brak");//Pole7
            prepare.setString(42,"brak");//Pole8
            prepare.setString(43,"brak");//Pole9
            prepare.setString(44,"brak");//Pole10
            prepare.setString(45,"brak");//Pole11
            prepare.setString(46,"brak");//Pole12
            prepare.setString(47,"brak");//Pole13
            prepare.setString(48,"brak");//Pole14
            prepare.setString(49,"brak");//Pole15
            prepare.setString(50,"brak");//Pole16
            prepare.setString(51,"brak");//Pole17
            prepare.setString(52,"brak");//Pole18
            prepare.setString(53,"brak");//Pole19
            prepare.setString(54,"brak");//Pole20
            prepare.setString(55,"brak");//Pole21
            prepare.setString(56,"brak");//Pole22
            prepare.setString(57,"brak");//Pole23
            prepare.setString(58,"brak");//Pole24
            prepare.setInt(59,0);//Jednostka lekka
            prepare.setInt(60, 0);//Jednostak ciezka
            prepare.setInt(61, 0);//Lucznik
            prepare.setInt(62, 0);//Tropiciel
            prepare.setInt(63,0);//Kawaleria
            prepare.setInt(64,0);//Osadnik
            prepare.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Integer licznik(String sql){
        int licznik = 0;
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);            
            while(resultSet.next()){
                licznik = resultSet.getInt("count(*)");  
            }  
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return licznik;
    }
    public String zwroc(String sql){
        String tmp="";
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);            
            while(resultSet.next()){
                //Tu dodaj duzo tego ;p
                tmp = tmp + resultSet.getString("LOGIN")+"@";                
                tmp = tmp + resultSet.getInt("DREWNO")+"@";//Drewno
                tmp = tmp + resultSet.getInt("GLINA")+"@";//Glina
                tmp = tmp + resultSet.getInt("Zelazo")+"@";//Zelazo
                tmp = tmp + resultSet.getInt("ZBOZE")+"@";//Zboze
                tmp = tmp + resultSet.getInt("RATUSZ")+"@";//Ratusz
                tmp = tmp + resultSet.getInt("MAGAZYN")+"@";//Magazyn
                tmp = tmp + resultSet.getInt("KOSZARY")+"@";//Koszary
                tmp = tmp + resultSet.getInt("STAJNIA")+"@";//Stajnia
                tmp = tmp + resultSet.getInt("KRYJOWKA")+"@";//Kryjowka
                tmp = tmp + resultSet.getInt("MURYOBRONNE")+"@";//Mury Obronne
                tmp = tmp + resultSet.getInt("RYNEK")+"@";//Rynek
                tmp = tmp + resultSet.getInt("TARTAK")+"@";//tartak - produkcja
                tmp = tmp + resultSet.getInt("KOPALNIAGLINY")+"@";//kopania gliny - produkcja
                tmp = tmp + resultSet.getInt("KOPALNIAZELAZA")+"@";//kopalnia żelazo - produkcja
                tmp = tmp + resultSet.getInt("POLEZBOZA")+"@";//pole zboza - produkcja
                tmp = tmp + resultSet.getInt("PALAC")+"@";//palac
                //Mur obronny nie musi byc w tej puli przyciskow
                tmp = tmp + resultSet.getString("PRZYCISK1")+"@";//Przycisk1
                tmp = tmp + resultSet.getString("PRZYCISK2")+"@";//Przycisk2
                tmp = tmp + resultSet.getString("PRZYCISK3")+"@";//Przycisk3
                tmp = tmp + resultSet.getString("PRZYCISK4")+"@";//Przycisk4
                tmp = tmp + resultSet.getString("PRZYCISK5")+"@";//Przycisk5
                tmp = tmp + resultSet.getString("PRZYCISK6")+"@";//Przycisk6
                tmp = tmp + resultSet.getString("PRZYCISK7")+"@";//Przycisk7
                tmp = tmp + resultSet.getString("PRZYCISK8")+"@";//Przycisk8
                tmp = tmp + resultSet.getString("PRZYCISK9")+"@";//Przycisk19
                tmp = tmp + resultSet.getString("PRZYCISK10")+"@";//Przycisk10
                tmp = tmp + resultSet.getString("PRZYCISK11")+"@";//Przycisk11
                tmp = tmp + resultSet.getString("PRZYCISK12")+"@";//Przycisk12
                tmp = tmp + resultSet.getString("PRZYCISK13")+"@";//Przycisk13
                tmp = tmp + resultSet.getString("PRZYCISK14")+"@";//Przycisk14
                tmp = tmp + resultSet.getString("PRZYCISK15")+"@";//Przycisk15
                tmp = tmp + resultSet.getString("POLE1")+"@";//Pole1
                tmp = tmp + resultSet.getString("POLE2")+"@";//Pole2
                tmp = tmp + resultSet.getString("POLE3")+"@";//Pole3
                tmp = tmp + resultSet.getString("POLE4")+"@";//Pole4
                tmp = tmp + resultSet.getString("POLE5")+"@";//Pole5
                tmp = tmp + resultSet.getString("POLE6")+"@";//Pole6
                tmp = tmp + resultSet.getString("POLE7")+"@";//Pole7
                tmp = tmp + resultSet.getString("POLE8")+"@";//Pole8
                tmp = tmp + resultSet.getString("POLE9")+"@";//Pole9
                tmp = tmp + resultSet.getString("POLE10")+"@";//Pole10
                tmp = tmp + resultSet.getString("POLE11")+"@";//Pole11
                tmp = tmp + resultSet.getString("POLE12")+"@";//Pole12
                tmp = tmp + resultSet.getString("POLE13")+"@";//Pole13
                tmp = tmp + resultSet.getString("POLE14")+"@";//Pole14
                tmp = tmp + resultSet.getString("POLE15")+"@";//Pole15
                tmp = tmp + resultSet.getString("POLE16")+"@";//Pole16
                tmp = tmp + resultSet.getString("POLE17")+"@";//Pole17
                tmp = tmp + resultSet.getString("POLE18")+"@";//Pole18
                tmp = tmp + resultSet.getString("POLE19")+"@";//Pole19
                tmp = tmp + resultSet.getString("POLE20")+"@";//Pole20
                tmp = tmp + resultSet.getString("POLE21")+"@";//Pole21
                tmp = tmp + resultSet.getString("POLE22")+"@";//Pole22
                tmp = tmp + resultSet.getString("POLE23")+"@";//Pole23
                tmp = tmp + resultSet.getString("POLE24")+"@";//Pole24
                tmp = tmp + resultSet.getInt("JEDNOSTKALEKKA")+"@";//Jednostka lekka
                tmp = tmp + resultSet.getInt("JEDNOSTKACIEZKA")+"@";//Jednostak ciezka
                tmp = tmp + resultSet.getInt("LUCZNIK")+"@";//Lucznik
                tmp = tmp + resultSet.getInt("TROPICIEL")+"@";//Tropiciel
                tmp = tmp + resultSet.getInt("KAWALERIA")+"@";//Kawaleria
                tmp = tmp + resultSet.getInt("OSADNIK");//Osadnik
            } 
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    public String surowce(String sql, String xyz){
        String tmp = xyz+"@";
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql); 
            while(resultSet.next()){
                tmp = tmp + resultSet.getInt("DREWNO")+"@";
                tmp = tmp + resultSet.getInt("GLINA")+"@";
                tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                tmp = tmp + resultSet.getInt("ZBOZE");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }        
        return tmp;
    }
    public void updatePole(int drewno, int glina, int zelazo, int zboze, String gdzie, String pole, String login){//Do budowy pol
        String sql = "UPDATE ALLIANCE.UZYTKOWNIK SET DREWNO = ?, GLINA = ?, ZELAZO = ?, ZBOZE = ?, "+gdzie+" = ? WHERE LOGIN = ?;";
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, drewno);
            prepare.setInt(2, glina);
            prepare.setInt(3, zelazo);
            prepare.setInt(4, zboze);
            prepare.setString(5,pole);
            prepare.setString(6,login);
            prepare.executeUpdate();
            String user = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+login+"';";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(user);
            String [] tmp = new String[24];
            Integer produkcjaDrewna = 0;
            Integer produkcjaGliny = 0;
            Integer produkcjaZelaza = 0;
            Integer produkcjaZboza = 0;
            //Stara produkcja zostaje po staremu tylko nowe wartosci musza byc dodawane!!!!
            while(resultSet.next()){
                tmp[0] = resultSet.getString("POLE1");//Pole1
                tmp[1] = resultSet.getString("POLE2");//Pole2
                tmp[2] = resultSet.getString("POLE3");//Pole3
                tmp[3] = resultSet.getString("POLE4");//Pole4
                tmp[4] = resultSet.getString("POLE5");//Pole5
                tmp[5] = resultSet.getString("POLE6");//Pole6
                tmp[6] = resultSet.getString("POLE7");//Pole7
                tmp[7] = resultSet.getString("POLE8");//Pole8
                tmp[8] = resultSet.getString("POLE9");//Pole9
                tmp[9] = resultSet.getString("POLE10");//Pole10
                tmp[10] = resultSet.getString("POLE11");//Pole11
                tmp[11] = resultSet.getString("POLE12");//Pole12
                tmp[12] = resultSet.getString("POLE13");//Pole13
                tmp[13] = resultSet.getString("POLE14");//Pole14
                tmp[14] = resultSet.getString("POLE15");//Pole15
                tmp[15] = resultSet.getString("POLE16");//Pole16
                tmp[16] = resultSet.getString("POLE17");//Pole17
                tmp[17] = resultSet.getString("POLE18");//Pole18
                tmp[18] = resultSet.getString("POLE19");//Pole19
                tmp[19] = resultSet.getString("POLE20");//Pole20
                tmp[20] = resultSet.getString("POLE21");//Pole21
                tmp[21] = resultSet.getString("POLE22");//Pole22
                tmp[22] = resultSet.getString("POLE23");//Pole23
                tmp[23] = resultSet.getString("POLE24");//Pole24
                //produkcjaDrewna = resultSet.getInt("TARTAK");//tartak
                //produkcjaGliny = resultSet.getInt("KOPALNIAGLINY");//kopania gliny
                //produkcjaZelaza = resultSet.getInt("KOPALNIAZELAZA");//kopalnia żelazo
                //produkcjaZboza = resultSet.getInt("POLEZBOZA");//pole zboza
            }
            for(int i=0;i<tmp.length;i++){
                String tak = tmp[i];       
                tmp[i] = tmp[i].replaceAll("[^0-9]+", "");
                tak = tak.replaceAll("\\d", "");
                if(tak.equals("Drewno")){
                    String search = "SELECT PRODUKCJA FROM ALLIANCE.DREWNO WHERE ID = "+tmp[i]+";";
                    Statement st = connect.createStatement();
                    ResultSet res = st.executeQuery(search);
                    while(res.next()){
                        //System.out.println(res.getInt("PRODUKCJA"));
                        produkcjaDrewna = produkcjaDrewna + res.getInt("PRODUKCJA");
                    }                   
                }
                if(tak.equals("Glina")){
                    String search = "SELECT PRODUKCJA FROM ALLIANCE.GLINA WHERE ID = "+tmp[i]+";";
                    Statement st = connect.createStatement();
                    ResultSet res = st.executeQuery(search);
                    while(res.next()){
                        //System.out.println(search);
                        produkcjaGliny = produkcjaGliny + res.getInt("PRODUKCJA");
                    }                   
                }
                if(tak.equals("Zelazo")){
                    String search = "SELECT PRODUKCJA FROM ALLIANCE.ZELAZO WHERE ID = "+tmp[i]+";";
                    Statement st = connect.createStatement();
                    ResultSet res = st.executeQuery(search);
                    while(res.next()){
                        //System.out.println(search);
                        produkcjaZelaza = produkcjaZelaza + res.getInt("PRODUKCJA");
                    }                    
                }
                if(tak.equals("Zboze")){
                    String search = "SELECT PRODUKCJA FROM ALLIANCE.ZBOZE WHERE ID = "+tmp[i]+";";
                    Statement st = connect.createStatement();
                    ResultSet res = st.executeQuery(search);
                    while(res.next()){
                        //System.out.println(search);
                        produkcjaZboza = produkcjaZboza + res.getInt("PRODUKCJA");
                    }                   
                }
            }
            String boTak = "UPDATE ALLIANCE.UZYTKOWNIK SET TARTAK = ?, KOPALNIAGLINY = ?, KOPALNIAZELAZA = ?, POLEZBOZA = ? WHERE LOGIN = ?;";
            PreparedStatement prep = connect.prepareStatement(boTak);
            prep.setInt(1, produkcjaDrewna);
            prep.setInt(2, produkcjaGliny);
            prep.setInt(3, produkcjaZelaza);
            prep.setInt(4, produkcjaZboza);
            prep.setString(5,login);
            prep.executeUpdate();           
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String opis(String sql){
        String tmp = "Opis@";
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tmp = tmp + resultSet.getString("OPIS");
                tmp = tmp + "<html>.<br>By wybudować ten budynek potrzebujesz: </html>";
                tmp = tmp + resultSet.getString("WYMAGANIA");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    //Zwraca liste budynkow wraz z nazwa, opisem, surowcami potrzebnymi na dany poziom - 6 
    //Tu mamy tylko liste budynkow ktore mozna wybudowac a nie rozubowac    
    //Mamy tu podzial na dwa dzielniki @ ktory dzieli na poszczegolne informacje
    //Oraz $ ktory oddziela budynki
    public String listaBudynkow(){
        String tmp ="";
        String sql1 = "SELECT * FROM ALLIANCE.RATUSZ WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql1, "Ratusz")+"$";
        String sql2 = "SELECT * FROM ALLIANCE.MAGAZYN WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql2, "Magazyn")+"$";
        String sql3 = "SELECT * FROM ALLIANCE.KOSZARY WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql3, "Koszary")+"$";
        String sql4 = "SELECT * FROM ALLIANCE.KRYJOWKA WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql4, "Kryjowka")+"$";
        String sql5 = "SELECT * FROM ALLIANCE.RYNEK WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql5, "Rynek")+"$";
        String sql6 = "SELECT * FROM ALLIANCE.STAJNIA WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql6, "Stajnia")+"$";
        String sql7 = "SELECT * FROM ALLIANCE.PALAC WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql7, "Palac")+"$";
        return tmp;
    }
    private String tmpLista(String sql, String nazwa){
        String tmp = nazwa+"@";
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tmp = tmp + resultSet.getInt("DREWNO")+"@";
                tmp = tmp + resultSet.getInt("GLINA")+"@";
                tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                tmp = tmp + resultSet.getInt("ZBOZE")+"@";
                tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
                tmp = tmp + resultSet.getString("OPIS");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    public void updateBudynek(Integer drewno, Integer glina, Integer zelazo, Integer zboze, String budynek, Integer poziom, String pozycja, String login){
        String sql = "UPDATE ALLIANCE.UZYTKOWNIK SET DREWNO = ?, GLINA = ?, ZELAZO = ?, ZBOZE = ?, "+budynek.toUpperCase()+" = ? ,"+pozycja+" = ? WHERE LOGIN = ?";
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1,drewno);
            prepare.setInt(2,glina);
            prepare.setInt(3,zelazo);
            prepare.setInt(4,zboze);
            prepare.setInt(5,poziom);
            prepare.setString(6,budynek);
            prepare.setString(7,login);
            prepare.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String kryjowka(Integer id){
        String tmp = "Opis@";
        try{
            String sql = "SELECT * FROM ALLIANCE.KRYJOWKA WHERE ID = "+id;
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tmp = tmp + resultSet.getInt("DREWNO")+"@";
                tmp = tmp + resultSet.getInt("GLINA")+"@";
                tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                tmp = tmp + resultSet.getInt("ZBOZE")+"@";
                tmp = tmp + resultSet.getInt("POJEMNOSC")+"@";
                tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
                tmp = tmp + resultSet.getString("OPIS");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    public String magazyn(Integer id){
        String tmp = "Opis@";
        try{
            String sql = "SELECT * FROM ALLIANCE.MAGAZYN WHERE ID = "+id;
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tmp = tmp + resultSet.getInt("DREWNO")+"@";
                tmp = tmp + resultSet.getInt("GLINA")+"@";
                tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                tmp = tmp + resultSet.getInt("ZBOZE")+"@";
                tmp = tmp + resultSet.getInt("POJEMNOSC")+"@";
                tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
                tmp = tmp + resultSet.getString("OPIS");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    public String muryObronne(Integer id){
        String tmp = "Opis@";
        try{
            String sql = "SELECT * FROM ALLIANCE.MURYOBRONNE WHERE ID = "+id;
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tmp = tmp + resultSet.getInt("DREWNO")+"@";
                tmp = tmp + resultSet.getInt("GLINA")+"@";
                tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                tmp = tmp + resultSet.getInt("ZBOZE")+"@";
                tmp = tmp + resultSet.getInt("OBRONNOSC")+"@";
                tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
                tmp = tmp + resultSet.getString("OPIS");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    public void updateMuryObronne(Integer drewno, Integer glina, Integer zelazo, Integer zboze, Integer poziom, String login){
        String sql = "UPDATE ALLIANCE.UZYTKOWNIK SET DREWNO = ?, GLINA = ?, ZELAZO = ?, ZBOZE = ?, MURYOBRONNE = ? WHERE LOGIN = ?";
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1,drewno);
            prepare.setInt(2,glina);
            prepare.setInt(3,zelazo);
            prepare.setInt(4,zboze);
            prepare.setInt(5,poziom);          
            prepare.setString(6,login);
            prepare.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String koszary(Integer id){
        String tmp = "Opis@";
        try{
           String sql = "SELECT * FROM ALLIANCE.KOSZARY WHERE ID = "+id;
           Statement statement = connect.createStatement();
           ResultSet resultSet = statement.executeQuery(sql); 
           while(resultSet.next()){
               tmp = tmp + resultSet.getInt("DREWNO")+"@";
               tmp = tmp + resultSet.getInt("GLINA")+"@";
               tmp = tmp + resultSet.getInt("ZELAZO")+"@";
               tmp = tmp + resultSet.getInt("ZBOZE")+"@";               
               tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
               tmp = tmp + resultSet.getString("OPIS");
           }
        }
        catch(SQLException e){
            e.printStackTrace();
        }        
        return tmp;
    }
    public String stajnia(Integer id){
        String tmp = "Opis@";
        try{
            String sql = "SELECT * FROM ALLIANCE.STAJNIA WHERE ID = "+id;
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
               tmp = tmp + resultSet.getInt("DREWNO")+"@";
               tmp = tmp + resultSet.getInt("GLINA")+"@";
               tmp = tmp + resultSet.getInt("ZELAZO")+"@";
               tmp = tmp + resultSet.getInt("ZBOZE")+"@";               
               tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
               tmp = tmp + resultSet.getString("OPIS");
           }
        }
        catch(SQLException e){
            e.printStackTrace();
        }        
        return tmp;
    }
    public String listaStajnia(){
        String tmp = "";
        String nazwy = wojsko("Stajnia");
        String [] tmpNazwy = nazwy.split("@");
        for(int i=0;i<tmpNazwy.length;i++){
            String sql = "SELECT * FROM ALLIANCE.JEDNOSTKI WHERE NAZWA = '"+tmpNazwy[i]+"';"; 
            try{
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    tmp = tmp + resultSet.getString("NAZWA")+"@";
                    tmp = tmp + resultSet.getInt("ATAK")+"@";
                    tmp = tmp + resultSet.getInt("OBRONNOSC")+"@";
                    tmp = tmp + resultSet.getInt("DREWNO")+"@";
                    tmp = tmp + resultSet.getInt("GLINA")+"@";
                    tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                    tmp = tmp + resultSet.getInt("ZBOZE")+"@";
                    tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
                    tmp = tmp + resultSet.getString("OPIS")+"$";
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }        
        return tmp;
    }
    public String listaKoszary(){
        String tmp = "";
        String nazwy = wojsko("Koszary");
        String [] tmpNazwy = nazwy.split("@");
        for(int i=0;i<tmpNazwy.length;i++){
            String sql = "SELECT * FROM ALLIANCE.JEDNOSTKI WHERE NAZWA = '"+tmpNazwy[i]+"';";            
            try{
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    tmp = tmp + resultSet.getString("NAZWA")+"@";
                    tmp = tmp + resultSet.getInt("ATAK")+"@";
                    tmp = tmp + resultSet.getInt("OBRONNOSC")+"@";
                    tmp = tmp + resultSet.getInt("DREWNO")+"@";
                    tmp = tmp + resultSet.getInt("GLINA")+"@";
                    tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                    tmp = tmp + resultSet.getInt("ZBOZE")+"@";
                    tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
                    tmp = tmp + resultSet.getString("OPIS")+"$";
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }        
        return tmp;
    }
    public String wojsko(String typ){
        String tmp = "";
        String nazwa = "";
        String wymagania = "";
        try{
            String sql = "SELECT * FROM ALLIANCE.JEDNOSTKI";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
                nazwa = nazwa + resultSet.getString("NAZWA")+"@";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        String [] pom = tmp.split("@");
        String [] pomNazwa = nazwa.split("@");
        for(int i=0;i<pom.length;i++){
            String tak = pom[i]; 
            pom[i] = pom[i].replaceAll("[^0-9]+", "");
            tak = tak.replaceAll("\\d", ""); 
            if(tak.equals(typ)){
                wymagania = wymagania + pomNazwa[i]+"@";
            }            
        }
        return wymagania;
    }
    public void uploadJednostki(Integer drewno, Integer glina, Integer zelazo, Integer zboze, String typ, Integer ilosc, String login){        
        typ = typ.replaceAll(" ", "");
        String licznik = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+login+"';";
        Integer tak = 0;
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(licznik);
            while(resultSet.next()){
                tak = resultSet.getInt(typ.toUpperCase());
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        String sql = "UPDATE ALLIANCE.UZYTKOWNIK SET DREWNO = ?, GLINA = ?, ZELAZO = ?, ZBOZE = ?, "+typ.toUpperCase()+ " = ? WHERE LOGIN = ?";
        System.out.println(typ.toUpperCase());
        Integer suma = tak + ilosc;
        System.out.println("Suma "+suma+" bylo "+tak+" wyszkolono "+ilosc);
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, drewno);
            prepare.setInt(2, glina);
            prepare.setInt(3, zelazo);
            prepare.setInt(4, zboze);
            //prepare.setInt(5,ilosc);
            prepare.setInt(5,suma);
            prepare.setString(6,login);
            prepare.executeUpdate();
            System.out.println(prepare.toString());
            System.out.println(sql);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}

                + "POLE18, POLE19, POLE20, POLE21, POLE22, POLE23, POLE24,"
                + "JEDNOSTKALEKKA, JEDNOSTKACIEZKA, LUCZNIK, TROPICIEL, KAWALERIA, "
                + "OSADNIK) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, licznik+1);
            prepare.setString(2,login);
            prepare.setString(3,haslo);
            prepare.setInt(4,500);//Drewno
            prepare.setInt(5,500);//Glina
            prepare.setInt(6,500);//Zelazo
            prepare.setInt(7,500);//Zboze
            prepare.setInt(8,1);//Ratusz
            prepare.setInt(9,1);//Magazyn
            prepare.setInt(10,0);//Koszary
            prepare.setInt(11,0);//Stajnia
            prepare.setInt(12,0);//Kryjowka
            prepare.setInt(13, 1);//Mury Obronne
            prepare.setInt(14,0);//Rynek
            prepare.setInt(15,0);//tartak
            prepare.setInt(16, 0);//kopania gliny
            prepare.setInt(17,0);//kopalnia żelazo
            prepare.setInt(18,0);//pole zboza
            prepare.setInt(19,0);//palac
            //Mur obronny nie musi byc w tej puli przyciskow
            prepare.setString(20,"Ratusz");//Przycisk1
            prepare.setString(21, "Magazyn");//Przycisk2
            prepare.setString(22, "brak");//Przycisk3
            prepare.setString(23,"brak");//Przycisk4
            prepare.setString(24,"brak");//Przycisk5
            prepare.setString(25,"brak");//Przycisk6
            prepare.setString(26,"brak");//Przycisk7
            prepare.setString(27,"brak");//Przycisk8
            prepare.setString(28,"brak");//Przycisk9
            prepare.setString(29,"brak");//Przycisk10
            prepare.setString(30,"brak");//Przycisk11
            prepare.setString(31,"brak");//Przycisk12
            prepare.setString(32,"brak");//Przycisk13
            prepare.setString(33,"brak");//Przycisk14
            prepare.setString(34,"brak");//Przycisk15
            prepare.setString(35,"brak");//Pole1
            prepare.setString(36,"brak");//Pole2
            prepare.setString(37,"brak");//Pole3
            prepare.setString(38,"brak");//Pole4
            prepare.setString(39,"brak");//Pole5
            prepare.setString(40,"brak");//Pole6
            prepare.setString(41,"brak");//Pole7
            prepare.setString(42,"brak");//Pole8
            prepare.setString(43,"brak");//Pole9
            prepare.setString(44,"brak");//Pole10
            prepare.setString(45,"brak");//Pole11
            prepare.setString(46,"brak");//Pole12
            prepare.setString(47,"brak");//Pole13
            prepare.setString(48,"brak");//Pole14
            prepare.setString(49,"brak");//Pole15
            prepare.setString(50,"brak");//Pole16
            prepare.setString(51,"brak");//Pole17
            prepare.setString(52,"brak");//Pole18
            prepare.setString(53,"brak");//Pole19
            prepare.setString(54,"brak");//Pole20
            prepare.setString(55,"brak");//Pole21
            prepare.setString(56,"brak");//Pole22
            prepare.setString(57,"brak");//Pole23
            prepare.setString(58,"brak");//Pole24
            prepare.setInt(59,0);//Jednostka lekka
            prepare.setInt(60, 0);//Jednostak ciezka
            prepare.setInt(61, 0);//Lucznik
            prepare.setInt(62, 0);//Tropiciel
            prepare.setInt(63,0);//Kawaleria
            prepare.setInt(64,0);//Osadnik
            prepare.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Integer licznik(String sql){
        int licznik = 0;
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);            
            while(resultSet.next()){
                licznik = resultSet.getInt("count(*)");  
            }  
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return licznik;
    }
    public String zwroc(String sql){
        String tmp="";
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);            
            while(resultSet.next()){
                //Tu dodaj duzo tego ;p
                tmp = tmp + resultSet.getString("LOGIN")+"@";                
                tmp = tmp + resultSet.getInt("DREWNO")+"@";//Drewno
                tmp = tmp + resultSet.getInt("GLINA")+"@";//Glina
                tmp = tmp + resultSet.getInt("Zelazo")+"@";//Zelazo
                tmp = tmp + resultSet.getInt("ZBOZE")+"@";//Zboze
                tmp = tmp + resultSet.getInt("RATUSZ")+"@";//Ratusz
                tmp = tmp + resultSet.getInt("MAGAZYN")+"@";//Magazyn
                tmp = tmp + resultSet.getInt("KOSZARY")+"@";//Koszary
                tmp = tmp + resultSet.getInt("STAJNIA")+"@";//Stajnia
                tmp = tmp + resultSet.getInt("KRYJOWKA")+"@";//Kryjowka
                tmp = tmp + resultSet.getInt("MURYOBRONNE")+"@";//Mury Obronne
                tmp = tmp + resultSet.getInt("RYNEK")+"@";//Rynek
                tmp = tmp + resultSet.getInt("TARTAK")+"@";//tartak - produkcja
                tmp = tmp + resultSet.getInt("KOPALNIAGLINY")+"@";//kopania gliny - produkcja
                tmp = tmp + resultSet.getInt("KOPALNIAZELAZA")+"@";//kopalnia żelazo - produkcja
                tmp = tmp + resultSet.getInt("POLEZBOZA")+"@";//pole zboza - produkcja
                tmp = tmp + resultSet.getInt("PALAC")+"@";//palac
                //Mur obronny nie musi byc w tej puli przyciskow
                tmp = tmp + resultSet.getString("PRZYCISK1")+"@";//Przycisk1
                tmp = tmp + resultSet.getString("PRZYCISK2")+"@";//Przycisk2
                tmp = tmp + resultSet.getString("PRZYCISK3")+"@";//Przycisk3
                tmp = tmp + resultSet.getString("PRZYCISK4")+"@";//Przycisk4
                tmp = tmp + resultSet.getString("PRZYCISK5")+"@";//Przycisk5
                tmp = tmp + resultSet.getString("PRZYCISK6")+"@";//Przycisk6
                tmp = tmp + resultSet.getString("PRZYCISK7")+"@";//Przycisk7
                tmp = tmp + resultSet.getString("PRZYCISK8")+"@";//Przycisk8
                tmp = tmp + resultSet.getString("PRZYCISK9")+"@";//Przycisk19
                tmp = tmp + resultSet.getString("PRZYCISK10")+"@";//Przycisk10
                tmp = tmp + resultSet.getString("PRZYCISK11")+"@";//Przycisk11
                tmp = tmp + resultSet.getString("PRZYCISK12")+"@";//Przycisk12
                tmp = tmp + resultSet.getString("PRZYCISK13")+"@";//Przycisk13
                tmp = tmp + resultSet.getString("PRZYCISK14")+"@";//Przycisk14
                tmp = tmp + resultSet.getString("PRZYCISK15")+"@";//Przycisk15
                tmp = tmp + resultSet.getString("POLE1")+"@";//Pole1
                tmp = tmp + resultSet.getString("POLE2")+"@";//Pole2
                tmp = tmp + resultSet.getString("POLE3")+"@";//Pole3
                tmp = tmp + resultSet.getString("POLE4")+"@";//Pole4
                tmp = tmp + resultSet.getString("POLE5")+"@";//Pole5
                tmp = tmp + resultSet.getString("POLE6")+"@";//Pole6
                tmp = tmp + resultSet.getString("POLE7")+"@";//Pole7
                tmp = tmp + resultSet.getString("POLE8")+"@";//Pole8
                tmp = tmp + resultSet.getString("POLE9")+"@";//Pole9
                tmp = tmp + resultSet.getString("POLE10")+"@";//Pole10
                tmp = tmp + resultSet.getString("POLE11")+"@";//Pole11
                tmp = tmp + resultSet.getString("POLE12")+"@";//Pole12
                tmp = tmp + resultSet.getString("POLE13")+"@";//Pole13
                tmp = tmp + resultSet.getString("POLE14")+"@";//Pole14
                tmp = tmp + resultSet.getString("POLE15")+"@";//Pole15
                tmp = tmp + resultSet.getString("POLE16")+"@";//Pole16
                tmp = tmp + resultSet.getString("POLE17")+"@";//Pole17
                tmp = tmp + resultSet.getString("POLE18")+"@";//Pole18
                tmp = tmp + resultSet.getString("POLE19")+"@";//Pole19
                tmp = tmp + resultSet.getString("POLE20")+"@";//Pole20
                tmp = tmp + resultSet.getString("POLE21")+"@";//Pole21
                tmp = tmp + resultSet.getString("POLE22")+"@";//Pole22
                tmp = tmp + resultSet.getString("POLE23")+"@";//Pole23
                tmp = tmp + resultSet.getString("POLE24")+"@";//Pole24
                tmp = tmp + resultSet.getInt("JEDNOSTKALEKKA")+"@";//Jednostka lekka
                tmp = tmp + resultSet.getInt("JEDNOSTKACIEZKA")+"@";//Jednostak ciezka
                tmp = tmp + resultSet.getInt("LUCZNIK")+"@";//Lucznik
                tmp = tmp + resultSet.getInt("TROPICIEL")+"@";//Tropiciel
                tmp = tmp + resultSet.getInt("KAWALERIA")+"@";//Kawaleria
                tmp = tmp + resultSet.getInt("OSADNIK");//Osadnik
            } 
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    public String surowce(String sql, String xyz){
        String tmp = xyz+"@";
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql); 
            while(resultSet.next()){
                tmp = tmp + resultSet.getInt("DREWNO")+"@";
                tmp = tmp + resultSet.getInt("GLINA")+"@";
                tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                tmp = tmp + resultSet.getInt("ZBOZE");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }        
        return tmp;
    }
    public void updatePole(int drewno, int glina, int zelazo, int zboze, String gdzie, String pole, String login){//Do budowy pol
        String sql = "UPDATE ALLIANCE.UZYTKOWNIK SET DREWNO = ?, GLINA = ?, ZELAZO = ?, ZBOZE = ?, "+gdzie+" = ? WHERE LOGIN = ?;";
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, drewno);
            prepare.setInt(2, glina);
            prepare.setInt(3, zelazo);
            prepare.setInt(4, zboze);
            prepare.setString(5,pole);
            prepare.setString(6,login);
            prepare.executeUpdate();
            String user = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+login+"';";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(user);
            String [] tmp = new String[24];
            Integer produkcjaDrewna = 0;
            Integer produkcjaGliny = 0;
            Integer produkcjaZelaza = 0;
            Integer produkcjaZboza = 0;
            //Stara produkcja zostaje po staremu tylko nowe wartosci musza byc dodawane!!!!
            while(resultSet.next()){
                tmp[0] = resultSet.getString("POLE1");//Pole1
                tmp[1] = resultSet.getString("POLE2");//Pole2
                tmp[2] = resultSet.getString("POLE3");//Pole3
                tmp[3] = resultSet.getString("POLE4");//Pole4
                tmp[4] = resultSet.getString("POLE5");//Pole5
                tmp[5] = resultSet.getString("POLE6");//Pole6
                tmp[6] = resultSet.getString("POLE7");//Pole7
                tmp[7] = resultSet.getString("POLE8");//Pole8
                tmp[8] = resultSet.getString("POLE9");//Pole9
                tmp[9] = resultSet.getString("POLE10");//Pole10
                tmp[10] = resultSet.getString("POLE11");//Pole11
                tmp[11] = resultSet.getString("POLE12");//Pole12
                tmp[12] = resultSet.getString("POLE13");//Pole13
                tmp[13] = resultSet.getString("POLE14");//Pole14
                tmp[14] = resultSet.getString("POLE15");//Pole15
                tmp[15] = resultSet.getString("POLE16");//Pole16
                tmp[16] = resultSet.getString("POLE17");//Pole17
                tmp[17] = resultSet.getString("POLE18");//Pole18
                tmp[18] = resultSet.getString("POLE19");//Pole19
                tmp[19] = resultSet.getString("POLE20");//Pole20
                tmp[20] = resultSet.getString("POLE21");//Pole21
                tmp[21] = resultSet.getString("POLE22");//Pole22
                tmp[22] = resultSet.getString("POLE23");//Pole23
                tmp[23] = resultSet.getString("POLE24");//Pole24
                //produkcjaDrewna = resultSet.getInt("TARTAK");//tartak
                //produkcjaGliny = resultSet.getInt("KOPALNIAGLINY");//kopania gliny
                //produkcjaZelaza = resultSet.getInt("KOPALNIAZELAZA");//kopalnia żelazo
                //produkcjaZboza = resultSet.getInt("POLEZBOZA");//pole zboza
            }
            for(int i=0;i<tmp.length;i++){
                String tak = tmp[i];       
                tmp[i] = tmp[i].replaceAll("[^0-9]+", "");
                tak = tak.replaceAll("\\d", "");
                if(tak.equals("Drewno")){
                    String search = "SELECT PRODUKCJA FROM ALLIANCE.DREWNO WHERE ID = "+tmp[i]+";";
                    Statement st = connect.createStatement();
                    ResultSet res = st.executeQuery(search);
                    while(res.next()){
                        //System.out.println(res.getInt("PRODUKCJA"));
                        produkcjaDrewna = produkcjaDrewna + res.getInt("PRODUKCJA");
                    }                   
                }
                if(tak.equals("Glina")){
                    String search = "SELECT PRODUKCJA FROM ALLIANCE.GLINA WHERE ID = "+tmp[i]+";";
                    Statement st = connect.createStatement();
                    ResultSet res = st.executeQuery(search);
                    while(res.next()){
                        //System.out.println(search);
                        produkcjaGliny = produkcjaGliny + res.getInt("PRODUKCJA");
                    }                   
                }
                if(tak.equals("Zelazo")){
                    String search = "SELECT PRODUKCJA FROM ALLIANCE.ZELAZO WHERE ID = "+tmp[i]+";";
                    Statement st = connect.createStatement();
                    ResultSet res = st.executeQuery(search);
                    while(res.next()){
                        //System.out.println(search);
                        produkcjaZelaza = produkcjaZelaza + res.getInt("PRODUKCJA");
                    }                    
                }
                if(tak.equals("Zboze")){
                    String search = "SELECT PRODUKCJA FROM ALLIANCE.ZBOZE WHERE ID = "+tmp[i]+";";
                    Statement st = connect.createStatement();
                    ResultSet res = st.executeQuery(search);
                    while(res.next()){
                        //System.out.println(search);
                        produkcjaZboza = produkcjaZboza + res.getInt("PRODUKCJA");
                    }                   
                }
            }
            String boTak = "UPDATE ALLIANCE.UZYTKOWNIK SET TARTAK = ?, KOPALNIAGLINY = ?, KOPALNIAZELAZA = ?, POLEZBOZA = ? WHERE LOGIN = ?;";
            PreparedStatement prep = connect.prepareStatement(boTak);
            prep.setInt(1, produkcjaDrewna);
            prep.setInt(2, produkcjaGliny);
            prep.setInt(3, produkcjaZelaza);
            prep.setInt(4, produkcjaZboza);
            prep.setString(5,login);
            prep.executeUpdate();           
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String opis(String sql){
        String tmp = "Opis@";
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tmp = tmp + resultSet.getString("OPIS");
                tmp = tmp + "<html>.<br>By wybudować ten budynek potrzebujesz: </html>";
                tmp = tmp + resultSet.getString("WYMAGANIA");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    //Zwraca liste budynkow wraz z nazwa, opisem, surowcami potrzebnymi na dany poziom - 6 
    //Tu mamy tylko liste budynkow ktore mozna wybudowac a nie rozubowac    
    //Mamy tu podzial na dwa dzielniki @ ktory dzieli na poszczegolne informacje
    //Oraz $ ktory oddziela budynki
    public String listaBudynkow(){
        String tmp ="";
        String sql1 = "SELECT * FROM ALLIANCE.RATUSZ WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql1, "Ratusz")+"$";
        String sql2 = "SELECT * FROM ALLIANCE.MAGAZYN WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql2, "Magazyn")+"$";
        String sql3 = "SELECT * FROM ALLIANCE.KOSZARY WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql3, "Koszary")+"$";
        String sql4 = "SELECT * FROM ALLIANCE.KRYJOWKA WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql4, "Kryjowka")+"$";
        String sql5 = "SELECT * FROM ALLIANCE.RYNEK WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql5, "Rynek")+"$";
        String sql6 = "SELECT * FROM ALLIANCE.STAJNIA WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql6, "Stajnia")+"$";
        String sql7 = "SELECT * FROM ALLIANCE.PALAC WHERE ID = 1;";   
        tmp = tmp + tmpLista(sql7, "Palac")+"$";
        return tmp;
    }
    private String tmpLista(String sql, String nazwa){
        String tmp = nazwa+"@";
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tmp = tmp + resultSet.getInt("DREWNO")+"@";
                tmp = tmp + resultSet.getInt("GLINA")+"@";
                tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                tmp = tmp + resultSet.getInt("ZBOZE")+"@";
                tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
                tmp = tmp + resultSet.getString("OPIS");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    public void updateBudynek(Integer drewno, Integer glina, Integer zelazo, Integer zboze, String budynek, Integer poziom, String pozycja, String login){
        String sql = "UPDATE ALLIANCE.UZYTKOWNIK SET DREWNO = ?, GLINA = ?, ZELAZO = ?, ZBOZE = ?, "+budynek.toUpperCase()+" = ? ,"+pozycja+" = ? WHERE LOGIN = ?";
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1,drewno);
            prepare.setInt(2,glina);
            prepare.setInt(3,zelazo);
            prepare.setInt(4,zboze);
            prepare.setInt(5,poziom);
            prepare.setString(6,budynek);
            prepare.setString(7,login);
            prepare.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String kryjowka(Integer id){
        String tmp = "Opis@";
        try{
            String sql = "SELECT * FROM ALLIANCE.KRYJOWKA WHERE ID = "+id;
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tmp = tmp + resultSet.getInt("DREWNO")+"@";
                tmp = tmp + resultSet.getInt("GLINA")+"@";
                tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                tmp = tmp + resultSet.getInt("ZBOZE")+"@";
                tmp = tmp + resultSet.getInt("POJEMNOSC")+"@";
                tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
                tmp = tmp + resultSet.getString("OPIS");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    public String magazyn(Integer id){
        String tmp = "Opis@";
        try{
            String sql = "SELECT * FROM ALLIANCE.MAGAZYN WHERE ID = "+id;
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tmp = tmp + resultSet.getInt("DREWNO")+"@";
                tmp = tmp + resultSet.getInt("GLINA")+"@";
                tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                tmp = tmp + resultSet.getInt("ZBOZE")+"@";
                tmp = tmp + resultSet.getInt("POJEMNOSC")+"@";
                tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
                tmp = tmp + resultSet.getString("OPIS");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    public String muryObronne(Integer id){
        String tmp = "Opis@";
        try{
            String sql = "SELECT * FROM ALLIANCE.MURYOBRONNE WHERE ID = "+id;
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tmp = tmp + resultSet.getInt("DREWNO")+"@";
                tmp = tmp + resultSet.getInt("GLINA")+"@";
                tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                tmp = tmp + resultSet.getInt("ZBOZE")+"@";
                tmp = tmp + resultSet.getInt("OBRONNOSC")+"@";
                tmp = tmp + resultSet.getString("WYMAGANIA")+"@";
                tmp = tmp + resultSet.getString("OPIS");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    public void updateMuryObronne(Integer drewno, Integer glina, Integer zelazo, Integer zboze, Integer poziom, String login){
        String sql = "UPDATE ALLIANCE.UZYTKOWNIK SET DREWNO = ?, GLINA = ?, ZELAZO = ?, ZBOZE = ?, MURYOBRONNE = ? WHERE LOGIN = ?";
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1,drewno);
            prepare.setInt(2,glina);
            prepare.setInt(3,zelazo);
            prepare.setInt(4,zboze);
            prepare.setInt(5,poziom);          
            prepare.setString(6,login);
            prepare.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}

                + "POLE18, POLE19, POLE20, POLE21, POLE22, POLE23, POLE24,"
                + "JEDNOSTKALEKKA, JEDNOSTKACIEZKA, LUCZNIK, TROPICIEL, KAWALERIA, "
                + "OSADNIK) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, licznik+1);
            prepare.setString(2,login);
            prepare.setString(3,haslo);
            prepare.setInt(4,500);//Drewno
            prepare.setInt(5,500);//Glina
            prepare.setInt(6,500);//Zelazo
            prepare.setInt(7,500);//Zboze
            prepare.setInt(8,1);//Ratusz
            prepare.setInt(9,1);//Magazyn
            prepare.setInt(10,0);//Koszary
            prepare.setInt(11,0);//Stajnia
            prepare.setInt(12,0);//Kryjowka
            prepare.setInt(13, 1);//Mury Obronne
            prepare.setInt(14,0);//Rynek
            prepare.setInt(15,0);//tartak
            prepare.setInt(16, 0);//kopania gliny
            prepare.setInt(17,0);//kopalnia żelazo
            prepare.setInt(18,0);//pole zboza
            prepare.setInt(19,0);//palac
            //Mur obronny nie musi byc w tej puli przyciskow
            prepare.setString(20,"Ratusz");//Przycisk1
            prepare.setString(21, "Magazyn");//Przycisk2
            prepare.setString(22, "brak");//Przycisk3
            prepare.setString(23,"brak");//Przycisk4
            prepare.setString(24,"brak");//Przycisk5
            prepare.setString(25,"brak");//Przycisk6
            prepare.setString(26,"brak");//Przycisk7
            prepare.setString(27,"brak");//Przycisk8
            prepare.setString(28,"brak");//Przycisk9
            prepare.setString(29,"brak");//Przycisk10
            prepare.setString(30,"brak");//Przycisk11
            prepare.setString(31,"brak");//Przycisk12
            prepare.setString(32,"brak");//Przycisk13
            prepare.setString(33,"brak");//Przycisk14
            prepare.setString(34,"brak");//Przycisk15
            prepare.setString(35,"brak");//Pole1
            prepare.setString(36,"brak");//Pole2
            prepare.setString(37,"brak");//Pole3
            prepare.setString(38,"brak");//Pole4
            prepare.setString(39,"brak");//Pole5
            prepare.setString(40,"brak");//Pole6
            prepare.setString(41,"brak");//Pole7
            prepare.setString(42,"brak");//Pole8
            prepare.setString(43,"brak");//Pole9
            prepare.setString(44,"brak");//Pole10
            prepare.setString(45,"brak");//Pole11
            prepare.setString(46,"brak");//Pole12
            prepare.setString(47,"brak");//Pole13
            prepare.setString(48,"brak");//Pole14
            prepare.setString(49,"brak");//Pole15
            prepare.setString(50,"brak");//Pole16
            prepare.setString(51,"brak");//Pole17
            prepare.setString(52,"brak");//Pole18
            prepare.setString(53,"brak");//Pole19
            prepare.setString(54,"brak");//Pole20
            prepare.setString(55,"brak");//Pole21
            prepare.setString(56,"brak");//Pole22
            prepare.setString(57,"brak");//Pole23
            prepare.setString(58,"brak");//Pole24
            prepare.setInt(59,0);//Jednostka lekka
            prepare.setInt(60, 0);//Jednostak ciezka
            prepare.setInt(61, 0);//Lucznik
            prepare.setInt(62, 0);//Tropiciel
            prepare.setInt(63,0);//Kawaleria
            prepare.setInt(64,0);//Osadnik
            prepare.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Integer licznik(String sql){
        int licznik = 0;
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);            
            while(resultSet.next()){
                licznik = resultSet.getInt("count(*)");  
            }  
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return licznik;
    }
    public String zwroc(String sql){
        String tmp="";
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);            
            while(resultSet.next()){
                //Tu dodaj duzo tego ;p
                tmp = tmp + resultSet.getString("LOGIN")+"@";                
                tmp = tmp + resultSet.getInt("DREWNO")+"@";//Drewno
                tmp = tmp + resultSet.getInt("GLINA")+"@";//Glina
                tmp = tmp + resultSet.getInt("Zelazo")+"@";//Zelazo
                tmp = tmp + resultSet.getInt("ZBOZE")+"@";//Zboze
                tmp = tmp + resultSet.getInt("RATUSZ")+"@";//Ratusz
                tmp = tmp + resultSet.getInt("MAGAZYN")+"@";//Magazyn
                tmp = tmp + resultSet.getInt("KOSZARY")+"@";//Koszary
                tmp = tmp + resultSet.getInt("STAJNIA")+"@";//Stajnia
                tmp = tmp + resultSet.getInt("KRYJOWKA")+"@";//Kryjowka
                tmp = tmp + resultSet.getInt("MURYOBRONNE")+"@";//Mury Obronne
                tmp = tmp + resultSet.getInt("RYNEK")+"@";//Rynek
                tmp = tmp + resultSet.getInt("TARTAK")+"@";//tartak - produkcja
                tmp = tmp + resultSet.getInt("KOPALNIAGLINY")+"@";//kopania gliny - produkcja
                tmp = tmp + resultSet.getInt("KOPALNIAZELAZA")+"@";//kopalnia żelazo - produkcja
                tmp = tmp + resultSet.getInt("POLEZBOZA")+"@";//pole zboza - produkcja
                tmp = tmp + resultSet.getInt("PALAC")+"@";//palac
                //Mur obronny nie musi byc w tej puli przyciskow
                tmp = tmp + resultSet.getString("PRZYCISK1")+"@";//Przycisk1
                tmp = tmp + resultSet.getString("PRZYCISK2")+"@";//Przycisk2
                tmp = tmp + resultSet.getString("PRZYCISK3")+"@";//Przycisk3
                tmp = tmp + resultSet.getString("PRZYCISK4")+"@";//Przycisk4
                tmp = tmp + resultSet.getString("PRZYCISK5")+"@";//Przycisk5
                tmp = tmp + resultSet.getString("PRZYCISK6")+"@";//Przycisk6
                tmp = tmp + resultSet.getString("PRZYCISK7")+"@";//Przycisk7
                tmp = tmp + resultSet.getString("PRZYCISK8")+"@";//Przycisk8
                tmp = tmp + resultSet.getString("PRZYCISK9")+"@";//Przycisk19
                tmp = tmp + resultSet.getString("PRZYCISK10")+"@";//Przycisk10
                tmp = tmp + resultSet.getString("PRZYCISK11")+"@";//Przycisk11
                tmp = tmp + resultSet.getString("PRZYCISK12")+"@";//Przycisk12
                tmp = tmp + resultSet.getString("PRZYCISK13")+"@";//Przycisk13
                tmp = tmp + resultSet.getString("PRZYCISK14")+"@";//Przycisk14
                tmp = tmp + resultSet.getString("PRZYCISK15")+"@";//Przycisk15
                tmp = tmp + resultSet.getString("POLE1")+"@";//Pole1
                tmp = tmp + resultSet.getString("POLE2")+"@";//Pole2
                tmp = tmp + resultSet.getString("POLE3")+"@";//Pole3
                tmp = tmp + resultSet.getString("POLE4")+"@";//Pole4
                tmp = tmp + resultSet.getString("POLE5")+"@";//Pole5
                tmp = tmp + resultSet.getString("POLE6")+"@";//Pole6
                tmp = tmp + resultSet.getString("POLE7")+"@";//Pole7
                tmp = tmp + resultSet.getString("POLE8")+"@";//Pole8
                tmp = tmp + resultSet.getString("POLE9")+"@";//Pole9
                tmp = tmp + resultSet.getString("POLE10")+"@";//Pole10
                tmp = tmp + resultSet.getString("POLE11")+"@";//Pole11
                tmp = tmp + resultSet.getString("POLE12")+"@";//Pole12
                tmp = tmp + resultSet.getString("POLE13")+"@";//Pole13
                tmp = tmp + resultSet.getString("POLE14")+"@";//Pole14
                tmp = tmp + resultSet.getString("POLE15")+"@";//Pole15
                tmp = tmp + resultSet.getString("POLE16")+"@";//Pole16
                tmp = tmp + resultSet.getString("POLE17")+"@";//Pole17
                tmp = tmp + resultSet.getString("POLE18")+"@";//Pole18
                tmp = tmp + resultSet.getString("POLE19")+"@";//Pole19
                tmp = tmp + resultSet.getString("POLE20")+"@";//Pole20
                tmp = tmp + resultSet.getString("POLE21")+"@";//Pole21
                tmp = tmp + resultSet.getString("POLE22")+"@";//Pole22
                tmp = tmp + resultSet.getString("POLE23")+"@";//Pole23
                tmp = tmp + resultSet.getString("POLE24")+"@";//Pole24
                tmp = tmp + resultSet.getInt("JEDNOSTKALEKKA")+"@";//Jednostka lekka
                tmp = tmp + resultSet.getInt("JEDNOSTKACIEZKA")+"@";//Jednostak ciezka
                tmp = tmp + resultSet.getInt("LUCZNIK")+"@";//Lucznik
                tmp = tmp + resultSet.getInt("TROPICIEL")+"@";//Tropiciel
                tmp = tmp + resultSet.getInt("KAWALERIA")+"@";//Kawaleria
                tmp = tmp + resultSet.getInt("OSADNIK");//Osadnik
            } 
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
    public String surowce(String sql, String xyz){
        String tmp = xyz+"@";
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql); 
            while(resultSet.next()){
                tmp = tmp + resultSet.getInt("DREWNO")+"@";
                tmp = tmp + resultSet.getInt("GLINA")+"@";
                tmp = tmp + resultSet.getInt("ZELAZO")+"@";
                tmp = tmp + resultSet.getInt("ZBOZE");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }        
        return tmp;
    }
    public void updatePole(int drewno, int glina, int zelazo, int zboze, String gdzie, String pole, String login){//Do budowy pol
        String sql = "UPDATE ALLIANCE.UZYTKOWNIK SET DREWNO = ?, GLINA = ?, ZELAZO = ?, ZBOZE = ?, "+gdzie+" = ? WHERE LOGIN = ?;";
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, drewno);
            prepare.setInt(2, glina);
            prepare.setInt(3, zelazo);
            prepare.setInt(4, zboze);
            prepare.setString(5,pole);
            prepare.setString(6,login);
            prepare.executeUpdate();
            String user = "SELECT * FROM ALLIANCE.UZYTKOWNIK WHERE LOGIN = '"+login+"';";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(user);
            String [] tmp = new String[24];
            Integer produkcjaDrewna = 0;
            Integer produkcjaGliny = 0;
            Integer produkcjaZelaza = 0;
            Integer produkcjaZboza = 0;
            //Stara produkcja zostaje po staremu tylko nowe wartosci musza byc dodawane!!!!
            while(resultSet.next()){
                tmp[0] = resultSet.getString("POLE1");//Pole1
                tmp[1] = resultSet.getString("POLE2");//Pole2
                tmp[2] = resultSet.getString("POLE3");//Pole3
                tmp[3] = resultSet.getString("POLE4");//Pole4
                tmp[4] = resultSet.getString("POLE5");//Pole5
                tmp[5] = resultSet.getString("POLE6");//Pole6
                tmp[6] = resultSet.getString("POLE7");//Pole7
                tmp[7] = resultSet.getString("POLE8");//Pole8
                tmp[8] = resultSet.getString("POLE9");//Pole9
                tmp[9] = resultSet.getString("POLE10");//Pole10
                tmp[10] = resultSet.getString("POLE11");//Pole11
                tmp[11] = resultSet.getString("POLE12");//Pole12
                tmp[12] = resultSet.getString("POLE13");//Pole13
                tmp[13] = resultSet.getString("POLE14");//Pole14
                tmp[14] = resultSet.getString("POLE15");//Pole15
                tmp[15] = resultSet.getString("POLE16");//Pole16
                tmp[16] = resultSet.getString("POLE17");//Pole17
                tmp[17] = resultSet.getString("POLE18");//Pole18
                tmp[18] = resultSet.getString("POLE19");//Pole19
                tmp[19] = resultSet.getString("POLE20");//Pole20
                tmp[20] = resultSet.getString("POLE21");//Pole21
                tmp[21] = resultSet.getString("POLE22");//Pole22
                tmp[22] = resultSet.getString("POLE23");//Pole23
                tmp[23] = resultSet.getString("POLE24");//Pole24
                //produkcjaDrewna = resultSet.getInt("TARTAK");//tartak
                //produkcjaGliny = resultSet.getInt("KOPALNIAGLINY");//kopania gliny
                //produkcjaZelaza = resultSet.getInt("KOPALNIAZELAZA");//kopalnia żelazo
                //produkcjaZboza = resultSet.getInt("POLEZBOZA");//pole zboza
            }
            for(int i=0;i<tmp.length;i++){
                String tak = tmp[i];       
                tmp[i] = tmp[i].replaceAll("[^0-9]+", "");
                tak = tak.replaceAll("\\d", "");
                if(tak.equals("Drewno")){
                    String search = "SELECT PRODUKCJA FROM ALLIANCE.DREWNO WHERE ID = "+tmp[i]+";";
                    Statement st = connect.createStatement();
                    ResultSet res = st.executeQuery(search);
                    while(res.next()){
                        //System.out.println(res.getInt("PRODUKCJA"));
                        produkcjaDrewna = produkcjaDrewna + res.getInt("PRODUKCJA");
                    }                   
                }
                if(tak.equals("Glina")){
                    String search = "SELECT PRODUKCJA FROM ALLIANCE.GLINA WHERE ID = "+tmp[i]+";";
                    Statement st = connect.createStatement();
                    ResultSet res = st.executeQuery(search);
                    while(res.next()){
                        //System.out.println(search);
                        produkcjaGliny = produkcjaGliny + res.getInt("PRODUKCJA");
                    }                   
                }
                if(tak.equals("Zelazo")){
                    String search = "SELECT PRODUKCJA FROM ALLIANCE.ZELAZO WHERE ID = "+tmp[i]+";";
                    Statement st = connect.createStatement();
                    ResultSet res = st.executeQuery(search);
                    while(res.next()){
                        //System.out.println(search);
                        produkcjaZelaza = produkcjaZelaza + res.getInt("PRODUKCJA");
                    }                    
                }
                if(tak.equals("Zboze")){
                    String search = "SELECT PRODUKCJA FROM ALLIANCE.ZBOZE WHERE ID = "+tmp[i]+";";
                    Statement st = connect.createStatement();
                    ResultSet res = st.executeQuery(search);
                    while(res.next()){
                        //System.out.println(search);
                        produkcjaZboza = produkcjaZboza + res.getInt("PRODUKCJA");
                    }                   
                }
            }
            String boTak = "UPDATE ALLIANCE.UZYTKOWNIK SET TARTAK = ?, KOPALNIAGLINY = ?, KOPALNIAZELAZA = ?, POLEZBOZA = ? WHERE LOGIN = ?;";
            PreparedStatement prep = connect.prepareStatement(boTak);
            prep.setInt(1, produkcjaDrewna);
            prep.setInt(2, produkcjaGliny);
            prep.setInt(3, produkcjaZelaza);
            prep.setInt(4, produkcjaZboza);
            prep.setString(5,login);
            prep.executeUpdate();           
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String opis(String sql){
        String tmp = "Opis@";
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tmp = tmp + resultSet.getString("OPIS");
                tmp = tmp + "<html>.<br>By wybudować ten budynek potrzebujesz: </html>";
                tmp = tmp + resultSet.getString("WYMAGANIA");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
}

                + "POLE18, POLE19, POLE20, POLE21, POLE22, POLE23, POLE24,"
                + "JEDNOSTKALEKKA, JEDNOSTKACIEZKA, LUCZNIK, TROPICIEL, KAWALERIA, "
                + "OSADNIK) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, licznik+1);
            prepare.setString(2,login);
            prepare.setString(3,haslo);
            prepare.setInt(4,500);//Drewno
            prepare.setInt(5,500);//Glina
            prepare.setInt(6,500);//Zelazo
            prepare.setInt(7,500);//Zboze
            prepare.setInt(8,1);//Ratusz
            prepare.setInt(9,1);//Magazyn
            prepare.setInt(10,0);//Koszary
            prepare.setInt(11,0);//Stajnia
            prepare.setInt(12,0);//Kryjowka
            prepare.setInt(13, 1);//Mury Obronne
            prepare.setInt(14,0);//Rynek
            prepare.setInt(15,0);//tartak
            prepare.setInt(16, 0);//kopania gliny
            prepare.setInt(17,0);//kopalnia żelazo
            prepare.setInt(18,0);//pole zboza
            prepare.setInt(19,0);//palac
            //Mur obronny nie musi byc w tej puli przyciskow
            prepare.setString(20,"Ratusz");//Przycisk1
            prepare.setString(21, "Magazyn");//Przycisk2
            prepare.setString(22, "brak");//Przycisk3
            prepare.setString(23,"brak");//Przycisk4
            prepare.setString(24,"brak");//Przycisk5
            prepare.setString(25,"brak");//Przycisk6
            prepare.setString(26,"brak");//Przycisk7
            prepare.setString(27,"brak");//Przycisk8
            prepare.setString(28,"brak");//Przycisk9
            prepare.setString(29,"brak");//Przycisk10
            prepare.setString(30,"brak");//Przycisk11
            prepare.setString(31,"brak");//Przycisk12
            prepare.setString(32,"brak");//Przycisk13
            prepare.setString(33,"brak");//Przycisk14
            prepare.setString(34,"brak");//Przycisk15
            prepare.setString(35,"brak");//Pole1
            prepare.setString(36,"brak");//Pole2
            prepare.setString(37,"brak");//Pole3
            prepare.setString(38,"brak");//Pole4
            prepare.setString(39,"brak");//Pole5
            prepare.setString(40,"brak");//Pole6
            prepare.setString(41,"brak");//Pole7
            prepare.setString(42,"brak");//Pole8
            prepare.setString(43,"brak");//Pole9
            prepare.setString(44,"brak");//Pole10
            prepare.setString(45,"brak");//Pole11
            prepare.setString(46,"brak");//Pole12
            prepare.setString(47,"brak");//Pole13
            prepare.setString(48,"brak");//Pole14
            prepare.setString(49,"brak");//Pole15
            prepare.setString(50,"brak");//Pole16
            prepare.setString(51,"brak");//Pole17
            prepare.setString(52,"brak");//Pole18
            prepare.setString(53,"brak");//Pole19
            prepare.setString(54,"brak");//Pole20
            prepare.setString(55,"brak");//Pole21
            prepare.setString(56,"brak");//Pole22
            prepare.setString(57,"brak");//Pole23
            prepare.setString(58,"brak");//Pole24
            prepare.setInt(59,0);//Jednostka lekka
            prepare.setInt(60, 0);//Jednostak ciezka
            prepare.setInt(61, 0);//Lucznik
            prepare.setInt(62, 0);//Tropiciel
            prepare.setInt(63,0);//Kawaleria
            prepare.setInt(64,0);//Osadnik
            prepare.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Integer licznik(String sql){
        int licznik = 0;
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);            
            while(resultSet.next()){
                licznik = resultSet.getInt("count(*)");  
            }  
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return licznik;
    }
    public String zwroc(String sql){
        String tmp="";
        try{
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);            
            while(resultSet.next()){
                //Tu dodaj duzo tego ;p
                tmp = tmp + resultSet.getString("LOGIN")+"@";                
                tmp = tmp + resultSet.getInt("DREWNO")+"@";//Drewno
                tmp = tmp + resultSet.getInt("GLINA")+"@";//Glina
                tmp = tmp + resultSet.getInt("Zelazo")+"@";//Zelazo
                tmp = tmp + resultSet.getInt("ZBOZE")+"@";//Zboze
                tmp = tmp + resultSet.getInt("RATUSZ")+"@";//Ratusz
                tmp = tmp + resultSet.getInt("MAGAZYN")+"@";//Magazyn
                tmp = tmp + resultSet.getInt("KOSZARY")+"@";//Koszary
                tmp = tmp + resultSet.getInt("STAJNIA")+"@";//Stajnia
                tmp = tmp + resultSet.getInt("KRYJOWKA")+"@";//Kryjowka
                tmp = tmp + resultSet.getInt("MURYOBRONNE")+"@";//Mury Obronne
                tmp = tmp + resultSet.getInt("RYNEK")+"@";//Rynek
                tmp = tmp + resultSet.getInt("TARTAK")+"@";//tartak
                tmp = tmp + resultSet.getInt("KOPALNIAGLINY")+"@";//kopania gliny
                tmp = tmp + resultSet.getInt("KOPALNIAZELAZA")+"@";//kopalnia żelazo
                tmp = tmp + resultSet.getInt("POLEZBOZA")+"@";//pole zboza
                tmp = tmp + resultSet.getInt("PALAC")+"@";//palac
                //Mur obronny nie musi byc w tej puli przyciskow
                tmp = tmp + resultSet.getString("PRZYCISK1")+"@";//Przycisk1
                tmp = tmp + resultSet.getString("PRZYCISK2")+"@";//Przycisk2
                tmp = tmp + resultSet.getString("PRZYCISK3")+"@";//Przycisk3
                tmp = tmp + resultSet.getString("PRZYCISK4")+"@";//Przycisk4
                tmp = tmp + resultSet.getString("PRZYCISK5")+"@";//Przycisk5
                tmp = tmp + resultSet.getString("PRZYCISK6")+"@";//Przycisk6
                tmp = tmp + resultSet.getString("PRZYCISK7")+"@";//Przycisk7
                tmp = tmp + resultSet.getString("PRZYCISK8")+"@";//Przycisk8
                tmp = tmp + resultSet.getString("PRZYCISK9")+"@";//Przycisk19
                tmp = tmp + resultSet.getString("PRZYCISK10")+"@";//Przycisk10
                tmp = tmp + resultSet.getString("PRZYCISK11")+"@";//Przycisk11
                tmp = tmp + resultSet.getString("PRZYCISK12")+"@";//Przycisk12
                tmp = tmp + resultSet.getString("PRZYCISK13")+"@";//Przycisk13
                tmp = tmp + resultSet.getString("PRZYCISK14")+"@";//Przycisk14
                tmp = tmp + resultSet.getString("PRZYCISK15")+"@";//Przycisk15
                tmp = tmp + resultSet.getString("POLE1")+"@";//Pole1
                tmp = tmp + resultSet.getString("POLE2")+"@";//Pole2
                tmp = tmp + resultSet.getString("POLE3")+"@";//Pole3
                tmp = tmp + resultSet.getString("POLE4")+"@";//Pole4
                tmp = tmp + resultSet.getString("POLE5")+"@";//Pole5
                tmp = tmp + resultSet.getString("POLE6")+"@";//Pole6
                tmp = tmp + resultSet.getString("POLE7")+"@";//Pole7
                tmp = tmp + resultSet.getString("POLE8")+"@";//Pole8
                tmp = tmp + resultSet.getString("POLE9")+"@";//Pole9
                tmp = tmp + resultSet.getString("POLE10")+"@";//Pole10
                tmp = tmp + resultSet.getString("POLE11")+"@";//Pole11
                tmp = tmp + resultSet.getString("POLE12")+"@";//Pole12
                tmp = tmp + resultSet.getString("POLE13")+"@";//Pole13
                tmp = tmp + resultSet.getString("POLE14")+"@";//Pole14
                tmp = tmp + resultSet.getString("POLE15")+"@";//Pole15
                tmp = tmp + resultSet.getString("POLE16")+"@";//Pole16
                tmp = tmp + resultSet.getString("POLE17")+"@";//Pole17
                tmp = tmp + resultSet.getString("POLE18")+"@";//Pole18
                tmp = tmp + resultSet.getString("POLE19")+"@";//Pole19
                tmp = tmp + resultSet.getString("POLE20")+"@";//Pole20
                tmp = tmp + resultSet.getString("POLE21")+"@";//Pole21
                tmp = tmp + resultSet.getString("POLE22")+"@";//Pole22
                tmp = tmp + resultSet.getString("POLE23")+"@";//Pole23
                tmp = tmp + resultSet.getString("POLE24")+"@";//Pole24
                tmp = tmp + resultSet.getInt("JEDNOSTKALEKKA")+"@";//Jednostka lekka
                tmp = tmp + resultSet.getInt("JEDNOSTKACIEZKA")+"@";//Jednostak ciezka
                tmp = tmp + resultSet.getInt("LUCZNIK")+"@";//Lucznik
                tmp = tmp + resultSet.getInt("TROPICIEL")+"@";//Tropiciel
                tmp = tmp + resultSet.getInt("KAWALERIA")+"@";//Kawaleria
                tmp = tmp + resultSet.getInt("OSADNIK");//Osadnik
            } 
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tmp;
    }
}
