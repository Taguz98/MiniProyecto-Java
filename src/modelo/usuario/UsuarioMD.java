package modelo.usuario;

import java.awt.Image;
import java.io.FileInputStream;

/**
 *
 * @author Usuario
 */
public class UsuarioMD {
    
    private int id; 
    private String usuario; 
    private String clave; 
    private FileInputStream file; 
    private int logBytes; 
    
    //Para guardar la foto consultada 
    private Image foto; 
    
    public UsuarioMD(){
        
    }

    public UsuarioMD(int id, String usuario, String clave, FileInputStream file, int logBytes) {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
        this.file = file;
        this.logBytes = logBytes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public FileInputStream getFile() {
        return file;
    }

    public void setFile(FileInputStream file) {
        this.file = file;
    }

    public int getLogBytes() {
        return logBytes;
    }

    public void setLogBytes(int logBytes) {
        this.logBytes = logBytes;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }
    
}
