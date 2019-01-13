package controlador;

import controlador.usuario.RegistroCTR;
import java.util.Arrays;
import modelo.estilo.BtnHover;
import modelo.usuario.UsuarioDB;
import vista.LoginUI;
import vista.VtnFrm;
import vista.VtnPrincipal;
import vista.usuario.UsuarioFrm;

/**
 *
 * @author Usuario
 */
public class LoginCTR {
    private final LoginUI vtnLogin; 

    public LoginCTR(LoginUI vtnLogin) {
        this.vtnLogin = vtnLogin;
        
        vtnLogin.setVisible(true);
    }
    
    public void iniciar(){ 
        //Animaciones a los btns 
        vtnLogin.getBtnEntrar().addMouseListener(new BtnHover(vtnLogin.getBtnEntrar())); 
        
        //Ocultamos los errores 
        ocultarErrores();
        
        vtnLogin.getBtnEntrar().addActionListener(e -> entrar());
        vtnLogin.getBtnRegistrarse().addActionListener(e -> registrarte()); 
    }
    
    public void ocultarErrores(){ 
        vtnLogin.getLblErrorContra().setVisible(false);
        vtnLogin.getLblErrorUsuario().setVisible(false);
    }
    
    public void registrarte(){ 
        VtnFrm vtnFrm = new VtnFrm(); 
        UsuarioFrm usuarioFrm = new UsuarioFrm(); 
        RegistroCTR reg = new RegistroCTR(vtnFrm, usuarioFrm, vtnLogin); 
        reg.iniciar();
    }
    
    public void entrar(){ 
        VtnPrincipal vtnPrin = new VtnPrincipal(); 
        UsuarioDB user = new UsuarioDB(); 
        
        String username = vtnLogin.getTxtUsuario().getText().trim(); 
        String pass = Arrays.toString(vtnLogin.getTxtPass().getPassword()).trim(); 
        user = user.consultarUser(username, pass);
        if ( user != null) {
            VtnPrincipalCTR ctrVtnPrin = new VtnPrincipalCTR(user, vtnPrin); 
            ctrVtnPrin.iniciar();
            //Cerramos esta ventana  
            vtnLogin.dispose();
        }else{
            vtnLogin.getLblErrorContra().setVisible(true);
            System.out.println("Usuario no encontrado");
        }
    }
    
    
    
    
}
