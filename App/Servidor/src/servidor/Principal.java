package servidor;

import java.util.Scanner;
import java.net.*; 
import java.io.*; 
import java.net.InetAddress; 
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal 
{
    private final Encriptador encripta;
    private final BaseDatos bd;
    private final String[] dias;

    public Principal() 
    {
        this.encripta = new Encriptador();
        this.bd = new BaseDatos();
        dias = new String[] {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
    }
    
    public String clave(String m)
    {
        return encripta.cryptWithMD5(m);
    }
    
    public void agregarCliente()
    {
        String cUsuario;
        int cTipo;
        int cMaximo;
        
        Scanner s = new Scanner(System.in);

        System.out.print("Ingrese Nombre de Cliente: ");
        cUsuario = s.next();
        
        System.out.print("Ingrese Tipo de Cliente: ");
        cTipo = Integer.parseInt(s.next());
        
        System.out.print("Ingrese Cantidad Maxima de Clases: ");
        cMaximo = Integer.parseInt(s.next());
        
        bd.insertarCliente(cUsuario, "", cTipo, cMaximo, 0);
    }
    
    public void agregarClase()
    {
        int cDia;
        int cHora;
        int cMin;
        int cTipo;
        
        Scanner s = new Scanner(System.in);

        System.out.print("Ingrese Dia de Clase: ");
        cDia = Integer.parseInt(s.next());
        
        System.out.print("Ingrese Hora de Clase: ");
        cHora = Integer.parseInt(s.next());
        
        System.out.print("Ingrese Minutos de Clase: ");
        cMin = Integer.parseInt(s.next());
        
        System.out.print("Ingrese Tipo de Clase: ");
        cTipo = Integer.parseInt(s.next());
        
        bd.insertarClase(cDia, cHora, cMin, cTipo, 15, 0);
    }
    
    public void buscarCliente()
    {
        String cUsuario;
        
        Scanner s = new Scanner(System.in);

        System.out.print("Ingrese Nombre de Cliente: ");
        cUsuario = s.next();
        
        boolean enco = bd.buscarCliente(cUsuario);
        
        if (enco)
        {
            System.out.println("Usuario encontrado");
        }
        else
        {
            System.out.println("Usuario NO encontrado");
        }
    }
    
    public void verClasesCliente()
    {
        String cUsuario;
        int cTipo;
        
        Date date = new Date();
        
        DateFormat dateFormat = new SimpleDateFormat("HH");
	int cHora = Integer.parseInt(dateFormat.format(date));
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int cDia;
        cDia = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        
        Scanner s = new Scanner(System.in);
        System.out.print("Ingrese Nombre de Cliente: ");
        cUsuario = s.next();
        cTipo = bd.buscarTipoCliente(cUsuario);
        
        System.out.print(cDia);
        
        if ((cHora + 12) > 24 )
        {
            int cIni = cHora + 1;
            int cFin = cHora - 12;
            System.out.println(bd.buscarClaseTipo(cTipo, cDia, cIni, 23));
            System.out.println(bd.buscarClaseTipo(cTipo, (cDia + 1), 0, cFin));
        }
        else
        {
            int cIni = cHora + 1;
            int cFin = cHora + 12;
            System.out.println(bd.buscarClaseTipo(cTipo, cDia, cIni, cFin));
        }
    }
    
    public static void main(String args[])
    {
        IP();
        Principal controlador = new Principal();
        
        Servidor ser = new Servidor();
        Thread hSer = new Thread(ser);
        hSer.start();
        
        BaseDatos bd = new BaseDatos();
        
        
        int option;
        do 
        {
            System.out.println("");
            System.out.println("Servidor");
            System.out.println("1 - Añadir Cliente");
            System.out.println("2 - Añadir Clase");
            System.out.println("3 - Buscar Cliente");
            System.out.println("4 - Buscar Clases para Cliente");
            System.out.println("9 - Salir");
            
            Scanner s = new Scanner(System.in);
            
            System.out.print("Ingrese opcion: ");
            option = Integer.parseInt(s.next());
            
            switch (option)
            {
                case 1:
                {
                    controlador.agregarCliente();
                } break;
                case 2:
                {
                    controlador.agregarClase();
                } break;
                case 3:
                {
                    controlador.buscarCliente();
                } break;
                case 4:
                {
                    controlador.verClasesCliente();
                } break;
            }
        } while (option != 9);
        
        ser.activo = false;
        hSer.stop();
    }
    
    public void probarEn()
    {
        String clave;
        String claveH;
        String prueba;
        String pruebaH;
        
        Scanner s = new Scanner(System.in);
        
        System.out.print("Ingrese 1: ");
        clave = s.next();
        claveH = encripta.cryptWithMD5(clave);
        System.out.println("Hash: " + claveH);
        
        System.out.print("Ingrese 2: ");
        prueba = s.next();
        pruebaH = encripta.cryptWithMD5(prueba);
        System.out.println("Hash: " + pruebaH);
        
        if (claveH.compareTo(pruebaH) == 0)
            System.out.println("Exito");
        else
            System.out.println("Fallo");
    }
    
    public static void IP()
    { 
        // Returns the instance of InetAddress containing 
        // local host name and address 
        InetAddress localhost = null; 
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("System IP Address : " + 
                      (localhost.getHostAddress()).trim()); 
  
        // Find public IP address 
        String systemipaddress = "";
        try
        { 
            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
  
            BufferedReader sc = 
            new BufferedReader(new InputStreamReader(url_name.openStream())); 
  
            // reads system IPAddress 
            systemipaddress = sc.readLine().trim(); 
        } 
        catch (IOException e) 
        { 
            systemipaddress = "Cannot Execute Properly"; 
        } 
        System.out.println("Public IP Address: " + systemipaddress +"\n"); 
    }
}
