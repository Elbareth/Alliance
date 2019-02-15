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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author lenovo
 */
//Sprawdz czy wojsko dobrze sie szkoli
public class Stajnia extends JPanel{
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    private String linia;
    private Integer poziom;
    private String pozycja;
    private WiadomoscServer serwus = new WiadomoscServer();
    private Thread wat;
    private String [] tmp;
    private JFrame parent;
    private JPanel panel1;
    private JLabel drewnoIlosc;
    private JLabel drewnoMini;
    private JLabel glinaIlosc;
    private JLabel glinaMini;
    private JLabel zelazoIlosc;
    private JLabel zelazoMini;
    private JLabel zbozeIlosc;
    private JLabel zbozeMini;
    private JButton wyloguj;
    private JButton powrot;
    private JPanel panel2;
    private JButton przegladWojsk;
    private JPanel tmpPanel;
    private JLabel tytul;
    private JPanel panelGlowny;
    private JLabel ilustracja;
    private JLabel opis;
    private JButton buduj;
    private JLabel drewnoBuduj;
    private JLabel drewnoBudujIkona;
    private JLabel glinaBuduj;
    private JLabel glinaBudujIkona;
    private JLabel zelazoBuduj;
    private JLabel zelazoBudujIkona;
    private JLabel zbozeBuduj;
    private JLabel zbozeBudujIkona;
    private JLabel [] tytulLabel;
    private JPanel [] panelGlow;
    private JLabel [] opisLabel;
    private JTextField [] ilosc;
    private JButton [] buttonIlosc;
    private JLabel [] drewnoIloscLabel;
    private JLabel [] drewnoIkonaLabel;
    private JLabel [] glinaIloscLabel;
    private JLabel [] glinaIkonaLabel;
    private JLabel [] zelazoIloscLabel;
    private JLabel [] zelazoIkonaLabel;
    private JLabel [] zbozeIloscLabel;
    private JLabel [] zbozeIkonaLabel;
    private JButton [] wybuduj;
    private boolean czyMoznaBudowac = true;
    private boolean czyMoznaSzkolic = true;
    public Stajnia(PrintWriter wyslij, BufferedReader odbierz, String linia, Integer poziom, String pozycja){
        this.wyslij = serwus.getWyslij();
        this.odbierz = serwus.getOdbierz();
        this.linia = linia;
        this.poziom = poziom;
        this.pozycja = pozycja;
        tmp = linia.split("@");
        this.wyslij.println("Stajnia@"+(poziom+1));
        this.wyslij.flush();
        /*wat = new Thread(new Watek());
        wat.start();*/
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
        panel2 = new JPanel();        
        przegladWojsk = new JButton("Przegląd Wojsk");
        przegladWojsk.setMaximumSize(new Dimension(300,200));
        przegladWojsk.addActionListener(new PrzegladWojskTmp());
        tmpPanel = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.PAGE_AXIS));
        panel2.setPreferredSize(new Dimension(1600,160));
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        tytul = new JLabel("Stajnia poziom "+poziom);
        tytul.setPreferredSize(new Dimension(300,50));
        tytul.setAlignmentX(Component.CENTER_ALIGNMENT);
        tytul.setFont(new Font("Lucida Handwriting kursywa",Font.BOLD,20));
        panel2.add(tytul);
        panelGlowny = new JPanel();
        panelGlowny.setPreferredSize(new Dimension(500,100));
        opis = new JLabel();//opis dodawany dopiero w watku
        opis.setPreferredSize(new Dimension(400,200));
        panelGlowny.add(opis);
        ilustracja = new JLabel(new ImageIcon(this.getClass().getResource("stajnia.png")));
        ilustracja.setPreferredSize(new Dimension(200,100));
        panelGlowny.add(ilustracja);
        panel2.add(panelGlowny);
        buduj = new JButton("");        
        buduj.setMaximumSize(new Dimension(500,200));
        buduj.setAlignmentX(Component.CENTER_ALIGNMENT);
        buduj.setLayout(new FlowLayout());        
        drewnoBudujIkona = new JLabel(new ImageIcon(this.getClass().getResource("drewnoMini.png")));        
        drewnoBudujIkona.setPreferredSize(new Dimension(30,30));
        buduj.add(drewnoBudujIkona);
        drewnoBuduj = new JLabel();       
        drewnoBuduj.setPreferredSize(new Dimension(50,30));
        buduj.add(drewnoBuduj);
        
        glinaBudujIkona = new JLabel(new ImageIcon(this.getClass().getResource("glinaMini.png")));        
        glinaBudujIkona.setPreferredSize(new Dimension(30,30));
        buduj.add(glinaBudujIkona);
        glinaBuduj = new JLabel();
        glinaBuduj.setPreferredSize(new Dimension(50,30));
        buduj.add(glinaBuduj);
        
        zelazoBudujIkona = new JLabel(new ImageIcon(this.getClass().getResource("zelazoMini.png")));        
        zelazoBudujIkona.setPreferredSize(new Dimension(30,30));
        buduj.add(zelazoBudujIkona);
        zelazoBuduj = new JLabel();
        zelazoBuduj.setPreferredSize(new Dimension(50,30));
        buduj.add(zelazoBuduj);
        
        zbozeBudujIkona = new JLabel(new ImageIcon(this.getClass().getResource("zbozeMini.png")));        
        zbozeBudujIkona.setPreferredSize(new Dimension(30,30));
        buduj.add(zbozeBudujIkona);
        zbozeBuduj = new JLabel();
        zbozeBuduj.setPreferredSize(new Dimension(50,30));
        buduj.add(zbozeBuduj);
        panel2.add(buduj);
        tmpPanel.setPreferredSize(new Dimension(1600,1900));
        tmpPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tmpPanel.setLayout(new BoxLayout(tmpPanel,BoxLayout.PAGE_AXIS));        
        JScrollPane scroll = new JScrollPane(tmpPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(1200,900));
        tmpPanel.add(panel1);
        tmpPanel.add(przegladWojsk);
        tmpPanel.add(panel2);
        add(scroll);       
        setVisible(true);
        wat = new Thread(new Watek());
        wat.start();
    }
    class Watek implements Runnable{
        @Override
        public void run() {
            String linia = "";
            try{
                while((linia = odbierz.readLine())!= null){                    
                    String [] tak = linia.split("\\$");
                    tytulLabel = new JLabel[tak.length];
                    panelGlow = new JPanel[tak.length];
                    opisLabel = new JLabel[tak.length];
                    ilosc = new JTextField[tak.length];
                    buttonIlosc = new JButton[tak.length];
                    drewnoIloscLabel = new JLabel[tak.length];
                    drewnoIkonaLabel = new JLabel[tak.length];
                    glinaIloscLabel = new JLabel[tak.length];
                    glinaIkonaLabel = new JLabel[tak.length];
                    zelazoIloscLabel = new JLabel[tak.length];
                    zelazoIkonaLabel = new JLabel[tak.length];
                    zbozeIloscLabel = new JLabel[tak.length];
                    zbozeIkonaLabel = new JLabel[tak.length];
                    wybuduj = new JButton[tak.length];
                    for(int i=0;i<tak.length;i++){                        
                        String [] tmp = tak[i].split("@");
                        if(tmp[0].equals("Opis")){
                            drewnoBuduj.setText(tmp[1]);
                            glinaBuduj.setText(tmp[2]);
                            zelazoBuduj.setText(tmp[3]);
                            zbozeBuduj.setText(tmp[4]);
                            opis.setText("<html>"+tmp[6]+".<br> By rozbudować ten budynke potrzebujesz "+tmp[5]+". </html>");
                            buduj.addActionListener(new Buduj(tmp[5]));
                            wyslij.println("WojskoStajnia");
                            wyslij.flush();
                            Stajnia.this.validate();
                            Stajnia.this.repaint(); 
                        }
                        if(tmp.length == 9){
                            createList(tmp[0], tytulLabel[i],panelGlow[i], tmp[8],tmp[7], opisLabel[i], ilosc[i], buttonIlosc[i], drewnoIloscLabel[i], drewnoIkonaLabel[i], tmp[3], glinaIloscLabel[i],glinaIkonaLabel[i], tmp[4], zelazoIloscLabel[i], zelazoIkonaLabel[i], tmp[5], zbozeIloscLabel[i], zbozeIkonaLabel[i], tmp[6], wybuduj[i]);
                        }
                        if(tmp.length > 55 ){
                            Stajnia.this.linia = linia;
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
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Stajnia.this);
            parent.getContentPane().removeAll();            
            parent.add(new Osada(wyslij, odbierz,linia));
            parent.validate();
            parent.repaint();
        }        
    }
    class PrzegladWojskTmp implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Stajnia.this);
            parent.getContentPane().removeAll();            
            parent.add(new PrzegladWojsk(wyslij, odbierz,linia,"Stajnia",poziom,pozycja));
            parent.validate();
            parent.repaint();
        }        
    }
    class Buduj implements ActionListener{
        String wymagania;
        public Buduj(String wymagania){
            this.wymagania = wymagania;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer drewno = Integer.parseInt(drewnoIlosc.getText()) - Integer.parseInt(drewnoBuduj.getText());
            Integer glina = Integer.parseInt(glinaIlosc.getText()) - Integer.parseInt(glinaBuduj.getText());
            Integer zelazo = Integer.parseInt(zelazoIlosc.getText()) - Integer.parseInt(zelazoBuduj.getText());
            Integer zboze = Integer.parseInt(zbozeIlosc.getText()) - Integer.parseInt(zbozeBuduj.getText());
            if(drewno < 0 || glina < 0 || zelazo < 0 || zboze< 0){
                JOptionPane.showMessageDialog(null,"Masz za mało surowców by wybudować stajnię");
                czyMoznaBudowac = false;
            }
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
            if(czyMoznaBudowac){
                drewnoIlosc.setText(drewno.toString());
                glinaIlosc.setText(glina.toString());
                zelazoIlosc.setText(zelazo.toString());
                zbozeIlosc.setText(zboze.toString());
                String sql = "UploadBudynek@"+drewnoIlosc.getText()+"@"+glinaIlosc.getText()+"@"+zelazoIlosc.getText()+"@"+
                        zbozeIlosc.getText()+"@Stajnia@"+(poziom+1)+"@"+pozycja;
                wyslij.println(sql);
                wyslij.flush();
                try{
                    Thread.sleep(200);
                }
                catch(InterruptedException e1){
                    e1.printStackTrace();
                }
                parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Stajnia.this);
                parent.getContentPane().removeAll();            
                parent.add(new Osada(wyslij, odbierz,linia));
                parent.validate();
                parent.repaint();
            }
        }        
    }
    public void createList(String tytul, JLabel tytulLabel,JPanel panel, String opis,String wymagania, JLabel opisLabel, JTextField ilosc, JButton button, JLabel drewno, JLabel drewnoIkona, String drewnoIlosc, JLabel glina, JLabel glinaIkona, String glinaIlosc, JLabel zelazo, JLabel zelazoIkona, String zelazoIlosc, JLabel zboze, JLabel zbozeIkona, String zbozeIlosc, JButton buduj){
        tytulLabel = new JLabel(tytul);       
        tytulLabel.setPreferredSize(new Dimension(300,50));
        tytulLabel.setFont(new Font("Lucida Handwriting kursywa",Font.BOLD,20));
        tmpPanel.add(tytulLabel);
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,100));
        panel.setLayout(new FlowLayout());
        opisLabel = new JLabel("<html>"+opis+".<br>By wybodować tą jednostkę potrzebujesz: "+wymagania +" </html>");       
        opisLabel.setPreferredSize(new Dimension(300,100));
        panel.add(opisLabel);  
        ilosc = new JTextField("0");
        Szkolenie szkolenie = new Szkolenie(drewnoIlosc,glinaIlosc,zelazoIlosc, zbozeIlosc, ilosc.getText(),tytul, wymagania);
        ilosc.setPreferredSize(new Dimension(50,50));        
        ilosc.getDocument().addDocumentListener(new IloscChange(szkolenie, ilosc));
        panel.add(ilosc);
        Integer a = Integer.parseInt(this.drewnoIlosc.getText()) / Integer.parseInt(drewnoIlosc);
        Integer b = Integer.parseInt(this.glinaIlosc.getText()) / Integer.parseInt(glinaIlosc);
        Integer c = Integer.parseInt(this.zelazoIlosc.getText()) / Integer.parseInt(zelazoIlosc);
        Integer d = Integer.parseInt(this.zbozeIlosc.getText()) / Integer.parseInt(zbozeIlosc);
        Integer e = a<b?a:b;
        Integer f = c<d?c:d;
        Integer min = e<f?e:f;
        button = new JButton(min.toString());
        button = new JButton(min.toString());
        button.setMaximumSize(new Dimension(500,200));
        button.addActionListener(new SetIlosc(ilosc, button));
        panel.add(button);
        tmpPanel.add(panel);
        buduj = new JButton("");        
        buduj.setMaximumSize(new Dimension(500,200));
        buduj.setAlignmentX(Component.CENTER_ALIGNMENT);
        buduj.setLayout(new FlowLayout());
        buduj.addActionListener(szkolenie);
        drewnoIkona = new JLabel(new ImageIcon(this.getClass().getResource("drewnoMini.png")));        
        drewnoIkona.setPreferredSize(new Dimension(30,30));
        buduj.add(drewnoIkona);
        drewno = new JLabel(drewnoIlosc);
        drewno.setText(drewnoIlosc);
        drewno.setPreferredSize(new Dimension(50,30));
        buduj.add(drewno);
        
        glinaIkona = new JLabel(new ImageIcon(this.getClass().getResource("glinaMini.png")));       
        glinaIkona.setPreferredSize(new Dimension(30,30));
        buduj.add(glinaIkona);
        glina = new JLabel(glinaIlosc);
        glina.setPreferredSize(new Dimension(50,30));
        buduj.add(glina);
        
        zelazoIkona = new JLabel(new ImageIcon(this.getClass().getResource("zelazoMini.png")));        
        zelazoIkona.setPreferredSize(new Dimension(30,30));
        buduj.add(zelazoIkona);
        zelazo = new JLabel(zelazoIlosc);
        zelazo.setPreferredSize(new Dimension(50,30));
        buduj.add(zelazo);
        
        zbozeIkona = new JLabel(new ImageIcon(this.getClass().getResource("zbozeMini.png")));       
        zbozeIkona.setPreferredSize(new Dimension(30,30));
        buduj.add(zbozeIkona);
        zboze = new JLabel(zbozeIlosc);
        zboze.setPreferredSize(new Dimension(50,30));
        buduj.add(zboze);
        tmpPanel.add(buduj);
        tmpPanel.validate();
        tmpPanel.repaint();
    }
    class Szkolenie implements ActionListener{
        String drewno;
        String glina;
        String zelazo;
        String zboze;
        String ilosc;
        String typ;
        String wymagania;

        public Szkolenie(String drewno, String glina, String zelazo, String zboze, String ilosc, String typ, String wymagania) {
            this.drewno = drewno;
            this.glina = glina;
            this.zelazo = zelazo;
            this.zboze = zboze;
            this.ilosc = ilosc;
            this.typ = typ;
            this.wymagania = wymagania;           
        }
        public void setIlosc(String ilosc){
            this.ilosc = ilosc;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer drewnoMax = Integer.parseInt(drewnoIlosc.getText()) - (Integer.parseInt(drewno)*Integer.parseInt(ilosc));
            Integer glinaMax = Integer.parseInt(glinaIlosc.getText()) - (Integer.parseInt(glina)*Integer.parseInt(ilosc));
            Integer zelazoMax = Integer.parseInt(zelazoIlosc.getText()) - (Integer.parseInt(zelazo)*Integer.parseInt(ilosc));
            Integer zbozeMax = Integer.parseInt(zbozeIlosc.getText()) - (Integer.parseInt(zboze)*Integer.parseInt(ilosc));
            if(drewnoMax<0||glinaMax<0||zelazoMax<0||zbozeMax<0){
                JOptionPane.showMessageDialog(null,"Nie masz wystarczającej liczby surowców by wyszkolić tylu żołnierzy");
                czyMoznaSzkolic = false;
            }
            String tak = wymagania;       
            wymagania = wymagania.replaceAll("[^0-9]+", "");
            tak = tak.replaceAll("\\d", ""); 
            if(tak.equals("Stajnia")){
                if(poziom < Integer.parseInt(wymagania)){
                    JOptionPane.showMessageDialog(null,"Nie możesz wyszkolić tej jednostki. Rozbuduj Stajnie");
                    czyMoznaSzkolic = false;
                }
            }
            if(czyMoznaSzkolic){
                drewnoIlosc.setText(drewnoMax.toString());
                glinaIlosc.setText(glinaMax.toString());
                zelazoIlosc.setText(zelazoMax.toString());
                zbozeIlosc.setText(zbozeMax.toString()); 
                String sql = "UploadJednostki@"+drewnoIlosc.getText()+"@"+glinaIlosc.getText()+"@"+zelazoIlosc.getText()+"@"+
                        zbozeIlosc.getText()+"@"+typ+"@"+ilosc;
                wyslij.println(sql);
                wyslij.flush();
                try{
                    Thread.sleep(200);
                }
                catch(InterruptedException e1){
                    e1.printStackTrace();
                }                
                parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Stajnia.this);
                parent.getContentPane().removeAll();            
                parent.add(new Stajnia(wyslij, odbierz,linia, poziom, pozycja));
                parent.validate();
                parent.repaint();
            }
        }        
    }
    class IloscChange implements DocumentListener{
        Szkolenie szkolenie;
        JTextField field;
        public IloscChange(Szkolenie szkolenie, JTextField field){
            this.szkolenie = szkolenie;
            this.field = field;           
        }
        @Override
        public void insertUpdate(DocumentEvent e) {
            szkolenie.setIlosc(field.getText());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            szkolenie.setIlosc(field.getText());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            szkolenie.setIlosc(field.getText());
        }               
    }
    class SetIlosc implements ActionListener{
        JTextField ilosc;
        JButton button;
        public SetIlosc(JTextField ilosc, JButton button){
            this.ilosc = ilosc;            
            this.button = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            ilosc.setText(button.getText());           
            ilosc.validate();
            ilosc.repaint(); 
        }        
    }
    class Wyloguj implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            serwus.close();                    
        }        
    }
}
