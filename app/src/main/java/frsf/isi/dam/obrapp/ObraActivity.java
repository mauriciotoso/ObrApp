package frsf.isi.dam.obrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.Guard;

import frsf.isi.dam.obrapp.db.DBClient;
import frsf.isi.dam.obrapp.db.ObraAppDB;
import frsf.isi.dam.obrapp.db.ObraDao;
import frsf.isi.dam.obrapp.modelo.Obra;

public class ObraActivity extends AppCompatActivity {

    Obra obraActual;
    Button btnGuardarObra;
    EditText etNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obra);
        etNombre= (EditText) findViewById(R.id.obraNombre);
        if(getIntent().getExtras() != null) {
            this.obraActual = (Obra) getIntent().getExtras().get("obraEditar");
            etNombre.setText(this.obraActual.getDescripcion());
        }

        btnGuardarObra = (Button) findViewById(R.id.btnGuardarObra);
        btnGuardarObra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(obraActual == null) {
                    obraActual = new Obra();
                }
                obraActual.setDescripcion(etNombre.getText().toString());
                GuardarObra tareaGuardarObra = new GuardarObra();
                tareaGuardarObra.execute(obraActual);
            }
        });
    }

    class GuardarObra extends AsyncTask<Obra, Void, Void> {

        @Override
        protected Void doInBackground(Obra... obras) {
            ObraDao dao = DBClient.getInstance(ObraActivity.this).getObraDb().obraDao();
            if(obras[0].getId() != null && obras[0].getId() >0) {
                dao.actualizar(obras[0]);
            }else {
                dao.insert(obras[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            obraActual = null;
            Intent i = new Intent(ObraActivity.this, ObraListActivity.class);
            startActivity(i);
        }
    }

}
