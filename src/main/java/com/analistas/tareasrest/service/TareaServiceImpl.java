package com.analistas.tareasrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.analistas.tareasrest.model.Tarea;
import com.analistas.tareasrest.repository.ITareaRepository;

@Service
public class TareaServiceImpl implements ITareaService {

    private final ITareaRepository tareaRepository;

    public TareaServiceImpl(ITareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarea> listarTodo() {
        return tareaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Tarea> buscarPorId(Long id) {
        return tareaRepository.findById(id);
    }

    @Transactional
    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Transactional
    public void eliminar(Long id) {
        tareaRepository.deleteById(id);
    }
}