package frsf.isi.dam.obrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import frsf.isi.dam.obrapp.room.ObraDao;
import frsf.isi.dam.obrapp.room.RoomDB;
import frsf.isi.dam.obrapp.modelo.Obra;

public class ObraActivity extends AppCompatActivity {

    Obra obraActual;
    Button btnGuardarObra;
    EditText etNombre;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obra);

        etNombre= (EditText) findViewById(R.id.obraNombre);
        btnGuardarObra = (Button) findViewById(R.id.btnGuardarObra);

        database= RoomDB.getInstance(this);

        btnGuardarObra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(obraActual == null) obraActual = new Obra();
                obraActual.setDescripcion(etNombre.getText().toString());
                new GuardarObra().execute(obraActual);
            }
        });

        if(getIntent().getExtras() != null) {
            this.obraActual = (Obra) getIntent().getExtras().get("obraEditar");
            etNombre.setText(this.obraActual.getDescripcion());
        }
    }

    class GuardarObra extends AsyncTask<Obra, Void, Void> {
        @Override
        protected Void doInBackground(Obra... obras) {
            ObraDao dao = database.obraDao();
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
