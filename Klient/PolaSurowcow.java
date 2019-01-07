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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
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
//Nie przesylam ID (element 1) i haslo (element 3) i liczymy od 0
//Napisz powitanie dla gracza!!!!
//Sprawdź czemu ostatni przycisk nie dziala
public class PolaSurowcow extends JPanel{
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    private String linia;
    private String [] tmp;
    private JPanel panel1;
    private JLabel drewno;
    private JLabel drewnoIlosc;
    private JLabel glina;
    private JLabel glinaIlosc;
    private JLabel zelazo;
    private JLabel zelazoIlosc;
    private JLabel zboze;
    private JLabel zbozeIlosc;
    private JPanel panel2;
    private JPanel grid;
    private JPanel surowce;
    private JButton pole1 = new JButton();    
    private JButton pole2 = new JButton();
    private JButton pole3 = new JButton();
    private JButton pole4 = new JButton();
    private JButton pole5 = new JButton();
    private JButton pole6 = new JButton();
    private JButton pole7 = new JButton();
    private JButton pole8 = new JButton();
    private JButton pole9 = new JButton();
    private JButton pole10 = new JButton();
    private JButton pole11 = new JButton();
    private JButton pole12 = new JButton();
    private JButton pole13 = new JButton();
    private JButton pole14 = new JButton();
    private JButton pole15 = new JButton();
    private JButton pole16 = new JButton();
    private JButton pole17 = new JButton();
    private JButton pole18 = new JButton();
    private JButton pole19 = new JButton();
    private JButton pole20 = new JButton();
    private JButton pole21 = new JButton();
    private JButton pole22 = new JButton();
    private JButton pole23 = new JButton();
    private JButton pole24 = new JButton();
    private Integer poziom1;
    private Integer poziom2;
    private Integer poziom3;
    private Integer poziom4;
    private Integer poziom5;
    private Integer poziom6;
    private Integer poziom7;
    private Integer poziom8;
    private Integer poziom9;
    private Integer poziom10;
    private Integer poziom11;
    private Integer poziom12;
    private Integer poziom13;
    private Integer poziom14;
    private Integer poziom15;
    private Integer poziom16;
    private Integer poziom17;
    private Integer poziom18;
    private Integer poziom19;
    private Integer poziom20;
    private Integer poziom21;
    private Integer poziom22;
    private Integer poziom23;
    private Integer poziom24;
    private JButton osada;
    private JButton wyloguj;
    private JLabel iloscDrewna;
    private JLabel iloscGliny;
    private JLabel iloscZelaza;
    private JLabel iloscZboza;
    private JLabel drewno2;
    private JLabel glina2;
    private JLabel zelazo2;
    private JLabel zboze2;
    private JButton mapa;
    private JFrame parent;
    private JLabel powitanie;
    public PolaSurowcow(PrintWriter wyslij, BufferedReader odbierz, String linia){
        pole1.setName("POLE1");
        pole2.setName("POLE2");
        pole3.setName("POLE3");
        pole4.setName("POLE4");
        pole5.setName("POLE5");
        pole6.setName("POLE6");
        pole7.setName("POLE7");
        pole8.setName("POLE8");
        pole9.setName("POLE9");
        pole10.setName("POLE10");
        pole11.setName("POLE11");
        pole12.setName("POLE12");
        pole13.setName("POLE13");
        pole14.setName("POLE14");
        pole15.setName("POLE15");
        pole16.setName("POLE16");
        pole17.setName("POLE17");
        pole18.setName("POLE18");
        pole19.setName("POLE19");
        pole20.setName("POLE20");
        pole21.setName("POLE21");
        pole22.setName("POLE22");
        pole23.setName("POLE23");
        pole24.setName("POLE24");
        this.wyslij = wyslij;
        this.odbierz = odbierz;
        this.linia = linia;  
        System.out.println(linia);
        tmp = linia.split("@");        
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        //add(Box.createRigidArea(new Dimension(5,5)));
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(50,900));
        panel1.setLayout(new FlowLayout());
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        drewno = new JLabel(new ImageIcon(this.getClass().getResource("drewnoMini.png")));
        drewno.setPreferredSize(new Dimension(30,30));
        panel1.add(drewno);
        panel1.add(Box.createRigidArea(new Dimension(10,0)));
        drewnoIlosc = new JLabel(tmp[1]);
        drewnoIlosc.setPreferredSize(new Dimension(30,150));
        panel1.add(drewnoIlosc);
        panel1.add(Box.createRigidArea(new Dimension(100,0)));
        
