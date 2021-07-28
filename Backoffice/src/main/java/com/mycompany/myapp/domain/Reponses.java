package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Reponses.
 */
@Entity
@Table(name = "reponses")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reponses implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "idreponse", nullable = false)
    private Integer idreponse;

    @OneToOne
    @JoinColumn(unique = true)
    private Propositions proposition;

    @OneToOne
    @JoinColumn(unique = true)
    private Questions question;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdreponse() {
        return idreponse;
    }

    public Reponses idreponse(Integer idreponse) {
        this.idreponse = idreponse;
        return this;
    }

    public void setIdreponse(Integer idreponse) {
        this.idreponse = idreponse;
    }

    public Propositions getProposition() {
        return proposition;
    }

    public Reponses proposition(Propositions propositions) {
        this.proposition = propositions;
        return this;
    }

    public void setProposition(Propositions propositions) {
        this.proposition = propositions;
    }

    public Questions getQuestion() {
        return question;
    }

    public Reponses question(Questions questions) {
        this.question = questions;
        return this;
    }

    public void setQuestion(Questions questions) {
        this.question = questions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reponses)) {
            return false;
        }
        return id != null && id.equals(((Reponses) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Reponses{" +
            "id=" + getId() +
            ", idreponse=" + getIdreponse() +
            "}";
    }
}
