/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alliancegracz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo
 */
//Odbieranie informacji z bazy danych 
public class WiadomoscServer {
    private Socket gniazdo;
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    public WiadomoscServer(){
        try {
            gniazdo = new Socket("127.0.0.1",1234);
            InputStreamReader input = new InputStreamReader(gniazdo.getInputStream());
            odbierz = new BufferedReader(input);
            wyslij = new PrintWriter(gniazdo.getOutputStream());            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public PrintWriter getWyslij() {
        return wyslij;
    }

    public BufferedReader getOdbierz() {
        return odbierz;
    }
    public void close(){
        wyslij.println("Close");
        wyslij.flush();        
        try{
            wyslij.close();
            odbierz.close();
            gniazdo.close();
            JOptionPane.showMessageDialog(null,"Zostałeś pomyślnie wylogowany");
            System.exit(0);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }    
}
