package fr.istic.taa.web.rest;

import fr.istic.taa.domain.UserStory;
import fr.istic.taa.repository.UserStoryRepository;
import fr.istic.taa.web.rest.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Antoine Brezillon on 24/02/16.
 */

@RestController
@RequestMapping("UserStory")
public class UserStoryResource {

    @Autowired
    private UserStoryRepository userStoryRepository;

    /**
     * POST  /userStories-> Create a new userstory.
     */
    @RequestMapping(value = "/userStories",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<UserStory> create(@RequestBody UserStory userStory) throws URISyntaxException {
        if (userStory.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new userStory cannot already have an ID").body(null);
        }
        UserStory result = userStoryRepository.save(userStory);
        return ResponseEntity.created(new URI("/api/userStories/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("userStory", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /userStories -> Updates an existing userStory.
     */
    @RequestMapping(value = "/userStories",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<UserStory> update(@RequestBody UserStory userStory) throws URISyntaxException {
        if (userStory.getId() == null) {
            return create(userStory);
        }
        UserStory result = userStoryRepository.save(userStory);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("userStory", userStory.getId().toString()))
                .body(result);
    }

    /**
     * GET  /userStories -> get all the userStories.
     */
    @RequestMapping(value = "/userStories",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public List<UserStory> getAll() {
        return userStoryRepository.findAll();
    }

    /**
     * GET  /userStories/:id -> get the "id" userStory.
     */
    @RequestMapping(value = "/userStories/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<UserStory> get(@PathVariable Long id) {
        return Optional.ofNullable(userStoryRepository.findOne(id))
                .map(userStory -> new ResponseEntity<>(
                        userStory,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /userStories/:id -> delete the "id" userStory.
     */
    @RequestMapping(value = "/userStories/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userStoryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("userStory", id.toString())).build();
    }
}
