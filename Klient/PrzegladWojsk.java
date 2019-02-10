/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alliancegracz;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author lenovo
 */
public class PrzegladWojsk extends JPanel{
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    private String linia;
    private String [] tmp;
    private WiadomoscServer serwus = new WiadomoscServer();
    private Thread wat;
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
    private String typ;
    private JFrame parent;
    private Integer poziom;
    private String pozycja;
    private Vector<String> kolumny = new Vector<String>();
    private Vector<Vector<String>> wnetrze = new Vector<Vector<String>>();
    private JTable tabelka;
    private String [] lista = {"Jednostka Lekka", "Jednostka Ciężka", "Łucznik", "Tropiciel", "Kawalerzysta", "Osadnik"};
    public PrzegladWojsk(PrintWriter wyslij, BufferedReader odbierz, String linia, String typ, Integer poziom, String pozycja){
        this.wyslij = serwus.getWyslij();
        this.odbierz = serwus.getOdbierz();
        this.typ = typ;
        this.linia = linia;
        this.poziom = poziom;
        this.pozycja = pozycja;
        tmp = linia.split("@");
        wat = new Thread(new Watek());
        wat.start();
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setPreferredSize(new Dimension(1600,0));
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
        kolumny.add("Nazwa");
        kolumny.add("Ilość");
        for(int i=56;i<62;i++){
            Vector<String> tak = new Vector<String>();
            tak.add(lista[i-56]);
            tak.add(tmp[i]);
            wnetrze.add(tak);
        }
        tabelka = new JTable(wnetrze, kolumny){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tabelka.setMaximumSize(new Dimension(500,300));
        tabelka.setPreferredScrollableViewportSize(new Dimension(500,300));
        tabelka.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(tabelka);
        add(scroll);
    }
    //Tylko do uzupełnienia surowców
    class Watek implements Runnable{        
        @Override
        public void run() {
            String linia = "";
            try{
                while((linia = odbierz.readLine())!= null){
                    String [] tmp = linia.split("@");
                    if(tmp.length > 55){
                        PrzegladWojsk.this.linia = linia;
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
            if(typ.equals("Koszary")){
                parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, PrzegladWojsk.this);
                parent.getContentPane().removeAll();            
                parent.add(new Koszary(wyslij, odbierz,linia, poziom, pozycja));
                parent.validate();
                parent.repaint();
            }
            if(typ.equals("Stajnia")){
                parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, PrzegladWojsk.this);
                parent.getContentPane().removeAll();            
                parent.add(new Stajnia(wyslij, odbierz,linia, poziom, pozycja));
                parent.validate();
                parent.repaint();
            }
            if(typ.equals("Palac")){
                //Gdy juz bede miec palac
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
