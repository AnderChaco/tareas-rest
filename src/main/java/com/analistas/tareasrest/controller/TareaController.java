package com.analistas.tareasrest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.analistas.tareasrest.model.Tarea;
import com.analistas.tareasrest.service.ITareaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tareas")
@Tag(name = "Tareas", description = "API para gestión de tareas")
public class TareaController {

    private final ITareaService tareaService;

    public TareaController(ITareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    @Operation(summary = "Obtiene todas las tareas")
    public ResponseEntity<List<Tarea>> listarTareas() {
        return ResponseEntity.ok(tareaService.listarTodo());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una tarea por su ID")
    public ResponseEntity<Tarea> obtenerTarea(@PathVariable Long id) {
        return tareaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crea una nueva tarea")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Tarea> crearTarea(@Valid @RequestBody Tarea tarea) throws URISyntaxException {
        Tarea nuevaTarea = tareaService.guardar(tarea);
        return ResponseEntity
                .created(new URI("/api/tareas/" + nuevaTarea.getId()))
                .body(nuevaTarea);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una tarea existente")
    public ResponseEntity<Tarea> actualizarTarea(
            @PathVariable Long id,
            @Valid @RequestBody Tarea tarea) {
        return tareaService.buscarPorId(id)
                .map(tareaExistente -> {
                    tarea.setId(id);
                    return ResponseEntity.ok(tareaService.guardar(tarea));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una tarea")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        return tareaService.buscarPorId(id)
                .map(tarea -> {
                    tareaService.eliminar(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //Marcar la tarea como realizada
    @PutMapping("/{id}/realizar")
    @Operation(summary = "Marca una tarea como realizada")
    public ResponseEntity<Tarea> marcarComoRealizada(@PathVariable Long id) {
        return tareaService.buscarPorId(id)
                .map(tarea -> {
                    tarea.setRealizada(tarea.isRealizada() ? false : true);
                    return ResponseEntity.ok(tareaService.guardar(tarea));
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
