package fr.istic.taa.repository;

import fr.istic.taa.domain.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Antoine Brezillon on 21/01/16.
 */
@Repository
public interface UserStoryRepository extends JpaRepository<UserStory,Long> {

}
