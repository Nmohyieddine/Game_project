package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PropositionsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Propositions} and its DTO {@link PropositionsDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuestionsMapper.class})
public interface PropositionsMapper extends EntityMapper<PropositionsDTO, Propositions> {

    @Mapping(source = "idquestion.id", target = "idquestionId")
    PropositionsDTO toDto(Propositions propositions);

    @Mapping(source = "idquestionId", target = "idquestion")
    Propositions toEntity(PropositionsDTO propositionsDTO);

    default Propositions fromId(Long id) {
        if (id == null) {
            return null;
        }
        Propositions propositions = new Propositions();
        propositions.setId(id);
        return propositions;
    }
}
