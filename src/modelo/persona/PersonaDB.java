package modelo.persona;

import modelo.persona.PersonaMD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.ConectarDB;

/**
 *
 * @author Usuario
 */
public class PersonaDB extends PersonaMD {

    //Nos conectamos a la base de datos 
    ConectarDB conect = new ConectarDB();

    public PersonaDB() {

    }

    //Con este metodo guardaremos una persona en nuestra base de datos
    public boolean guardarPersona() {
        String nsql = "INSERT INTO public.persona(\n"
                + "	 cedula, nombre, apellido, telefono, sueldo, fechanacimiento, sexo)\n"
                + "	VALUES ( '" + getCedula() + "', '" + getNombre() + "', "
                + "'" + getApellido() + "', '" + getTelefono() + "', "
                + "" + getSueldo() + ", '" + getFechaNacimiento() + "',"
                + " '" + getSexo() + "');";
        if (conect.nosql(nsql) == null) {
            System.out.println("Se guardo a " + getNombre() + " " + getApellido() + " correctamente.");
            return true;
        } else {
            System.out.println("No se pudo guardar a " + getNombre() + " " + getApellido() + " correctamente.");
            return false;
        }
    }

    //Con este metodo editaremos una persona  
    public boolean editarPersona(String id) {
        String nsql = "UPDATE public.persona\n"
                + "SET nombre= '" + getNombre() + "', apellido='" + getApellido() + "', "
                + "telefono= '" + getTelefono() + "', sueldo=" + getSueldo() + ", "
                + "fechanacimiento='" + getFechaNacimiento() + "', sexo='" + getSexo() + "'\n"
                + "WHERE idpersona = " + id + ";";

        if (conect.nosql(nsql) == null) {
            System.out.println("Se edito a " + getNombre() + " " + getApellido() + " correctamente.");
            return true;
        } else {
            System.out.println("No se pudo editar a " + getNombre() + " " + getApellido() + " correctamente.");
            return false;
        }
    }

    //Con este metodo editaremos una persona  
    public boolean eliminarPersona(String id) {
        String nsql = "UPDATE public.persona\n"
                + "SET eliminado ='true' "
                + "WHERE idpersona = " + id + ";";

        if (conect.nosql(nsql) == null) {
            System.out.println("Se elimino a " + getNombre() + " " + getApellido() + " correctamente.");
            return true;
        } else {
            System.out.println("No se pudo eliminar a " + getNombre() + " " + getApellido() + " correctamente.");
            return false;
        }
    }

    //Con este meotodo consultaremos a una persona
    public PersonaDB consultaPersona(String id) {
        PersonaDB per = new PersonaDB();

        try {
            String sql = "SELECT idpersona, cedula, nombre, apellido, telefono, sueldo, fechanacimiento, sexo\n"
                    + "	FROM public.persona "
                    + "WHERE elminado = 'false' AND idpersona = " + id + ";";
            ResultSet rs = conect.sql(sql);

            while (rs.next()) {
                per.setApellido(rs.getString("apellido"));
                per.setCedula(rs.getString("cedula"));
                per.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                per.setId(rs.getInt("idpersona"));
                per.setNombre(rs.getString("nombre"));
                per.setSexo(rs.getString("sexo").charAt(0));
                per.setSueldo(rs.getDouble("sueldo"));
                per.setTelefono(rs.getString("telefono"));

            }
            //Si todo salio bien retornamos la persona
            return per;
        } catch (SQLException e) {
            System.out.println("An ocurrido un error al consultar una persona. " + e.getMessage());
            //Si ocurre un error retornamos nulo 
            return null;
        }
    }

    //Con este meotodo consultaremos todas las personas de nuestra base de datos  
    public ArrayList<PersonaMD> cargarPersonas() {
        ArrayList<PersonaMD> personas = new ArrayList<>();

        try {
            String sql = "SELECT idpersona, cedula, nombre, apellido, telefono, sueldo, fechanacimiento, sexo\n"
                    + "	FROM public.persona "
                    + "WHERE elminado = 'false' "
                    + "ORDER BY nombre;";
            ResultSet rs = conect.sql(sql);

            if (rs != null) {

                while (rs.next()) {
                    PersonaMD per = new PersonaMD();

                    per.setApellido(rs.getString("apellido"));
                    per.setCedula(rs.getString("cedula"));
                    per.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                    per.setId(rs.getInt("idpersona"));
                    per.setNombre(rs.getString("nombre"));
                    per.setSexo(rs.getString("sexo").charAt(0));
                    per.setSueldo(rs.getDouble("sueldo"));
                    per.setTelefono(rs.getString("telefono"));

                    //Le agregamos a la lista  
                    personas.add(per);
                }
                //Si todo salio bien retornamos la lista de personas
                return personas;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("An ocurrido un error al consultar personas. " + e.getMessage());
            //Si ocurre un error retornamos nulo 
            return null;
        }
    }

    //Para hacer el buscador de personas
    public ArrayList<PersonaMD> cargarPersonas(String aguja) {
        ArrayList<PersonaMD> personas = new ArrayList<>();

        try {
            String sql = "SELECT idpersona, cedula, nombre, apellido, telefono, sueldo, fechanacimiento, sexo\n"
                    + "FROM public.persona  "
                    + "WHERE elminado = 'false' AND  ("
                    + "\"cedula\" ILIKE  '%" + aguja + "%' "
                    + "OR \"nombre\" ILIKE  '%" + aguja + "%'  "
                    + "OR \"apellido\" ILIKE  '%" + aguja + "%' );";

            ResultSet rs = conect.sql(sql);

            while (rs.next()) {
                PersonaMD per = new PersonaMD();

                per.setApellido(rs.getString("apellido"));
                per.setCedula(rs.getString("cedula"));
                per.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                per.setId(rs.getInt("idpersona"));
                per.setNombre(rs.getString("nombre"));
                per.setSexo(rs.getString("sexo").charAt(0));
                per.setSueldo(rs.getDouble("sueldo"));
                per.setTelefono(rs.getString("telefono"));

                //Le agregamos a la lista  
                personas.add(per);
            }
            //Si todo salio bien retornamos la lista de personas
            return personas;
        } catch (SQLException e) {
            System.out.println("An ocurrido un error al consultar personas. " + e.getMessage());
            //Si ocurre un error retornamos nulo 
            return null;
        }
    }

}
