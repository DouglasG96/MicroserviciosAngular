package com.curso.microservicios.app.usuarios.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.microservicios.app.usuarios.models.entity.Alumno;
import com.curso.microservicios.app.usuarios.services.AlumnoService;

@RestController// Traduce el resultado en JSON o XML
public class AlumnoController {
	//Inyectamos la interfaz
	@Autowired
	private AlumnoService service;
	
	/*
	 * nos permite mapear una ruta, url al metodo
	 * la peticion es del tipo GET
	 * al no colocar ruta indica la ruta raiz de la peticion
	 * Todos los mapping son para dar una ruta a la peticion
	 * conocidos tambien como metodos handler o manejadores del request
	 */
	@GetMapping
	public ResponseEntity<?> listar()
	{
		// Pasamos al cuerpo de la respuesta una lista
		return ResponseEntity.ok().body(service.findAll());
	}
	
	/*
	 * WildCard o expresion de parametro
	 * un path o una ruta variable en la url
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id)
	{
		Optional<Alumno> opt = service.findById(id);
		if(opt.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		//Si existe el metodo get retorna el objeto alumno
		return ResponseEntity.ok(opt.get());
	}
	
	/*
	 * El post esta tambien en la raiz pero del verbo post
	 * Indicamos que los datos los pueble en el objeto Alumno que recibe como 
	 * parametro el metodo
	 */
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumno alumno)
	{
		Alumno alumnoDb = service.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
	}
	
	/*
	 *  El primer parametro es el objeto con datos actualizados desde la peticion
	 *  El segundo parametro es el id a actualizar para validar que exista
	 */
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id)
	{
		Optional<Alumno> opt = service.findById(id);
		if(opt.isEmpty())
		{
			// build construye la respuesta con un contenido vacio
			return ResponseEntity.notFound().build();
		}
		//Si existe se actualiza ese registro existente por ende se extrae
		Alumno alumnoDb = opt.get();
		//Se actualizan solo los campos que querramos para mantener ciertos datos
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());
		//Se persiste de una sola vez el alumno y se envia la respuesta
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id)
	{
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
