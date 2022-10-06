package com.luxoft.sb.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class UserEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String username;
	@JsonIgnore
	private String password;

}
