package DAO;

import java.util.List;

public interface DAO <T> {
    // Creo un nuevo registro
    void crear(T t);
    // Listo todos los registros (que están habilitados)
    List<T> listar();

    // Actualizo un registro existente
    void actualizar(T t);

    // Elimino (lógicamente) un registro por su ID
    void eliminar(int id);

    // Obtengo un registro por su ID
    T obtenerPorId(int id);
}
