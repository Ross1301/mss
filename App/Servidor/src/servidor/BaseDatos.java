
package servidor;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDatos 
{
    private Connection conn;
    private final String driver = "com.mysql.jdbc.Driver";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/mss";
    
    private void conectar()
    {
        conn = null;
        try 
        {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, user, password);
        }
        catch (ClassNotFoundException | SQLException e){}
    }
    
    private void desconectar()
    {
        try 
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e){}
    }
    
    public void insertarCliente(String usuario, String pass, int tipo, int maximo, int lleva)
    {
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String insertar = "insert into Cliente values (?,?,?,?,?)";
            
            ps = conn.prepareStatement(insertar);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ps.setInt(3, tipo);
            ps.setInt(4, maximo);
            ps.setInt(5, lleva);
            ps.executeUpdate();
	} 
        catch(SQLException e) {}
        desconectar();
    }
    
    public void insertarClase(int dia, int hora, int minuto, int tipo, int cupo, int cantidad)
    {
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String insertar = "insert into Clase values (?,?,?,?,?,?)";
            
            ps = conn.prepareStatement(insertar);
            ps.setInt(1, dia);
            ps.setInt(2, hora);
            ps.setInt(3, minuto);
            ps.setInt(4, tipo);
            ps.setInt(5, cupo);
            ps.setInt(6, cantidad);
            ps.executeUpdate();
	} 
        catch(SQLException e) {}
        desconectar();
    }
    
    public void insertarEntrena(String usuario, int dia, int hora, int minuto)
    {
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String insertar = "insert into Entrena values (?,?,?,?)";
            
            ps = conn.prepareStatement(insertar);
            ps.setString(1, usuario);
            ps.setInt(2, dia);
            ps.setInt(3, hora);
            ps.setInt(4, minuto);
            ps.executeUpdate();
            
            String actualizar = "update Cliente set Lleva = Lleva + 1 "
                    + "Where Usuario = '"+usuario+"'";
            
            ps = conn.prepareStatement(actualizar);
            ps.executeUpdate();
            
            String actualizar2 = "update Clase set Cantidad = Cantidad + 1 "
                    + "where Dia = "+dia+" and Hora = "+hora+" and Minuto = "+minuto+"";
            
            ps = conn.prepareStatement(actualizar2);
            ps.executeUpdate();
	} 
        catch(SQLException e) {}
        
        desconectar();
    }
    
    public void eliminarEntrena(String usuario, int dia, int hora, int minuto)
    {
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String actualizar = "Delete from Entrena "
                    + "where Usuario = '"+usuario+"'"
                    + "and Dia = "+dia+" and Hora = "+hora+" and Minuto = "+minuto+"";
            
            ps = conn.prepareStatement(actualizar);
            ps.executeUpdate();
            
            String actualizar2 = "update Cliente set Lleva = Lleva - 1 "
                    + "Where Usuario = '"+usuario+"'";
            
            ps = conn.prepareStatement(actualizar2);
            ps.executeUpdate();
            
            String actualizar3 = "update Clase set Cantidad = Cantidad - 1 "
                    + "where Dia = "+dia+" and Hora = "+hora+" and Minuto = "+minuto+"";
            
            ps = conn.prepareStatement(actualizar3);
            ps.executeUpdate();
	} 
        catch(SQLException e) {}
        
        desconectar();
    }
    
    public boolean buscarCliente(String cliente) 
    {
        boolean res = false;
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String consulta = "select * from Cliente Where Usuario = '"+cliente+"'";

            ps  = conn.prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) 
            {
                res = true;
            }

        } 
        catch (SQLException e) {}
        desconectar();
        return res;
    }
    
    public int buscarTipoCliente(String cliente) 
    {
        int res = 0;
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String consulta = "select Tipo from Cliente Where Usuario = '"+cliente+"'";

            ps  = conn.prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) 
            {
                res = rs.getInt("Tipo");
            }

        } 
        catch (SQLException e) {}
        desconectar();
        return res;
    }
    
    public int buscarDisponibleCliente(String cliente) 
    {
        int res = 0;
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String consulta = "select Maximo, Lleva from Cliente Where Usuario = '"+cliente+"'";

            ps  = conn.prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) 
            {
                res = rs.getInt("Maximo") - rs.getInt("Lleva");
            }

        } 
        catch (SQLException e) {}
        desconectar();
        return res;
    }
    
    public int buscarCupoClase(int dia, int hora, int minuto)
    {
        int res = 0;
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String consulta = "select Cupo, Cantidad from Clase Where "
                    + "Dia = "+dia+" and Hora = "+hora+" and Minuto = "+minuto+"";

            ps  = conn.prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) 
            {
                res = rs.getInt("Cupo") - rs.getInt("Cantidad");
            }

        } 
        catch (SQLException e) {}
        desconectar();
        return res;
    }
    
    public String buscarEntreno(int dia, int ini, int fin) 
    {
        String res = "";
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String consulta = "select * from Entrena Where"
                    + " Dia = "+dia+" and Hora between "+ini+" and"
                    + " "+fin+"";

            ps  = conn.prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) 
            {
                String cCliente = rs.getString("Usuario");
                int cDia = rs.getInt("Dia");
                int cHora = rs.getInt("Hora");
                int cMinuto = rs.getInt("Minuto");
                res += cCliente + "/" + cDia + "," + cHora + ":" + cMinuto + "\n";
            }

        } 
        catch (SQLException e) {}
        desconectar();
        return res;
    }
    
    public String buscarClaseTipo(int tipo, int dia, int ini, int fin) 
    {
        String res = "";
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String consulta = "select * from Clase Where Tipo = "+tipo+""
                    + " and Dia = "+dia+" and Hora between "+ini+" and"
                    + " "+fin+"";

            ps  = conn.prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) 
            {
                int cDia = rs.getInt("Dia");
                int cHora = rs.getInt("Hora");
                int cMinuto = rs.getInt("Minuto");
                res += cDia + "," + cHora + ":" + cMinuto + "\n";
            }

        } 
        catch (SQLException e) {}
        desconectar();
        return res;
    }
    
    public String buscarClaseTipo(String cliente, int tipo, int dia, int ini, int fin) 
    {
        String res = "";
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String consulta = "select * from Clase Where Tipo = "+tipo+""
                    + " and Dia = "+dia+" and Hora between "+ini+" and"
                    + " "+fin+""
                    + " and Dia + Hora + Minuto NOT in (SELECT Dia + Hora + Minuto"
                    + " from entrena WHERE Usuario = '"+ cliente +"')";
            
            ps  = conn.prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) 
            {
                int cDia = rs.getInt("Dia");
                int cHora = rs.getInt("Hora");
                int cMinuto = rs.getInt("Minuto");
                res += cDia + "," + cHora + ":" + cMinuto + "\n";
            }

        } 
        catch (SQLException e) {}
        desconectar();
        return res;
    }
    public String buscarReservadas(String cliente, int dia, int ini, int fin) 
    {
        String res = "";
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String consulta = "select * from entrena Where Usuario = '"+cliente+"'"
                    + " and Dia = "+dia+" and Hora between "+ini+" and"
                    + " "+fin+"";

            ps  = conn.prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) 
            {
                int cDia = rs.getInt("Dia");
                int cHora = rs.getInt("Hora");
                int cMinuto = rs.getInt("Minuto");
                res += cDia + "," + cHora + ":" + cMinuto + "\n";
            }

        } 
        catch (SQLException e) {}
        desconectar();
        return res;
    }
    
    public void reinicarServidor()
    {
        conectar();
        PreparedStatement ps = null;
        try 
        {
            String actualizar = "Delete from Entrena ";
            
            ps = conn.prepareStatement(actualizar);
            ps.executeUpdate();
            
            String actualizar2 = "update Cliente set Lleva = 0 ";
            
            ps = conn.prepareStatement(actualizar2);
            ps.executeUpdate();
            
            String actualizar3 = "update Clase set Cantidad = 0 ";
            
            ps = conn.prepareStatement(actualizar3);
            ps.executeUpdate();
	} 
        catch(SQLException e) {}
        
        desconectar();
    }
}
