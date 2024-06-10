package com.pespasioninterior.demo_ppi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pespasioninterior.demo_ppi.Entity.Novedades;
import com.pespasioninterior.demo_ppi.Repository.iNovedadesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NovedadesService {
	@Autowired
	iNovedadesRepository rNovedades;
	
	public List<Novedades> listNovedades(){
		return rNovedades.findAll();
	}
	
	public Optional<Novedades> getOneNovedad(int id){
		return rNovedades.findById(id);
	}
	
	public void saveNovedad(Novedades novedad) {
		rNovedades.save(novedad);
	}
	
	public void deleteNovedad(int id) {
		rNovedades.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return rNovedades.existsById(id);
	}
	
	public boolean existsByTitulo(String titulo) {
		return rNovedades.existsByTitulo(titulo);
	}
}
