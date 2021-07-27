package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.BackofficeApp;
import com.mycompany.myapp.domain.Reponses;
import com.mycompany.myapp.repository.ReponsesRepository;
import com.mycompany.myapp.service.ReponsesService;
import com.mycompany.myapp.service.dto.ReponsesDTO;
import com.mycompany.myapp.service.mapper.ReponsesMapper;
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
 * Integration tests for the {@Link ReponsesResource} REST controller.
 */
@SpringBootTest(classes = BackofficeApp.class)
public class ReponsesResourceIT {

    private static final Integer DEFAULT_IDREPONSE = 1;
    private static final Integer UPDATED_IDREPONSE = 2;

    private static final String DEFAULT_REPONSE = "AAAAAAAAAA";
    private static final String UPDATED_REPONSE = "BBBBBBBBBB";

    @Autowired
    private ReponsesRepository reponsesRepository;

    @Autowired
    private ReponsesMapper reponsesMapper;

    @Autowired
    private ReponsesService reponsesService;

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

    private MockMvc restReponsesMockMvc;

    private Reponses reponses;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReponsesResource reponsesResource = new ReponsesResource(reponsesService);
        this.restReponsesMockMvc = MockMvcBuilders.standaloneSetup(reponsesResource)
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
    public static Reponses createEntity(EntityManager em) {
        Reponses reponses = new Reponses()
            .idreponse(DEFAULT_IDREPONSE)
            .reponse(DEFAULT_REPONSE);
        return reponses;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Reponses createUpdatedEntity(EntityManager em) {
        Reponses reponses = new Reponses()
            .idreponse(UPDATED_IDREPONSE)
            .reponse(UPDATED_REPONSE);
        return reponses;
    }

    @BeforeEach
    public void initTest() {
        reponses = createEntity(em);
    }

    @Test
    @Transactional
    public void createReponses() throws Exception {
        int databaseSizeBeforeCreate = reponsesRepository.findAll().size();

        // Create the Reponses
        ReponsesDTO reponsesDTO = reponsesMapper.toDto(reponses);
        restReponsesMockMvc.perform(post("/api/reponses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reponsesDTO)))
            .andExpect(status().isCreated());

        // Validate the Reponses in the database
        List<Reponses> reponsesList = reponsesRepository.findAll();
        assertThat(reponsesList).hasSize(databaseSizeBeforeCreate + 1);
        Reponses testReponses = reponsesList.get(reponsesList.size() - 1);
        assertThat(testReponses.getIdreponse()).isEqualTo(DEFAULT_IDREPONSE);
        assertThat(testReponses.getReponse()).isEqualTo(DEFAULT_REPONSE);
    }

    @Test
    @Transactional
    public void createReponsesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reponsesRepository.findAll().size();

        // Create the Reponses with an existing ID
        reponses.setId(1L);
        ReponsesDTO reponsesDTO = reponsesMapper.toDto(reponses);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReponsesMockMvc.perform(post("/api/reponses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reponsesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reponses in the database
        List<Reponses> reponsesList = reponsesRepository.findAll();
        assertThat(reponsesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdreponseIsRequired() throws Exception {
        int databaseSizeBeforeTest = reponsesRepository.findAll().size();
        // set the field null
        reponses.setIdreponse(null);

        // Create the Reponses, which fails.
        ReponsesDTO reponsesDTO = reponsesMapper.toDto(reponses);

        restReponsesMockMvc.perform(post("/api/reponses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reponsesDTO)))
            .andExpect(status().isBadRequest());

        List<Reponses> reponsesList = reponsesRepository.findAll();
        assertThat(reponsesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllReponses() throws Exception {
        // Initialize the database
        reponsesRepository.saveAndFlush(reponses);

        // Get all the reponsesList
        restReponsesMockMvc.perform(get("/api/reponses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reponses.getId().intValue())))
            .andExpect(jsonPath("$.[*].idreponse").value(hasItem(DEFAULT_IDREPONSE)))
            .andExpect(jsonPath("$.[*].reponse").value(hasItem(DEFAULT_REPONSE.toString())));
    }
    
    @Test
    @Transactional
    public void getReponses() throws Exception {
        // Initialize the database
        reponsesRepository.saveAndFlush(reponses);

        // Get the reponses
        restReponsesMockMvc.perform(get("/api/reponses/{id}", reponses.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reponses.getId().intValue()))
            .andExpect(jsonPath("$.idreponse").value(DEFAULT_IDREPONSE))
            .andExpect(jsonPath("$.reponse").value(DEFAULT_REPONSE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReponses() throws Exception {
        // Get the reponses
        restReponsesMockMvc.perform(get("/api/reponses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReponses() throws Exception {
        // Initialize the database
        reponsesRepository.saveAndFlush(reponses);

        int databaseSizeBeforeUpdate = reponsesRepository.findAll().size();

        // Update the reponses
        Reponses updatedReponses = reponsesRepository.findById(reponses.getId()).get();
        // Disconnect from session so that the updates on updatedReponses are not directly saved in db
        em.detach(updatedReponses);
        updatedReponses
            .idreponse(UPDATED_IDREPONSE)
            .reponse(UPDATED_REPONSE);
        ReponsesDTO reponsesDTO = reponsesMapper.toDto(updatedReponses);

        restReponsesMockMvc.perform(put("/api/reponses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reponsesDTO)))
            .andExpect(status().isOk());

        // Validate the Reponses in the database
        List<Reponses> reponsesList = reponsesRepository.findAll();
        assertThat(reponsesList).hasSize(databaseSizeBeforeUpdate);
        Reponses testReponses = reponsesList.get(reponsesList.size() - 1);
        assertThat(testReponses.getIdreponse()).isEqualTo(UPDATED_IDREPONSE);
        assertThat(testReponses.getReponse()).isEqualTo(UPDATED_REPONSE);
    }

    @Test
    @Transactional
    public void updateNonExistingReponses() throws Exception {
        int databaseSizeBeforeUpdate = reponsesRepository.findAll().size();

        // Create the Reponses
        ReponsesDTO reponsesDTO = reponsesMapper.toDto(reponses);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReponsesMockMvc.perform(put("/api/reponses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reponsesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reponses in the database
        List<Reponses> reponsesList = reponsesRepository.findAll();
        assertThat(reponsesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReponses() throws Exception {
        // Initialize the database
        reponsesRepository.saveAndFlush(reponses);

        int databaseSizeBeforeDelete = reponsesRepository.findAll().size();

        // Delete the reponses
        restReponsesMockMvc.perform(delete("/api/reponses/{id}", reponses.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Reponses> reponsesList = reponsesRepository.findAll();
        assertThat(reponsesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Reponses.class);
        Reponses reponses1 = new Reponses();
        reponses1.setId(1L);
        Reponses reponses2 = new Reponses();
        reponses2.setId(reponses1.getId());
        assertThat(reponses1).isEqualTo(reponses2);
        reponses2.setId(2L);
        assertThat(reponses1).isNotEqualTo(reponses2);
        reponses1.setId(null);
        assertThat(reponses1).isNotEqualTo(reponses2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReponsesDTO.class);
        ReponsesDTO reponsesDTO1 = new ReponsesDTO();
        reponsesDTO1.setId(1L);
        ReponsesDTO reponsesDTO2 = new ReponsesDTO();
        assertThat(reponsesDTO1).isNotEqualTo(reponsesDTO2);
        reponsesDTO2.setId(reponsesDTO1.getId());
        assertThat(reponsesDTO1).isEqualTo(reponsesDTO2);
        reponsesDTO2.setId(2L);
        assertThat(reponsesDTO1).isNotEqualTo(reponsesDTO2);
        reponsesDTO1.setId(null);
        assertThat(reponsesDTO1).isNotEqualTo(reponsesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(reponsesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(reponsesMapper.fromId(null)).isNull();
    }
}
