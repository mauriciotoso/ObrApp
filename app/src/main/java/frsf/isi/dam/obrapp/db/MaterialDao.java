package frsf.isi.dam.obrapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import frsf.isi.dam.obrapp.modelo.Material;

@Dao
public interface MaterialDao {
    @Query("SELECT * FROM material")
    List<Material> getAll();

    @Insert
    void insert(Material material);
    
    @Insert
    void insertAll(Material... materials);

    @Delete
    void delete(Material material);

    @Update
    void actualizar(Material material);
}
