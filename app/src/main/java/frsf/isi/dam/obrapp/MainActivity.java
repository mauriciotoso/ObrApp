package frsf.isi.dam.obrapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button btnNuevaObra;
    Button btnListaObras;
    Button btnMapas;
    Button btnSalir;
    Button btnSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNuevaObra = (Button) findViewById(R.id.btnFormObra);
        btnListaObras = (Button) findViewById(R.id.btnListaObras);
        btnSurface= (Button) findViewById(R.id.btnSurface);
        btnMapas = (Button) findViewById(R.id.btnMapaObras);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        btnNuevaObra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ObraActivity.class);
                startActivity(i);
            }
        });

        btnListaObras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ObraListActivity.class);
                startActivity(i);
            }
        });

        btnSurface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Camara.class);
                startActivity(i);
            }
        });

        btnMapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
