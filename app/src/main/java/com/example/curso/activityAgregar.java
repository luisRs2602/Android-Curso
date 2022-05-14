package com.example.curso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class activityAgregar extends AppCompatActivity {
   private EditText edtName,edtApe,edtMail,edtUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        edtName=(EditText)findViewById(R.id.edt_name);
        edtApe=(EditText)findViewById(R.id.edt_ape);
        edtMail=(EditText)findViewById(R.id.edt_mail);
        edtUser=(EditText)findViewById(R.id.edt_user);
        String dato=getIntent().getStringExtra("dato");
        edtUser.setText(dato);

    }
    public void agregarUser(View vw){
        String nombre=edtName.getText().toString();
        String apellido=edtApe.getText().toString();
        String mail=edtMail.getText().toString();
        String usuario=edtUser.getText().toString();
        if (nombre.trim().isEmpty()){
            Toast.makeText(this,"Ingresa el nombre",Toast.LENGTH_SHORT).show();
            edtName.requestFocus();
        }else if(apellido.trim().isEmpty()){
            Toast.makeText(this,"Ingresa un apellido",Toast.LENGTH_SHORT).show();
            edtApe.requestFocus();
        }else if (mail.trim().isEmpty()){
            Toast.makeText(this,"Ingresa un mail",Toast.LENGTH_SHORT).show();
            edtMail.requestFocus();
        }else if (usuario.trim().isEmpty()){
            Toast.makeText(this,"Ingresa tu usuario",Toast.LENGTH_SHORT).show();
        }else{

            ejecutarServicio("http://192.168.1.73/webServices/insertForm.php",nombre,apellido,mail,usuario);
        }

    }
    public void ejecutarServicio(String url,String name,String ape,String mail,String user){
        Toast.makeText(this,"metodoo",Toast.LENGTH_SHORT).show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operación exitosa", Toast.LENGTH_SHORT).show();
                resetEdits();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
               // parametros.put("name",edtName.getText().toString());
                parametros.put("name",name);
                parametros.put("apellido",ape);
                parametros.put("mail",mail);
                parametros.put("user",user);
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);// stringRequest= instancia de clase StringRequest

    }
    public void resetEdits(){
        edtName.getText().clear();
        edtApe.getText().clear();
        edtMail.getText().clear();
        edtUser.getText().clear();
    }
    public void cancelar(View v){
        resetEdits();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
