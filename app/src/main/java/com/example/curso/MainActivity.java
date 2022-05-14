package com.example.curso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtNum1;
    private EditText edtNum2;
    private TextView tvRes;
    private RadioButton rbt1,rbt2;
    private Spinner spiner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNum1=(EditText)findViewById(R.id.et_num1);
        edtNum2=(EditText)findViewById(R.id.et_num2);
        tvRes=(TextView)findViewById(R.id.tv_res);
        rbt1=(RadioButton)findViewById(R.id.rb_sumar);
        rbt2=(RadioButton)findViewById(R.id.rb_restar);
        SharedPreferences pref=getSharedPreferences("datos", Context.MODE_PRIVATE);
        tvRes.setText(pref.getString("rest",""));

        //spiner=(Spinner)findViewById(R.id.spinner);
        //String [] opciones={"Elige una opción","Sumar","Restar","Multiplicar","Dividir"};
        //ArrayAdapter<String> lista= new ArrayAdapter<String>(this, R.layout.spinner_item_action, opciones);
        //spiner.setAdapter(lista);


    }

    public void calcular(View view){
        String tn1=edtNum1.getText().toString();
        String tn2=edtNum2.getText().toString();
        if (tn1.equals("")){
            Toast.makeText(this, "Inresa un Número", Toast.LENGTH_SHORT).show();
            edtNum1.requestFocus();


        }else if (tn2.equals("")){
            Toast.makeText(this, "Inresa otro número", Toast.LENGTH_SHORT).show();
            edtNum2.requestFocus();

        }else{
            int num1=Integer.parseInt(tn1);
            int num2=Integer.parseInt(tn2);
            int rest=0;
            /*String seleccion=spiner.getSelectedItem().toString();//obtener item seleccionado del spinner
            if (seleccion.equals("Sumar")){
                rest=num1+num2;
            }else if (seleccion.equals("Restar")){
                rest=num1-num2;
            }else if (seleccion.equals("Multiplicar")){
                rest=num1*num2;
            } else if (seleccion.equals("Dividir")){
                if (num2!=0){
                    rest=num1/num2;
                }else{
                    Toast.makeText(this,"No se puede dividir entre 0",Toast.LENGTH_SHORT).show();
                }

            }*/
            if (rbt1.isChecked()){
                rest=num1+num2;
            }else if (rbt2.isChecked()){
                rest=num1-num2;
            }else{
                Toast.makeText(this, "Selecciona una opción", Toast.LENGTH_SHORT).show();

            }


            String result=String.valueOf(rest);
            tvRes.setText(result);
            SharedPreferences pr=getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor obj_editor=pr.edit();
            obj_editor.putString("rest", tvRes.getText().toString());
            obj_editor.commit();

        }
        //RadioGroup.clearCheck(); limpiar radioGroup


    }
    public void navegar(View v){
        Intent in=new Intent(this,ActivityWeb.class);
        startActivity(in);
    }
    public void agregar(View v){
        Toast.makeText(this,"Lammada",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,activityAgregar.class);
        intent.putExtra("dato",edtNum1.getText().toString());
        startActivity(intent);

    }
    public void abrirBloc(View vw){
        Intent intent=new Intent(this,BlocNotas.class);
        startActivity(intent);
    }
    public void abrirBd(View v){
        Intent in=new Intent(this,ActivityProductos.class);
        startActivity(in);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show();
        // La actividad est� a punto de hacerse visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show();
        // La actividad se ha vuelto visible (ahora se "reanuda").
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "OnPause", Toast.LENGTH_SHORT).show();
        // Enfocarse en otra actividad  (esta actividad est� a punto de ser "detenida").
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show();
        // La actividad ya no es visible (ahora est� "detenida")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "OnDestroy", Toast.LENGTH_SHORT).show();
        // La actividad est� a punto de ser destruida.
    }

}