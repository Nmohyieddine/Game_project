package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Reponses} entity.
 */
public class ReponsesDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer idreponse;


    private Long propositionId;

    private Long questionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdreponse() {
        return idreponse;
    }

    public void setIdreponse(Integer idreponse) {
        this.idreponse = idreponse;
    }

    public Long getPropositionId() {
        return propositionId;
    }

    public void setPropositionId(Long propositionsId) {
        this.propositionId = propositionsId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionsId) {
        this.questionId = questionsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReponsesDTO reponsesDTO = (ReponsesDTO) o;
        if (reponsesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reponsesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReponsesDTO{" +
            "id=" + getId() +
            ", idreponse=" + getIdreponse() +
            ", proposition=" + getPropositionId() +
            ", question=" + getQuestionId() +
            "}";
    }
}
