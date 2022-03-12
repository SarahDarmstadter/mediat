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
@Table(name ="DVD_ref_entity")
public class DVDRefEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String director;

    private LocalDateTime publicationDate;

    private Integer duree;
    
    private TypeDVD type;
    
    @OneToMany
    private List <DVDEntity> dvdList;
}
