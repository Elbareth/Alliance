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
//Potrzebuje ile kosztuje nastepny poziom oraz 
//Opis dla 
public class Surowce extends JPanel{
    private PrintWriter wyslij;
    private BufferedReader odbierz;
    private String linia;
    private String [] tmp;
    private JButton powrot;
    private JButton wyloguj;
    private JPanel panel1;
    private JLabel drewnoMini;
    private JLabel glinaMini;
    private JLabel zelazoMini;
    private JLabel zbozeMini;
    private JLabel drewnoIlosc;
    private JLabel glinaIlosc;
    private JLabel zelazoIlosc;
    private JLabel zbozeIlosc;
    private JFrame parent;
    private Integer poziom;//Jaki poziom ma moje pole
    private String pozycja;//W ktorym miejscu znajduje sie moje pole
    private String rodzaj;//Czy uprawiam tu drewno czy gline czy itp.
    private JLabel tytul;
    private JPanel glowny;
    private JLabel opis;
    private JLabel zdjecie;
    private JButton buduj;   
    private JLabel buttonDrewnoIkona;
    private JLabel buttonDrewno = new JLabel();
    private JLabel buttonGlinaIkona;
    private JLabel buttonGlina = new JLabel();
    private JLabel buttonZelazoIkona;
    private JLabel buttonZelazo = new JLabel();
    private JLabel buttonZbozeIkona;
    private JLabel buttonZboze = new JLabel(); 
    private Thread wat;
    private WiadomoscServer serwus = new WiadomoscServer();
    public Surowce(PrintWriter wyslij, BufferedReader odbierz, String linia, Integer poziom, String rodzaj, String pozycja){
        //this.wyslij = wyslij;       
        this.wyslij = serwus.getWyslij();
        this.odbierz = serwus.getOdbierz();        
        this.linia = linia;
        this.poziom = poziom;
        this.pozycja = pozycja;
        this.rodzaj = rodzaj;
        /*wyslij.println("IleKosztuje@"+rodzaj+"@"+(poziom+1));//Ile kosztuje dany poziom pola
        wyslij.flush();*/
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
        add(Box.createRigidArea(new Dimension(0,5)));
        tytul = new JLabel(rodzaj+" poziom "+poziom.toString());
        tytul.setFont(new Font("Lucida Handwriting kursywa",Font.BOLD,20));
        tytul.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(tytul);
        glowny = new JPanel();
        glowny.setLayout(new FlowLayout());
        glowny.setPreferredSize(new Dimension(900,400));
        opis = new JLabel("Ładowanie opisu");
        opis.setPreferredSize(new Dimension(300,300));
        glowny.add(opis);
        zdjecie = new JLabel(new ImageIcon(this.getClass().getResource(rodzaj.toLowerCase()+"MAX.jpg")));
        zdjecie.setPreferredSize(new Dimension(300,300));
        glowny.add(zdjecie);
        add(glowny);        
        buduj = new JButton("Rozbuduj pole");
        buduj.setPreferredSize(new Dimension(50,50));
        //buduj.setMaximumSize(new Dimension(500,500));
        //buduj.setMinimumSize(new Dimension(300,300));
        buduj.setAlignmentX(Component.CENTER_ALIGNMENT);
        buduj.setLayout(new FlowLayout());
        buduj.addActionListener(new Buduj());
        buttonDrewnoIkona = new JLabel(new ImageIcon(this.getClass().getResource("drewnoMini.png")));
        buttonDrewnoIkona.setPreferredSize(new Dimension(30,30));
        buduj.add(buttonDrewnoIkona); 
        //buttonDrewno.setText("Lad");        
        buduj.add(buttonDrewno);        
       
        buttonGlinaIkona = new JLabel(new ImageIcon(this.getClass().getResource("glinaMini.png")));
        buttonGlinaIkona.setPreferredSize(new Dimension(30,30));
        buduj.add(buttonGlinaIkona);        
        //buttonGlina.setText("Lad");
        buduj.add(buttonGlina);        
        
        buttonZelazoIkona = new JLabel(new ImageIcon(this.getClass().getResource("zelazoMini.png")));
        buttonZelazoIkona.setPreferredSize(new Dimension(30,30));
        buduj.add(buttonZelazoIkona);       
        //buttonZelazo.setText("Lad");
        buduj.add(buttonZelazo);        
        
        buttonZbozeIkona = new JLabel(new ImageIcon(this.getClass().getResource("zbozeMini.png")));
        buttonZbozeIkona.setPreferredSize(new Dimension(30,30));
        buduj.add(buttonZbozeIkona);        
        //buttonZboze.setText("Lad");
        buduj.add(buttonZboze);
        add(buduj);        
        setVisible(true);
    }
    class Powrot implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Surowce.this);
            parent.getContentPane().removeAll();            
            parent.add(new PolaSurowcow(wyslij, odbierz,linia));
            parent.validate();
            parent.repaint();
            //Thread.currentThread().stop();
            //wat.stop();
        }        
    }
    class Watek implements Runnable{        
        String linia="";
        @Override
        public void run() {            
            wyslij.println("IleKosztuje@"+rodzaj+"@"+(poziom+1));//Ile kosztuje dany poziom pola
            wyslij.flush();            
            try{                
                while((linia=odbierz.readLine())!=null){
                    String [] tmp = linia.split("@");
                    //System.out.println(linia);
                    //System.out.println(tmp[0]);
                    //System.out.println(tmp[1]);
                    if(tmp[0].equals("Drewno")||tmp[0].equals("Glina")||tmp[0].equals("Zelazo")||tmp[0].equals("Zboze")){
                        //System.out.println("Tutaj");
                        buttonDrewno.setText(tmp[1]);
                        buttonGlina.setText(tmp[2]);
                        buttonZelazo.setText(tmp[3]);
                        buttonZboze.setText(tmp[4]);
                        wyslij.println("Informacja@"+rodzaj+"@"+poziom.toString());//Pyta sie o cala linijke z bazy - opis itp
                        wyslij.flush();
                    }
                    //System.out.println("Za");
                    if(tmp[0].equals("Opis")){
                        opis.setText("<html>"+tmp[1]+"</html>");
                    }
                    if(tmp.length > 55 ){
                        Surowce.this.linia = linia;
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
    class Buduj implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer drewno1 = Integer.parseInt(drewnoIlosc.getText());
            Integer drewno2 = Integer.parseInt(buttonDrewno.getText());
            Integer drewno = drewno1 - drewno2;
            Integer glina1 = Integer.parseInt(glinaIlosc.getText());
            Integer glina2 = Integer.parseInt(buttonGlina.getText());
            Integer glina = glina1 - glina2;
            Integer zelazo1 = Integer.parseInt(zelazoIlosc.getText());
            Integer zelazo2 = Integer.parseInt(buttonZelazo.getText());
            Integer zelazo = zelazo1 - zelazo2;
            Integer zboze1 = Integer.parseInt(zbozeIlosc.getText());
            Integer zboze2 = Integer.parseInt(buttonZboze.getText());
            Integer zboze = zboze1 - zboze2;
            //System.out.println(drewno +" "+glina+" "+zelazo+" "+zboze);
            if(drewno<0||glina<0||zelazo<0||zboze<0){
                JOptionPane.showMessageDialog(null,"Nie masz wystarczającej ilości surowców");
            }
            else{
                //System.out.println("Za else");
                drewnoIlosc.setText(drewno.toString());
                glinaIlosc.setText(glina.toString());
                zelazoIlosc.setText(zelazo.toString());
                zbozeIlosc.setText(zboze.toString());
                String tmp = "Upload@"+drewnoIlosc.getText()+"@"+glinaIlosc.getText()+"@"+zelazoIlosc.getText()+"@"+
                        zbozeIlosc.getText()+"@"+rodzaj+(poziom+1)+"@"+pozycja;
                //System.out.println(tmp);
                wyslij.println(tmp);
                wyslij.flush();
                try{
                    Thread.sleep(200);
                }
                catch(InterruptedException e1){
                    e1.printStackTrace();
                }
                parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Surowce.this);
                parent.getContentPane().removeAll();            
                parent.add(new PolaSurowcow(wyslij, odbierz,linia));
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