        glina = new JLabel(new ImageIcon(this.getClass().getResource("glinaMini.png")));
        glina.setPreferredSize(new Dimension(30,30));
        panel1.add(glina);
        panel1.add(Box.createRigidArea(new Dimension(10,0)));
        glinaIlosc = new JLabel(tmp[2]);
        glinaIlosc.setPreferredSize(new Dimension(30,150));
        panel1.add(glinaIlosc);
        panel1.add(Box.createRigidArea(new Dimension(100,0)));
        
        zelazo = new JLabel(new ImageIcon(this.getClass().getResource("zelazoMini.png")));
        zelazo.setPreferredSize(new Dimension(30,30));
        panel1.add(zelazo);
        panel1.add(Box.createRigidArea(new Dimension(10,0)));
        zelazoIlosc = new JLabel(tmp[3]);
        zelazoIlosc.setPreferredSize(new Dimension(30,150));
        panel1.add(zelazoIlosc);
        panel1.add(Box.createRigidArea(new Dimension(100,0)));
        
        zboze = new JLabel(new ImageIcon(this.getClass().getResource("zbozeMini.png")));
        zboze.setPreferredSize(new Dimension(30,30));
        panel1.add(zboze);
        panel1.add(Box.createRigidArea(new Dimension(10,0)));
        zbozeIlosc = new JLabel(tmp[4]);
        zbozeIlosc.setPreferredSize(new Dimension(30,150));
        panel1.add(zbozeIlosc);
        panel1.add(Box.createRigidArea(new Dimension(100,0)));
        wyloguj = new JButton("Wyloguj się");
        wyloguj.setPreferredSize(new Dimension(150,30));
        panel1.add(wyloguj);
        add(panel1); 
        add(Box.createRigidArea(new Dimension(0,20)));
        powitanie = new JLabel("Witaj "+tmp[0]+"!");
        powitanie.setFont(new Font("Maiandra GD zwykła",Font.BOLD,20));
        add(powitanie);
        
