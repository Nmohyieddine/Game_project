package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Questions} entity.
 */
public class QuestionsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer idquestion;

    private String question;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdquestion() {
        return idquestion;
    }

    public void setIdquestion(Integer idquestion) {
        this.idquestion = idquestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuestionsDTO questionsDTO = (QuestionsDTO) o;
        if (questionsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), questionsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuestionsDTO{" +
            "id=" + getId() +
            ", idquestion=" + getIdquestion() +
            ", question='" + getQuestion() + "'" +
            "}";
    }
}
