package modelo.usuario;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import modelo.ConectarDB;

/**
 *
 * @author Usuario
 */
public class UsuarioDB extends UsuarioMD{
    //Nos conectamos a la base de datos 
    ConectarDB conect = new ConectarDB();
    
    public UsuarioDB(){ 
        
    }
    
    public boolean guardar(){
        String nsql = "INSERT INTO public.usuario( \n"
                + "usuario, foto, clave) \n"
                + "VALUES ( '"+getUsuario()+"', '', md5('"+getClave()+"'));";
        if (conect.nosql(nsql) == null) {
            return true; 
        }else{
            System.out.println("No se pudo guardar el usuario "+getUsuario());
            return false; 
        }
    }
    
    public boolean guardarUser(){ 
        String nsql = "INSERT INTO public.usuario (\n"
                + "usuario, foto, clave)\n"
                + "VALUES(?, ?, md5('"+getClave()+"'));";
        
        PreparedStatement ps = conect.sqlPS(nsql); 
        if (ps != null) {
            try {
                ps.setString(1, getUsuario());
                ps.setBinaryStream(2, getFile(), getLogBytes());
                System.out.println(ps);
                ps.execute(); 
                ps.close();
                
                return true; 
            } catch (SQLException ex) {
                System.out.println("No pudimos preparar el statement: "+ex.getMessage());
                return false; 
            }
        }else{
            System.out.println("No se puede guardar al usuario.");
            return false; 
        }
    }
    
    public UsuarioDB consultarUser(String username, String pass){ 
        UsuarioDB user = new UsuarioDB(); 
        InputStream is; 
        
        String sql = "SELECT idusuario, usuario, foto, clave \n"
                + "FROM public.usuario \n"
                + "WHERE usuario ILIKE '%"+username+"%' AND clave = md5('"+pass+"')";
        ResultSet rs = conect.sql(sql); 
        
        try {
            while(rs.next()){
                user.setClave(rs.getString("clave"));
                user.setId(rs.getInt("idusuario"));
                user.setUsuario(rs.getString("usuario")); 
                
                is = rs.getBinaryStream("foto");
                //Pasamos la imagen
                try { 
                    BufferedImage bi = ImageIO.read(is);
                    ImageIcon foto = new ImageIcon(bi); 
                    Image img = foto.getImage(); 
                    user.setFoto(img); 
                } catch (IOException ex) {
                    System.out.println("Error al pasar la foto: "+ex.getMessage());
                }
            }
            
            return user; 
            
        } catch (SQLException e) {
            System.out.println("No se pudo consultar el usuario.");
            return null; 
        }
    }
    
}
