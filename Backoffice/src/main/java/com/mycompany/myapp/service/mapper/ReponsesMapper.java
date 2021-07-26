package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ReponsesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reponses} and its DTO {@link ReponsesDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuestionsMapper.class})
public interface ReponsesMapper extends EntityMapper<ReponsesDTO, Reponses> {

    @Mapping(source = "idreponse.id", target = "idreponseId")
    ReponsesDTO toDto(Reponses reponses);

    @Mapping(source = "idreponseId", target = "idreponse")
    Reponses toEntity(ReponsesDTO reponsesDTO);

    default Reponses fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reponses reponses = new Reponses();
        reponses.setId(id);
        return reponses;
    }
}
