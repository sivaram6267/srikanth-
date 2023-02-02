package com.lancesoft.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Authorities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer role_id;
	private String role;
	

}
