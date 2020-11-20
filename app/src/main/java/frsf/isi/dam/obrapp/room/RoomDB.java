package frsf.isi.dam.obrapp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import frsf.isi.dam.obrapp.modelo.Material;
import frsf.isi.dam.obrapp.modelo.Obra;

@Database(entities = {Obra.class, Material.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB database;
    private static String DATABASE_NAME="database";

    public synchronized static RoomDB getInstance(Context context){
        if(database==null) {
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    };

    public abstract ObraDao obraDao();
    public abstract MaterialDao materialDao();

}
