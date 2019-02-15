/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alliancegracz;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.swing.SwingUtilities;

/**
 *
 * @author lenovo
 */
//Wyglad, dzialanie
public class Pole extends JPanel{
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    private String linia;
    private String nazwa;
    private String [] tmp;
    private JPanel panel1;
    private JPanel panelDrewno;
    private JPanel panelGlina;
    private JPanel panelZelazo;
    private JPanel panelZboze;
    private JLabel drewnoMini;
    private JLabel drewnoIlosc;
    private JLabel glinaMini;
    private JLabel glinaIlosc;
    private JLabel zelazoMini;
    private JLabel zelazoIlosc;
    private JLabel zbozeMini;
    private JLabel zbozeIlosc;   
    private JButton drewno;
    private JLabel drewnoIkona;
    private JButton glina;
    private JLabel glinaIkona;
    private JButton zelazo;
    private JLabel zelazoIkona;
    private JButton zboze;
    private JLabel zbozeIkona;
    private JButton wyloguj;
    
    private JLabel drewnoDrewnoIkona;
    private JLabel drewnoDrewno = new JLabel();
    private JLabel drewnoGlinaIkona;
    private JLabel drewnoGlina = new JLabel();
    private JLabel drewnoZelazoIkona;
    private JLabel drewnoZelazo = new JLabel();
    private JLabel drewnoZbozeIkona;
    private JLabel drewnoZboze = new JLabel();
    
    private JLabel glinaDrewnoIkona;
    private JLabel glinaDrewno = new JLabel();
    private JLabel glinaGlinaIkona;
    private JLabel glinaGlina = new JLabel();
    private JLabel glinaZelazoIkona;
    private JLabel glinaZelazo = new JLabel();
    private JLabel glinaZbozeIkona;
    private JLabel glinaZboze = new JLabel();
    
    private JLabel zelazoDrewnoIkona;
    private JLabel zelazoDrewno = new JLabel();
    private JLabel zelazoGlinaIkona;
    private JLabel zelazoGlina = new JLabel();
    private JLabel zelazoZelazoIkona;
    private JLabel zelazoZelazo = new JLabel();
    private JLabel zelazoZbozeIkona;
    private JLabel zelazoZboze = new JLabel();
    
