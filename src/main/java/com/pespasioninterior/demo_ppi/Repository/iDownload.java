package com.pespasioninterior.demo_ppi.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pespasioninterior.demo_ppi.Entity.Download;

@Repository
public interface iDownload extends JpaRepository<Download, Integer>{
	public Optional<Download> findByName(String name);
	public boolean existsByName(String name);
}
