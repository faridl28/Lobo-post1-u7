package com.universidad.productosweb.controller;

import com.universidad.productosweb.model.Producto;
import com.universidad.productosweb.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador MVC para la gestión de productos.
 * Todas las rutas se mapean bajo /productos.
 * Implementa el patrón PRG (Post/Redirect/Get) en las
 * operaciones de escritura para evitar reenvíos del formulario con F5.
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService servicio;

    /**
     * GET /productos
     * Lista todos los productos y los pasa a la vista.
     */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", servicio.obtenerTodos());
        return "productos/lista";
    }

    /**
     * GET /productos/nuevo
     * Muestra el formulario vacío para crear un producto.
     */
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("producto", new Producto()); // objeto vacío que Thymeleaf enlaza
        model.addAttribute("accion", "Crear");
        return "productos/formulario";
    }

    /**
     * GET /productos/editar/{id}
     * Muestra el formulario prellenado para editar un producto existente.
     * Lanza RuntimeException si el ID no existe (Spring mostrará error 500).
     */
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Producto producto = servicio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
        model.addAttribute("producto", producto);
        model.addAttribute("accion", "Editar");
        return "productos/formulario";
    }

    /**
     * POST /productos/guardar
     * Recibe los datos del formulario, guarda (crea o actualiza) y
     * redirige a la lista — esto completa el patrón PRG.
     * @ModelAttribute enlaza automáticamente los campos del form al objeto Producto.
     */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        servicio.guardar(producto);
        return "redirect:/productos"; // PRG: evita reenvío si el usuario presiona F5
    }

    /**
     * GET /productos/eliminar/{id}
     * Elimina el producto y redirige a la lista.
     * (Se usa GET porque la acción viene de un <a href>, no de un <form>)
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return "redirect:/productos";
    }
}