        add(Box.createRigidArea(new Dimension(0,20)));
        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(400,900));
        panel2.setLayout(new FlowLayout());
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        grid = new JPanel();
        grid.setLayout(new GridLayout(0,5));
        grid.setPreferredSize(new Dimension(500,500));
        setPola(pole1,poziom1,tmp[32]);
        setPola(pole2,poziom2,tmp[33]);
        setPola(pole3,poziom3,tmp[34]);
        setPola(pole4,poziom4,tmp[35]);
        setPola(pole5,poziom5,tmp[36]);
        setPola(pole6,poziom6,tmp[37]);
        setPola(pole7,poziom7,tmp[38]);
        setPola(pole8,poziom8,tmp[39]);
        setPola(pole9,poziom9,tmp[40]);
        setPola(pole10,poziom10,tmp[41]);
        setPola(pole11,poziom11,tmp[42]);
        setPola(pole12,poziom12,tmp[43]);
        osada = new JButton(new ImageIcon(this.getClass().getResource("miniOsada.png")));
        osada.setPreferredSize(new Dimension(100,100));
        grid.add(osada);
        setPola(pole13,poziom13,tmp[44]);
        setPola(pole14,poziom14,tmp[45]);
        setPola(pole15,poziom15,tmp[46]);
        setPola(pole16,poziom16,tmp[47]);
        setPola(pole17,poziom17,tmp[48]);
        setPola(pole18,poziom18,tmp[49]);
        setPola(pole19,poziom19,tmp[50]);
        setPola(pole20,poziom20,tmp[51]);
        setPola(pole21,poziom21,tmp[52]);
        setPola(pole22,poziom22,tmp[53]);
        setPola(pole23,poziom23,tmp[54]);
        setPola(pole24,poziom24,tmp[55]);
        panel2.add(grid);
        
        //tmp13+4
        surowce = new JPanel();
        surowce.setLayout(new GridLayout(0,2));
        surowce.setPreferredSize(new Dimension(500,300));
        drewno2 = new JLabel(new ImageIcon(this.getClass().getResource("drewnoMini.png")));
        drewno2.setPreferredSize(new Dimension(30,30));
        surowce.add(drewno2);
        iloscDrewna = new JLabel("Produkcja na godzinę: "+tmp[13].toString());        
        iloscDrewna.setPreferredSize(new Dimension(200,50));
        surowce.add(iloscDrewna);        
        glina2 = new JLabel(new ImageIcon(this.getClass().getResource("glinaMini.png")));
        glina2.setPreferredSize(new Dimension(30,30));
        surowce.add(glina2);
        iloscGliny = new JLabel("Produkcja na godzinę: "+tmp[14].toString());
        iloscGliny.setPreferredSize(new Dimension(200,50));
        surowce.add(iloscGliny);
        zelazo2 = new JLabel(new ImageIcon(this.getClass().getResource("zelazoMini.png")));
        zelazo2.setPreferredSize(new Dimension(30,30));
        surowce.add(zelazo2);
        iloscZelaza = new JLabel("Produkcja na godzinę: "+tmp[15].toString());
        iloscZelaza.setPreferredSize(new Dimension(200,50));
        surowce.add(iloscZelaza);
        zboze2 = new JLabel(new ImageIcon(this.getClass().getResource("zbozeMini.png")));
        zboze2.setPreferredSize(new Dimension(30,30));
        surowce.add(zboze2);
        iloscZboza = new JLabel("Produkcja na godzinę: "+tmp[16].toString());
        iloscZboza.setPreferredSize(new Dimension(200,50));
        surowce.add(iloscZboza);
        panel2.add(surowce);
        mapa = new JButton(new ImageIcon(this.getClass().getResource("mapaMini.jpg")));
        mapa.setPreferredSize(new Dimension(160,100));
        panel2.add(mapa);
        add(panel2);
    }
    private void setPola(JButton pole, Integer poziom, String tmp){       
        //pole = new JButton();
        //System.out.println(tmp);
        pole.setPreferredSize(new Dimension(100,100));
        String tak = tmp;       
        tmp = tmp.replaceAll("[^0-9]+", "");
        tak = tak.replaceAll("\\d", "");  
        //System.out.println(tak);
        if(tak.equals("Drewno")){
            pole.setIcon(new ImageIcon(this.getClass().getResource("drewno.jpg")));
            pole.setContentAreaFilled(true);
            poziom = Integer.parseInt(tmp);
        }
        if(tak.equals("Glina")){
            pole.setIcon(new ImageIcon(this.getClass().getResource("glina.jpg")));
            pole.setContentAreaFilled(true);
            poziom = Integer.parseInt(tmp);
        }
        if(tak.equals("Zelazo")){
            pole.setIcon(new ImageIcon(this.getClass().getResource("zelazo.jpg")));
            pole.setContentAreaFilled(true);
            poziom = Integer.parseInt(tmp);
        }
        if(tak.equals("Zboze")){
            pole.setIcon(new ImageIcon(this.getClass().getResource("zboze.jpg")));
            pole.setContentAreaFilled(true);
            poziom = Integer.parseInt(tmp);
        }
        if(tak.equals("brak")){            
            pole.addActionListener(new BrakSurowca(linia, pole));
        }
        grid.add(pole);
    }
    class BrakSurowca implements ActionListener{
        String linia;
        JButton button;
        public BrakSurowca(String linia, JButton button){
            this.linia = linia;
            this.button = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("klik");
            //button.setText("Klik");
            //System.out.println(button.getName());
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, PolaSurowcow.this);
            parent.getContentPane().removeAll();            
            parent.add(new Pole(wyslij, odbierz,linia,button.getName()));
            parent.validate();
            parent.repaint();
        }        
    }
}
