package org.projet.data.repository;

import java.util.List;

import org.projet.data.entity.DvdRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DVDRefEntityRepository extends JpaRepository<DvdRefEntity, Long> {

	public List<DvdRefEntity> findByDirectorIgnoreCase(String director);
	public DvdRefEntity findByTitle(String title);
	public List<DvdRefEntity> findAllById(Long id);
	public List<DvdRefEntity> findAllByTitle(String title);

}
