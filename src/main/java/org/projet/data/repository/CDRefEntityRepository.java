package org.projet.data.repository;

import java.util.List;

import org.projet.data.entity.CdRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDRefEntityRepository extends JpaRepository<CdRefEntity, Long> {

	public List<CdRefEntity> findByArtistIgnoreCase(String artist);

	public CdRefEntity findByTitle(String title);

	public List<CdRefEntity> findAllById(Long id);

<<<<<<< HEAD
=======
	public List<CdRefEntity> findByArtist(String artist);

>>>>>>> a04640e729022018c70316405b26cd587a92b6d4
	public List<CdRefEntity> findAllByTitle(String title);



}
