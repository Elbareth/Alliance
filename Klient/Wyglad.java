/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alliancegracz;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author lenovo
 */
public class Wyglad extends JFrame{
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    public Wyglad(){
        WiadomoscServer server = new WiadomoscServer();
        wyslij = server.getWyslij();
        odbierz = server.getOdbierz();
        setSize(500,500);
        setTitle("Alliance");
        try{
             setIconImage(ImageIO.read(this.getClass().getResource("ikonaMini.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Logowanie(wyslij,odbierz));
        setVisible(true);
    }
}
