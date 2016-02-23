package fr.istic.taa.repository;

import fr.istic.taa.domain.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Antoine Brezillon on 21/01/16.
 */
@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {

    List<Developer> findByName(@Param("name") String Name);

}
