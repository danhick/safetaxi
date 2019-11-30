package com.example.safetrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class Monitoreo_viaje extends AppCompatActivity implements View.OnClickListener {
    Button compartir,calificar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoreo_viaje);

        compartir=findViewById(R.id.btcompartirmoni);
        calificar=findViewById(R.id.btcalificar);



       compartir.setOnClickListener(this);
        calificar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if (compartir.isPressed()){
            compartirApp();

        }
        if (calificar.isPressed()){
            Intent cali = new Intent(Monitoreo_viaje.this,Calificacion_taxista.class);
            startActivity(cali);
        }
    }
    private void compartirApp() {
        try {
            Intent a = new Intent(Intent.ACTION_SEND);
            a.setType("text/plain");
            a.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
            String aux = "Te envio mi ubicacion:\n";
            //aux = aux + "espero tu ayuda "+getBaseContext().getPackageName();
            a.putExtra(Intent.EXTRA_TEXT, aux);
            startActivity(a);
        } catch (Exception e) {
        }
    }

}
