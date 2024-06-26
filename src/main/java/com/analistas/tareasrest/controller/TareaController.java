package com.analistas.tareasrest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analistas.tareasrest.model.Tarea;
import com.analistas.tareasrest.repository.ITareaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    ITareaRepository tareaRepository;

    @GetMapping("/list")
    List<Tarea> listar() {

        return tareaRepository.findAll();
    }

    @GetMapping("/get/{id}")
    ResponseEntity<?> getTarea(@PathVariable Long id) {

        Optional<Tarea> tarea = tareaRepository.findById(id);

        return tarea.map(response -> ResponseEntity.ok().body(response))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/tarea")
    ResponseEntity<Tarea> create(@Valid @RequestBody Tarea tarea) throws URISyntaxException {

        Tarea result = tareaRepository.save(tarea);

        return ResponseEntity.created(new URI("/api/tareas/" + result.getId())).body(result);
    }

}
