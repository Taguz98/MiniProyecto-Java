package controlador;

import controlador.persona.PersonaCTR;
import controlador.usuario.RegistroCTR;
import modelo.persona.PersonaDB;
import modelo.usuario.UsuarioDB;
import vista.InternalFrame;
import vista.VtnFrm;
import vista.VtnPrincipal;
import vista.persona.PersonaUI;
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
        
        vtnPrin.getBtnPersona().addActionListener(e -> persona());
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
    
    public void persona(){
        System.out.println("Agregamos al panel principal");
        PersonaUI vtnPersona = new PersonaUI(); 
        PersonaDB persona = new PersonaDB(); 
        
        PersonaCTR perCTR = new PersonaCTR(vtnPersona, persona);
        
        perCTR.iniciar();
        vtnPrin.getDpnlPrincipal().add(vtnPersona); 
        vtnPersona.show();
    }
    
    public void caja(){ 
        System.out.println("Agregamos caja ");
        InternalFrame inf = new InternalFrame(); 
        vtnPrin.getDpnlPrincipal().add(inf); 
        inf.setResizable(true); 
        inf.show();
        
        
    }
    
}
