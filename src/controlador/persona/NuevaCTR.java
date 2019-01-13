package controlador.persona;

import controlador.PanelCTR;
import java.awt.Color;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import modelo.persona.PersonaDB;
import modelo.estilo.BtnHover;
import modelo.validaciones.Validar;
import vista.persona.PersonaFrm;
import vista.persona.PersonaUI;
import vista.VtnFrm;

/**
 *
 * @author Usuario
 */
public class NuevaCTR {

    private final PersonaFrm frmPersona;
    private final PersonaUI vtnPersona;
    private PersonaDB persona;
    private final VtnFrm vtnFrm;
    private boolean editando = false;
    private String id;

    public NuevaCTR(PersonaFrm frmPersona, PersonaUI vtnPersona, PersonaDB persona, VtnFrm vtnFrm) {
        this.frmPersona = frmPersona;
        this.vtnPersona = vtnPersona;
        this.persona = persona;
        this.vtnFrm = vtnFrm;

        vtnFrm.setVisible(true);
        vtnPersona.setEnabled(false);
        PanelCTR.cambioPanel(vtnFrm.getPnlFrm(), frmPersona); 
    }

    public void iniciar() {
        //Le agregamos las animaciones a las ventanas  
        vtnFrm.getBtnCancelar().addMouseListener(new BtnHover(vtnFrm.getBtnCancelar()));
        vtnFrm.getBtnGuardar().addMouseListener(new BtnHover(vtnFrm.getBtnGuardar()));

        ocultarLbl();

        //Le daremos una accion al boton cancelar
        vtnFrm.getBtnCancelar().addActionListener(e -> cancelar());
        vtnFrm.getBtnGuardar().addActionListener(e -> guardar());
    }

    public void guardar() {
        boolean guardar = true;
        String anio = frmPersona.getTxtAnio().getText().trim();
        String apellido = frmPersona.getTxtApellido().getText().trim();
        String cedula = frmPersona.getTxtCedula().getText().trim();
        String dia = frmPersona.getTxtDia().getText().trim();
        String mes = frmPersona.getTxtMes().getText().trim();
        String nombre = frmPersona.getTxtNombre().getText().trim();
        String sueldo = frmPersona.getTxtSueldo().getText().trim();
        String telefono = frmPersona.getTxtTelefono().getText().trim();
        char sexo = 'n';

        LocalDate HOY = LocalDate.now();

        if (frmPersona.getBtnrFemenino().isSelected()) {
            sexo = 'F';
        } else if (frmPersona.getBtnrMasculino().isSelected()) {
            sexo = 'M';
        } else {
            frmPersona.getLblErrorSexo().setVisible(true);
            guardar = false;
        }

        if (!Validar.esAnio(anio)) {
            guardar = false;
            frmPersona.getLblErrorFechaNac().setVisible(true);
            frmPersona.getTxtAnio().setBorder(BorderFactory.createLineBorder(new Color(170, 0, 0), 1));
        } else if (Integer.parseInt(anio) - 5 > HOY.getYear()) {
            frmPersona.getTxtAnio().setBorder(BorderFactory.createLineBorder(new Color(170, 0, 0), 1));
        } else {
            frmPersona.getTxtAnio().setBorder(BorderFactory.createLineBorder(new Color(171, 173, 179), 1));
        }

        if (!Validar.esLetras(apellido)) {
            guardar = false;
            frmPersona.getLblErrorApellido().setVisible(true);
            frmPersona.getTxtApellido().setBorder(BorderFactory.createLineBorder(new Color(170, 0, 0), 1));
        } else if (apellido.length() > 25) {
            frmPersona.getTxtApellido().setBorder(BorderFactory.createLineBorder(new Color(170, 0, 0), 1));
        } else {
            frmPersona.getTxtApellido().setBorder(BorderFactory.createLineBorder(new Color(171, 173, 179), 1));
        }

        if (!Validar.esCedula(cedula)) {
            guardar = false;
            frmPersona.getLblErrorCedula().setVisible(true);
            frmPersona.getTxtCedula().setBorder(BorderFactory.createLineBorder(new Color(170, 0, 0), 1));
        } else {
            frmPersona.getTxtCedula().setBorder(BorderFactory.createLineBorder(new Color(171, 173, 179), 1));
        }

        if (!Validar.esDia(dia)) {
            guardar = false;
            frmPersona.getLblErrorFechaNac().setVisible(true);
            frmPersona.getTxtDia().setBorder(BorderFactory.createLineBorder(new Color(170, 0, 0), 1));
        } else {
            frmPersona.getTxtDia().setBorder(BorderFactory.createLineBorder(new Color(171, 173, 179), 1));
        }

        if (!Validar.esMes(mes)) {
            guardar = false;
            frmPersona.getLblErrorFechaNac().setVisible(true);
            frmPersona.getTxtMes().setBorder(BorderFactory.createLineBorder(new Color(170, 0, 0), 1));
        } else {
            frmPersona.getTxtMes().setBorder(BorderFactory.createLineBorder(new Color(171, 173, 179), 1));
        }

        if (!Validar.esLetras(nombre)) {
            guardar = false;
            frmPersona.getLblErrorNombre().setVisible(true);
            frmPersona.getTxtNombre().setBorder(BorderFactory.createLineBorder(new Color(170, 0, 0), 1));
        } else if (nombre.length() > 25) {
            frmPersona.getTxtNombre().setBorder(BorderFactory.createLineBorder(new Color(170, 0, 0), 1));
        } else {
            frmPersona.getTxtNombre().setBorder(BorderFactory.createLineBorder(new Color(171, 173, 179), 1));
        }

        if (!Validar.esDinero(sueldo)) {
            guardar = false;
            frmPersona.getLblErrorSueldo().setVisible(true);
            frmPersona.getTxtSueldo().setBorder(BorderFactory.createLineBorder(new Color(170, 0, 0), 1));
        } else {
            frmPersona.getTxtSueldo().setBorder(BorderFactory.createLineBorder(new Color(171, 173, 179), 1));
        }

        if (!Validar.esTelefono(telefono)) {
            guardar = false;
            frmPersona.getLblErrorTelefono().setVisible(true);
            frmPersona.getTxtTelefono().setBorder(BorderFactory.createLineBorder(new Color(170, 0, 0), 1));
        } else {
            frmPersona.getTxtTelefono().setBorder(BorderFactory.createLineBorder(new Color(171, 173, 179), 1));
        }

        LocalDate fn = null;
        if (Validar.esAnio(anio) && Validar.esDia(dia) && Validar.esMes(mes)) {
            try {
                fn = LocalDate.of(Integer.parseInt(anio), Integer.parseInt(mes), Integer.parseInt(dia));
            } catch (NumberFormatException e) {
                System.out.println("No se pudo transformar la fecha");
                guardar = false;
                frmPersona.getLblErrorFechaNac().setVisible(true);
            }
        }

        //Si no existe ningun error guardamos
        if (guardar) {

            if (!editando) {

                persona.setApellido(apellido);
                persona.setCedula(cedula);

                persona.setFechaNacimiento(fn);
                persona.setNombre(nombre);
                persona.setSexo(sexo);
                persona.setSueldo(Double.parseDouble(sueldo));
                persona.setTelefono(telefono);
                //Guardamos la personas en base de datos
                //Si se guardao enviamos el mensaje de guardado y cerramos la ventana 
                if (persona.guardarPersona()) {
                    vtnPersona.setEnabled(true);
                    vtnPersona.getLblMensaje().setText("Se guardó a " + persona.getNombre() + " " + persona.getApellido() + ", correctamente.");
                    vtnFrm.dispose();
                } else {
                    vtnPersona.getLblMensaje().setText("No se pudo guardar a " + persona.getNombre() + " " + persona.getApellido() + ", correctamente.");
                }

            } else if (persona.editarPersona(id)) {
                vtnPersona.setEnabled(true);
                vtnPersona.getLblMensaje().setText("Se editó a " + persona.getNombre() + " " + persona.getApellido() + ", correctamente.");
            } else {
                vtnPersona.getLblMensaje().setText("No se pudo editar a " + persona.getNombre() + " " + persona.getApellido() + ", correctamente.");
            }
        }
    }

