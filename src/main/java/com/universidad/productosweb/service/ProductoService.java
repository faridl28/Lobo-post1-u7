package com.universidad.productosweb.service;

import com.universidad.productosweb.model.Producto;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Servicio que simula la capa de persistencia usando un HashMap en memoria.
 * Spring lo gestiona como Singleton (@Service), por lo que los datos
 * persisten durante toda la sesión de la aplicación.
 */
@Service
public class ProductoService {

    // LinkedHashMap mantiene el orden de inserción
    private final Map<Long, Producto> productos = new LinkedHashMap<>();
    private Long contadorId = 1L;

    // Constructor: carga tres productos de ejemplo al arrancar
    public ProductoService() {
        guardar(new Producto(null, "Laptop",   "Laptop 15 pulgadas 16GB RAM", 1299.99));
        guardar(new Producto(null, "Mouse",    "Mouse inalámbrico ergonómico",   29.99));
        guardar(new Producto(null, "Teclado",  "Teclado mecánico TKL",           89.99));
    }

    /** Devuelve todos los productos como lista. */
    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos.values());
    }

    /** Busca un producto por ID; devuelve Optional vacío si no existe. */
    public Optional<Producto> buscarPorId(Long id) {
        return Optional.ofNullable(productos.get(id));
    }

    /**
     * Guarda un producto nuevo (asigna ID) o actualiza uno existente.
     * Regla: si el ID es null → creación; si tiene ID → actualización.
     */
    public Producto guardar(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(contadorId++);
        }
        productos.put(producto.getId(), producto);
        return producto;
    }

    /** Elimina el producto con el ID indicado. */
    public void eliminar(Long id) {
        productos.remove(id);
    }
}
