/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.estilo;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class VtnBorde implements WindowFocusListener{

    private final JPanel vtn; 

    public VtnBorde(JPanel vtn) {
        this.vtn = vtn;
    }
    
    @Override
    public void windowGainedFocus(WindowEvent e) {
        vtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(24, 131, 215), 3));
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        vtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170), 3));
    }
    
    
}
