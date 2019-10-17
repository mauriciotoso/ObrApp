package frsf.isi.dam.obrapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import frsf.isi.dam.obrapp.modelo.Obra;

public class ObraAdapter extends ArrayAdapter<Obra> {
    Context context;
    public ObraAdapter(Context ctx, List<Obra> lista){
        super(ctx,0,lista);
        this.context = ctx;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return super.getView(position, convertView, parent);
    }
}
