package com.example.semna520;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private TextView coordenadas;

    //establecer conexion con el servidor


    private Button a,ab,iz,d,color;
    private int movInt;

    private BufferedWriter bfw;
    private BufferedReader bfr;
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        a = findViewById(R.id.aButton);
        ab = findViewById(R.id.abButton);
        iz = findViewById(R.id.iButton);
        d = findViewById(R.id.dButton);
        color = findViewById(R.id.colorButton);

        movInt=5;
        String movString = String.valueOf(movInt);



      iniciarConexion();

        a.setOnClickListener(
                (v)->{
                    int id=1;
                    String idString = String.valueOf(id);
                    enviarMensaje(movString+":"+idString);
                    Toast.makeText(this, "se envio "+ movInt , Toast.LENGTH_SHORT).show();
                }
        );

        ab.setOnClickListener(
                (v)->{
                    int id=2;
                    String idString = String.valueOf(id);
                    enviarMensaje(movString+":"+idString);
                    Toast.makeText(this, "se envio "+ movInt , Toast.LENGTH_SHORT).show();
                }
        );

        iz.setOnClickListener(
                (v)->{
                    int id=3;
                    String idString = String.valueOf(id);
                    enviarMensaje(movString+":"+idString);
                    Toast.makeText(this, "se envio "+ movInt , Toast.LENGTH_SHORT).show();
                }
        );

        d.setOnClickListener(
                (v)->{
                    int id=4;
                    String idString = String.valueOf(id);
                    enviarMensaje(movString+":"+idString);
                    Toast.makeText(this, "se envio "+ movInt , Toast.LENGTH_SHORT).show();
                }
        );

        color.setOnClickListener(
                (v)->{
                    int id=5;
                    String idString = String.valueOf(id);movInt=0;
                    enviarMensaje(movString+":"+idString);
                    Toast.makeText(this, "se envio "+ movInt , Toast.LENGTH_SHORT).show();
                }
        );


    }

    public void iniciarConexion(){
        new Thread(() -> {
            try {

                socket =new Socket("192.168.1.53",9000);

                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                bfr= new BufferedReader(isr);

                OutputStream os= socket.getOutputStream();
                OutputStreamWriter osw= new OutputStreamWriter(os);
                bfw= new BufferedWriter(osw);

                while(true){
                    System.out.println("esperando mensaje");
                    String line= bfr.readLine();
                    System.out.println("Mensaje recibido"+ line);
                }

            }  catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void enviarMensaje(String mensaje){


        new Thread(
                ()-> {
                    try {
                        bfw.write(mensaje+ "\n");
                        bfw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } ).start();

    }
}


