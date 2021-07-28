package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.PropositionsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.PropositionsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Propositions}.
 */
@RestController
@RequestMapping("/api")
public class PropositionsResource {

    private final Logger log = LoggerFactory.getLogger(PropositionsResource.class);

    private static final String ENTITY_NAME = "propositions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PropositionsService propositionsService;

    public PropositionsResource(PropositionsService propositionsService) {
        this.propositionsService = propositionsService;
    }

    /**
     * {@code POST  /propositions} : Create a new propositions.
     *
     * @param propositionsDTO the propositionsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new propositionsDTO, or with status {@code 400 (Bad Request)} if the propositions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/propositions")
    public ResponseEntity<PropositionsDTO> createPropositions(@Valid @RequestBody PropositionsDTO propositionsDTO) throws URISyntaxException {
        log.debug("REST request to save Propositions : {}", propositionsDTO);
        if (propositionsDTO.getId() != null) {
            throw new BadRequestAlertException("A new propositions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PropositionsDTO result = propositionsService.save(propositionsDTO);
        return ResponseEntity.created(new URI("/api/propositions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /propositions} : Updates an existing propositions.
     *
     * @param propositionsDTO the propositionsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated propositionsDTO,
     * or with status {@code 400 (Bad Request)} if the propositionsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the propositionsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/propositions")
    public ResponseEntity<PropositionsDTO> updatePropositions(@Valid @RequestBody PropositionsDTO propositionsDTO) throws URISyntaxException {
        log.debug("REST request to update Propositions : {}", propositionsDTO);
        if (propositionsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PropositionsDTO result = propositionsService.save(propositionsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, propositionsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /propositions} : get all the propositions.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of propositions in body.
     */
    @GetMapping("/propositions")
    public ResponseEntity<List<PropositionsDTO>> getAllPropositions(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Propositions");
        Page<PropositionsDTO> page = propositionsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /propositions/:id} : get the "id" propositions.
     *
     * @param id the id of the propositionsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the propositionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/propositions/{id}")
    public ResponseEntity<PropositionsDTO> getPropositions(@PathVariable Long id) {
        log.debug("REST request to get Propositions : {}", id);
        Optional<PropositionsDTO> propositionsDTO = propositionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(propositionsDTO);
    }

    /**
     * {@code DELETE  /propositions/:id} : delete the "id" propositions.
     *
     * @param id the id of the propositionsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/propositions/{id}")
    public ResponseEntity<Void> deletePropositions(@PathVariable Long id) {
        log.debug("REST request to delete Propositions : {}", id);
        propositionsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
