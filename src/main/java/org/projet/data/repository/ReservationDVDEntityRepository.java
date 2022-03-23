package org.projet.data.repository;

import java.util.Optional;

import org.projet.data.entity.ReservationDVDEntity;
import org.projet.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDVDEntityRepository extends JpaRepository<ReservationDVDEntity, Long> {

	public Integer countAllByUser(UserEntity user);
	public ReservationDVDEntity findByUser(UserEntity userEntity);

}
