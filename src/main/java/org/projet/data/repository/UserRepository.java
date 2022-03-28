package org.projet.data.repository;


import org.apache.tomcat.jni.User;
import org.projet.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity getById(UserEntity user);

	public UserEntity findByEmail(String email);

}
