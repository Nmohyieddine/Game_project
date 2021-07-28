package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Questions.
 */
@Entity
@Table(name = "questions")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Questions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "idquestion", nullable = false)
    private Integer idquestion;

    @Column(name = "question")
    private String question;

    @OneToMany(mappedBy = "questions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Propositions> iDQUESTIONS = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdquestion() {
        return idquestion;
    }

    public Questions idquestion(Integer idquestion) {
        this.idquestion = idquestion;
        return this;
    }

    public void setIdquestion(Integer idquestion) {
        this.idquestion = idquestion;
    }

    public String getQuestion() {
        return question;
    }

    public Questions question(String question) {
        this.question = question;
        return this;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<Propositions> getIDQUESTIONS() {
        return iDQUESTIONS;
    }

    public Questions iDQUESTIONS(Set<Propositions> propositions) {
        this.iDQUESTIONS = propositions;
        return this;
    }

    public Questions addIDQUESTION(Propositions propositions) {
        this.iDQUESTIONS.add(propositions);
        propositions.setQuestions(this);
        return this;
    }

    public Questions removeIDQUESTION(Propositions propositions) {
        this.iDQUESTIONS.remove(propositions);
        propositions.setQuestions(null);
        return this;
    }

    public void setIDQUESTIONS(Set<Propositions> propositions) {
        this.iDQUESTIONS = propositions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Questions)) {
            return false;
        }
        return id != null && id.equals(((Questions) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Questions{" +
            "id=" + getId() +
            ", idquestion=" + getIdquestion() +
            ", question='" + getQuestion() + "'" +
            "}";
    }
}
