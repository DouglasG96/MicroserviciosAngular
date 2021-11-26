package com.curso.microservicios.app.usuarios.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.microservicios.app.usuarios.models.entity.Alumno;
import com.curso.microservicios.app.usuarios.models.repository.AlumnoRepository;

//Es un tipo de componente de spring
@Service
public class AlumnoServiceImpl implements AlumnoService {
	
	/*
	 * Inyecta el repositorio
	 * */
	@Autowired
	private AlumnoRepository repository;
	
	@Override
	@Transactional(readOnly = true) // Ya que solo es para consultas
	public Iterable<Alumno> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true) // Ya que solo es para consultas
	public Optional<Alumno> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional() // Ya que permite escrituras, modificar la tabla
	public Alumno save(Alumno alumno) {
		return repository.save(alumno);
	}

	@Override
	@Transactional() // Ya que permite escrituras, modificar la tabla
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
