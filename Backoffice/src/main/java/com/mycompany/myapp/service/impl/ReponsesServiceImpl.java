package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ReponsesService;
import com.mycompany.myapp.domain.Reponses;
import com.mycompany.myapp.repository.ReponsesRepository;
import com.mycompany.myapp.service.dto.ReponsesDTO;
import com.mycompany.myapp.service.mapper.ReponsesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Reponses}.
 */
@Service
@Transactional
public class ReponsesServiceImpl implements ReponsesService {

    private final Logger log = LoggerFactory.getLogger(ReponsesServiceImpl.class);

    private final ReponsesRepository reponsesRepository;

    private final ReponsesMapper reponsesMapper;

    public ReponsesServiceImpl(ReponsesRepository reponsesRepository, ReponsesMapper reponsesMapper) {
        this.reponsesRepository = reponsesRepository;
        this.reponsesMapper = reponsesMapper;
    }

    /**
     * Save a reponses.
     *
     * @param reponsesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ReponsesDTO save(ReponsesDTO reponsesDTO) {
        log.debug("Request to save Reponses : {}", reponsesDTO);
        Reponses reponses = reponsesMapper.toEntity(reponsesDTO);
        reponses = reponsesRepository.save(reponses);
        return reponsesMapper.toDto(reponses);
    }

    /**
     * Get all the reponses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReponsesDTO> findAll() {
        log.debug("Request to get all Reponses");
        return reponsesRepository.findAll().stream()
            .map(reponsesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one reponses by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReponsesDTO> findOne(Long id) {
        log.debug("Request to get Reponses : {}", id);
        return reponsesRepository.findById(id)
            .map(reponsesMapper::toDto);
    }

    /**
     * Delete the reponses by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reponses : {}", id);
        reponsesRepository.deleteById(id);
    }
}
