package controlador;

import controlador.usuario.RegistroCTR;
import modelo.usuario.UsuarioDB;
import vista.InternalFrame;
import vista.Prueba;
import vista.VtnFrm;
import vista.VtnPrincipal;
import vista.usuario.UsuarioFrm;

/**
 *
 * @author Usuario
 */
public class VtnPrincipalCTR {
    private final UsuarioDB user; 
    private final VtnPrincipal vtnPrin;  

    public VtnPrincipalCTR(UsuarioDB user, VtnPrincipal vtnPrin) {
        this.user = user;
        this.vtnPrin = vtnPrin;
        
        vtnPrin.setVisible(true); 
    }
    
    public void iniciar(){ 
        System.out.println("Iniciamos la ventana prinicupal"); 
        
        vtnPrin.getBtnPerfil().addActionListener(e -> perfil()); 
        
        vtnPrin.getBtnPersona().addActionListener(e -> agregar());
        vtnPrin.getBtnCaja().addActionListener(e -> caja());
    } 
    
    public void perfil(){
        VtnFrm vtnFrm = new VtnFrm(); 
        UsuarioFrm usuarioFrm = new UsuarioFrm(); 
        
        RegistroCTR rg = new RegistroCTR(vtnFrm, usuarioFrm); 
        rg.iniciar(); 
        rg.medidasLblFoto();
        rg.editar(user); 
    }
    
    public void agregar(){
        
        System.out.println("Agregamos al panel principal");
        Prueba pb = new Prueba(); 
        
        vtnPrin.getDpnlPrincipal().add(pb); 
        pb.show();
    }
    
    public void caja(){ 
        System.out.println("Agregamos caja ");
        InternalFrame inf = new InternalFrame(); 
        vtnPrin.getDpnlPrincipal().add(inf); 
        inf.setResizable(true); 
        inf.show();
        
        
    }
    
}
