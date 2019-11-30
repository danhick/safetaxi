package com.example.safetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Registro_taxista extends AppCompatActivity implements View.OnClickListener{
    EditText etnombre,etapellido,etcorreo,ettelefono,etcontra,etplaca,etmarca,etmodelo,
            etciudad,etnumerotaxi;
    Button guardartaxi,cancelartaxi;
    ImageView fototaxita;
    String nom,apel,corre,telefono,ciudad,placa,numerotaxi,foto,contr,modelo,marca;
    private static String URL_REGIST = "https://safetaxi.webcindario.com/registertaxi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_taxista);
        etnombre=findViewById(R.id.etnombrechofer);
        etapellido=findViewById(R.id.etapellidochofer);
        etcorreo=findViewById(R.id.etcorrechofer);
        ettelefono=findViewById(R.id.ettelefonochofer);
        etcontra=findViewById(R.id.etcontrataxi);
        etplaca=findViewById(R.id.etplaca);
        etmarca=findViewById(R.id.etmarca);
        etmodelo=findViewById(R.id.etmodelo);
        etciudad=findViewById(R.id.etciudad);
        etnumerotaxi=findViewById(R.id.ettaxi);
        fototaxita=findViewById(R.id.imbtaxista);

        guardartaxi=findViewById(R.id.btguardartaxi);
        cancelartaxi=findViewById(R.id.btcancelartaxi);

        guardartaxi.setOnClickListener(this);
        cancelartaxi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        nom=etnombre.getText().toString();
        apel=etapellido.getText().toString();
        corre=etcorreo.getText().toString();
        telefono=ettelefono.getText().toString();
        contr=etcontra.getText().toString();
        numerotaxi=etnumerotaxi.getText().toString();
        placa=etplaca.getText().toString();
        marca=etmarca.getText().toString();
        modelo=etmodelo.getText().toString();
        ciudad=etciudad.getText().toString();
        foto=fototaxita.toString();
        /*final String nom = this.etnombre.getText().toString().trim();
        final String apel = this.etapellido.getText().toString().trim();
        final String corre = this.etcorreo.getText().toString().trim();
        final String telefono = this.ettelefono.getText().toString().trim();
        final String contr = this.etcontra.getText().toString().trim();
        final String numerotaxi = this.etnumerotaxi.getText().toString().trim();
        final String placa = this.etplaca.getText().toString().trim();
        final String marca = this.etmarca.getText().toString().trim();
        final String modelo= this.etmodelo.getText().toString().trim();
        final String ciudad = this.etciudad.getText().toString().trim();
        final String foto = this.fototaxita.toString().trim();*/
        if (guardartaxi.isPressed()){
            if (!nom.isEmpty()&!apel.isEmpty()&!contr.isEmpty()&!telefono.isEmpty()&!contr.isEmpty()&!numerotaxi.isEmpty()&!placa.isEmpty()&
                    !marca.isEmpty()&!modelo.isEmpty()&!ciudad.isEmpty()){

                registtaxi();
                Toast.makeText(Registro_taxista.this, "Registrando", Toast.LENGTH_SHORT).show();
                Intent guar = new Intent(Registro_taxista.this,Login_taxista.class);
                startActivity(guar);
                finish();
            }else{
                Toast.makeText(Registro_taxista.this, "No se permite campos vacios", Toast.LENGTH_SHORT).show();
            }



        }
        if (cancelartaxi.isPressed()){
            Intent registro = new Intent(Registro_taxista.this,Login_taxista.class);
            startActivity(registro);
            finish();
        }
    }
    private void registtaxi(){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")){
                                Toast.makeText(Registro_taxista.this, "Registrando", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(Registro_taxista.this, "Registro de Error" + e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registro_taxista.this, "Registro de Error" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nombre",nom);
                params.put("apellido",apel);
                params.put("correo",corre);
                params.put("telefono",telefono);
                params.put("numerotaxi",numerotaxi);
                params.put("placa",placa);
                params.put("marca",marca);
                params.put("modelo",modelo);
                params.put("ciudad",ciudad);
                params.put("contra",contr);
                params.put("foto",foto);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
