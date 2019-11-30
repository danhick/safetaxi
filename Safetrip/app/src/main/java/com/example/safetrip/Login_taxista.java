package com.example.safetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Login_taxista extends AppCompatActivity implements View.OnClickListener {
    EditText usuario,contra;
    Button inicia,registar;
    String user,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_taxista);
        usuario=findViewById(R.id.edttaxista);
        contra=findViewById(R.id.edtPasswordtaxi);
        inicia=findViewById(R.id.btnLogintaxita);
        registar=findViewById(R.id.btregistrotaxista);

        inicia.setOnClickListener(this);
        registar.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (inicia.isPressed()){
            user=usuario.getText().toString();
            password=contra.getText().toString();
            if (!user.isEmpty() && !password.isEmpty()){
                validarusuario("https://safetaxi.webcindario.com/validar_taxista.php");
            }else{
                Toast.makeText(Login_taxista.this, "No se permite campos vacios", Toast.LENGTH_SHORT).show();

            }



        }
        if (registar.isPressed()){
            Intent registro = new Intent(Login_taxista.this,Registro_taxista.class);
            startActivity(registro);
            finish();
        }
    }

    private void validarusuario(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.isEmpty()) {
                            Intent intent= new Intent(getApplicationContext(),Inicio_Chofer.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login_taxista.this, "Usuario y contraseña son incorrecta", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login_taxista.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("numerotaxi",usuario.getText().toString());
                parametros.put("contra",contra.getText().toString());
                return parametros;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
