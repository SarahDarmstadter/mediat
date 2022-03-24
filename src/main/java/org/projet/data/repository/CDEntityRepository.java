package org.projet.data.repository;

import java.util.List;

import org.projet.data.entity.CdEntity;
import org.projet.data.entity.CdRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDEntityRepository extends JpaRepository<CdEntity, Long> {

	public List<CdEntity> findAllByisDispo(boolean b);
	public int countAllByReference(CdRefEntity cdReference);
	public List<CdEntity> findAllByisDispoAndReference(boolean b, CdRefEntity reference);

}
