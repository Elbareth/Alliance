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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author lenovo
 */
//Wprowadz watek od sluchania komunikacji
public class Logowanie extends JPanel{
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel login;
    private JLabel haslo;
    private JTextField wprowadzLogin;
    private JPasswordField wprowadzHaslo;
    private JButton zaloguj;
    private JButton zarejestruj;
    private JLabel tekst;
    private JFrame parent;
    private BufferedReader odbierz;
    private PrintWriter wyslij;
    public Logowanie(PrintWriter wyslij, BufferedReader odbierz){
        this.wyslij = wyslij;
        this.odbierz = odbierz;
        Thread wat = new Thread(new Watek());
        wat.start();
        setSize(500,500);
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        add(Box.createRigidArea(new Dimension(50,50)));
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        login = new JLabel("Login:");
        login.setMaximumSize(new Dimension(150,30));
        wprowadzLogin = new JTextField();
        wprowadzLogin.setPreferredSize(new Dimension(150,30));
        panel1.add(login);
        panel1.add(Box.createRigidArea(new Dimension(30,0)));
        panel1.add(wprowadzLogin);
        add(panel1);
        
        add(Box.createRigidArea(new Dimension(0,10)));
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        haslo = new JLabel("Haslo: ");
        haslo.setMaximumSize(new Dimension(150,30));
        wprowadzHaslo = new JPasswordField();
        wprowadzHaslo.setPreferredSize(new Dimension(150,30));
        panel2.add(haslo);
        panel2.add(Box.createRigidArea(new Dimension(30,0)));
        panel2.add(wprowadzHaslo);
        add(panel2);
        
        add(Box.createRigidArea(new Dimension(0,10)));
        zaloguj = new JButton("Zaloguj się");
        zaloguj.setPreferredSize(new Dimension(150,50));
        zaloguj.addActionListener(new LogIn());
        add(zaloguj);
        
        add(Box.createRigidArea(new Dimension(0,30)));
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        tekst = new JLabel("Nie masz jeszcze u nas konta?");
        tekst.setPreferredSize(new Dimension(200,30));
        zarejestruj = new JButton("Zarejestruj się");
        zarejestruj.setPreferredSize(new Dimension(150,30));
        zarejestruj.addActionListener(new Rejestr());
        panel3.add(tekst);
        panel3.add(Box.createRigidArea(new Dimension(30,0)));
        panel3.add(zarejestruj); 
        add(panel3);
    }
    class Rejestr implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Logowanie.this);
            parent.getContentPane().removeAll();            
            parent.add(new Zarejestruj(wyslij, odbierz));
            parent.validate();
            parent.repaint();
        }        
    }
    class LogIn implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                //Przesyla informacje do serwera a ten do bazy  
                MessageDigest md = MessageDigest.getInstance("SHA-256"); 
                byte[] messageDigest = md.digest(Arrays.toString(wprowadzHaslo.getPassword()).getBytes()); 
                BigInteger no = new BigInteger(1, messageDigest);
                String hashtext = no.toString(16); 
                String tmp = "Logowanie@"+wprowadzLogin.getText()+"@"+hashtext;
                wyslij.println(tmp);
                wyslij.flush();
                wprowadzLogin.setText("");
                wprowadzHaslo.setText("");               
                //Gdy wszystko ok to otwiera sie okienko gry i dostaje od serwera tone info
                //Gdy nie ok wyswietla komunikat
            }
            catch (NoSuchAlgorithmException ex) { 
                ex.printStackTrace();
            }  
        }        
    }
    class Watek implements Runnable{
        @Override
        public void run() {
            String linia ="";
            try{
                while((linia=odbierz.readLine())!=null){
                    if(linia.equals("Brak")){
                        JOptionPane.showMessageDialog(null,"Podałeś zły login lub hasło");
                    }
                    else{
                        parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Logowanie.this);
                        parent.getContentPane().removeAll();            
                        parent.add(new PolaSurowcow(wyslij, odbierz,linia));
                        parent.setSize(1600, 900);
                        parent.validate();
                        parent.repaint();
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }        
    }
}
