package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable 
{
    boolean activo = true;
    
    @Override
    public void run() 
    {
        Controlador control = new Controlador();
        try 
        {
            System.out.println("Servidor en marcha...");
            ServerSocket sk = new ServerSocket(7);
            while (activo) 
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
                    
                    datos = control.responder(datos);
                    salida.println(datos);
                }
                System.out.println("Respuesta: " + datos);
            }
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
        
        System.out.print("Servidor Apagado");
   }
}