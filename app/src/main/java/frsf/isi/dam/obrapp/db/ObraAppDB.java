package frsf.isi.dam.obrapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import frsf.isi.dam.obrapp.modelo.Material;
import frsf.isi.dam.obrapp.modelo.Obra;

@Database(entities = {Obra.class, Material.class}, version = 1)
public abstract class ObraAppDB extends RoomDatabase {
    public abstract ObraDao obraDao();
    public abstract MaterialDao materialDao();
}
