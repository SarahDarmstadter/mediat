package org.projet.data.repository;

import java.util.List;

import org.projet.data.entity.DVDRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DVDRefEntityRepository extends JpaRepository<DVDRefEntity, Long> {

	public List<DVDRefEntity> findByDirectorIgnoreCase(String director);

	public DVDRefEntity findByTitle(String title);

}
