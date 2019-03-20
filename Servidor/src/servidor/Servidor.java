/*package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor 
{
    public static void main(String args[]) 
    {
        try 
        {
            System.out.println("Servidor en marcha...");
            ServerSocket sk = new ServerSocket(7);
            while (true) 
            {
                String datos;
                try (Socket cliente = sk.accept()) 
                {
                    BufferedReader entrada = new BufferedReader
                        (new InputStreamReader(cliente.getInputStream()));
                    PrintWriter salida = new PrintWriter(new OutputStreamWriter
                        (cliente.getOutputStream()), true);
                    
                    datos = entrada.readLine();
                    System.out.println("Entrada: " + datos);
                    if (datos.compareToIgnoreCase("Hola") == 0)
                    {
                        datos = "Adios";
                    }
                    salida.println(datos);
                }
                System.out.println("Respuesta:" + datos);
            }
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
   }
}
*/

// Java program to find IP address of your computer 
// java.net.InetAddress class provides method to get 
// IP of any host name 
package servidor;
import java.net.*; 
import java.io.*; 
import java.util.*; 
import java.net.InetAddress; 
  
public class Servidor 
{ 
    public static void main(String args[]) throws Exception 
    { 
        // Returns the instance of InetAddress containing 
        // local host name and address 
        InetAddress localhost = InetAddress.getLocalHost(); 
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
        catch (Exception e) 
        { 
            systemipaddress = "Cannot Execute Properly"; 
        } 
        System.out.println("Public IP Address: " + systemipaddress +"\n"); 
    } 
} 