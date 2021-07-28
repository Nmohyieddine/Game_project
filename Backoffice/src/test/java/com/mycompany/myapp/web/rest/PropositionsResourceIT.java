package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.BackofficeApp;
import com.mycompany.myapp.domain.Propositions;
import com.mycompany.myapp.repository.PropositionsRepository;
import com.mycompany.myapp.service.PropositionsService;
import com.mycompany.myapp.service.dto.PropositionsDTO;
import com.mycompany.myapp.service.mapper.PropositionsMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link PropositionsResource} REST controller.
 */
@SpringBootTest(classes = BackofficeApp.class)
public class PropositionsResourceIT {

    private static final Integer DEFAULT_IDPROPOSITIONS = 1;
    private static final Integer UPDATED_IDPROPOSITIONS = 2;

    private static final String DEFAULT_PROPOSITION = "AAAAAAAAAA";
    private static final String UPDATED_PROPOSITION = "BBBBBBBBBB";

    @Autowired
    private PropositionsRepository propositionsRepository;

    @Autowired
    private PropositionsMapper propositionsMapper;

    @Autowired
    private PropositionsService propositionsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPropositionsMockMvc;

    private Propositions propositions;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PropositionsResource propositionsResource = new PropositionsResource(propositionsService);
        this.restPropositionsMockMvc = MockMvcBuilders.standaloneSetup(propositionsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Propositions createEntity(EntityManager em) {
        Propositions propositions = new Propositions()
            .idpropositions(DEFAULT_IDPROPOSITIONS)
            .proposition(DEFAULT_PROPOSITION);
        return propositions;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Propositions createUpdatedEntity(EntityManager em) {
        Propositions propositions = new Propositions()
            .idpropositions(UPDATED_IDPROPOSITIONS)
            .proposition(UPDATED_PROPOSITION);
        return propositions;
    }

    @BeforeEach
    public void initTest() {
        propositions = createEntity(em);
    }

    @Test
    @Transactional
    public void createPropositions() throws Exception {
        int databaseSizeBeforeCreate = propositionsRepository.findAll().size();

        // Create the Propositions
        PropositionsDTO propositionsDTO = propositionsMapper.toDto(propositions);
        restPropositionsMockMvc.perform(post("/api/propositions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propositionsDTO)))
            .andExpect(status().isCreated());

        // Validate the Propositions in the database
        List<Propositions> propositionsList = propositionsRepository.findAll();
        assertThat(propositionsList).hasSize(databaseSizeBeforeCreate + 1);
        Propositions testPropositions = propositionsList.get(propositionsList.size() - 1);
        assertThat(testPropositions.getIdpropositions()).isEqualTo(DEFAULT_IDPROPOSITIONS);
        assertThat(testPropositions.getProposition()).isEqualTo(DEFAULT_PROPOSITION);
    }

    @Test
    @Transactional
    public void createPropositionsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = propositionsRepository.findAll().size();

        // Create the Propositions with an existing ID
        propositions.setId(1L);
        PropositionsDTO propositionsDTO = propositionsMapper.toDto(propositions);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPropositionsMockMvc.perform(post("/api/propositions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propositionsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Propositions in the database
        List<Propositions> propositionsList = propositionsRepository.findAll();
        assertThat(propositionsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdpropositionsIsRequired() throws Exception {
        int databaseSizeBeforeTest = propositionsRepository.findAll().size();
        // set the field null
        propositions.setIdpropositions(null);

        // Create the Propositions, which fails.
        PropositionsDTO propositionsDTO = propositionsMapper.toDto(propositions);

        restPropositionsMockMvc.perform(post("/api/propositions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propositionsDTO)))
            .andExpect(status().isBadRequest());

        List<Propositions> propositionsList = propositionsRepository.findAll();
        assertThat(propositionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPropositions() throws Exception {
        // Initialize the database
        propositionsRepository.saveAndFlush(propositions);

        // Get all the propositionsList
        restPropositionsMockMvc.perform(get("/api/propositions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(propositions.getId().intValue())))
            .andExpect(jsonPath("$.[*].idpropositions").value(hasItem(DEFAULT_IDPROPOSITIONS)))
            .andExpect(jsonPath("$.[*].proposition").value(hasItem(DEFAULT_PROPOSITION.toString())));
    }
    
    @Test
    @Transactional
    public void getPropositions() throws Exception {
        // Initialize the database
        propositionsRepository.saveAndFlush(propositions);

        // Get the propositions
        restPropositionsMockMvc.perform(get("/api/propositions/{id}", propositions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(propositions.getId().intValue()))
            .andExpect(jsonPath("$.idpropositions").value(DEFAULT_IDPROPOSITIONS))
            .andExpect(jsonPath("$.proposition").value(DEFAULT_PROPOSITION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPropositions() throws Exception {
        // Get the propositions
        restPropositionsMockMvc.perform(get("/api/propositions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePropositions() throws Exception {
        // Initialize the database
        propositionsRepository.saveAndFlush(propositions);

        int databaseSizeBeforeUpdate = propositionsRepository.findAll().size();

        // Update the propositions
        Propositions updatedPropositions = propositionsRepository.findById(propositions.getId()).get();
        // Disconnect from session so that the updates on updatedPropositions are not directly saved in db
        em.detach(updatedPropositions);
        updatedPropositions
            .idpropositions(UPDATED_IDPROPOSITIONS)
            .proposition(UPDATED_PROPOSITION);
        PropositionsDTO propositionsDTO = propositionsMapper.toDto(updatedPropositions);

        restPropositionsMockMvc.perform(put("/api/propositions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propositionsDTO)))
            .andExpect(status().isOk());

        // Validate the Propositions in the database
        List<Propositions> propositionsList = propositionsRepository.findAll();
        assertThat(propositionsList).hasSize(databaseSizeBeforeUpdate);
        Propositions testPropositions = propositionsList.get(propositionsList.size() - 1);
        assertThat(testPropositions.getIdpropositions()).isEqualTo(UPDATED_IDPROPOSITIONS);
        assertThat(testPropositions.getProposition()).isEqualTo(UPDATED_PROPOSITION);
    }

    @Test
    @Transactional
    public void updateNonExistingPropositions() throws Exception {
        int databaseSizeBeforeUpdate = propositionsRepository.findAll().size();

        // Create the Propositions
        PropositionsDTO propositionsDTO = propositionsMapper.toDto(propositions);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPropositionsMockMvc.perform(put("/api/propositions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propositionsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Propositions in the database
        List<Propositions> propositionsList = propositionsRepository.findAll();
        assertThat(propositionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePropositions() throws Exception {
        // Initialize the database
        propositionsRepository.saveAndFlush(propositions);

        int databaseSizeBeforeDelete = propositionsRepository.findAll().size();

        // Delete the propositions
        restPropositionsMockMvc.perform(delete("/api/propositions/{id}", propositions.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Propositions> propositionsList = propositionsRepository.findAll();
        assertThat(propositionsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Propositions.class);
        Propositions propositions1 = new Propositions();
        propositions1.setId(1L);
        Propositions propositions2 = new Propositions();
        propositions2.setId(propositions1.getId());
        assertThat(propositions1).isEqualTo(propositions2);
        propositions2.setId(2L);
        assertThat(propositions1).isNotEqualTo(propositions2);
        propositions1.setId(null);
        assertThat(propositions1).isNotEqualTo(propositions2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PropositionsDTO.class);
        PropositionsDTO propositionsDTO1 = new PropositionsDTO();
        propositionsDTO1.setId(1L);
        PropositionsDTO propositionsDTO2 = new PropositionsDTO();
        assertThat(propositionsDTO1).isNotEqualTo(propositionsDTO2);
        propositionsDTO2.setId(propositionsDTO1.getId());
        assertThat(propositionsDTO1).isEqualTo(propositionsDTO2);
        propositionsDTO2.setId(2L);
        assertThat(propositionsDTO1).isNotEqualTo(propositionsDTO2);
        propositionsDTO1.setId(null);
        assertThat(propositionsDTO1).isNotEqualTo(propositionsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(propositionsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(propositionsMapper.fromId(null)).isNull();
    }
}
