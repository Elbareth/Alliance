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
import java.io.BufferedReader;
import java.io.PrintWriter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private JLabel drewnoMini;
    private JLabel drewnoIlosc;
    private JLabel glinaMini;
    private JLabel glinaIlosc;
    private JLabel zelazoMini;
    private JLabel zelazoIlosc;
    private JLabel zbozeMini;
    private JLabel zbozeIlosc;
    private JPanel grid;
    private JButton drewno;
    private JLabel drewnoIkona;
    private JButton glina;
    private JLabel glinaIkona;
    private JButton zelazo;
    private JLabel zelazoIkona;
    private JButton zboze;
    private JLabel zbozeIkona;
    private JButton wyloguj;
    private JPanel tmmp;
    public Pole(PrintWriter wyslij, BufferedReader odbierz, String linia, String nazwa){
        this.wyslij = wyslij;
        this.odbierz = odbierz;
        this.linia = linia;
        this.nazwa = nazwa;
        tmp = linia.split("@");
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        add(Box.createRigidArea(new Dimension(0,30)));
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setPreferredSize(new Dimension(10,10));
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        panel1.add(wyloguj);
        add(panel1);
        
        add(Box.createRigidArea(new Dimension(0,50)));
        tmmp = new JPanel();
        tmmp.setLayout(new FlowLayout());
        tmmp.setPreferredSize(new Dimension(300,300));
        grid = new JPanel();
        grid.setLayout(new GridLayout(0,2));
        grid.setPreferredSize(new Dimension(300,300));
        grid.setAlignmentX(Component.CENTER_ALIGNMENT);
        drewno = new JButton("Posadz las");
        drewno.setPreferredSize(new Dimension(150,50));
        grid.add(drewno);
        drewnoIkona = new JLabel(new ImageIcon(this.getClass().getResource("drewnoMAX.jpg")));
        drewnoIkona.setPreferredSize(new Dimension(300,300));
        grid.add(drewnoIkona);
        
        glina = new JButton("Zbuduj kopalnie gliny");
        glina.setPreferredSize(new Dimension(150,50));
        grid.add(glina);
        glinaIkona = new JLabel(new ImageIcon(this.getClass().getResource("glinaMAX.jpg")));
        glinaIkona.setPreferredSize(new Dimension(300,300));
        grid.add(glinaIkona);
        
        zelazo = new JButton("Zbuduj kopalnie żelaza");
        zelazo.setPreferredSize(new Dimension(150,50));
        grid.add(zelazo);
        zelazoIkona = new JLabel(new ImageIcon(this.getClass().getResource("zelazoMAX.jpg")));
        zelazoIkona.setPreferredSize(new Dimension(300,300));
        grid.add(zelazoIkona);
        
        zboze = new JButton("Posadź pole zboża");
        zboze.setPreferredSize(new Dimension(150,50));
        grid.add(zboze);
        zbozeIkona = new JLabel(new ImageIcon(this.getClass().getResource("zbozeMAX.jpg")));
        zbozeIkona.setPreferredSize(new Dimension(300,300));
        grid.add(zbozeIkona);
        tmmp.add(grid);
        add(tmmp);
        add(panel1);
    }
}
