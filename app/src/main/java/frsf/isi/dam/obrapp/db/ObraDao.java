package frsf.isi.dam.obrapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import frsf.isi.dam.obrapp.modelo.Obra;

@Dao
public interface ObraDao {
    @Query("SELECT * FROM obra")
    List<Obra> getAll();

    @Insert
    void insert(Obra obra);

    @Insert
    void insertAll(Obra... obras);

    @Delete
    void delete(Obra obra);

    @Update
    void actualizar(Obra obra);
}
