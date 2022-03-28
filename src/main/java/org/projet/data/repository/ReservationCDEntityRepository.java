package org.projet.data.repository;

import java.util.List;

import org.projet.data.entity.ReservationCdEntity;
import org.projet.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationCDEntityRepository extends JpaRepository<ReservationCdEntity, Long> {

	public Integer countAllByUser(UserEntity user);
	public List<ReservationCdEntity> findByUser(UserEntity userEntity);


}
