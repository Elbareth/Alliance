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
            connect = DriverManager.getConnection("jdbc:h2:file:~/test;USER=ROOT;PASSWORD=Focus7341Canson");          
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