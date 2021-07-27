package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ReponsesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReponsesDTO> findAll(Pageable pageable);


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
