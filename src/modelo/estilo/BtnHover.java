/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.estilo;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author Usuario
 */
public class BtnHover implements MouseListener{
    
    private final JButton btn; 

    public BtnHover(JButton btn) {
        this.btn = btn;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        btn.setBackground(new Color(27, 49, 71));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        btn.setBackground(new Color(66, 88, 110));
    }
    
}
