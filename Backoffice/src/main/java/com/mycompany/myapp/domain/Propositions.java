package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "propositions")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Propositions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "idpropositions", nullable = false)
    private Integer idpropositions;

    @Column(name = "proposition")
    private String proposition;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdpropositions() {
        return idpropositions;
    }

    public Propositions idpropositions(Integer idpropositions) {
        this.idpropositions = idpropositions;
        return this;
    }

    public void setIdpropositions(Integer idpropositions) {
        this.idpropositions = idpropositions;
    }

    public String getProposition() {
        return proposition;
    }

    public Propositions proposition(String proposition) {
        this.proposition = proposition;
        return this;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Propositions)) {
            return false;
        }
        return id != null && id.equals(((Propositions) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Propositions{" +
            "id=" + getId() +
            ", idpropositions=" + getIdpropositions() +
            ", proposition='" + getProposition() + "'" +
            "}";
    }
}
