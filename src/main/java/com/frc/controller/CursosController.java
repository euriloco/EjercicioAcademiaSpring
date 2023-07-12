package com.frc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frc.model.Curso;
import com.frc.service.CursosService;

@RestController
public class CursosController {
	@Autowired
	CursosService service;
	@GetMapping(value = "listado", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> listadoCursos() {
		return service.listarCursos();
	}
	@PostMapping(value = "alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> altaCurso(@RequestBody Curso curso) {
		return service.altaCurso(curso);
	}
	@DeleteMapping(value = "borrado/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> borradoCurso(@PathVariable("codigo") String codigoCurso) {
		return service.eliminarCurso(codigoCurso);
	}
	@PutMapping(value = "actualizar/{codigo}/{horas}")
	public void actualizarCurso(@PathVariable("codigo") String codigoCurso, @PathVariable("horas") int horasCurso) {
		service.actualizarDuracion(codigoCurso, horasCurso);
	}
	@GetMapping(value = "buscar/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Curso buscar(@PathVariable("codigo") String codigo) {
		return service.buscarCurso(codigo);
	}
	@GetMapping(value = "precio")
	public List<Curso> listadoPrecio(@RequestParam("minimo") double minimoPrecio, @RequestParam("maximo") double maximoPrecio){
		
		return service.listadoPorPrecio(minimoPrecio, maximoPrecio);
	}
	
	
}
