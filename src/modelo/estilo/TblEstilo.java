package modelo.estilo;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Usuario
 */
public class TblEstilo {
    //Para ocultar las id de la tbl 
    public static void ocultarID(JTable tbl){ 
        TableColumnModel mdColum = tbl.getColumnModel(); 
        
        //Ocultamso la id 
        mdColum.getColumn(0).setPreferredWidth(0);
        mdColum.getColumn(0).setWidth(0);
        mdColum.getColumn(0).setMinWidth(0);
        mdColum.getColumn(0).setMaxWidth(0);
    }
    
    //Para modificar el estilo de los titulos de tbls  
    public static void tituloTbl(JTable tbl){
        JTableHeader tblHead = tbl.getTableHeader(); 
        
        tblHead.setBackground(new Color(65, 87, 108));
        tblHead.setForeground(new Color(255, 255, 255)); 
        tblHead.setOpaque(false);
        tblHead.setFont(new Font("Arial", Font.BOLD, 12));
        //Para que no se pueda reordenar las columnas 
        tblHead.setReorderingAllowed(false); 
        //Para que no se pueda cambiar su longitud  
        tblHead.setResizingAllowed(false); 
        
        //Centramos los titulos de las tablas 
        DefaultTableCellRenderer hedRender = (DefaultTableCellRenderer)tblHead.getDefaultRenderer(); 
        //Centramos los textos
        hedRender.setHorizontalAlignment(JLabel.CENTER);
        //Le pasamos el render a nuestro table head
        tblHead.setDefaultRenderer(hedRender);
    }
    
    //Para cambiar el estilo de letras  
    public static void letrasTbl(JTable tbl){ 
        tbl.setFont(new Font("Arial", Font.PLAIN, 12));
        tbl.setRowHeight(25);
        tbl.setShowVerticalLines(false);
        //tbl.setShowHorizontalLines(false);  
    }
    
}
