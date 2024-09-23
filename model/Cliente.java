package model;

import java.sql.Timestamp;

public class Cliente {

    private int idCliente;
    private int dni;
    private String nombreCliente;
    private String email;
    private int telefono;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
    private boolean enabled;


    public Cliente(int idCliente, int dni, String nombreCliente, String email, int telefono,
                   Timestamp created_at, Timestamp updated_at, Timestamp deleted_at, boolean enabled) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombreCliente = nombreCliente;
        this.email = email;
        this.telefono = telefono;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.enabled = enabled;
    }


    public Cliente(int dni, String nombreCliente, String email, int telefono) {
        this.dni = dni;
        this.nombreCliente = nombreCliente;
        this.email = email;
        this.telefono = telefono;
        this.enabled = true;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    public Cliente(int idCliente, int dni, String nombreCliente, String email, int telefono) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombreCliente = nombreCliente;
        this.email = email;
        this.telefono = telefono;
        this.enabled = true;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    public Cliente() {
    }


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
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
        return "Cliente [idCliente=" + idCliente + ", dni=" + dni + ", nombreCliente=" + nombreCliente + ", email=" + email
                + ", telefono=" + telefono + "]";
    }
}
