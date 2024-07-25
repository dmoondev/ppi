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
import com.pespasioninterior.demo_ppi.Dto.DownloadDto;
import com.pespasioninterior.demo_ppi.Entity.Download;
import com.pespasioninterior.demo_ppi.Security.Controller.Mensaje;
import com.pespasioninterior.demo_ppi.Service.DownloadService;

@RestController
@RequestMapping("/downloads")
@CrossOrigin(origins = "localhost:4200")
public class DownloadController {
	
	@Autowired
	DownloadService downloadService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Download>> listDownloads(){
		List<Download> list = downloadService.listDownloads();
		return new ResponseEntity<List<Download>>(list, HttpStatus.OK);
	}
	
	@GetMapping("detail/{id}")
	public ResponseEntity<Download> detailNovedad(@PathVariable ("id") int id){
		Download download = downloadService.getOneDownload(id).get();
		return new ResponseEntity<>(download, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createDownload(@RequestBody DownloadDto downloadDto){
		if(StringUtils.isBlank(downloadDto.getName()) && StringUtils.isBlank(downloadDto.getLink())) {
			return new ResponseEntity<>(new Mensaje("Se requiere un nombre para cargar el link"), HttpStatus.BAD_REQUEST);
		}
		
		Download newDownload = new Download(downloadDto.getName(), downloadDto.getLink());
		downloadService.saveDownload(newDownload);
		return new ResponseEntity<>(new Mensaje("El link de descarga se cargó correctamente"), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateDownload(@PathVariable("id") int id, @RequestBody DownloadDto downloadDto){
		if(StringUtils.isBlank(downloadDto.getName()) && StringUtils.isBlank(downloadDto.getLink())) {
			return new ResponseEntity<>(new Mensaje("Se requiere un nombre para cargar el link"), HttpStatus.BAD_REQUEST);
		}
		
		Download updateDownload = downloadService.getOneDownload(id).get();
		updateDownload.setName(downloadDto.getName());
		updateDownload.setLink(downloadDto.getLink());
		downloadService.saveDownload(updateDownload);
		
		return new ResponseEntity<>(new Mensaje("Link actualizado correctamente"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDownload(@PathVariable("id") int id){
		if(downloadService.existsById(id)) {
			downloadService.deleteDownload(id);
			return new ResponseEntity<>(new Mensaje("Link eliminado correctamente."), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Mensaje("La novedad no se encuentra en la base de datos."), HttpStatus.BAD_REQUEST);
	}
}
