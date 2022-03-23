package org.projet.data.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Entity
@JsonInclude(JsonInclude.Include.ALWAYS)

public class CDRefEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String artist;

    private LocalDateTime publicationDate;

    private Integer songNumber;

    private Integer duration;
    
    private Integer copies;
    
	@OneToMany(mappedBy = "reference", cascade = CascadeType.ALL)
	@JsonIgnore
    private List <CDEntity> cdList;
}
