package com.example.safetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Calificacion_taxista extends AppCompatActivity implements View.OnClickListener {
    Button btinicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion_taxista);

        btinicio=findViewById(R.id.btinicio);
        btinicio.setOnClickListener(this);




    }
    @Override
    public void onClick(View v) {
        if (btinicio.isPressed()){


        }

    }


}
