package frsf.isi.dam.obrapp.modelo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Material {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String descripcion;
    private Long codigo;
    private Double precio;

    public Material() {
    }

    public Material(Integer id, Long codigo, Double precio) {
        this.id = id;
        this.codigo = codigo;
        this.precio = precio;
    }

    public Material(Long codigo, Double precio) {
        this.codigo = codigo;
        this.precio = precio;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "MaterialDTO{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", codigo=" + codigo +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(id, material.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
