package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PropositionsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Propositions}.
 */
public interface PropositionsService {

    /**
     * Save a propositions.
     *
     * @param propositionsDTO the entity to save.
     * @return the persisted entity.
     */
    PropositionsDTO save(PropositionsDTO propositionsDTO);

    /**
     * Get all the propositions.
     *
     * @return the list of entities.
     */
    List<PropositionsDTO> findAll();


    /**
     * Get the "id" propositions.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PropositionsDTO> findOne(Long id);

    /**
     * Delete the "id" propositions.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
