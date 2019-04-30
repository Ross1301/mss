package com.example.cliente;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements MyEventListener
{
    private Button btnAcceder;
    private Button btnReservar;
    private Button btnRetirar;

    private EditText txtAcceder;
    private EditText txtIP;

    private Spinner spClase;
    private Spinner spReservadas;

    ProgressDialog progressDialog;

    private Context context = this;
    private MyEventListener mel = this;

    private int accion;
    private boolean acceso;

    private ArrayList<String> listDisponible = new ArrayList<String>();
    private ArrayList<String> listReservada = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAcceder = findViewById(R.id.btnAcceder);
        btnReservar = findViewById(R.id.btnReservar);
        btnRetirar = findViewById(R.id.btnRetirar);

        txtAcceder = findViewById(R.id.txtAcceder);
        txtIP = findViewById(R.id.txtIP);

        spClase = findViewById(R.id.spClase);
        spReservadas = findViewById(R.id.spReservadas);

        accion = 0;
        acceso = false;

        btnReservar.setEnabled(false);
        btnRetirar.setEnabled(false);

        btnAcceder.setOnClickListener
        (
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    btnEntrarSalir();
                }
            }
        );

        btnReservar.setOnClickListener
        (
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        btnReservar();
                    }
                }
        );

        btnRetirar.setOnClickListener
        (
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        btnRetirar();
                    }
                }
        );
    }

    @Override
    public void onEventCompleted(String respuesta)
    {
        switch(accion)
        {
            case 1:
            {
                resEntrar(respuesta);
            } break;
            case 2:
            {
                resReservar(respuesta);
            } break;
            case 3:
            {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public void onEventError(String respuesta)
    {
        progressDialog.dismiss();
        Toast.makeText(context, "No se ha podido comunicar con el Servidor, Error", Toast.LENGTH_LONG).show();
    }

    private void cargarSpinner()
    {
        String[] dias;
        dias = new String[]{"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};

        ArrayList<String> disponibles = new ArrayList<String>();

        for (int i = 0; i < listDisponible.size(); i++)
        {
            String dato = listDisponible.get(i);
            String temp = "";
            StringTokenizer tokens2 = new StringTokenizer(dato, ",:");

            temp += dias[Integer.parseInt(tokens2.nextToken())];
            int hora = Integer.parseInt(tokens2.nextToken());
            int minuto = Integer.parseInt(tokens2.nextToken());
            String cMin;
            if (minuto == 0)
                cMin = "00";
            else
                cMin = "" + minuto;
            if (hora > 12) {
                hora -= 12;
                temp += " A las " + hora + ":" + cMin + " pm";
            } else {
                if (hora < 12)
                    temp += " A las " + hora + ":" + cMin + " am";
                else
                    temp += " A las " + hora + ":" + cMin + " md";
            }
            disponibles.add(temp);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, disponibles);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClase.setAdapter(dataAdapter);

        ArrayList<String> reservado = new ArrayList<String>();

        for (int i = 0; i < listReservada.size(); i++)
        {
            String dato = listReservada.get(i);
            String temp = "";
            StringTokenizer tokens2 = new StringTokenizer(dato, ",:");

            temp += dias[Integer.parseInt(tokens2.nextToken())];
            int hora = Integer.parseInt(tokens2.nextToken());
            int minuto = Integer.parseInt(tokens2.nextToken());
            String cMin;
            if (minuto == 0)
                cMin = "00";
            else
                cMin = "" + minuto;
            if (hora > 12) {
                hora -= 12;
                temp += " A las " + hora + ":" + cMin + " pm";
            } else {
                if (hora < 12)
                    temp += " A las " + hora + ":" + cMin + " am";
                else
                    temp += " A las " + hora + ":" + cMin + " md";
            }
            reservado.add(temp);
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, reservado);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spReservadas.setAdapter(dataAdapter2);
    }

    ///////////// Acciones de Botones

    private void btnEntrarSalir()
    {
        if (!acceso)
        {
            if (txtAcceder.getText().toString().length() > 0)
            {
                accion = 1;
                String mensaje = "1/" + txtAcceder.getText().toString();

                String IP = txtIP.getText().toString();
                Comunicacion comuni = new Comunicacion(mel, IP);
                comuni.execute(mensaje);

                progressDialog = new ProgressDialog(context);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setTitle("Conectando con el servidor");
                progressDialog.setMessage("Por favor, espera...");
                progressDialog.show();
            }
            else
            {
                Toast.makeText(context, "Texto en Vacio, Error", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            btnAcceder.setText("Ingresar");
            txtAcceder.setEnabled(true);
            txtIP.setEnabled(true);
            btnReservar.setEnabled(false);
            btnRetirar.setEnabled(false);

            listDisponible.clear();
            listReservada.clear();
            cargarSpinner();

            acceso = false;
        }
    }

    private void btnReservar()
    {
        accion = 2;

        if(!listDisponible.isEmpty())
        {
            String mensaje = "2/" + txtAcceder.getText().toString() + "*" + listDisponible.get(spClase.getSelectedItemPosition());

            String IP = txtIP.getText().toString();
            Comunicacion comuni = new Comunicacion(mel, IP);
            comuni.execute(mensaje);

            progressDialog = new ProgressDialog(context);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle("Realizando Reserva");
            progressDialog.setMessage("Por favor, espera...");
            progressDialog.show();
        }
    }

    private void btnRetirar()
    {
        accion = 3;

        if(!listReservada.isEmpty())
        {
            String dato = listReservada.get(spReservadas.getSelectedItemPosition());

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("HH");
            int aHora = Integer.parseInt(dateFormat.format(date));


            StringTokenizer tokens2 = new StringTokenizer(dato, ",:");
            tokens2.nextToken();
            int cHora = Integer.parseInt(tokens2.nextToken());

            if (aHora > cHora)
            {
                int resto = aHora - 24;
                if ((cHora - resto) <= 4)
                {
                    Toast.makeText(context, "No puede retirar, Faltan Menos de 4 Horas", Toast.LENGTH_LONG).show();
                }
                else
                {
                    String mensaje = "3/" + txtAcceder.getText().toString() + "*" + dato;

                    String IP = txtIP.getText().toString();
                    Comunicacion comuni = new Comunicacion(mel, IP);
                    comuni.execute(mensaje);

                    progressDialog = new ProgressDialog(context);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setTitle("Realizando Reserva");
                    progressDialog.setMessage("Por favor, espera...");
                    progressDialog.show();

                    listDisponible.add(listReservada.get(spReservadas.getSelectedItemPosition()));
                    listReservada.remove(spReservadas.getSelectedItemPosition());
                    cargarSpinner();
                }
            }
            else
            {
                if ((cHora - aHora) <= 4)
                {
                    Toast.makeText(context, "No puede retirar, Faltan Menos de 4 Horas", Toast.LENGTH_LONG).show();
                }
                else
                {
                    String mensaje = "3/" + txtAcceder.getText().toString() + "*" + dato;

                    String IP = txtIP.getText().toString();
                    Comunicacion comuni = new Comunicacion(mel, IP);
                    comuni.execute(mensaje);

                    progressDialog = new ProgressDialog(context);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setTitle("Realizando Reserva");
                    progressDialog.setMessage("Por favor, espera...");
                    progressDialog.show();

                    listDisponible.add(listReservada.get(spReservadas.getSelectedItemPosition()));
                    listReservada.remove(spReservadas.getSelectedItemPosition());
                    cargarSpinner();
                }
            }
        }
    }

    ///////////// Acciones de Respuesta

    private void resEntrar(String respuesta)
    {
        if (respuesta.equals("0"))
        {
            Toast.makeText(context, "Cliente NO encontrado, Error", Toast.LENGTH_LONG).show();
        }
        else
        {
            listDisponible.clear();
            listReservada.clear();

            StringTokenizer tokens = new StringTokenizer(respuesta, "/");

            String disponible = "";
            String reservado = "";

            if (respuesta.startsWith("/"))
            {
                if (tokens.hasMoreTokens())
                    reservado = tokens.nextToken();
            }
            else
            {
                if (tokens.hasMoreTokens())
                    disponible = tokens.nextToken();
                if (tokens.hasMoreTokens())
                    reservado = tokens.nextToken();
            }

            tokens = new StringTokenizer(disponible, "\n");
            while (tokens.hasMoreTokens())
            {
                String dato = tokens.nextToken();
                listDisponible.add(dato);
            }

            tokens = new StringTokenizer(reservado, "\n");
            while (tokens.hasMoreTokens())
            {
                String dato = tokens.nextToken();
                listReservada.add(dato);
            }

            cargarSpinner();

            btnAcceder.setText("Salir");
            txtAcceder.setEnabled(false);
            txtIP.setEnabled(false);
            btnReservar.setEnabled(true);
            btnRetirar.setEnabled(true);

            acceso = true;
        }

        progressDialog.dismiss();
    }

    private void resReservar(String respuesta)
    {
        if (respuesta.equals("0"))
        {
            Toast.makeText(context, "Ya ha cumplido la Semana", Toast.LENGTH_LONG).show();
        }
        else
        {
            if (respuesta.equals("1"))
            {
                Toast.makeText(context, "Clase Llena", Toast.LENGTH_LONG).show();
            }
            else
            {
                listReservada.add(listDisponible.get(spClase.getSelectedItemPosition()));
                listDisponible.remove(spClase.getSelectedItemPosition());
                cargarSpinner();
            }
        }
        progressDialog.dismiss();
    }
}