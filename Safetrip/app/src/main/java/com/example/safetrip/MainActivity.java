package com.example.safetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btusuario,bttaxista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btusuario = findViewById(R.id.btusuario);
        bttaxista = findViewById(R.id.bttaxista);

        btusuario.setOnClickListener(this);
        bttaxista.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (btusuario.isPressed()){
            Intent usuario = new Intent(MainActivity.this,Login_Usuario.class);
            startActivity(usuario);



        }
        if (bttaxista.isPressed()){
            Intent taxista = new Intent(MainActivity.this,Login_taxista.class);
            startActivity(taxista);




        }
    }
}
