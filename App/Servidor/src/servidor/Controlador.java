package servidor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Controlador 
{
    private BaseDatos bd;
    
    public Controlador()
    {
        bd = new BaseDatos();
    }
    
    private boolean buscarCliente(String cliente)
    {
        return bd.buscarCliente(cliente);
    }
    
    public String responder (String men)
    {
        String respuesta = "0";
        StringTokenizer tokens2 = new StringTokenizer(men, "/");
        int codigo = Integer.parseInt(tokens2.nextToken());
        
        String datos = "";
        if (tokens2.hasMoreTokens())
             datos = tokens2.nextToken();
        
        switch(codigo)
        {
            case 1:
            {
                respuesta = ingresar(datos);
            } break;
            
            case 2:
            {
                respuesta = reservar(datos);
            }break;
            
            case 3:
            {
                respuesta = retirar(datos);
            }break;
            
            case 4:
            {
                respuesta = verClases();
            }break;
            
            case 5:
            {
                respuesta = reinicarServidor();
            }break;
        }
        return respuesta;
    }
    
    public String ingresar (String men)
    {
        String respuesta = "";
        if (bd.buscarCliente(men))
        {
            String cUsuario = men;
            int cTipo;

            Date date = new Date();

            DateFormat dateFormat = new SimpleDateFormat("HH");
            int cHora = Integer.parseInt(dateFormat.format(date));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int cDia;
            cDia = calendar.get(Calendar.DAY_OF_WEEK) - 1;

            cTipo = bd.buscarTipoCliente(cUsuario);

            if ((cHora + 12) > 24 )
            {
                int cIni = cHora + 1;
                int cFin = cHora - 12;
                respuesta += bd.buscarClaseTipo(cUsuario, cTipo, cDia, cIni, 23);
                respuesta += bd.buscarClaseTipo(cUsuario, cTipo, (cDia + 1), 0, cFin);
                respuesta += "/";
                respuesta += bd.buscarReservadas(cUsuario, cDia, cIni, 23);
                respuesta += bd.buscarReservadas(cUsuario, (cDia + 1), 0, cFin);
            }
            else
            {
                int cIni = cHora + 1;
                int cFin = cHora + 12;
                respuesta += bd.buscarClaseTipo(cUsuario, cTipo, cDia, cIni, cFin);
                respuesta += "/";
                respuesta += bd.buscarReservadas(cUsuario, cDia, cIni, cFin);
            }
        }
        else
            respuesta = "0";
        
        return respuesta;
    }
    
    public String reservar (String men)
    {
        String respuesta = "";
        
        StringTokenizer tokens2 = new StringTokenizer(men, "*,:");
        
        String cUsuario = tokens2.nextToken();
        int cDia = Integer.parseInt(tokens2.nextToken());
        int cHora = Integer.parseInt(tokens2.nextToken());
        int cMinuto = Integer.parseInt(tokens2.nextToken());
        
        if (bd.buscarDisponibleCliente(cUsuario) == 0)
        {
            respuesta = "0";
        }
        else
        {
            if (bd.buscarCupoClase(cDia, cHora, cMinuto) == 0)
            {
                respuesta = "1";
            }
            else
            {
                bd.insertarEntrena(cUsuario, cDia, cHora, cMinuto);
                respuesta = "2";
            }
        }
        
        return respuesta;
    }
    
    public String retirar (String men)
    {
        String respuesta = "";
        
        StringTokenizer tokens2 = new StringTokenizer(men, "*,:");
        
        String cUsuario = tokens2.nextToken();
        int cDia = Integer.parseInt(tokens2.nextToken());
        int cHora = Integer.parseInt(tokens2.nextToken());
        int cMinuto = Integer.parseInt(tokens2.nextToken());
        
        bd.eliminarEntrena(cUsuario, cDia, cHora, cMinuto);
        
        respuesta = "2";
        
        return respuesta;
    }
    
    public String verClases()
    {
        String respuesta = "";
        
        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat("HH");
        int cHora = Integer.parseInt(dateFormat.format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int cDia;
        cDia = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        String sRes = "";
        
        if ((cHora + 12) > 24 )
        {
            int cIni = cHora;
            int cFin = cHora - 12;
            respuesta += bd.buscarEntreno(cDia, cIni, 23);
            respuesta += bd.buscarEntreno((cDia + 1), 0, cFin);
        }
        else
        {
            int cIni = cHora;
            int cFin = cHora + 12;
            respuesta += bd.buscarEntreno(cDia, cIni, cFin);
        }
        
        return respuesta;
    }
    
    public String reinicarServidor()
    {
        String respuesta = "";
        
        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat("HH");
        int cHora = Integer.parseInt(dateFormat.format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int cDia;
        cDia = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        if ((cDia == 0) && (cHora < 17))
        {
            bd.reinicarServidor();
        }
        
        return respuesta;
    }
}
