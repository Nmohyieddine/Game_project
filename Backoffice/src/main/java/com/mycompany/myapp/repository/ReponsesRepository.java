package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Reponses;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Reponses entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReponsesRepository extends JpaRepository<Reponses, Long> {

}
