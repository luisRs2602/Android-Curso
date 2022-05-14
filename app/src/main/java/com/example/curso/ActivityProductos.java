package com.example.curso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityProductos extends AppCompatActivity {
    private EditText edtCodigo,edtNombre,edtPrecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        edtCodigo=(EditText)findViewById(R.id.edt_codigo);
        edtNombre=(EditText)findViewById(R.id.edt_nombre);
        edtPrecio=(EditText)findViewById(R.id.edt_precio);

    }
    public void guardarProducto(View v){
        String codigo,nombre,strPrecio;
        float precio;
        codigo=edtCodigo.getText().toString();
        nombre=edtNombre.getText().toString();
        strPrecio=edtPrecio.getText().toString();
        if (codigo.equals("")){
            Toast.makeText(this,"Ingresa un código",Toast.LENGTH_SHORT).show();
            edtCodigo.requestFocus();
        }else if (nombre.trim().equals("")){
            Toast.makeText(this,"Ingresa un nombre",Toast.LENGTH_SHORT).show();
            edtNombre.requestFocus();
        }else if (strPrecio.equals("")){
            Toast.makeText(this,"Ingresa un Precio",Toast.LENGTH_SHORT).show();
            edtPrecio.requestFocus();

        }else{
            precio=Float.parseFloat(strPrecio);
            //Toast.makeText(this,"Todo ok"+precio,Toast.LENGTH_SHORT).show();
            AdminSqLiteOpenHelper admin=new AdminSqLiteOpenHelper(this,"administracion",null,1);
            SQLiteDatabase db=admin.getWritableDatabase();
            ContentValues registro=new ContentValues();
            registro.put("codigo",codigo);
            registro.put("nombreProducto",nombre);
            registro.put("precio",precio);
            db.insert("articulos",null,registro);
            db.close();
            edtCodigo.setText("");
            edtNombre.setText("");
            edtPrecio.setText("");
            Toast.makeText(this,"Guardado Correctamente",Toast.LENGTH_SHORT).show();


        }

    }
    public void buscarProducto(View v){
        AdminSqLiteOpenHelper admin=new AdminSqLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String codigo=edtCodigo.getText().toString();
        if (codigo.trim().equals("")){
            Toast.makeText(this,"Ingresa un código",Toast.LENGTH_SHORT).show();
        }else{
            Cursor fila=bd.rawQuery("Select nombreProducto,precio from articulos where codigo="+codigo, null);
            if (fila.moveToFirst()){
                edtNombre.setText(fila.getString(0));
                edtPrecio.setText(fila.getString(1));
                bd.close();
            }else{
                Toast.makeText(this,"No existe el articulo",Toast.LENGTH_SHORT).show();
            }
        }


    }

}