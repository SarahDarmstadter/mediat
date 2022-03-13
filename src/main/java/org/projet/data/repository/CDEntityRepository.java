package org.projet.data.repository;

import java.util.List;

import org.projet.data.entity.CDEntity;
import org.projet.data.entity.CDRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDEntityRepository extends JpaRepository<CDEntity, Long> {

	public List<CDEntity> findAllByisDispo(boolean b);
	public int countAllByReference(CDRefEntity cdReference);
	public List<CDEntity> findAllByisDispoAndReference(boolean b, CDRefEntity reference);

}
