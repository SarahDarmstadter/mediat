package org.projet.data.repository;

import org.projet.data.entity.DVDRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DVDRefEntityRepository extends JpaRepository<DVDRefEntity, Long> {

}
