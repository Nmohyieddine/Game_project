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

    private String reponse;


    private Long questionsId;

    private String questionsIdquestion;

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

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
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
            ", reponse='" + getReponse() + "'" +
            ", questions=" + getQuestionsId() +
            ", questions='" + getQuestionsIdquestion() + "'" +
            "}";
    }
}
