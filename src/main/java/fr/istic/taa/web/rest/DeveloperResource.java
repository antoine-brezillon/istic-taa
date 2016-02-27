package fr.istic.taa.web.rest;

import fr.istic.taa.domain.Developer;
import fr.istic.taa.repository.DeveloperRepository;
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
@RequestMapping("Developer")
public class DeveloperResource {

    @Autowired
    private DeveloperRepository developerRepository;

    /**
     * POST  /developers -> Create a new developer.
     */
    @RequestMapping(value = "/developers",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Developer> create(@RequestBody Developer developer) throws URISyntaxException {
        if (developer.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new developer cannot already have an ID").body(null);
        }
        Developer result = developerRepository.save(developer);
        return ResponseEntity.created(new URI("/api/developers/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("developer", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /developers -> Updates an existing developer.
     */
    @RequestMapping(value = "/developers",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Developer> update(@RequestBody Developer developer) throws URISyntaxException {
        if (developer.getId() == null) {
            return create(developer);
        }
        Developer result = developerRepository.save(developer);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("developer", developer.getId().toString()))
                .body(result);
    }

    /**
     * GET  /developers -> get all the developers.
     */
    @RequestMapping(value = "/developers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    /**
     * GET  /developers/:id -> get the "id" developer.
     */
    @RequestMapping(value = "/developers/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Developer> get(@PathVariable Long id) {
        return Optional.ofNullable(developerRepository.findOne(id))
                .map(developer -> new ResponseEntity<>(
                        developer,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET  /developers/name/:name -> get the list of "name" developers.
     */
    @RequestMapping(value = "/developers/name/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public List<Developer> get(@PathVariable String name) {
        return developerRepository.findByName(name);
    }

    /**
     * DELETE  /developers/:id -> delete the "id" developer.
     */
    @RequestMapping(value = "/developers/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        developerRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("developer", id.toString())).build();
    }
}