    public void ocultarLbl() {
        //Oculatamos todos los lbl de error
        frmPersona.getLblErrorApellido().setVisible(false);
        frmPersona.getLblErrorCedula().setVisible(false);
        frmPersona.getLblErrorFechaNac().setVisible(false);
        frmPersona.getLblErrorNombre().setVisible(false);
        frmPersona.getLblErrorSueldo().setVisible(false);
        frmPersona.getLblErrorTelefono().setVisible(false);
    }

    //Cerraremos la venta del formulario de una persona y habilitaremos la ventana principal
    public void cancelar() {
        vtnPersona.setEnabled(true);
        vtnFrm.dispose();
    }

    //Para editar una persona 
    public void cargarFormulario(String id) {
        this.id = id;
        persona = persona.consultaPersona(id);
        editando = true; 
        if (persona != null) {
            frmPersona.getTxtAnio().setText(persona.getFechaNacimiento().getYear() + "");
            frmPersona.getTxtApellido().setText(persona.getApellido());
            frmPersona.getTxtCedula().setText(persona.getCedula());
            frmPersona.getTxtDia().setText(persona.getFechaNacimiento().getDayOfMonth() + "");
            frmPersona.getTxtMes().setText(persona.getFechaNacimiento().getMonthValue() + "");
            frmPersona.getTxtNombre().setText(persona.getNombre());
            frmPersona.getTxtSueldo().setText(persona.getSueldo() + "");
            frmPersona.getTxtTelefono().setText(persona.getTelefono());

            if (persona.getSexo() == 'F') {
                frmPersona.getBtnrFemenino().setSelected(true);
            } else {
                frmPersona.getBtnrMasculino().setSelected(true);
            }

            //Desabilitamos el campo de cedula porque ese no se puede editar 
            frmPersona.getTxtCedula().setEnabled(false);
        }else{
            vtnFrm.dispose();
            vtnPersona.getLblMensaje().setText("No se puede editar a esta persona.");
        }
    }
}
