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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author lenovo
 */
//Tu mamy liste z budynkami
//Pierwsza rzecza jaka robimy to sciagamy z bazy danych ile budynkow mozemy wybudowac
//I jakie to budynki, a potem dopiero tworzymy nasza liste
//Wszystkie przyciski sa aktywne, dopiero jak nacisniemy, program moze nam
//ewentualnie powiedziec, ze nie spelniamy warunow
public class BrakBudynku extends JPanel{
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    private String linia;
    private String nazwa; // nasza pozycja
    private WiadomoscServer serwus = new WiadomoscServer();
    private JLabel [] tytul = new JLabel[7];
    private JPanel [] panelGlowny = new JPanel [7];
    private JLabel [] opis = new JLabel [7];
    private JLabel [] ilustracja = new JLabel [7];
    private JButton [] wybuduj = new JButton [7];
    private JPanel panel1;
    private JButton powrot;
    private JButton wyloguj; 
    private JLabel drewnoMini;
    private JLabel glinaMini;
    private JLabel zelazoMini;
    private JLabel zbozeMini;
    private JLabel drewnoIlosc;
    private JLabel glinaIlosc;
    private JLabel zelazoIlosc;
    private JLabel zbozeIlosc;
    private JLabel [] buttonDrewnoIkona = new JLabel [7];
    private JLabel [] buttonDrewno = new JLabel [7];
    private JLabel [] buttonGlinaIkona = new JLabel [7];
    private JLabel [] buttonGlina = new JLabel [7];
    private JLabel [] buttonZelazoIkona = new JLabel [7];
    private JLabel [] buttonZelazo = new JLabel [7];
    private JLabel [] buttonZbozeIkona = new JLabel [7];
    private JLabel [] buttonZboze = new JLabel [7]; 
    private String [] tmp;
    private JPanel tmpPanel;
    private Thread wat;
    private JFrame parent;
    public BrakBudynku(PrintWriter wyslij, BufferedReader odbierz, String linia, String nazwa){
        //JOptionPane.showMessageDialog(null,"Poczekaj! Ładowane danych. To może chwile potrwać");
        this.wyslij = serwus.getWyslij();
        this.odbierz = serwus.getOdbierz();
        this.linia = linia;
        this.nazwa = nazwa;
        this.wyslij.println("ListaBudynkow");
        this.wyslij.flush();
        wat = new Thread(new Watek());
        wat.start();
        tmp = linia.split("@");
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setPreferredSize(new Dimension(1600,160));
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
        //add(panel1);
        //add(Box.createRigidArea(new Dimension(0,5)));
        tmpPanel = new JPanel();
        tmpPanel.setPreferredSize(new Dimension(1600,1900));
        tmpPanel.setLayout(new BoxLayout(tmpPanel,BoxLayout.PAGE_AXIS));        
        JScrollPane scroll = new JScrollPane(tmpPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(1600,1500));
        add(scroll);       
        setVisible(true);
    }
    class Watek implements Runnable{
        String linia = "";
        @Override
        public void run() {
            tmpPanel.add(panel1);
            try{                
                while((linia=odbierz.readLine())!=null){                    
                    String [] tak = linia.split("\\$");                    
                    for(int i=0;i<tak.length;i++){                       
                        String [] tmp = tak[i].split("@");                       
                        if(tmp.length <10){   
                            //System.out.println(tmp[i]);
                            createList(tytul[i],tmp[0],panelGlowny[i],opis[i],tmp[6],ilustracja[i],wybuduj[i], buttonDrewnoIkona[i], buttonDrewno[i], tmp[1],buttonGlinaIkona[i], buttonGlina[i], tmp[2],buttonZelazoIkona[i], buttonZelazo[i], tmp[3],buttonZbozeIkona[i], buttonZboze[i], tmp[4], tmp[5]);
                        }
                        if(tmp.length > 55){
                            BrakBudynku.this.linia = linia;
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
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }        
    }
    class Powrot implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, BrakBudynku.this);
            parent.getContentPane().removeAll();            
            parent.add(new Osada(wyslij, odbierz,linia));
            parent.validate();
            parent.repaint();
        }        
    }
    private void createList(JLabel tytul, String tytulTmp, JPanel panel, JLabel opis, String opisTmp, JLabel ilustracja, JButton button, JLabel drewnoIlustracja, JLabel drewno, String drewnoIlosc, JLabel glinaIlustracja, JLabel glina, String glinaIlosc, JLabel zelazoIlustracja, JLabel zelazo, String zelazoIlosc, JLabel zbozeIlustracja, JLabel zboze, String zbozeIlosc, String wymagania){
        //tmpPanel.add(panel1);
        tytul = new JLabel(tytulTmp);       
        tytul.setPreferredSize(new Dimension(300,50));
        tytul.setFont(new Font("Lucida Handwriting kursywa",Font.BOLD,20));
        tmpPanel.add(tytul);
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,100));
        opis = new JLabel("<html>"+opisTmp+".<br>Wymagane sa nastepujace budynki "+wymagania +" </html>");
        //opis.setText("<html>"+opisTmp+".<br>Wymagane sa nastepujace budynki "+wymagania +" </html>");
        opis.setPreferredSize(new Dimension(300,100));
        panel.add(opis);
        ilustracja = new JLabel(new ImageIcon(this.getClass().getResource(tmpCreate(tytulTmp))));
        //ilustracja.setIcon(new ImageIcon(this.getClass().getResource(tmpCreate(tytulTmp))));
        ilustracja.setPreferredSize(new Dimension(200,100));
        panel.add(ilustracja);
        tmpPanel.add(panel);
        button = new JButton("");        
        button.setPreferredSize(new Dimension(300,200));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setLayout(new FlowLayout());
        button.addActionListener(new Buduj(tytul.getText(), wymagania, drewnoIlosc, glinaIlosc, zelazoIlosc, zbozeIlosc, nazwa));
        drewnoIlustracja = new JLabel(new ImageIcon(this.getClass().getResource("drewnoMini.png")));
        //drewnoIlustracja.setIcon(new ImageIcon(this.getClass().getResource("drewnoMini.png")));
        drewnoIlustracja.setPreferredSize(new Dimension(30,30));
        button.add(drewnoIlustracja);
        drewno = new JLabel(drewnoIlosc);
        drewno.setText(drewnoIlosc);
        drewno.setPreferredSize(new Dimension(50,30));
        button.add(drewno);
        
