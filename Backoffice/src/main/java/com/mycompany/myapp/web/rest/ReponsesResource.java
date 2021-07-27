package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ReponsesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ReponsesDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Reponses}.
 */
@RestController
@RequestMapping("/api")
public class ReponsesResource {

    private final Logger log = LoggerFactory.getLogger(ReponsesResource.class);

    private static final String ENTITY_NAME = "reponses";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReponsesService reponsesService;

    public ReponsesResource(ReponsesService reponsesService) {
        this.reponsesService = reponsesService;
    }

    /**
     * {@code POST  /reponses} : Create a new reponses.
     *
     * @param reponsesDTO the reponsesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reponsesDTO, or with status {@code 400 (Bad Request)} if the reponses has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reponses")
    public ResponseEntity<ReponsesDTO> createReponses(@Valid @RequestBody ReponsesDTO reponsesDTO) throws URISyntaxException {
        log.debug("REST request to save Reponses : {}", reponsesDTO);
        if (reponsesDTO.getId() != null) {
            throw new BadRequestAlertException("A new reponses cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReponsesDTO result = reponsesService.save(reponsesDTO);
        return ResponseEntity.created(new URI("/api/reponses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reponses} : Updates an existing reponses.
     *
     * @param reponsesDTO the reponsesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reponsesDTO,
     * or with status {@code 400 (Bad Request)} if the reponsesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reponsesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reponses")
    public ResponseEntity<ReponsesDTO> updateReponses(@Valid @RequestBody ReponsesDTO reponsesDTO) throws URISyntaxException {
        log.debug("REST request to update Reponses : {}", reponsesDTO);
        if (reponsesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReponsesDTO result = reponsesService.save(reponsesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reponsesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reponses} : get all the reponses.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reponses in body.
     */
    @GetMapping("/reponses")
    public ResponseEntity<List<ReponsesDTO>> getAllReponses(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Reponses");
        Page<ReponsesDTO> page = reponsesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reponses/:id} : get the "id" reponses.
     *
     * @param id the id of the reponsesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reponsesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reponses/{id}")
    public ResponseEntity<ReponsesDTO> getReponses(@PathVariable Long id) {
        log.debug("REST request to get Reponses : {}", id);
        Optional<ReponsesDTO> reponsesDTO = reponsesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reponsesDTO);
    }

    /**
     * {@code DELETE  /reponses/:id} : delete the "id" reponses.
     *
     * @param id the id of the reponsesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reponses/{id}")
    public ResponseEntity<Void> deleteReponses(@PathVariable Long id) {
        log.debug("REST request to delete Reponses : {}", id);
        reponsesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
