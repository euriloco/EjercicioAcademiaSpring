package com.frc.service;

import java.util.List;

import com.frc.model.Curso;

public interface CursosService {
	List<Curso> listarCursos();
	List<Curso>	altaCurso(Curso curso);
	List<Curso> eliminarCurso(String codCurso);
	void actualizarDuracion(String codCurso, int horas);
	Curso buscarCurso(String codCurso);
	List<Curso> listadoPorPrecio(double minimo, double maximo);
	

}
