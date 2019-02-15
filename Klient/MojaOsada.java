/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alliancegracz;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author lenovo
 */
public class MojaOsada extends JPanel{
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    private String linia;
    private String [] tmp;
    private String nazwa;
    private String login;
    private Thread wat;
    private JLabel drewnoMini;
    private JLabel drewnoIlosc;
    private JLabel glinaMini;
    private JLabel glinaIlosc;
    private JLabel zelazoMini;
    private JLabel zelazoIlosc;
    private JLabel zbozeMini;
    private JLabel zbozeIlosc;
    private JPanel panel1;
    private JButton powrot;
    private JButton wyloguj;
    private JFrame parent;
    private JLabel nazwaLabel;
    private JLabel wlascicielLabel;
    private JButton doOsady;
    private JButton wyslijHandlarzy;
    private JButton wyslijWojsko;
    private Integer id;
    private JLabel obraz;
    private WiadomoscServer serwus = new WiadomoscServer();
    public MojaOsada(PrintWriter wyslij, BufferedReader odbierz, String linia, String nazwa, String login, Integer id){
        this.wyslij = serwus.getWyslij();
        this.odbierz = serwus.getOdbierz();
        this.linia = linia;
        tmp = linia.split("@");
        this.nazwa = nazwa;
        this.login = login;
        this.id = id;
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setPreferredSize(new Dimension(1000,160));
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        powrot = new JButton("<<-");
        powrot.setPreferredSize(new Dimension(50,50));
        powrot.addActionListener(new Powrot());
        panel1.add(powrot);
        add(Box.createRigidArea(new Dimension(100,0)));
        drewnoMini = new JLabel(new ImageIcon(this.getClass().getResource("drewnoMini.png")));
        drewnoMini.setPreferredSize(new Dimension(30,30));
        panel1.add(drewnoMini);
        panel1.add(Box.createRigidArea(new Dimension(10,0)));
        drewnoIlosc = new JLabel(tmp[1]);
        drewnoIlosc.setPreferredSize(new Dimension(30,150));
        panel1.add(drewnoIlosc);
        panel1.add(Box.createRigidArea(new Dimension(100,0)));
        
        glinaMini = new JLabel(new ImageIcon(this.getClass().getResource("glinaMini.png")));
        glinaMini.setPreferredSize(new Dimension(30,30));
        panel1.add(glinaMini);
        panel1.add(Box.createRigidArea(new Dimension(10,0)));
        glinaIlosc = new JLabel(tmp[2]);
        glinaIlosc.setPreferredSize(new Dimension(30,150));
        panel1.add(glinaIlosc);
        panel1.add(Box.createRigidArea(new Dimension(100,0)));
        
        zelazoMini = new JLabel(new ImageIcon(this.getClass().getResource("zelazoMini.png")));
        zelazoMini.setPreferredSize(new Dimension(30,30));
        panel1.add(zelazoMini);
        panel1.add(Box.createRigidArea(new Dimension(10,0)));
        zelazoIlosc = new JLabel(tmp[3]);
        zelazoIlosc.setPreferredSize(new Dimension(30,150));
        panel1.add(zelazoIlosc);
        panel1.add(Box.createRigidArea(new Dimension(100,0)));
        
        zbozeMini = new JLabel(new ImageIcon(this.getClass().getResource("zbozeMini.png")));
        zbozeMini.setPreferredSize(new Dimension(30,30));
        panel1.add(zbozeMini);
        panel1.add(Box.createRigidArea(new Dimension(10,0)));
        zbozeIlosc = new JLabel(tmp[4]);
        zbozeIlosc.setPreferredSize(new Dimension(30,150));
        panel1.add(zbozeIlosc);
        panel1.add(Box.createRigidArea(new Dimension(100,0)));
        wyloguj = new JButton("Wyloguj się");
        wyloguj.setPreferredSize(new Dimension(150,30));
        wyloguj.addActionListener(new Wyloguj());
        panel1.add(wyloguj);
        add(panel1);
        nazwaLabel = new JLabel("Nazwa: "+nazwa);
        nazwaLabel.setPreferredSize(new Dimension(300,50));
        nazwaLabel.setFont(new Font("Lucida Handwriting kursywa",Font.BOLD,20));
        add(nazwaLabel);
        wlascicielLabel = new JLabel("To Twoja osada "+login);
        wlascicielLabel.setPreferredSize(new Dimension(300,50));
        wlascicielLabel.setFont(new Font("Lucida Handwriting kursywa",Font.BOLD,20));
        add(wlascicielLabel);
        obraz = new JLabel(new ImageIcon(this.getClass().getResource("mojaOsadaMax.png")));
        obraz.setPreferredSize(new Dimension(650,400));
        add(obraz);
        doOsady = new JButton("Przejdź do mojej osady");
        doOsady.setPreferredSize(new Dimension(200,50));
        add(doOsady);
        wyslijHandlarzy = new JButton("Wyślij Handlarzy");
        wyslijHandlarzy.setPreferredSize(new Dimension(200,50));
        add(wyslijHandlarzy);
        wyslijWojsko = new JButton("Wyślij Wojsko");
        wyslijWojsko.setPreferredSize(new Dimension(200,50));
        add(wyslijWojsko);
        wat = new Thread(new Watek());
        wat.start();
    }
    class Watek implements Runnable{
        @Override
        public void run() {
            String linia = "";
            try{
                while((linia = odbierz.readLine())!=null){
                    String [] tmp = linia.split("@");
                    if(tmp.length > 55 ){
                        MojaOsada.this.linia = linia;
                        String [] pom = linia.split("@");
                        drewnoIlosc.setText(pom[1]);
                        glinaIlosc.setText(pom[2]);
                        zelazoIlosc.setText(pom[3]);
                        zbozeIlosc.setText(pom[4]);                        
                        validate();
                        repaint();
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }        
    }
    class Powrot implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, MojaOsada.this);
            parent.getContentPane().removeAll();            
            parent.add(new Mapa(wyslij, odbierz,linia));
            parent.validate();
            parent.repaint();
        }        
    }
    class Wyloguj implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            serwus.close();  
        }        
    }
}
