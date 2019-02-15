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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author lenovo
 */
public class Mapa extends JPanel{
    private WiadomoscServer serwus = new WiadomoscServer();
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    private String linia;
    private String [] tmp;
    private Thread wat;
    private JLabel drewnoIlosc;
    private JLabel drewno;
    private JLabel glinaIlosc;
    private JLabel glina;
    private JLabel zelazoIlosc;
    private JLabel zelazo;
    private JLabel zbozeIlosc;
    private JLabel zboze;
    private JPanel panel1;
    private JButton wyloguj;
    private JButton powrot;
    private JFrame parent;
    private JPanel grid;
    private JButton [] osady;
    public Mapa(PrintWriter wyslij, BufferedReader odbierz, String linia){
        this.wyslij = serwus.getWyslij();
        this.odbierz = serwus.getOdbierz();
        this.wyslij.println("Mapa");
        this.wyslij.flush();
        this.linia = linia;
        tmp = linia.split("@");
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(50,900));
        panel1.setLayout(new FlowLayout());
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        powrot = new JButton("<<-");
        powrot.setPreferredSize(new Dimension(50,50));
        powrot.addActionListener(new Powrot());
        panel1.add(powrot);
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
        wyloguj.addActionListener(new Wyloguj());
        panel1.add(wyloguj);
        add(panel1);
        grid = new JPanel();
        grid.setLayout(new GridLayout(0,10));
        grid.setPreferredSize(new Dimension(1500,1500));
        JScrollPane scroll = new JScrollPane(grid,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(1500,1500));
        add(scroll);
        //daleko o wat
        wat = new Thread(new Watek());
        wat.start();
        setVisible(true);
    }
    class Watek implements Runnable{
        @Override
        public void run() {     
            String linia = "";
            try{
                while((linia = odbierz.readLine())!=null){
                    String [] tak = linia.split("\\$");
                    osady = new JButton[tak.length];
                    for(int i=0;i<tak.length;i++){
                        String [] tmp = tak[i].split("@");                        
                        if(tmp.length < 5){
                            setOsada(Integer.parseInt(tmp[0]),tmp[1],tmp[2],osady[i]);
                        }
                        if(tmp.length > 55){
                            Mapa.this.linia = linia;
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
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Mapa.this);
            parent.getContentPane().removeAll();            
            parent.add(new PolaSurowcow(wyslij, odbierz,linia));
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
    public void setOsada(Integer id, String nazwa, String wlasciciel, JButton button){
        //Każdy z nich potrzebuje actionListener!!!
        button = new JButton();
        button.setPreferredSize(new Dimension(50,50));   
        //button.setMaximumSize(new Dimension(50,50));
        //button.setMinimumSize(new Dimension(50,50));
        button.setBackground(Color.GREEN);
        button.setOpaque(true);
        if(wlasciciel.equals(tmp[0])){            
            //To jest nasza osada
            button.setIcon(new ImageIcon(this.getClass().getResource("mojaOsada.png")));
            button.addActionListener(new MojaOsadaTmp(nazwa, wlasciciel, id));
            //tu daje action listener
        }
        if(wlasciciel.equals("Natura")){
            //nikt tu jeszcz nie założył osady
            button.setIcon(new ImageIcon(this.getClass().getResource("pusto.png")));
            button.addActionListener(new OpuszczonaOsadaTmp(nazwa, wlasciciel, id));
        }
        if(!wlasciciel.equals("Natura")&&!wlasciciel.equals(tmp[0])){
            //tu jest wroga nam osada
            button.setIcon(new ImageIcon(this.getClass().getResource("osadaWroga.png")));            
            button.addActionListener(new OsadaWrogaTmp(nazwa, wlasciciel, id));
        }
        grid.add(button);
        grid.validate();
        grid.repaint();
        
    }
    class MojaOsadaTmp implements ActionListener{
        String nazwa;
        String wlasciciel;
        Integer id;
        public MojaOsadaTmp(String nazwa, String wlasciciel, Integer id){
            this.nazwa = nazwa;
            this.wlasciciel = wlasciciel;
            this.id = id;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Mapa.this);
            parent.getContentPane().removeAll();            
            parent.add(new MojaOsada(wyslij, odbierz,linia, nazwa, wlasciciel, id));
            parent.validate();
            parent.repaint();
        }        
    }
    class OsadaWrogaTmp implements ActionListener{
        String nazwa;
        String wlasciciel;
        Integer id;
        public OsadaWrogaTmp(String nazwa, String wlasciciel, Integer id){
            this.nazwa = nazwa;
            this.wlasciciel = wlasciciel;
            this.id = id;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Mapa.this);
            parent.getContentPane().removeAll();            
            parent.add(new OsadaWroga(wyslij, odbierz,linia, nazwa, wlasciciel, id));
            parent.validate();
            parent.repaint();
        }        
    }
    class OpuszczonaOsadaTmp implements ActionListener{
        String nazwa;
        String wlasciciel;
        Integer id;
        public OpuszczonaOsadaTmp(String nazwa, String wlasciciel, Integer id){
            this.nazwa = nazwa;
            this.wlasciciel = wlasciciel;
            this.id = id;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Mapa.this);
            parent.getContentPane().removeAll();            
            parent.add(new OpuszczonaOsada(wyslij, odbierz,linia, nazwa, wlasciciel, id));
            parent.validate();
            parent.repaint();
        }        
    }
}
