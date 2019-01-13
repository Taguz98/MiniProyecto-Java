package controlador;

import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class PanelCTR {
    
      //Metodo para cambiar de paneles en nuestra ventana principal
    public static void cambioPanel(JPanel pnlPadre, JPanel pnlHijo){
        pnlPadre.removeAll(); 
        pnlPadre.repaint();
        pnlPadre.revalidate();
        
        pnlPadre.add(pnlHijo); 
        pnlPadre.repaint(); 
        pnlPadre.revalidate();   
    }
    
    
}
