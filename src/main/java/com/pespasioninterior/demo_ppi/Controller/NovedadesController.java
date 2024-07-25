package com.pespasioninterior.demo_ppi.Controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pespasioninterior.demo_ppi.Dto.NovedadesDto;
import com.pespasioninterior.demo_ppi.Entity.Novedades;
import com.pespasioninterior.demo_ppi.Security.Controller.Mensaje;
import com.pespasioninterior.demo_ppi.Service.NovedadesService;

@RestController
@RequestMapping("/novedades")
@CrossOrigin(origins = "localhost:4200")
public class NovedadesController {
	
	@Autowired
	NovedadesService sNovedades;
	
	@GetMapping("/list")
	public ResponseEntity<List<Novedades>> listNovedades(){
		List<Novedades> list = sNovedades.listNovedades();
		return new ResponseEntity<List<Novedades>>(list, HttpStatus.OK);
	}
	
	@GetMapping("detail/{id}")
	public ResponseEntity<Novedades> detailNovedad(@PathVariable ("id") int id){
		Novedades novedad = sNovedades.getOneNovedad(id).get();
		return new ResponseEntity<>(novedad, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createNovedad(@RequestBody NovedadesDto dtoNovedades){
		if(StringUtils.isBlank(dtoNovedades.getTitle())) {
			return new ResponseEntity<>(new Mensaje("Se requiere título para crear la Novedad"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(dtoNovedades.getBody())) {
			return new ResponseEntity<>(new Mensaje("Se requiere el cuerpo para crear la Novedad"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(dtoNovedades.getImg())) {
			return new ResponseEntity<>(new Mensaje("Se requiere la ruta de la imagen"), HttpStatus.BAD_REQUEST);
		}
		
		Novedades novedad = new Novedades(dtoNovedades.getTitle(), dtoNovedades.getBody(), dtoNovedades.getImg());
		sNovedades.saveNovedad(novedad);
		return new ResponseEntity<>(new Mensaje("Novedad agregada con exito..."), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateNovedad(@PathVariable ("id") int id, @RequestBody NovedadesDto dtoNovedades){
		if(!sNovedades.existsById(id)) {
			return new ResponseEntity<>(new Mensaje("La novedad no existe..."), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(dtoNovedades.getTitle())) {
			return new ResponseEntity<>(new Mensaje("Se requiere título para crear la Novedad"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(dtoNovedades.getBody())) {
			return new ResponseEntity<>(new Mensaje("Se requiere el cuerpo para crear la Novedad"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(dtoNovedades.getImg())) {
			return new ResponseEntity<>(new Mensaje("Se requiere la ruta de la imagen"), HttpStatus.BAD_REQUEST);
		}
		
		Novedades novedad = sNovedades.getOneNovedad(id).get();
		novedad.setTitle(dtoNovedades.getTitle());
		novedad.setBody(dtoNovedades.getBody());
		novedad.setImg(dtoNovedades.getImg());
		sNovedades.saveNovedad(novedad);
		
		return new ResponseEntity<>(new Mensaje("Novedad actualizada con exito..."), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteNovedad(@PathVariable ("id") int id){
		if(sNovedades.existsById(id)) {
			sNovedades.deleteNovedad(id);
			return new ResponseEntity<>(new Mensaje("Novedad eliminada con exito..."), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Mensaje("La novedad que desea eliminar no se encuentra en la base de datos..."), HttpStatus.BAD_REQUEST);
	}
}
