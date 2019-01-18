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
import java.util.logging.Level;
import java.util.logging.Logger;
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
//Shaszuj haslo
//I zrob sprawdzanie czy juz taki uzytkownik istnieje
public class Zarejestruj extends JPanel{
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel login;
    private JLabel haslo;
    private JLabel reHaslo;
    private JTextField wprowadzLogin;
    private JPasswordField wprowadzHaslo;
    private JPasswordField wprowadzReHaslo;
    private JButton zarejestruj;
    private BufferedReader odbierz;
    private PrintWriter wyslij;
    private JFrame parent;
    public Zarejestruj(PrintWriter wyslij, BufferedReader odbierz){
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
        
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        haslo = new JLabel("Hasło:");
        haslo.setMaximumSize(new Dimension(150,30));
        wprowadzHaslo = new JPasswordField();
        wprowadzHaslo.setPreferredSize(new Dimension(150,30));
        panel2.add(haslo);
        panel2.add(Box.createRigidArea(new Dimension(30,0)));
        panel2.add(wprowadzHaslo);
        add(panel2);
        
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        reHaslo = new JLabel("Powtórz hasło:");
        reHaslo.setMaximumSize(new Dimension(150,30));
        wprowadzReHaslo = new JPasswordField();
        wprowadzReHaslo.setPreferredSize(new Dimension(150,30));
        panel3.add(reHaslo);
        panel3.add(Box.createRigidArea(new Dimension(30,0)));
        panel3.add(wprowadzReHaslo);
        add(panel3);
        
        add(Box.createRigidArea(new Dimension(0,10)));
        zarejestruj = new JButton("Zarejestruj się");
        zarejestruj.setPreferredSize(new Dimension(150,50));
        zarejestruj.addActionListener(new Rejestr());
        add(zarejestruj);
    }
    class Rejestr implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(wprowadzLogin.getText().equals("")||wprowadzHaslo.getPassword().length==0||wprowadzReHaslo.getPassword().length==0){
                JOptionPane.showMessageDialog(null, "Wprowadź wszystkie wymagane pola!");                
            }
            if(!Arrays.equals(wprowadzHaslo.getPassword(), wprowadzReHaslo.getPassword())){
                JOptionPane.showMessageDialog(null,"Podane hasła muszą być równe");                
            }
            if(wprowadzLogin.getText().length()<4||wprowadzHaslo.getPassword().length<8||wprowadzReHaslo.getPassword().length<8){
                JOptionPane.showMessageDialog(null,"Login musi zawierać co najmniej 4 znaki a hasło co najmniej 8");               
            }
            else{
                try{
                    MessageDigest md = MessageDigest.getInstance("SHA-256"); 
                    byte[] messageDigest = md.digest(Arrays.toString(wprowadzHaslo.getPassword()).getBytes()); 
                    BigInteger no = new BigInteger(1, messageDigest);
                    String hashtext = no.toString(16); 
                    String tmp = "Rejestracja@"+wprowadzLogin.getText()+"@"+hashtext;
                    wyslij.println(tmp);
                    wyslij.flush();                   
                } 
                catch (NoSuchAlgorithmException ex) { 
                    ex.printStackTrace();
                }             
            }
        }        
    }
    class Watek implements Runnable{
        @Override
        public void run() {
            String linia = "";
            try{
                while((linia=odbierz.readLine())!=null){
                    if(linia.equals("Uzytkownik istnieje")){
                        JOptionPane.showMessageDialog(null,"Użytkownik o takiej nazwie już istnieje");                        
                    }
                    if(linia.equals("Wszystko ok")){
                        parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, Zarejestruj.this);
                        parent.getContentPane().removeAll();            
                        parent.add(new Logowanie(wyslij, odbierz));
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
