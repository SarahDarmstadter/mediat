package org.projet.data.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "CD_ref_entity")
public class CDRefEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String artist;

    private LocalDateTime publicationDate;

    private Integer songNumber;

    private Integer duration;
    
    @OneToMany
    private List <CDEntity> cdList;
}
