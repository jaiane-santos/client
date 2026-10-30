package com.devsuperior.client.dto;

import java.time.LocalDate;

import com.devsuperior.client.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;


public class ClientDTO {
	
    private Long id;
    
    @NotBlank(message = "campo Nome não pode ser vazio")
	private String name;
	private String cpf;
	private Double income;
	
	@PastOrPresent(message = "A data de nascimento não pode ser futura")
	private LocalDate birthDate;
	private Integer children;

	public ClientDTO() {
		
	}
	
	public ClientDTO(Client entity) {
		super();
		id = entity.getId();
		name = entity.getName();
		cpf = entity.getCpf();
		income = entity.getIncome();
		birthDate = entity.getBirthDate();
		children = entity.getChildren();
	}
	

	public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}
	
}


