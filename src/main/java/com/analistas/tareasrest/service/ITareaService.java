package com.analistas.tareasrest.service;

import java.util.List;
import java.util.Optional;

import com.analistas.tareasrest.model.Tarea;

public interface ITareaService {

    List<Tarea> listarTodo();
    
    Optional<Tarea> buscarPorId(Long id);
    
    Tarea guardar(Tarea tarea);
    
    void eliminar(Long id);
}
