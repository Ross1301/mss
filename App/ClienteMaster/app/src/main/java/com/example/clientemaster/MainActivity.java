package com.example.clientemaster;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements MyEventListener
{
    private Button btnSolicitar;
    private Button btnReinicar;

    private EditText txtRespuesta;

    ProgressDialog progressDialog;

    private Context context = this;
    private MyEventListener mel = this;

    private int accion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSolicitar = findViewById(R.id.btnSolicitar);
        btnReinicar = findViewById(R.id.btnReinicar);

        txtRespuesta = findViewById(R.id.txtRespuesta);

        accion = 0;

        btnSolicitar.setOnClickListener
        (
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        btnSol();
                    }
                }
        );

        btnReinicar.setOnClickListener
        (
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        btnRei();
                    }
                }
        );
    }

    @Override
    public void onEventCompleted(String respuesta)
    {
        switch(accion)
        {
            case 4:
            {
                resSol(respuesta);
            } break;
            case 5:
            {
                progressDialog.dismiss();
            } break;
        }
    }

    @Override
    public void onEventError(String respuesta)
    {
        progressDialog.dismiss();
        Toast.makeText(context, "No se ha podido comunicar con el Servidor, Error", Toast.LENGTH_LONG).show();
    }

    ///////////// Acciones de Botones

    private void btnSol()
    {
        accion = 4;

        String mensaje = "4/";

        Comunicacion comuni = new Comunicacion(mel);
        comuni.execute(mensaje);

        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Conectando con el servidor");
        progressDialog.setMessage("Por favor, espera...");
        progressDialog.show();
    }

    private void btnRei()
    {
        accion = 5;

        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat("HH");
        int cHora = Integer.parseInt(dateFormat.format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int cDia;
        cDia = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        if ((cDia == 0) && (cHora < 17))
        {
            String mensaje = "5/";

            Comunicacion comuni = new Comunicacion(mel);
            comuni.execute(mensaje);

            progressDialog = new ProgressDialog(context);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle("Conectando con el servidor");
            progressDialog.setMessage("Por favor, espera...");
            progressDialog.show();
        }
        else
            Toast.makeText(context, "No se puede Reinicar la semana, Error", Toast.LENGTH_LONG).show();
    }

    ///////////// Acciones de Respuesta

    private void resSol(String respuesta)
    {
        progressDialog.dismiss();

        String[] dias;
        dias = new String[]{"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};

        String salida = "";
        int anteriorH = -1;

        StringTokenizer tokens = new StringTokenizer(respuesta, "\n");

        while (tokens.hasMoreTokens())
        {
            StringTokenizer tokens2 = new StringTokenizer(tokens.nextToken(), "/,:");

            String temp = "";


            String cliente = tokens2.nextToken();
            String cDia = dias[Integer.parseInt(tokens2.nextToken())];
            int hora = Integer.parseInt(tokens2.nextToken());
            int minuto = Integer.parseInt(tokens2.nextToken());

            if (anteriorH != hora)
            {
                temp += cDia;
                String cMin;
                int cHora;

                if (minuto == 0)
                    cMin = "00";
                else
                    cMin = "" + minuto;
                if (hora > 12)
                {
                    cHora = hora - 12;
                    temp += " A las " + cHora + ":" + cMin + " pm";
                }
                else
                {
                    if (hora < 12)
                        temp += " A las " + hora + ":" + cMin + " am";
                    else
                        temp += " A las " + hora + ":" + cMin + " md";
                }
                salida += temp + "\n";
                anteriorH = hora;
            }

            salida += "\t\t" + cliente + "\n";
        }

        txtRespuesta.setText(salida);
    }
}
