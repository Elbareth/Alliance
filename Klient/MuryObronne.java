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
import javax.swing.SwingUtilities;

/**
 *
 * @author lenovo
 */
public class MuryObronne extends JPanel{
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    private String linia;
    private String [] tmp;
    private Integer poziom;
    private String pozycja;
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
    private JButton powrot;
    private JButton wyloguj;
    private JFrame parent;
    private JLabel tytul;
    private JPanel panelGlowny;
    private JLabel ilustracja;
    private JLabel opis;
    private JButton rozbuduj;
    private JLabel drewnoBudujIkona;
    private JLabel drewnoBuduj;
    private JLabel glinaBudujIkona;
    private JLabel glinaBuduj;
    private JLabel zelazoBudujIkona;
    private JLabel zelazoBuduj;
    private JLabel zbozeBudujIkona;
    private JLabel zbozeBuduj;
    public MuryObronne(PrintWriter wyslij, BufferedReader odbierz, String linia, Integer poziom, String pozycja){
        this.wyslij = serwus.getWyslij();
        this.odbierz = serwus.getOdbierz();
        this.linia = linia;
        this.poziom = poziom;
        this.pozycja = pozycja;
        tmp = linia.split("@");
        this.wyslij.println("MuryObronne@"+(poziom+1));
        this.wyslij.flush();
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
        tytul = new JLabel("Mury Obronne poziom "+poziom);
        tytul.setPreferredSize(new Dimension(300,50));
        tytul.setAlignmentX(Component.CENTER_ALIGNMENT);
        tytul.setFont(new Font("Lucida Handwriting kursywa",Font.BOLD,20));
        add(tytul);
        panelGlowny = new JPanel();
        panelGlowny.setPreferredSize(new Dimension(500,100));
        opis = new JLabel();//opis dodawany dopiero w watku
        opis.setPreferredSize(new Dimension(400,200));
        panelGlowny.add(opis);
        ilustracja = new JLabel(new ImageIcon(this.getClass().getResource("muryObronne.png")));
        ilustracja.setPreferredSize(new Dimension(200,100));
        panelGlowny.add(ilustracja);
        add(panelGlowny);
        rozbuduj = new JButton("");
        //rozbuduj.setPreferredSize(new Dimension(300,200));
        rozbuduj.setMaximumSize(new Dimension(500,200));
        rozbuduj.setAlignmentX(Component.CENTER_ALIGNMENT);
        rozbuduj.setLayout(new FlowLayout());
        rozbuduj.addActionListener(new Buduj());
        drewnoBudujIkona = new JLabel(new ImageIcon(this.getClass().getResource("drewnoMini.png")));        
        drewnoBudujIkona.setPreferredSize(new Dimension(30,30));
        rozbuduj.add(drewnoBudujIkona);
        drewnoBuduj = new JLabel();       
        drewnoBuduj.setPreferredSize(new Dimension(50,30));
        rozbuduj.add(drewnoBuduj);
        
        glinaBudujIkona = new JLabel(new ImageIcon(this.getClass().getResource("glinaMini.png")));        
        glinaBudujIkona.setPreferredSize(new Dimension(30,30));
        rozbuduj.add(glinaBudujIkona);
        glinaBuduj = new JLabel();
        glinaBuduj.setPreferredSize(new Dimension(50,30));
        rozbuduj.add(glinaBuduj);
        
        zelazoBudujIkona = new JLabel(new ImageIcon(this.getClass().getResource("zelazoMini.png")));        
        zelazoBudujIkona.setPreferredSize(new Dimension(30,30));
        rozbuduj.add(zelazoBudujIkona);
        zelazoBuduj = new JLabel();
        zelazoBuduj.setPreferredSize(new Dimension(50,30));
        rozbuduj.add(zelazoBuduj);
        
        zbozeBudujIkona = new JLabel(new ImageIcon(this.getClass().getResource("zbozeMini.png")));        
        zbozeBudujIkona.setPreferredSize(new Dimension(30,30));
        rozbuduj.add(zbozeBudujIkona);
        zbozeBuduj = new JLabel();
        zbozeBuduj.setPreferredSize(new Dimension(50,30));
        rozbuduj.add(zbozeBuduj);
        add(rozbuduj);
        setVisible(true);
    }
    class Watek implements Runnable{
        @Override
        public void run() {
            String linia = "";
            try{
                while((linia=odbierz.readLine())!=null){
                    String [] tmp = linia.split("@");
                    if(tmp[0].equals("Opis")){
                        drewnoBuduj.setText(tmp[1]);
                        glinaBuduj.setText(tmp[2]);
                        zelazoBuduj.setText(tmp[3]);
                        zbozeBuduj.setText(tmp[4]);
                        opis.setText("<html>"+tmp[7]+".<br> By rozbudować ten budynke potrzebujesz "+tmp[6]+".<br>Następny poziom murów obronnych zwiększy Twoją obronność do "+tmp[5]+"00% </html>");
                        MuryObronne.this.validate();
                        MuryObronne.this.repaint();
                    }
                    if(tmp.length > 55 ){
                        MuryObronne.this.linia = linia;
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
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, MuryObronne.this);
            parent.getContentPane().removeAll();            
            parent.add(new Osada(wyslij, odbierz,linia));
            parent.validate();
            parent.repaint();
        }        
    }
    class Buduj implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer drewno = Integer.parseInt(drewnoIlosc.getText()) - Integer.parseInt(drewnoBuduj.getText());
            Integer glina = Integer.parseInt(glinaIlosc.getText()) - Integer.parseInt(glinaBuduj.getText());
            Integer zelazo = Integer.parseInt(zelazoIlosc.getText()) - Integer.parseInt(zelazoBuduj.getText());
            Integer zboze = Integer.parseInt(zbozeIlosc.getText()) - Integer.parseInt(zbozeBuduj.getText());
            if(drewno < 0 || glina < 0 || zelazo < 0 || zboze< 0){
                JOptionPane.showMessageDialog(null,"Masz za mało surowców by wybudować mury obronne");
            }
            else{
                drewnoIlosc.setText(drewno.toString());
                glinaIlosc.setText(glina.toString());
                zelazoIlosc.setText(zelazo.toString());
                zbozeIlosc.setText(zboze.toString());
                String sql = "UploadMuryObronne@"+drewnoIlosc.getText()+"@"+glinaIlosc.getText()+"@"+zelazoIlosc.getText()+"@"+
                        zbozeIlosc.getText()+"@"+(poziom+1);
                wyslij.println(sql);
                wyslij.flush();
                try{
                    Thread.sleep(200);
                }
                catch(InterruptedException e1){
                    e1.printStackTrace();
                }
                parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, MuryObronne.this);
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
