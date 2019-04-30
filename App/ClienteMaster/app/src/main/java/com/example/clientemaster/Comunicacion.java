package com.example.clientemaster;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Clase para interactuar con el servidor
 * */
class Comunicacion extends AsyncTask<String,Void,String>
{
    private final int SERVERPORT = 7;
    private String ADDRESS = "186.176.162.239";

    private MyEventListener callBack;

    private boolean error;

    public Comunicacion(MyEventListener mel, String IP)
    {
        callBack = mel;
        ADDRESS = IP;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    /**
     * Se conecta al servidor y trata resultado
     * */
    @Override
    protected String doInBackground(String... values)
    {
        String result = "";
        try
        {
            //Se conecta al servidor
            InetAddress serverAddr = InetAddress.getByName(ADDRESS);
            Log.i("I/TCP Client", "Connecting...");
            Socket socket = new Socket(serverAddr, SERVERPORT);
            Log.i("I/TCP Client", "Connected to server");

            //envia peticion de cliente
            Log.i("I/TCP Client", "Send data to server");
            PrintStream output = new PrintStream(socket.getOutputStream());
            String request = values[0];
            output.println(request);

            //recibe respuesta del servidor y formatea a String
            Log.i("I/TCP Client", "Received data to server");
            InputStream stream = socket.getInputStream();
            byte[] lenBytes = new byte[256];
            stream.read(lenBytes,0,256);
            String received = new String(lenBytes,"UTF-8").trim();
            Log.i("I/TCP Client", "Received " + received);
            Log.i("I/TCP Client", "");
            //cierra conexion
            socket.close();
            error = false;
            result = received;
        }
        catch (UnknownHostException ex)
        {
            Log.e("E/TCP Client", "" + ex.getMessage());
            error = true;
            result = ex.getMessage();
        }
        catch (IOException ex)
        {
            Log.e("E/TCP Client", "" + ex.getMessage());
            error = true;
            result = ex.getMessage();
        }
        return result;
    }

    /**
     * Oculta ventana emergente y muestra resultado en pantalla
     * */
    @Override
    protected void onPostExecute(String value)
    {
        if (error)
            callBack.onEventError(value);
        else
            callBack.onEventCompleted(value);
    }
}
