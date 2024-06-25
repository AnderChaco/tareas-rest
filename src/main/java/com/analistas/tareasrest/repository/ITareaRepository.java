package com.analistas.tareasrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analistas.tareasrest.model.Tarea;

public interface ITareaRepository extends JpaRepository<Tarea, Long> {

}