    private JLabel zbozeDrewnoIkona;
    private JLabel zbozeDrewno = new JLabel();
    private JLabel zbozeGlinaIkona;
    private JLabel zbozeGlina = new JLabel();
    private JLabel zbozeZelazoIkona;
    private JLabel zbozeZelazo = new JLabel();
    private JLabel zbozeZbozeIkona;
    private JLabel zbozeZboze = new JLabel();
    private JFrame parent;
    private JButton powrot;
    private Thread wat;
    private WiadomoscServer serwus = new WiadomoscServer();
    public Pole(PrintWriter wyslij, BufferedReader odbierz, String linia, String nazwa){
        setSize(1600,900);
        //this.wyslij = wyslij;
        //this.odbierz = odbierz;
        this.wyslij = serwus.getWyslij();
        this.odbierz = serwus.getOdbierz();
        this.linia = linia;
        this.nazwa = nazwa;
        wat = new Thread(new Watek());
        wat.start();
        tmp = linia.split("@");
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        //add(Box.createRigidArea(new Dimension(0,30)));
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setPreferredSize(new Dimension(10,10));
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        powrot = new JButton("<<-");
        powrot.setPreferredSize(new Dimension(50,50));
        powrot.addActionListener(new Powrot());
        panel1.add(powrot);
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
        
        panelDrewno = new JPanel();
        panelDrewno.setLayout(new FlowLayout());
        panelDrewno.setPreferredSize(new Dimension(100,100));
        panelDrewno.setAlignmentX(Component.CENTER_ALIGNMENT);
        drewno = new JButton("Posadz las");
        drewno.setPreferredSize(new Dimension(300,100)); 
        drewno.setLayout(new FlowLayout());
        drewno.addActionListener(new Drewno());
        ustawSurowce(drewno, drewnoDrewno, drewnoGlina, drewnoZelazo, drewnoZboze, drewnoDrewnoIkona, drewnoGlinaIkona, drewnoZelazoIkona, drewnoZbozeIkona);
        panelDrewno.add(drewno);
        drewnoIkona = new JLabel(new ImageIcon(this.getClass().getResource("drewnoMAX.jpg")));
        drewnoIkona.setPreferredSize(new Dimension(300,300));        
        panelDrewno.add(drewnoIkona);
        add(panelDrewno);
        
        panelGlina = new JPanel();
        panelGlina.setLayout(new FlowLayout());
        panelGlina.setPreferredSize(new Dimension(100,100));
        panelGlina.setAlignmentX(Component.CENTER_ALIGNMENT);
        glina = new JButton("Zbuduj kopalnie gliny");
        glina.setPreferredSize(new Dimension(300,100));  
        glina.setLayout(new FlowLayout());
        glina.addActionListener(new Glina());
        ustawSurowce(glina, glinaDrewno, glinaGlina, glinaZelazo, glinaZboze, glinaDrewnoIkona, glinaGlinaIkona, glinaZelazoIkona, glinaZbozeIkona);
        panelGlina.add(glina);
        glinaIkona = new JLabel(new ImageIcon(this.getClass().getResource("glinaMAX.jpg")));
        glinaIkona.setPreferredSize(new Dimension(300,300));        
        panelGlina.add(glinaIkona);
        add(panelGlina);
        
        panelZelazo = new JPanel();
        panelZelazo.setLayout(new FlowLayout());
        panelZelazo.setPreferredSize(new Dimension(100,100));
        panelZelazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        zelazo = new JButton("Zbuduj kopalnie żelaza");
        zelazo.setPreferredSize(new Dimension(300,100));  
        zelazo.setLayout(new FlowLayout());
        zelazo.addActionListener(new Zelazo());
        ustawSurowce(zelazo, zelazoDrewno, zelazoGlina, zelazoZelazo, zelazoZboze, zelazoDrewnoIkona, zelazoGlinaIkona, zelazoZelazoIkona, zelazoZbozeIkona);
        panelZelazo.add(zelazo);
        zelazoIkona = new JLabel(new ImageIcon(this.getClass().getResource("zelazoMAX.jpg")));
        zelazoIkona.setPreferredSize(new Dimension(300,300));        
        panelZelazo.add(zelazoIkona);
        add(panelZelazo);
        
        panelZboze = new JPanel();
        panelZboze.setLayout(new FlowLayout());
        panelZboze.setPreferredSize(new Dimension(100,100));
        panelZboze.setAlignmentX(Component.CENTER_ALIGNMENT);
        zboze = new JButton("Posadź pole zboża");
        zboze.setPreferredSize(new Dimension(300,100));  
        zboze.setLayout(new FlowLayout());
        zboze.addActionListener(new Zboze());
        ustawSurowce(zboze, zbozeDrewno, zbozeGlina, zbozeZelazo, zbozeZboze, zbozeDrewnoIkona, zbozeGlinaIkona, zbozeZelazoIkona, zbozeZbozeIkona);
        panelZboze.add(zboze);
        zbozeIkona = new JLabel(new ImageIcon(this.getClass().getResource("zbozeMAX.jpg")));
        zbozeIkona.setPreferredSize(new Dimension(300,300));        
        panelZboze.add(zbozeIkona);
        add(panelZboze);   
        wyslij.println("IleKosztuje@DREWNO@1");
        wyslij.flush();
    }
    class Watek implements Runnable{

