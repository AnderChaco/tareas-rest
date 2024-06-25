package com.analistas.tareasrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analistas.tareasrest.model.Tarea;
import com.analistas.tareasrest.repository.ITareaRepository;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    ITareaRepository tareaRepository;

    @GetMapping("/list")
    List<Tarea> listar() {

        return tareaRepository.findAll();
    }

}
