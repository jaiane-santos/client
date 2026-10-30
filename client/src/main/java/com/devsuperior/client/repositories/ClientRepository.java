package com.devsuperior.client.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.client.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
