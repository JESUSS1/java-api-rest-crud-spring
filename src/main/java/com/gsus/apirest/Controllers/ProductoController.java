package com.gsus.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsus.apirest.Entities.Producto;
import com.gsus.apirest.Repositories.ProductoRepository;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos (){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Integer id){
        return productoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("No se puede encontrar el producto con ID "+id));
    }

    @PostMapping
    public Producto postProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        Producto getProducto = productoRepository.findById(id).
        orElseThrow(()->new RuntimeException("No se puede encontrar el producto a actualizar"));

        getProducto.setNombre(producto.getNombre());
        getProducto.setPrecio(producto.getPrecio());
        getProducto.setDescripcion(producto.getDescripcion());
   
        return productoRepository.save(getProducto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Integer id){
        Producto getProducto = productoRepository.findById(id).
        orElseThrow(()->new RuntimeException("No se puede encontrar el producto a borrar"));

        productoRepository.delete(getProducto);
        return "El producto con Id "+ getProducto.getId()+" fue eliminado";
    }




}
