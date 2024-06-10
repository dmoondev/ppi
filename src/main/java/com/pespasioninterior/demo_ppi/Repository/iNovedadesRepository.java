package com.pespasioninterior.demo_ppi.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pespasioninterior.demo_ppi.Entity.Novedades;

@Repository
public interface iNovedadesRepository extends JpaRepository<Novedades, Integer>{
	public Optional<Novedades> findByTitulo(String titulo);
	public boolean existsByTitulo(String titulo);
}