        @Override
        public void run() {            
            String linia = "";
            try{                
                while((linia=odbierz.readLine())!=null){                    
                    String [] tmp = linia.split("@");                    
                    if(tmp[0].equals("DREWNO")){
                        drewnoDrewno.setText(tmp[1]);
                        drewnoGlina.setText(tmp[2]);
                        drewnoZelazo.setText(tmp[3]);
                        drewnoZboze.setText(tmp[4]);
                        wyslij.println("IleKosztuje@GLINA@1");
                        wyslij.flush();
                    }
                    if(tmp[0].equals("GLINA")){
                        glinaDrewno.setText(tmp[1]);
                        glinaGlina.setText(tmp[2]);
                        glinaZelazo.setText(tmp[3]);
                        glinaZboze.setText(tmp[4]);
                        wyslij.println("IleKosztuje@ZELAZO@1");
                        wyslij.flush();
                    }
                    if(tmp[0].equals("ZELAZO")){
                        zelazoDrewno.setText(tmp[1]);
                        zelazoGlina.setText(tmp[2]);
                        zelazoZelazo.setText(tmp[3]);
                        zelazoZboze.setText(tmp[4]);
                        wyslij.println("IleKosztuje@ZBOZE@1");
                        wyslij.flush();
                    }
                    if(tmp[0].equals("ZBOZE")){
                        zbozeDrewno.setText(tmp[1]);
                        zbozeGlina.setText(tmp[2]);
                        zbozeZelazo.setText(tmp[3]);
                        zbozeZboze.setText(tmp[4]);                        
                    }
                    if(tmp.length > 55){
                        Pole.this.linia = linia;
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
    private void ustawSurowce(JButton button, JLabel drewno, JLabel glina, JLabel zelazo, JLabel zboze, JLabel drewnoIkona, JLabel glinaIkona, JLabel zelazoIkona, JLabel zbozeIkona){
        
        drewnoIkona = new JLabel(new ImageIcon(this.getClass().getResource("drewnoMini.png")));
        drewnoIkona.setPreferredSize(new Dimension(30,30));
        button.add(drewnoIkona);        
        //drewno.setText("Lad");
        button.add(drewno);
        
       
        glinaIkona = new JLabel(new ImageIcon(this.getClass().getResource("glinaMini.png")));
        glinaIkona.setPreferredSize(new Dimension(30,30));
        button.add(glinaIkona);        
        //glina.setText("Lad");
        button.add(glina);
        
        
        zelazoIkona = new JLabel(new ImageIcon(this.getClass().getResource("zelazoMini.png")));
        zelazoIkona.setPreferredSize(new Dimension(30,30));
        button.add(zelazoIkona);       
        //zelazo.setText("Lad");
        button.add(zelazo);
        
        
        zbozeIkona = new JLabel(new ImageIcon(this.getClass().getResource("zbozeMini.png")));
        zbozeIkona.setPreferredSize(new Dimension(30,30));
        button.add(zbozeIkona);        
        //zboze.setText("Lad");
        button.add(zboze);
    }
    //Kazdy button powoduje 
    //sprawdzam czy mam dostateczna liczbe surowcow
    //odejmuje surowce od siebie
    //przesyla informacje ile mam surowcow, co wybudowalam i gdzie 
    //Powrot do PolaSurowcow!!!!!
    class Drewno implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer drewno1 = Integer.parseInt(drewnoIlosc.getText());
            Integer drewno2 = Integer.parseInt(drewnoDrewno.getText());
            Integer drewno = drewno1 - drewno2;
            Integer glina1 = Integer.parseInt(glinaIlosc.getText());
            Integer glina2 = Integer.parseInt(drewnoGlina.getText());
            Integer glina = glina1 - glina2;
            Integer zelazo1 = Integer.parseInt(zelazoIlosc.getText());
            Integer zelazo2 = Integer.parseInt(drewnoZelazo.getText());
            Integer zelazo = zelazo1 - zelazo2;
            Integer zboze1 = Integer.parseInt(zbozeIlosc.getText());
            Integer zboze2 = Integer.parseInt(drewnoZboze.getText());
            Integer zboze = zboze1 - zboze2;
            if(drewno<0||glina<0||zelazo<0||zboze<0){
                JOptionPane.showMessageDialog(null,"Nie masz wystarczającej ilości surowców");
            }
            else{
                drewnoIlosc.setText(drewno.toString());
                glinaIlosc.setText(glina.toString());
                zelazoIlosc.setText(zelazo.toString());
                zbozeIlosc.setText(zboze.toString());
                String tmp = "Upload@"+drewnoIlosc.getText()+"@"+glinaIlosc.getText()+"@"+zelazoIlosc.getText()+"@"+
                        zbozeIlosc.getText()+"@"+"Drewno1@"+nazwa;
                wyslij.println(tmp);
                wyslij.flush();
                metodaParenta();
            }
        }        
    }
    class Glina implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer drewno1 = Integer.parseInt(drewnoIlosc.getText());
            Integer drewno2 = Integer.parseInt(glinaDrewno.getText());
            Integer drewno = drewno1 - drewno2;
            Integer glina1 = Integer.parseInt(glinaIlosc.getText());
            Integer glina2 = Integer.parseInt(glinaGlina.getText());
            Integer glina = glina1 - glina2;
            Integer zelazo1 = Integer.parseInt(zelazoIlosc.getText());
            Integer zelazo2 = Integer.parseInt(glinaZelazo.getText());
            Integer zelazo = zelazo1 - zelazo2;
            Integer zboze1 = Integer.parseInt(zbozeIlosc.getText());
            Integer zboze2 = Integer.parseInt(glinaZboze.getText());
            Integer zboze = zboze1 - zboze2;
            if(drewno<0||glina<0||zelazo<0||zboze<0){
                JOptionPane.showMessageDialog(null,"Nie masz wystarczającej ilości surowców");
            }
            else{
                drewnoIlosc.setText(drewno.toString());
                glinaIlosc.setText(glina.toString());
                zelazoIlosc.setText(zelazo.toString());
                zbozeIlosc.setText(zboze.toString());
                String tmp = "Upload@"+drewnoIlosc.getText()+"@"+glinaIlosc.getText()+"@"+zelazoIlosc.getText()+"@"+
                        zbozeIlosc.getText()+"@"+"Glina1@"+nazwa;
                wyslij.println(tmp);
                wyslij.flush();
                metodaParenta();
            }
        }        
    }
    class Zelazo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer drewno1 = Integer.parseInt(drewnoIlosc.getText());
            Integer drewno2 = Integer.parseInt(zelazoDrewno.getText());
            Integer drewno = drewno1 - drewno2;
            Integer glina1 = Integer.parseInt(glinaIlosc.getText());
            Integer glina2 = Integer.parseInt(zelazoGlina.getText());
            Integer glina = glina1 - glina2;
            Integer zelazo1 = Integer.parseInt(zelazoIlosc.getText());
            Integer zelazo2 = Integer.parseInt(zelazoZelazo.getText());
            Integer zelazo = zelazo1 - zelazo2;
            Integer zboze1 = Integer.parseInt(zbozeIlosc.getText());
            Integer zboze2 = Integer.parseInt(zelazoZboze.getText());
            Integer zboze = zboze1 - zboze2;
            if(drewno<0||glina<0||zelazo<0||zboze<0){
                JOptionPane.showMessageDialog(null,"Nie masz wystarczającej ilości surowców");
            }
            else{
                drewnoIlosc.setText(drewno.toString());
                glinaIlosc.setText(glina.toString());
                zelazoIlosc.setText(zelazo.toString());
                zbozeIlosc.setText(zboze.toString());
                String tmp = "Upload@"+drewnoIlosc.getText()+"@"+glinaIlosc.getText()+"@"+zelazoIlosc.getText()+"@"+
                        zbozeIlosc.getText()+"@"+"Zelazo1@"+nazwa;
                wyslij.println(tmp);
                wyslij.flush();
                metodaParenta();
            }
        }        
    }
    class Zboze implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer drewno1 = Integer.parseInt(drewnoIlosc.getText());
            Integer drewno2 = Integer.parseInt(zbozeDrewno.getText());
            Integer drewno = drewno1 - drewno2;
            Integer glina1 = Integer.parseInt(glinaIlosc.getText());
            Integer glina2 = Integer.parseInt(zbozeGlina.getText());
            Integer glina = glina1 - glina2;
            Integer zelazo1 = Integer.parseInt(zelazoIlosc.getText());
            Integer zelazo2 = Integer.parseInt(zbozeZelazo.getText());
            Integer zelazo = zelazo1 - zelazo2;
            Integer zboze1 = Integer.parseInt(zbozeIlosc.getText());
            Integer zboze2 = Integer.parseInt(zbozeZboze.getText());
            Integer zboze = zboze1 - zboze2;
            if(drewno<0||glina<0||zelazo<0||zboze<0){
                JOptionPane.showMessageDialog(null,"Nie masz wystarczającej ilości surowców");
            }
            else{
                drewnoIlosc.setText(drewno.toString());
                glinaIlosc.setText(glina.toString());
                zelazoIlosc.setText(zelazo.toString());
                zbozeIlosc.setText(zboze.toString());
                String tmp = "Upload@"+drewnoIlosc.getText()+"@"+glinaIlosc.getText()+"@"+zelazoIlosc.getText()+"@"+
                        zbozeIlosc.getText()+"@"+"Zboze1@"+nazwa;
                wyslij.println(tmp);
                wyslij.flush();
                metodaParenta();
            }
        }        
    }
    private void metodaParenta(){
        try{
            Thread.sleep(200);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Pole.this);
        parent.getContentPane().removeAll();            
        parent.add(new PolaSurowcow(wyslij, odbierz,linia));
        parent.validate();
        parent.repaint();
    }
    class Powrot implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Pole.this);
            parent.getContentPane().removeAll();            
            parent.add(new PolaSurowcow(wyslij, odbierz,linia));
            parent.validate();
            parent.repaint();
            //Thread.currentThread().stop();
            //wat.stop();
        }        
    }
    class Wyloguj implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            serwus.close();                    
        }        
    }
}
