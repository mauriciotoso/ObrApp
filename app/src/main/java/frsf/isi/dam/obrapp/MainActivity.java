package frsf.isi.dam.obrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnNuevaObra;
    Button btnListaObras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
}
