package com.example.safetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Inicio_usuario extends AppCompatActivity  implements View.OnClickListener  {
    EditText ettaxi;
    TextView tvnombre;
    Button btinicarviaje, btcancelar;
    ImageView fotousuario;
    String numtaxi;
    String datosusuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_usuario);
        ettaxi =findViewById(R.id.ettaxi);
        btcancelar=findViewById(R.id.btcancelar);
        btinicarviaje=findViewById(R.id.btiniciartaxi);
        fotousuario=findViewById(R.id.imageView2);
        tvnombre=findViewById(R.id.tvnombre);

        btinicarviaje.setOnClickListener(this);
        btcancelar.setOnClickListener(this);

        Bundle datosusuario = new Bundle();
        datosusuario = getIntent().getExtras();
        datosusuarios= datosusuario.getString("datosusuario");
        try {
            JSONObject obj = new JSONObject(datosusuarios);
            tvnombre.setText(obj.getString("nombre")+" "+obj.getString("apellido"));
            //Toast.makeText(Inicio_usuario.this, obj.getString("nombre"),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(Inicio_usuario.this, "dato enviado no es un json",Toast.LENGTH_LONG).show();
        }



    }
    @Override
    public void onClick(View v) {
        numtaxi=ettaxi.getText().toString();
        if (btinicarviaje.isPressed()){

            if (!numtaxi.isEmpty()) {
                validartaxi("https://safetaxi.webcindario.com/buscartaxi.php");


            }else{
                Toast.makeText(Inicio_usuario.this,"No se puede seguir si el campo del numero del taxi esta vacio",Toast.LENGTH_LONG).show();
            }
        }
        if (btcancelar.isPressed()){
            Intent cancelar = new Intent(Inicio_usuario.this,Login_Usuario.class);
            startActivity(cancelar);
            finish();

        }
    }
    private void validartaxi(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.isEmpty()) {
                            Intent intent= new Intent(getApplicationContext(),Inicio_viaje.class);
                            intent.putExtra("datostaxi",response.toString());

                            //Toast.makeText(Inicio_usuario.this, response.toString(),Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }else{
                            Toast.makeText(Inicio_usuario.this, "Taxi no existe", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Inicio_usuario.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("numerotaxi",ettaxi.getText().toString());

                return parametros;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