        glinaIlustracja = new JLabel(new ImageIcon(this.getClass().getResource("glinaMini.png")));
        //glinaIlustracja.setIcon(new ImageIcon(this.getClass().getResource("glinaMini.png")));
        glinaIlustracja.setPreferredSize(new Dimension(30,30));
        button.add(glinaIlustracja);
        glina = new JLabel(glinaIlosc);
        glina.setPreferredSize(new Dimension(50,30));
        button.add(glina);
        
        zelazoIlustracja = new JLabel(new ImageIcon(this.getClass().getResource("zelazoMini.png")));
        //zelazoIlustracja.setIcon(new ImageIcon(this.getClass().getResource("zelazoMini.png")));
        zelazoIlustracja.setPreferredSize(new Dimension(30,30));
        button.add(zelazoIlustracja);
        zelazo = new JLabel(zelazoIlosc);
        zelazo.setPreferredSize(new Dimension(50,30));
        button.add(zelazo);
        
        zbozeIlustracja = new JLabel(new ImageIcon(this.getClass().getResource("zbozeMini.png")));
        //zbozeIlustracja.setIcon(new ImageIcon(this.getClass().getResource("zbozeMini.png")));
        zbozeIlustracja.setPreferredSize(new Dimension(30,30));
        button.add(zbozeIlustracja);
        zboze = new JLabel(zbozeIlosc);
        zboze.setPreferredSize(new Dimension(50,30));
        button.add(zboze);
        tmpPanel.add(button);
        tmpPanel.validate();
        tmpPanel.repaint();
        
    }
    private String tmpCreate(String name){
        String tmp = "";
        if(name.equals("Ratusz")) return "ratusz.png";
        if(name.equals("Magazyn")) return "magazyn.png";
        if(name.equals("Koszary")) return "koszary.png";
        if(name.equals("Kryjowka")) return "kryjowka.png";
        if(name.equals("Rynek")) return "rynek.png";
        if(name.equals("Stajnia")) return "stajnia.png";
        if(name.equals("Palac")) return "palac.png";
        return tmp;
    }
    class Buduj implements ActionListener{
        String budynek;
        String wymagania;
        String drewnoBuduj;
        String glinaBuduj;
        String zelazoBuduj;
        String zbozeBuduj;
        String pozycja;
        boolean czyMoznaBudowac = true;

        public Buduj(String budynek, String wymagania, String drewnoBuduj, String glinaBuduj, String zelazoBuduj, String zbozeBuduj, String pozycja) {
            this.budynek = budynek;
            this.wymagania = wymagania;
            this.drewnoBuduj = drewnoBuduj;
            this.glinaBuduj = glinaBuduj;
            this.zelazoBuduj = zelazoBuduj;
            this.zbozeBuduj = zbozeBuduj;
            this.pozycja = pozycja;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String tak = wymagania;       
            wymagania = wymagania.replaceAll("[^0-9]+", "");
            tak = tak.replaceAll("\\d", ""); 
            if(tak.equals("Ratusz")){
                int tym = Integer.parseInt(tmp[5]) - Integer.parseInt(wymagania);
                if(tym < 0){
                    JOptionPane.showMessageDialog(null,"Nie spelniasz wymagan. Rozbuduj ratusz");
                    czyMoznaBudowac = false;
                }
            } 
            if(tak.equals("Magazyn")){
                int tym = Integer.parseInt(tmp[6]) - Integer.parseInt(wymagania);
                if(tym < 0){
                    JOptionPane.showMessageDialog(null,"Nie spelniasz wymagan. Rozbuduj magazyn");
                    czyMoznaBudowac = false;
                }
            }
            if(tak.equals("Koszary")){
                int tym = Integer.parseInt(tmp[7]) - Integer.parseInt(wymagania);
                if(tym < 0){
                    JOptionPane.showMessageDialog(null,"Nie spelniasz wymagan. Rozbuduj koszary");
                    czyMoznaBudowac = false;
                }
            }
            if(tak.equals("Kryjowka")){
                int tym = Integer.parseInt(tmp[9]) - Integer.parseInt(wymagania);
                if(tym < 0){
                    JOptionPane.showMessageDialog(null,"Nie spelniasz wymagan. Rozbuduj kryjowke");
                    czyMoznaBudowac = false;
                }
            }
            if(tak.equals("Rynek")){
                int tym = Integer.parseInt(tmp[11]) - Integer.parseInt(wymagania);
                if(tym < 0){
                    JOptionPane.showMessageDialog(null,"Nie spelniasz wymagan. Rozbuduj rynek");
                    czyMoznaBudowac = false;
                }
            }
            if(tak.equals("Stajnia")){
                int tym = Integer.parseInt(tmp[8]) - Integer.parseInt(wymagania);
                if(tym < 0){
                    JOptionPane.showMessageDialog(null,"Nie spelniasz wymagan. Rozbuduj stajnie");
                    czyMoznaBudowac = false;
                }
            }
            if(tak.equals("MuryObronne")){
                int tym = Integer.parseInt(tmp[10]) - Integer.parseInt(wymagania);
                if(tym < 0){
                    JOptionPane.showMessageDialog(null,"Nie spelniasz wymagan. Rozbuduj mury obronne");
                    czyMoznaBudowac = false;
                }
            }
            if(tak.equals("Palac")){
                int tym = Integer.parseInt(tmp[16]) - Integer.parseInt(wymagania);
                if(tym < 0){
                    JOptionPane.showMessageDialog(null,"Nie spelniasz wymagan. Rozbuduj palac");
                    czyMoznaBudowac = false;
                }
            }
            //Sprawdzam czy mam tyle surowcow ile trzeba
            Integer drewno1 = Integer.parseInt(drewnoIlosc.getText()) - Integer.parseInt(drewnoBuduj);
            Integer glina1 = Integer.parseInt(glinaIlosc.getText()) - Integer.parseInt(glinaBuduj);
            Integer zelazo1 = Integer.parseInt(zelazoIlosc.getText()) - Integer.parseInt(zelazoBuduj);
            Integer zboze1 = Integer.parseInt(zbozeIlosc.getText()) - Integer.parseInt(zbozeBuduj);
            if(drewno1<0||glina1<0||zelazo1<0||zboze1<0){
                JOptionPane.showMessageDialog(null,"Nie masz wystarczającej ilości surowców");
                czyMoznaBudowac = false;
            }
            if(czyMoznaBudowac){
                drewnoIlosc.setText(drewno1.toString());
                glinaIlosc.setText(glina1.toString());
                zelazoIlosc.setText(zelazo1.toString());
                zbozeIlosc.setText(zboze1.toString());
                //Informujemy baze danych o tym
                String tmp1 = "UploadBudynek@"+drewnoIlosc.getText()+"@"+glinaIlosc.getText()+"@"+zelazoIlosc.getText()+"@"+
                        zbozeIlosc.getText()+"@"+budynek+"@1"+"@"+pozycja; // tu jest zawsze 1 bo dopiero budujemy budynek
                wyslij.println(tmp1);
                wyslij.flush();
                try{
                    Thread.sleep(200);
                }
                catch(InterruptedException e1){
                    e1.printStackTrace();
                }
                parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, BrakBudynku.this);
                parent.getContentPane().removeAll();            
                parent.add(new Osada(wyslij, odbierz,linia));
                parent.validate();
                parent.repaint();
            }
        }        
    }
    class Wyloguj implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            serwus.close();                    
        }        
    }
}
