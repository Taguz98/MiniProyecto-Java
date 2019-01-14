package controlador.usuario;

import controlador.PanelCTR;
import controlador.VtnPrincipalCTR;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.estilo.BtnHover;
import modelo.usuario.UsuarioDB;
import modelo.validaciones.Validar;
import vista.LoginUI;
import vista.VtnFrm;
import vista.VtnPrincipal;
import vista.usuario.UsuarioFrm;

/**
 *
 * @author Usuario
 */
public class RegistroCTR {

    private final VtnFrm vtnFrm;
    private final UsuarioFrm usuarioFrm;
    private final LoginUI vtnLogin;
    //Para la foto 
    private FileInputStream fis = null;
    private int lonBytes = 0;
    private boolean editando = false;
    Image ft;

    public RegistroCTR(VtnFrm vtnFrm, UsuarioFrm usuarioFrm) {
        this.vtnFrm = vtnFrm;
        this.usuarioFrm = usuarioFrm;
        vtnLogin = null;

        vtnFrm.setVisible(true);
        PanelCTR.cambioPanel(vtnFrm.getPnlFrm(), usuarioFrm);
    }

    public RegistroCTR(VtnFrm vtnFrm, UsuarioFrm usuarioFrm, LoginUI vtnLogin) {
        this.vtnFrm = vtnFrm;
        this.usuarioFrm = usuarioFrm;
        this.vtnLogin = vtnLogin;
        //Ocualtamos el login 
        this.vtnLogin.setVisible(false);
        //Mostramos la pantalla de registro
        this.vtnFrm.setVisible(true);
        PanelCTR.cambioPanel(vtnFrm.getPnlFrm(), usuarioFrm);
    }

    public void iniciar() {
        //Ocultamos los errores  
        ocultarErrores();

        //Estilo de los botones  
        usuarioFrm.getBtnSelecionar().addMouseListener(new BtnHover(usuarioFrm.getBtnSelecionar()));

        vtnFrm.getBtnCancelar().addMouseListener(new BtnHover(vtnFrm.getBtnCancelar()));
        vtnFrm.getBtnGuardar().addMouseListener(new BtnHover(vtnFrm.getBtnGuardar()));

        //Agregamos las acciones a los botones 
        usuarioFrm.getBtnSelecionar().addActionListener(e -> seleccionarFoto());
        vtnFrm.getBtnGuardar().addActionListener(e -> guardar());
        vtnFrm.getBtnCancelar().addActionListener(e -> cancelar());

    }

    public void ocultarErrores() {
        usuarioFrm.getLblErrorFoto().setVisible(false);
        usuarioFrm.getLblErrorPass().setVisible(false);
        usuarioFrm.getLblErrorUsuario().setVisible(false);
    }

    public void seleccionarFoto() {
        JFileChooser j = new JFileChooser();
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG && PNG", "jpg", "png");
        j.setFileFilter(filtro);

        int estado = j.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                fis = new FileInputStream(j.getSelectedFile());
                this.lonBytes = (int) j.getSelectedFile().length();
                //Ahora ponemos la imagen de la longitud del lbl 
                try {
                    usuarioFrm.getLblFoto().setIcon(null);
                    ft = ImageIO.read(j.getSelectedFile());

                    Image newft = ft.getScaledInstance(usuarioFrm.getLblFoto().getWidth(),
                            usuarioFrm.getLblFoto().getHeight(), Image.SCALE_SMOOTH);

                    usuarioFrm.getLblFoto().setIcon(new ImageIcon(newft));
                    usuarioFrm.getLblFoto().updateUI();
                } catch (IOException e) {
                    System.out.println("Error al pasar la foto al lbl: " + e.getMessage());
                }

            } catch (FileNotFoundException e) {
                System.out.println("Error al cargar la foto: " + e.getMessage());
            }
        }
    }

    public void guardar() {
        boolean guardar = true;

        String usuario = usuarioFrm.getTxtUsuario().getText().trim();
        String pass = Arrays.toString(usuarioFrm.getTxtPass().getPassword()).trim();

        if (!Validar.esLetras(usuario)) {
            guardar = false;
            usuarioFrm.getLblErrorUsuario().setVisible(true);
        }

        if (pass.length() < 8) {
            guardar = false;
            usuarioFrm.getLblErrorPass().setVisible(true);
        }

        if (fis == null || lonBytes == 0) {
            guardar = false;
            usuarioFrm.getLblErrorFoto().setVisible(true);
        }

        if (guardar) {
            UsuarioDB user = new UsuarioDB();

            user.setClave(pass);
            user.setUsuario(usuario);
            user.setFoto(ft);
            //Para guardar la foto 
            user.setFile(fis);
            user.setLogBytes(lonBytes);

            if (editando) {
                System.out.println("Editando el usuario.");
            } else {
                if (user.guardarUser()) {
                    VtnPrincipal vtnPrin = new VtnPrincipal();
                    VtnPrincipalCTR ctrVtnPrin = new VtnPrincipalCTR(user, vtnPrin);
                    ctrVtnPrin.iniciar();

                    vtnFrm.dispose();
                } else {
                    System.out.println("No se pudo guardar.");
                }
            }

        }
    }

    public void editar(UsuarioDB user) {
        editando = true;
        usuarioFrm.getTxtUsuario().setText(user.getUsuario());

        if (user.getFoto() != null) {
            medidasLblFoto();

            Image img = user.getFoto();
            Image newimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newimg);

            usuarioFrm.getLblFoto().setIcon(newIcon);
        }

        medidasLblFoto();
    }

    public void cancelar() {
        if (editando) {
            vtnFrm.dispose();
        } else {

            vtnLogin.setVisible(true);
            vtnFrm.dispose();
        }
    }

    public void medidasLblFoto() {

        System.out.println("Anchura: " + usuarioFrm.getLblFoto().getWidth());
        System.out.println("Altura: " + usuarioFrm.getLblFoto().getHeight());
    }

}
