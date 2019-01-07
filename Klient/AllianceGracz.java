/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alliancegracz;

import javax.swing.UIManager;

/**
 *
 * @author lenovo
 */
public class AllianceGracz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {            
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");            
            new Wyglad();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
