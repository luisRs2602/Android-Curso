package com.example.curso;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BlocNotas extends AppCompatActivity {
    private EditText edt_notas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloc_notas);
        edt_notas=(EditText)findViewById(R.id.edt_notas);
        String archivos[]=fileList();
        if (ArchivoExiste(archivos,"notas.txt")){
            try {
                InputStreamReader archivo=new InputStreamReader(openFileInput("notas.txt"));
                BufferedReader br=new BufferedReader(archivo);
                String linea=br.readLine();
                String strNotas="";
                while (linea!=null){
                    strNotas=strNotas+linea+"\n";
                    linea=br.readLine();
                }
                br.close();
                archivo.close();
                edt_notas.setText(strNotas);


            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private boolean ArchivoExiste(String[] archivos, String nombreArchivo) {
        for (int i = 0; i <archivos.length ; i++)
            if (nombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }
    public void guardar(View v){
        try {
            OutputStreamWriter archivo=new OutputStreamWriter(openFileOutput("notas.txt", Activity.MODE_PRIVATE));
            archivo.write(edt_notas.getText().toString());
            archivo.flush();
            archivo.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this,"SE guardó correctamente",Toast.LENGTH_LONG).show();



    }
    public void guardarSD(View v){
        String nombre="ejemplo";
        String notas=edt_notas.getText().toString();
        try {
            File tarjetaSd= Environment.getExternalStorageDirectory();
            Toast.makeText(this,tarjetaSd.getPath(),Toast.LENGTH_SHORT).show();
            File rutaArchivo=new File(tarjetaSd.getPath(), nombre);
            OutputStreamWriter crearArch=new OutputStreamWriter(openFileOutput(nombre,Activity.MODE_PRIVATE));
            crearArch.write(notas);
            crearArch.flush();
            crearArch.close();
        } catch (IOException e) {
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }


    }
}