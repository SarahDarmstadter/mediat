package org.projet.data.repository;

import org.projet.data.entity.CDEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDEntityRepository extends JpaRepository<CDEntity, Long> {

}
