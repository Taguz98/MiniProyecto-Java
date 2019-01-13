/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.persona;

import modelo.persona.PersonaDB;
import modelo.estilo.BtnHover;
import modelo.estilo.VtnBorde;
import vista.persona.PersonaElimUI;
import vista.persona.PersonaUI;

/**
 *
 * @author Usuario
 */
public class EliminarCTR {
    
    private final PersonaElimUI elimPersona; 
    private final PersonaUI vtnPersona;
    private PersonaDB persona; 
    private final String id; 
    
    public EliminarCTR(PersonaElimUI elimPersona, PersonaUI vtnPersona, PersonaDB persona, String id){ 
        this.elimPersona = elimPersona; 
        this.vtnPersona = vtnPersona; 
        this.persona = persona; 
        this.id = id;
        
        vtnPersona.setEnabled(false);
        elimPersona.setVisible(true); 
    }
    
    public void iniciar(){
        //Agregamos las animaciones a los btns  
        elimPersona.getBtnCancelar().addMouseListener(new BtnHover(elimPersona.getBtnCancelar()));
        elimPersona.getBtnContinuar().addMouseListener(new BtnHover(elimPersona.getBtnContinuar()));
        
        //Le agregamos los nombres a la ventana  
        persona = persona.consultaPersona(id); 
        elimPersona.getLblMensaje().setText("Se eliminará a "+persona.getNombre()+" "+persona.getApellido()+"."); 
        
        elimPersona.getBtnCancelar().addActionListener(e -> cancelar());
        elimPersona.getBtnContinuar().addActionListener(e -> continuar());
    }
    
    public void continuar(){ 
        if (persona.eliminarPersona(id)) {
            vtnPersona.setEnabled(true);
            vtnPersona.getLblMensaje().setText("Se eliminó a "+persona.getNombre()+" "+persona.getApellido()+", correctamente.");
            elimPersona.dispose();
        }else{
            vtnPersona.getLblMensaje().setText("No se pudo eliminar a "+persona.getNombre()+" "+persona.getApellido()+", correctamente.");
        }
    }
    
    public void cancelar(){ 
        vtnPersona.setEnabled(true); 
        elimPersona.dispose();
    }
    
}
