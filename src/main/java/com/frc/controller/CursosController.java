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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Microservicio de gestión de cursos")
public class CursosController {
	@Autowired
	CursosService service;
	@Operation(summary = "Listado de cursos", description = "Muestra el listado de cursos existente en la base de datos")
	@GetMapping(value = "listado", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> listadoCursos() {
		return service.listarCursos();
	}
	@Operation(summary = "Alta curso", description = "Da de alta un curso nuevo")
	@PostMapping(value = "alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> altaCurso(@Parameter(name = "curso", description = "Curso nuevo para guardar en base de datos")@RequestBody Curso curso) {
		return service.altaCurso(curso);
	}
	@Operation(summary = "Borrado de curso", description = "Borrar un curso existente")
	@DeleteMapping(value = "borrado/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> borradoCurso(@Parameter(name = "codigo", description = "Código del curso que se quiere borrar") @PathVariable("codigo") String codigoCurso) {
		return service.eliminarCurso(codigoCurso);
	}
	@Operation(summary = "Actualización curso", description = "Actualiza el número de horas que dura el curso")
	@PutMapping(value = "actualizar/{codigo}/{horas}")
	public void actualizarCurso(@Parameter(name = "codigo", description = "Código del curso que se quiere actualizar") @PathVariable("codigo") String codigoCurso, @Parameter(name = "horas", description = "Número de horas que se actualizarán del curso indicado") @PathVariable("horas") int horasCurso) {
		service.actualizarDuracion(codigoCurso, horasCurso);
	}
	@Operation(summary = "Buscar un curso por su código", description = "Devuelve los datos de un curso según el código pasado del mismo")
	@GetMapping(value = "buscar/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Curso buscar(@Parameter(name = "codigo", description = "Código del curso del que se van a obtener sus datos") @PathVariable("codigo") String codigo) {
		return service.buscarCurso(codigo);
	}
	@Operation(summary = "Cursos que se encuentran dentro de un rango de precios", description = "Obtención de un listado de cursos cuyo precio está entre dos valores que se facilitan")
	@GetMapping(value = "precio")
	public List<Curso> listadoPrecio(@Parameter(name = "minimo", description = "Precio mínimo que debe tener el curso") @RequestParam("minimo") double minimoPrecio, @Parameter(name = "maximo", description = "Precio máximo que debe tener el curso") @RequestParam("maximo") double maximoPrecio){
		
		return service.listadoPorPrecio(minimoPrecio, maximoPrecio);
	}
	
	
}
