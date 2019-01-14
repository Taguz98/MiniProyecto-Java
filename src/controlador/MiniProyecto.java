package controlador;

import controlador.persona.PersonaCTR;
import modelo.persona.PersonaDB;
import vista.LoginUI;
import vista.persona.PersonaUI;

/**
 *
 * @author Usuario
 */
public class MiniProyecto {

    public static void main(String[] args) {
        estiloVentana();
        
        /*PersonaUI per = new PersonaUI(); 
        PersonaDB personas = new PersonaDB(); 
        
        PersonaCTR ctrPer = new PersonaCTR(per, personas); 
        ctrPer.iniciar();*/
       LoginUI vtnLogin = new LoginUI(); 
        
        LoginCTR login = new LoginCTR(vtnLogin); 
        login.iniciar();
    }
    
    public static void estiloVentana(){
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PersonaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
}
