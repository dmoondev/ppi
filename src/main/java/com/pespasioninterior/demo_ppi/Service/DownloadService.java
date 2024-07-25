package com.pespasioninterior.demo_ppi.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pespasioninterior.demo_ppi.Entity.Download;
import com.pespasioninterior.demo_ppi.Repository.iDownload;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DownloadService {

	@Autowired
	iDownload downloadService;
	
	public List<Download> listDownloads(){
		return downloadService.findAll();
	}
	
	public Optional<Download> getOneDownload(int id){
		return downloadService.findById(id);
	}
	
	public void saveDownload(Download download) {
		downloadService.save(download);
	}
	
	public void deleteDownload(int id) {
		downloadService.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return downloadService.existsById(id);
	}
	
	public boolean existsByName(String titulo) {
		return downloadService.existsByName(titulo);
	}
	
}
