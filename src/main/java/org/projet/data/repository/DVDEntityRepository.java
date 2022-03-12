package org.projet.data.repository;

import org.projet.data.entity.DVDEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DVDEntityRepository extends JpaRepository<DVDEntity, Long> {

}
