package model;

import java.sql.Timestamp;
import java.util.Date;

public class Factura {

    private int numeroFactura;
    private int idCliente;
    private int idProducto;
    private Date fecha;
    private int cantidad;
    private double total;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
    private boolean enabled;

    public Factura(int numeroFactura, int idCliente, int idProducto, Date fecha, int cantidad, double total,
                   Timestamp created_at, Timestamp updated_at, Timestamp deleted_at, boolean enabled) {
        this.numeroFactura = numeroFactura;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.enabled = enabled;
    }

    public Factura(int numeroFactura, int idCliente, int idProducto, Date fecha, int cantidad, double total) {
        this.numeroFactura = numeroFactura;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
        this.enabled = true;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    public Factura() {
    }


    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
        return "Factura [numeroFactura=" + numeroFactura + ", idCliente=" + idCliente + ", idProducto=" + idProducto
                + ", fecha=" + fecha + ", cantidad=" + cantidad + ", total=" + total + "]";
    }
}
