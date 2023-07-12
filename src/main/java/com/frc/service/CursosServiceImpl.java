package com.frc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frc.dao.CursosDao;
import com.frc.model.Curso;

@Service
public class CursosServiceImpl implements CursosService {
	@Autowired
	CursosDao dao;

	@Override
	public List<Curso> listarCursos() {
		return dao.findAll();
	}

	@Override
	public List<Curso> altaCurso(Curso curso) {
		dao.save(curso);
		return dao.findAll();
	}

	@Override
	public List<Curso> eliminarCurso(String codCurso) {
		dao.deleteById(codCurso);
		return dao.findAll();
	}

	@Override
	public void actualizarDuracion(String codCurso, int horas) {
		Curso actualizado = dao.findById(codCurso).orElse(null);
		System.out.println(actualizado.getHoras());
		if (actualizado != null) {
			actualizado.setHoras(horas);
			dao.save(actualizado);
		}
	}
	/*  Es preferible utilizar las anotaciones de Spring Data JPA 
	 *  y dejarle a Spring que realice la implementación del código. 
	 *  A parte de ahorrarnos código, el hecho de que lo deleguemos 
	 *  en Spring esta tarea permitirá al framework aplicar las técnicas 
	 *  apropiadas para realizarla de la forma más óptima posible.
	 *  Con lo cual el método anterior estaría mejor si fuese:
	 *  @Modifying
		@Transactional
		@Query("update Curso c set c.duracion = ?1 where c.codCurso = ?2")
		void actualizarDuracionCurso(int duracion, int codCurso);
	 */

	@Override
	public Curso buscarCurso(String codCurso) {
		return dao.findById(codCurso).orElse(null);
	}

	@Override
	public List<Curso> listadoPorPrecio(double minimo, double maximo) {
		 List<Curso> cursosEnRango = new ArrayList<>();
		 List<Curso> cursosAcademia = listarCursos();
		 for(Curso curso : cursosAcademia) {
			 if(curso.getPrecio()>= minimo && curso.getPrecio()<= maximo) {
				 cursosEnRango.add(curso);
			 }
		 }
		return cursosEnRango;
	}

}
