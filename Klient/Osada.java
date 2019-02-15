/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alliancegracz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.Box;
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
//Button dla murow obronnych!!!!
//Tutaj dodaj jeszcze thread to odczytu ilosci surowcow
public class Osada extends JPanel{
    private JButton przycisk1 = new JButton();
    private JButton przycisk2 = new JButton();
    private JButton przycisk3 = new JButton();
    private JButton przycisk4 = new JButton();
    private JButton przycisk5 = new JButton();
    private JButton przycisk6 = new JButton();
    private JButton przycisk7 = new JButton();
    private JButton przycisk8 = new JButton();
    private JButton przycisk9 = new JButton();
    private JButton przycisk10 = new JButton();
    private JButton przycisk11 = new JButton();
    private JButton przycisk12 = new JButton();
    private JButton przycisk13 = new JButton();
    private JButton przycisk14 = new JButton();
    private JButton przycisk15 = new JButton(); 
    private JButton muryObronne;
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
    private String [] tmp;
    private JLabel tlo;
    private WiadomoscServer serwus = new WiadomoscServer();
    private String linia;
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    private JFrame parent;
    private Thread wat;
    public Osada(PrintWriter wyslij, BufferedReader odbierz, String linia){
        this.wyslij = serwus.getWyslij();
        this.odbierz = serwus.getOdbierz();
        this.linia = linia;
        setLayout(null);
        tmp = linia.split("@");
        przycisk1.setName("PRZYCISK1");
        przycisk2.setName("PRZYCISK2");
        przycisk3.setName("PRZYCISK3");
        przycisk4.setName("PRZYCISK4");
        przycisk5.setName("PRZYCISK5");
        przycisk6.setName("PRZYCISK6");
        przycisk7.setName("PRZYCISK7");
        przycisk8.setName("PRZYCISK8");
        przycisk9.setName("PRZYCISK9");
        przycisk10.setName("PRZYCISK10");
        przycisk11.setName("PRZYCISK11");
        przycisk12.setName("PRZYCISK12");
        przycisk13.setName("PRZYCISK13");
        przycisk14.setName("PRZYCISK14");
        przycisk15.setName("PRZYCISK15");
        wat = new Thread(new Watek());
        wat.start();
        tlo = new JLabel(new ImageIcon(this.getClass().getResource("tloMAX.png")));
        tlo.setLayout(null);
        tlo.setBounds(0,0,1600,900);
        //Dodaj pasek z surowcami                
        powrot = new JButton("<<-");       
        powrot.addActionListener(new Powrot());
        powrot.setBounds(10,5,50,50);        
        tlo.add(powrot);
        
        drewnoMini = new JLabel(new ImageIcon(this.getClass().getResource("drewnoMini.png")));
        drewnoMini.setBounds(600,5,30,30);
        tlo.add(drewnoMini);        
        drewnoIlosc = new JLabel(tmp[1]);
        drewnoIlosc.setBounds(650,5,30,50);
        tlo.add(drewnoIlosc);        
        
        glinaMini = new JLabel(new ImageIcon(this.getClass().getResource("glinaMini.png")));
        glinaMini.setBounds(700,5,30,30);
        tlo.add(glinaMini);        
        glinaIlosc = new JLabel(tmp[2]);
        glinaIlosc.setBounds(750,5,30,50);
        tlo.add(glinaIlosc);        
        
        zelazoMini = new JLabel(new ImageIcon(this.getClass().getResource("zelazoMini.png")));
        zelazoMini.setBounds(800,5,30,30);
        tlo.add(zelazoMini);        
        zelazoIlosc = new JLabel(tmp[3]);
        zelazoIlosc.setBounds(850,5,30,50);
        tlo.add(zelazoIlosc);        
        
        zbozeMini = new JLabel(new ImageIcon(this.getClass().getResource("zbozeMini.png")));
        zbozeMini.setBounds(900,5,30,30);
        tlo.add(zbozeMini);        
        zbozeIlosc = new JLabel(tmp[4]);
        zbozeIlosc.setBounds(950,5,30,50);
        tlo.add(zbozeIlosc);       
        wyloguj = new JButton("Wyloguj się");
        wyloguj.setBounds(1400,5,150,30);
        wyloguj.addActionListener(new Wyloguj());
        tlo.add(wyloguj);        
        //Dodaj buttony
        createButton(przycisk1,885,335,17);
        createButton(przycisk2,1165,400,18);
        createButton(przycisk3,1165,600,19);
        createButton(przycisk4,900,750,20);
        createButton(przycisk5,500,335,21);
        createButton(przycisk6,190,335,22);
        createButton(przycisk7,300,490,23);
        createButton(przycisk8,40,440,24);
        createButton(przycisk9,150,620,25);
        createButton(przycisk10,7,750,26);
        createButton(przycisk11,850,600,27);
        createButton(przycisk12,500,750,28);
        createButton(przycisk13,950,470,29);
        createButton(przycisk14,700,470,30);
        createButton(przycisk15,500,600,31); 
        muryObronne = new JButton("Mury Obronne");
        muryObronne.setBounds(7,300,100,30);
        muryObronne.addActionListener(new MuryObronneTmp(linia, muryObronne));
        tlo.add(muryObronne);
        add(tlo);
        setVisible(true);
    }
    private void createButton(JButton button, int x, int y, int poz){
        checkIcon(button, poz);
        button.setBounds(x, y, 200, 100);
        //button.setOpaque(true);
        //button.setContentAreaFilled(false);
        //button.addActionListener(new Brak(linia, button));
        tlo.add(button);
    }
    private void checkIcon(JButton button, int poz){        
        if(tmp[poz].equals("brak")){
            //button = new JButton();  
            button.setContentAreaFilled(true);
            //button.setBackground(new Color(0,204,0));
            //button.setOpaque(true);
            button.setText("Wybuduj budynek");
            button.addActionListener(new Brak(linia, button));
        }
        if(tmp[poz].equals("Ratusz")){            
            button.setIcon(new ImageIcon(this.getClass().getResource("ratusz.png")));
            button.setContentAreaFilled(false);
        }
        if(tmp[poz].equals("Magazyn")){            
            button.setIcon(new ImageIcon(this.getClass().getResource("magazyn.png")));
            button.addActionListener(new MagazynTmp(linia, button));
            button.setContentAreaFilled(false);
        }
        if(tmp[poz].equals("Koszary")){
            button.setIcon(new ImageIcon(this.getClass().getResource("koszary.png"))); 
            button.addActionListener(new KoszaryTmp(linia, button));
            button.setContentAreaFilled(false);            
        }
        if(tmp[poz].equals("Kryjowka")){
            button.setIcon(new ImageIcon(this.getClass().getResource("kryjowka.png")));            
            button.setContentAreaFilled(false);
            button.addActionListener(new KryjowkaTmp(linia,button));
        }
        if(tmp[poz].equals("Rynek")){
            button.setIcon(new ImageIcon(this.getClass().getResource("rynek.png")));            
            button.setContentAreaFilled(false);
        }
        if(tmp[poz].equals("Stajnia")){
            button.setIcon(new ImageIcon(this.getClass().getResource("stajnia.png")));            
            button.setContentAreaFilled(false);
            button.addActionListener(new StajniaTmp(linia, button));
        }              
        //Palac
    }
    class Powrot implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Osada.this);
            parent.getContentPane().removeAll();            
            parent.add(new PolaSurowcow(wyslij, odbierz,linia));
            parent.validate();
            parent.repaint();
        }        
    }
    class Brak implements ActionListener{
        private String linia;
        private JButton button;
        public Brak(String linia, JButton button){
            this.linia = linia;
            this.button = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Osada.this);
            parent.getContentPane().removeAll();            
            parent.add(new BrakBudynku(wyslij, odbierz, linia, button.getName()));
            parent.validate();
            parent.repaint();
        }        
    }
    class KryjowkaTmp implements ActionListener{
        private String linia;
        private JButton button;
        public KryjowkaTmp(String linia, JButton button){
            this.linia = linia;
            this.button = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Osada.this);
            parent.getContentPane().removeAll();            
            parent.add(new Kryjowka(wyslij, odbierz, linia, Integer.parseInt(tmp[9]), button.getName()));
            parent.validate();
            parent.repaint();
        }        
    }
    class MagazynTmp implements ActionListener{
        private String linia;
        private JButton button;
        public MagazynTmp(String linia, JButton button){
            this.linia = linia;
            this.button = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Osada.this);
            parent.getContentPane().removeAll();            
            parent.add(new Magazyn(wyslij, odbierz, linia, Integer.parseInt(tmp[6]), button.getName()));
            parent.validate();
            parent.repaint();
        }        
    }
    class MuryObronneTmp implements ActionListener{
        private String linia;
        private JButton button;
        public MuryObronneTmp(String linia, JButton button){
            this.linia = linia;
            this.button = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Osada.this);
            parent.getContentPane().removeAll();            
            parent.add(new MuryObronne(wyslij, odbierz, linia, Integer.parseInt(tmp[10]), button.getName()));
            parent.validate();
            parent.repaint();
        }        
    }
    class KoszaryTmp implements ActionListener{
        private String linia;
        private JButton button;

        public KoszaryTmp(String linia, JButton button) {
            this.linia = linia;
            this.button = button;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Osada.this);
            parent.getContentPane().removeAll();            
            parent.add(new Koszary(wyslij, odbierz, linia, Integer.parseInt(tmp[7]), button.getName()));
            parent.validate();
            parent.repaint();
        }        
    }
    class StajniaTmp implements ActionListener{
        private String linia;
        private JButton button;
        
        public StajniaTmp(String linia, JButton button){
            this.linia = linia;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Osada.this);
            parent.getContentPane().removeAll();            
            parent.add(new Stajnia(wyslij, odbierz, linia, Integer.parseInt(tmp[8]), button.getName()));
            parent.validate();
            parent.repaint();
        }
        
    }
    class Watek implements Runnable{
        @Override
        public void run() {
            String linia = "";
            try{
                while((linia=odbierz.readLine())!=null){
                    String [] tmp = linia.split("@");
                    if(tmp.length > 55){
                        Osada.this.linia = linia;
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
    class Wyloguj implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            serwus.close();                    
        }        
    }
}
