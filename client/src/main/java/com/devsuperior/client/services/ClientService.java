package com.devsuperior.client.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.client.dto.ClientDTO;
import com.devsuperior.client.entities.Client;
import com.devsuperior.client.repositories.ClientRepository;
import com.devsuperior.client.services.exceptions.DatabaseException;
import com.devsuperior.client.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {
	
	private final ClientRepository repository;
	
	public ClientService(ClientRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ClientDTO(client);
	}

   @Transactional(readOnly = true)
   public Page<ClientDTO> findAll(Pageable pageable) {
	 Page<Client> result = repository.findAll(pageable);
	 return result.map(x -> new ClientDTO(x));
   }
   
	@Transactional
	public ClientDTO insert(ClientDTO productdto) {
		
		Client entity = new Client();
		
    	copyDtoToEntity(productdto, entity);
		entity = repository.save(entity);
		
		return new ClientDTO(entity);
		
		
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO clientdto) {
		try {
			
		Client entity = repository.getReferenceById(id);
		
		copyDtoToEntity(clientdto, entity);
		
		entity = repository.save(entity);
		
		return new ClientDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		
	}
	
	private void copyDtoToEntity(ClientDTO clientdto, Client entity) {
		entity.setName(clientdto.getName()); 
		entity.setCpf(clientdto.getCpf());
		entity.setIncome(clientdto.getIncome());
		entity.setBirthDate(clientdto.getBirthDate());
		entity.setChildren(clientdto.getChildren());
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
        	repository.deleteById(id);    		
	}
    	catch (DataIntegrityViolationException e) {
        	throw new DatabaseException("Falha de integridade referencial");
   	}
}
		
}



