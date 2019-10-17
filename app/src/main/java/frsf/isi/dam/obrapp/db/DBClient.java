package frsf.isi.dam.obrapp.db;

import android.content.Context;

import androidx.room.Room;

public class DBClient {
    private static DBClient DB = null;

    private ObraAppDB obraDb;

    private DBClient(Context ctx){
        obraDb = Room.databaseBuilder(ctx,
                ObraAppDB.class, "obrapp-db").allowMainThreadQueries().build();
    }

    public synchronized static DBClient getInstance(Context ctx){
        if(DB==null){
            DB = new DBClient(ctx);
        }
        return DB;
    }

    public ObraAppDB getObraDb() {
        return obraDb;
    }
}
