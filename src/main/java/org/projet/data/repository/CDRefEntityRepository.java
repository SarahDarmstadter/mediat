package org.projet.data.repository;

import java.util.List;

import org.projet.data.entity.CDRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDRefEntityRepository extends JpaRepository<CDRefEntity, Long> {

	public List<CDRefEntity> findByArtistIgnoreCase(String artist);

	public CDRefEntity findByTitle(String title);

}
