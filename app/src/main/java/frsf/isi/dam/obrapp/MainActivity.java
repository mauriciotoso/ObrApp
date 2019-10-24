package frsf.isi.dam.obrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button btnNuevaObra;
    Button btnListaObras;
    Button btnMapas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().getExtras()!=null){
            Set<String> claves = getIntent().getExtras().keySet();
            Log.d("APP_MSG", "RECIBO DATOS EN ACTIVIDAD PRINCIPAL");
            for(String k : claves){
                Log.d("APP_MSG", k + ": "+ getIntent().getExtras().get(k).toString());
            }
        }
        btnNuevaObra = (Button) findViewById(R.id.btnFormObra);
        btnNuevaObra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ObraActivity.class);
                startActivity(i);
            }
        });
        btnListaObras = (Button) findViewById(R.id.btnListaObras);
        btnListaObras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ObraListActivity.class);
                startActivity(i);
            }
        });
        btnMapas = (Button) findViewById(R.id.btnMapaObras);
        btnMapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });
    }
}
