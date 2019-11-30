package com.example.safetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Bitmap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

public class Registro_usuario extends AppCompatActivity implements View.OnClickListener {
    EditText etnombre,etapellido,etcorreo,etedad,etcontra;
    Button btguarusuario,btcancelarusuario;
    ImageButton btcamera,btsubir;
    ImageView fotousuario;
    String nom,apel,corre,edad,foto,contr;
    private static String URL_REGIST = "https://safetaxi.webcindario.com/registerusuario.php";
    String KEY_IMAGEN="foto";
    String KEY_NOMBRE="nombre";
    private int PICK_IMAGE_REQUEST = 1;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        etnombre=findViewById(R.id.etnombreusario);
        etapellido=findViewById(R.id.etapellidousuario);
        etcorreo=findViewById(R.id.etcorreousario);
        etedad=findViewById(R.id.etedadousari);
        etcontra=findViewById(R.id.etcontrausuario);
        btguarusuario=findViewById(R.id.btguarusuario);
        btcancelarusuario=findViewById(R.id.btcanusuario);
        fotousuario=findViewById(R.id.imageView3);
        btcamera = findViewById(R.id.btcamera);
        btsubir=findViewById(R.id.btsubir);

        btguarusuario.setOnClickListener(this);
        btcancelarusuario.setOnClickListener(this);
        btcamera.setOnClickListener(this);
        btsubir.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        nom=etnombre.getText().toString();
        apel=etapellido.getText().toString();
        corre=etcorreo.getText().toString();
        edad=etedad.getText().toString();
        contr=etcontra.getText().toString();
        foto=fotousuario.toString();

        if (btguarusuario.isPressed()){
            if (!nom.isEmpty()&!apel.isEmpty()&!contr.isEmpty()&!corre.isEmpty()&!edad.isEmpty()) {
                /*if (!Patterns.EMAIL_ADDRESS.matcher(corre).matches()){
                    etcorreo.setError("Escriba un correo valido");
                }*/
                final ProgressDialog loading = ProgressDialog.show(this,"Subiendo...","Espere por favor...",false,false);
                regist();
                Intent guarusu = new Intent(Registro_usuario.this, Login_Usuario.class);
                startActivity(guarusu);
                finish();
            }else{
                Toast.makeText(Registro_usuario.this, "No se permite campos vacios", Toast.LENGTH_SHORT).show();
            }

        }
        if (btcancelarusuario.isPressed()){
            Intent registro = new Intent(Registro_usuario.this,Login_Usuario.class);
            startActivity(registro);


        }
        if (btcamera.isPressed()){
            //uploadImage();

        }
        if (btsubir.isPressed()){
          //  showFileChooser();
        }
    }
    private void regist(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")){
                                Toast.makeText(Registro_usuario.this, "Registrando", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(Registro_usuario.this, "Registro de Error" + e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registro_usuario.this, "Registro de Error" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nombre",nom);
                params.put("apellido",apel);
                params.put("correo",corre);
                params.put("edad",edad);
                params.put("contra",contr);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /*
    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imagenBytes = baos.toByteArray();
        String encodedImagen = Base64.encodeToString(imagenBytes,Base64.DEFAULT);
        return encodedImagen;
    }
    private void uploadImage(){
        //Mostrar el diálogo de progreso
        final ProgressDialog loading = ProgressDialog.show(this,"Subiendo...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(Registro_usuario.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(Registro_usuario.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);

                //Obtener el nombre de la imagen


                //Creación de parámetros
                Map<String,String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put(KEY_IMAGEN, imagen);

                //Parámetros de retorno
                return params;
            }
        };

        //Creación de una cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Agregar solicitud a la cola
        requestQueue.add(stringRequest);
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Cómo obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Configuración del mapa de bits en ImageView
                fotousuario.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

}
