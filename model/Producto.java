package model;

import java.sql.Timestamp;

public class Producto {

    private int idProducto;
    private String nombreProducto;
    private int stock;
    private double precio;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
    private boolean enabled;


    public Producto(int idProducto, String nombreProducto, int stock, double precio,
                    Timestamp created_at, Timestamp updated_at, Timestamp deleted_at, boolean enabled) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.stock = stock;
        this.precio = precio;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.enabled = enabled;
    }


    public Producto(int idProducto, String nombreProducto, int stock, double precio) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.stock = stock;
        this.precio = precio;
        this.enabled = true;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    public Producto(String nombreProducto, int stock, double precio) {
        this.nombreProducto = nombreProducto;
        this.stock = stock;
        this.precio = precio;
        this.enabled = true;
    }

    public Producto() {
    }


    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Timestamp getcreated_at() {
        return created_at;
    }

    public void setcreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getupdated_at() {
        return updated_at;
    }

    public void setupdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Timestamp getdeleted_at() {
        return deleted_at;
    }

    public void setdeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Producto [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", stock=" + stock
                + ", precio=" + precio + "]";
    }
}
