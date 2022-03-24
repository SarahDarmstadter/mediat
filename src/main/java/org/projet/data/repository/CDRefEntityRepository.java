package org.projet.data.repository;

import java.util.List;

import org.projet.data.entity.CdRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDRefEntityRepository extends JpaRepository<CdRefEntity, Long> {

	public List<CdRefEntity> findByArtistIgnoreCase(String artist);

	public CdRefEntity findByTitle(String title);

}
