package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ReponsesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Reponses}.
 */
public interface ReponsesService {

    /**
     * Save a reponses.
     *
     * @param reponsesDTO the entity to save.
     * @return the persisted entity.
     */
    ReponsesDTO save(ReponsesDTO reponsesDTO);

    /**
     * Get all the reponses.
     *
     * @return the list of entities.
     */
    List<ReponsesDTO> findAll();


    /**
     * Get the "id" reponses.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReponsesDTO> findOne(Long id);

    /**
     * Delete the "id" reponses.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
