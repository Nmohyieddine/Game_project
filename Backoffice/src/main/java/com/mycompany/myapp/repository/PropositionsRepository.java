package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Propositions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Propositions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PropositionsRepository extends JpaRepository<Propositions, Long> {

}
