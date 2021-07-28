package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Propositions} entity.
 */
public class PropositionsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer idpropositions;

    private String proposition;


    private Long questionsId;

    private String questionsIdquestion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdpropositions() {
        return idpropositions;
    }

    public void setIdpropositions(Integer idpropositions) {
        this.idpropositions = idpropositions;
    }

    public String getProposition() {
        return proposition;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }

    public Long getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(Long questionsId) {
        this.questionsId = questionsId;
    }

    public String getQuestionsIdquestion() {
        return questionsIdquestion;
    }

    public void setQuestionsIdquestion(String questionsIdquestion) {
        this.questionsIdquestion = questionsIdquestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PropositionsDTO propositionsDTO = (PropositionsDTO) o;
        if (propositionsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), propositionsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PropositionsDTO{" +
            "id=" + getId() +
            ", idpropositions=" + getIdpropositions() +
            ", proposition='" + getProposition() + "'" +
            ", questions=" + getQuestionsId() +
            ", questions='" + getQuestionsIdquestion() + "'" +
            "}";
    }
}
