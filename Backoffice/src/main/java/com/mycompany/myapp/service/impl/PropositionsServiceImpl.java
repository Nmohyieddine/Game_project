package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PropositionsService;
import com.mycompany.myapp.domain.Propositions;
import com.mycompany.myapp.repository.PropositionsRepository;
import com.mycompany.myapp.service.dto.PropositionsDTO;
import com.mycompany.myapp.service.mapper.PropositionsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Propositions}.
 */
@Service
@Transactional
public class PropositionsServiceImpl implements PropositionsService {

    private final Logger log = LoggerFactory.getLogger(PropositionsServiceImpl.class);

    private final PropositionsRepository propositionsRepository;

    private final PropositionsMapper propositionsMapper;

    public PropositionsServiceImpl(PropositionsRepository propositionsRepository, PropositionsMapper propositionsMapper) {
        this.propositionsRepository = propositionsRepository;
        this.propositionsMapper = propositionsMapper;
    }

    /**
     * Save a propositions.
     *
     * @param propositionsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PropositionsDTO save(PropositionsDTO propositionsDTO) {
        log.debug("Request to save Propositions : {}", propositionsDTO);
        Propositions propositions = propositionsMapper.toEntity(propositionsDTO);
        propositions = propositionsRepository.save(propositions);
        return propositionsMapper.toDto(propositions);
    }

    /**
     * Get all the propositions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PropositionsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Propositions");
        return propositionsRepository.findAll(pageable)
            .map(propositionsMapper::toDto);
    }


    /**
     * Get one propositions by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PropositionsDTO> findOne(Long id) {
        log.debug("Request to get Propositions : {}", id);
        return propositionsRepository.findById(id)
            .map(propositionsMapper::toDto);
    }

    /**
     * Delete the propositions by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Propositions : {}", id);
        propositionsRepository.deleteById(id);
    }
}
