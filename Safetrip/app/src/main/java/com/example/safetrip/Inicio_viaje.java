package com.example.safetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Inicio_viaje extends AppCompatActivity implements View.OnClickListener{
    EditText etnombres,etapellidos,ettelefonos,etplacas,etmarcas,etmodelos,
            etciudads,etnumerotaxis;
    ImageButton compartir,monitorear;
    ImageView fototaxita;
    String datostaxis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_viaje);
        etnombres=findViewById(R.id.etnombconductor);
        etapellidos=findViewById(R.id.etapellconductor);
        ettelefonos=findViewById(R.id.etcontataxi);
        etplacas=findViewById(R.id.etplacataxi);
        etmarcas=findViewById(R.id.etmarcataxi);
        etmodelos=findViewById(R.id.etmodetaxi);
        etciudads=findViewById(R.id.etciudadtaxi);
        etnumerotaxis=findViewById(R.id.etnumtaxi);
        fototaxita=findViewById(R.id.imageView4);

        compartir=findViewById(R.id.btcompartir);
        monitorear=findViewById(R.id.btmonitorear);

        compartir.setOnClickListener(this);
        monitorear.setOnClickListener(this);

        Bundle datotaxi = new Bundle();
        datotaxi = getIntent().getExtras();
        datostaxis= datotaxi.getString("datostaxi");
        try {
            JSONObject obj = new JSONObject(datostaxis);
            etnombres.setText(obj.getString("nombre"));
            etapellidos.setText(obj.getString("apellido"));
            ettelefonos.setText(obj.getString("telefono"));
            etnumerotaxis.setText(obj.getString("numerotaxi"));
            etplacas.setText(obj.getString("placa"));
            etmarcas.setText(obj.getString("marca"));
            etmodelos.setText(obj.getString("modelo"));
            etciudads.setText(obj.getString("ciudad"));
            //Toast.makeText(Inicio_usuario.this, obj.getString("nombre"),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(Inicio_viaje.this, "dato enviado no es un json",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View v) {
        if (compartir.isPressed()){
            compartirApp();


        }
        if (monitorear.isPressed()){
            Intent mo = new Intent(Inicio_viaje.this,Monitoreo_viaje.class);
            startActivity(mo);

        }
    }
    private void compartirApp() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
            String aux = "Hola, te envio los datos del taxi que acabo de tomar:\n"+"Nombre: "
                    +etnombres.getText()+" "+etapellidos.getText()+"\n"+"Telefono: "+ettelefonos.getText()+"\n"+"Numero de Taxi:"+etnumerotaxis.getText()+"\n"+"Numero de placa:"
                    +etplacas.getText()+"\n"+"Marca del Carro: "+etmarcas.getText()+"\n"
                    +"Modelo del Carro: "+etmodelos.getText()+"\n";
            //aux = aux + "espero tu ayuda "+getBaseContext().getPackageName();
            i.putExtra(Intent.EXTRA_TEXT, aux);
            startActivity(i);
        } catch (Exception e) {
        }
    }
}
