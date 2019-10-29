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
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


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
        btnSurface= (Button) findViewById(R.id.btnSurface);
        btnSurface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Camara.class);
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

        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                actualizarUI(null);
                            }
                        });
            }
        });


        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void actualizarUI(GoogleSignInAccount account ){
        if(account !=null){
            btnNuevaObra.setVisibility(View.VISIBLE);
            btnListaObras.setVisibility(View.VISIBLE);
            btnMapas.setVisibility(View.VISIBLE);
            btnSalir.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.GONE);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(MainActivity.this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
                Log.w("APP_MSG", "personName:" + personName);
                Log.w("APP_MSG", "personGivenName:" + personGivenName);
                Log.w("APP_MSG", "personFamilyName:" + personFamilyName);
                Log.w("APP_MSG", "personEmail:" + personEmail);
                Log.w("APP_MSG", "personId:" + personId);
            }
        } else {
            btnNuevaObra.setVisibility(View.GONE);
            btnListaObras.setVisibility(View.GONE);
            btnMapas.setVisibility(View.GONE);
            btnSalir.setVisibility(View.GONE);
            signInButton.setVisibility(View.VISIBLE);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 999);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        actualizarUI(account);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 999) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            actualizarUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("APP_MSG", "signInResult:failed code=" + e.getStatusCode());
            actualizarUI(null);
        }
    }
}
