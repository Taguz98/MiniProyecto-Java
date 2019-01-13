package controlador.persona;

import controlador.persona.NuevaCTR;
import controlador.persona.EliminarCTR;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.persona.PersonaDB;
import modelo.persona.PersonaMD;
import modelo.estilo.BtnHover;
import modelo.estilo.TblEstilo;
import vista.persona.PersonaElimUI;
import vista.persona.PersonaFrm;
import vista.persona.PersonaUI;
import vista.VtnFrm;

/**
 *
 * @author Usuario
 */
public class PersonaCTR {
    
    private final PersonaUI vtnPersona;
    private final PersonaDB persona;
    //El modelo de la tabla personas 
    private DefaultTableModel mdTblPersona;
    //Aqui guardamos todas las personas de nuestra base de datos 
    private ArrayList<PersonaMD> personas;
    //Ventana de formularios 
    private VtnFrm vtnFrm = new VtnFrm();    
    
    public PersonaCTR(PersonaUI vtnPersona, PersonaDB persona) {
        this.vtnPersona = vtnPersona;
        this.persona = persona;
        //Mostramos la ventana
        vtnPersona.setVisible(true);
    }
    
    public void iniciar() {
        //Inciamos el modelo de tabla personas 
        String titulo[] = {"id", "Cédula", "Nombre", "Apellido", "Sueldo", "Sexo", "Teléfono", "Fecha N"};
        String datos[][] = {};
        mdTblPersona = new DefaultTableModel(datos, titulo);
        //Le pasamos el modelo a la tabla  
        vtnPersona.getTblPersonas().setModel(mdTblPersona);
        //Cambiamos la anchura de la columna sexo  
        TableColumnModel mdColum = vtnPersona.getTblPersonas().getColumnModel();
        mdColum.getColumn(5).setPreferredWidth(50);
        mdColum.getColumn(5).setWidth(50);
        mdColum.getColumn(5).setMaxWidth(50);
        mdColum.getColumn(5).setMinWidth(50);

        //Ocultamos el id de la tabla  
        TblEstilo.ocultarID(vtnPersona.getTblPersonas());
        //Le pasamos el estilo del titulo de la tabla 
        TblEstilo.tituloTbl(vtnPersona.getTblPersonas());
        //Le pasamos el estilo de las letras de la tabala 
        TblEstilo.letrasTbl(vtnPersona.getTblPersonas());

        //Le pasamos las animaciones a todos los botones 
        vtnPersona.getBtnActualizar().addMouseListener(new BtnHover(vtnPersona.getBtnActualizar()));
        vtnPersona.getBtnEditar().addMouseListener(new BtnHover(vtnPersona.getBtnEditar()));
        vtnPersona.getBtnEliminar().addMouseListener(new BtnHover(vtnPersona.getBtnEliminar()));
        vtnPersona.getBtnNuevo().addMouseListener(new BtnHover(vtnPersona.getBtnNuevo()));

        //Creamos el evento de escucha para el buscados  
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                buscar(vtnPersona.getTxtBuscar().getText());
            }
        };
        //Le asignamos el escucha al txt buscar  
        vtnPersona.getTxtBuscar().addKeyListener(kl);
        vtnPersona.getTxtBuscar().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vtnPersona.getTxtBuscar().setText("");
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                vtnPersona.getTxtBuscar().setText("");
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });

        //Le daremos funcionamiento a los botones  
        vtnPersona.getBtnNuevo().addActionListener(e -> nueva());
        vtnPersona.getBtnEditar().addActionListener(e -> editar());
        vtnPersona.getBtnEliminar().addActionListener(e -> eliminar());
        vtnPersona.getBtnActualizar().addActionListener(e -> cargarPersonas());

        //Le pasamos funcionalidad al combo  
        vtnPersona.getCbFiltro().addActionListener(e -> ordenar());
        
        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                /*
                System.out.println("Se clickeo");
                vtnPersona.getScrollTblPersona().setSize(620, 270);
                vtnPersona.getTblPersonas().setSize(620, 270);
                int fila = vtnPersona.getTblPersonas().getSelectedRow();
                if (fila > 0) {
                    System.out.println("Esta selecionada una fila");
                } else {
                    vtnPersona.getScrollTblPersona().setSize(620, 310);
                    vtnPersona.getTblPersonas().setSize(620, 310);
                }*/
                mostrarBtns();
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                mostrarBtns();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        };

        //Le agregamos un action listener a la tabla para ver que fila seleciono  
        vtnPersona.getTblPersonas().addMouseListener(ml);

        //Ocultamos los botones  
        vtnPersona.getBtnEditar().setVisible(false);
        vtnPersona.getBtnEliminar().setVisible(false);
        
        cargarPersonas();
    }
    
    public void cargarPersonas() {
        personas = persona.cargarPersonas();
        //Cargamos todos los datos a la tabla personas 
        if (personas != null) {
            llenarTblPersonas(personas);
        }
    }
    
    public void buscar(String aguja) {
        personas = persona.cargarPersonas(aguja);
        //Cargamos todos los datos a la tabla 
        if (personas != null) {
            llenarTblPersonas(personas);
        }
    }
    
    public void ordenar() {
        String orden = vtnPersona.getCbFiltro().getSelectedItem().toString();
        
        if (orden.equalsIgnoreCase("nombre")) {
            ordenarPorNombre();
        } else if (orden.equalsIgnoreCase("apellido")) {
            ordenarPorApellido();
        } else if (orden.equalsIgnoreCase("cedula")) {
            ordenarPorCedula();
        }
    }
    
    public void ordenarPorNombre() {
        if (personas != null) {
            //Ordenamos por nombre
            mdTblPersona.setRowCount(0);
            personas.stream().sorted((per1, per2) -> (per1.getNombre().compareTo(per2.getNombre()))).
                    forEach(per -> {
                        Object valores[] = {per.getId(), per.getCedula(), per.getNombre(),
                            per.getApellido(), per.getSueldo(), per.getSexo(), per.getTelefono(),
                            per.getFechaNacimiento()};
                        mdTblPersona.addRow(valores);
                    });
        }
    }
    
    public void ordenarPorApellido() {
        if (personas != null) {
            mdTblPersona.setRowCount(0);
            personas.stream().sorted((per1, per2) -> (per1.getApellido().compareTo(per2.getApellido()))).
                    forEach(per -> {
                        Object valores[] = {per.getId(), per.getCedula(), per.getNombre(),
                            per.getApellido(), per.getSueldo(), per.getSexo(), per.getTelefono(),
                            per.getFechaNacimiento()};
                        mdTblPersona.addRow(valores);
                    });
        }
    }
    
    public void ordenarPorCedula() {
        if (personas != null) {
            mdTblPersona.setRowCount(0);
            personas.stream().sorted((per1, per2) -> (per1.getCedula().compareTo(per2.getCedula()))).
                    forEach(per -> {
                        Object valores[] = {per.getId(), per.getCedula(), per.getNombre(),
                            per.getApellido(), per.getSueldo(), per.getSexo(), per.getTelefono(),
                            per.getFechaNacimiento()};
                        mdTblPersona.addRow(valores);
                    });
        }
    }

    //Con este meotod llenamos la tabla personas
    public void llenarTblPersonas(ArrayList<PersonaMD> personas) {
        mdTblPersona.setRowCount(0);
        for (PersonaMD per : personas) {
            Object valores[] = {per.getId(), per.getCedula(), per.getNombre(),
                per.getApellido(), per.getSueldo(), per.getSexo(), per.getTelefono(),
                per.getFechaNacimiento()};
            mdTblPersona.addRow(valores);
        }
    }

    //Con este metodo iniciaremos el controlador para ingresar una nueva persona
    public void nueva() {
        PersonaFrm frmPer = new PersonaFrm();
        NuevaCTR nv = new NuevaCTR(frmPer, vtnPersona, persona, vtnFrm);
        nv.iniciar();
    }

    //Con este metodo eliminaremos a la persona seleccionada 
    public void eliminar() {
        int fila = vtnPersona.getTblPersonas().getSelectedRow();
        if (fila >= 0) {
            String id = vtnPersona.getTblPersonas().getValueAt(fila, 0).toString();
            PersonaElimUI elimPer = new PersonaElimUI();
            EliminarCTR elm = new EliminarCTR(elimPer, vtnPersona, persona, id);
            elm.iniciar();
        } else {
            vtnPersona.getLblMensaje().setText("No se puede eliminar.");
        }
        
    }

    //Con este metodo editaremos a la persona selecciona
    public void editar() {
        int fila = vtnPersona.getTblPersonas().getSelectedRow();
        
        if (fila >= 0) {
            //Seleciono el valor de id de la fila selecionada
            String id = vtnPersona.getTblPersonas().getValueAt(fila, 0).toString();
            PersonaFrm frmPer = new PersonaFrm();
            NuevaCTR nv = new NuevaCTR(frmPer, vtnPersona, persona, vtnFrm);
            nv.iniciar();
            nv.cargarFormulario(id);
            
        } else {
            vtnPersona.getLblMensaje().setText("No se puede editar.");
        }
    }

    //Mostrar los botones  
    public void mostrarBtns() {
        int fila = vtnPersona.getTblPersonas().getSelectedRow();
        if (fila >= 0) {
            vtnPersona.getBtnEditar().setVisible(true);
            vtnPersona.getBtnEliminar().setVisible(true);
        }
    }
    
}
