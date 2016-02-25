package fr.istic.taa.web.rest;

import fr.istic.taa.domain.ProductBacklog;
import fr.istic.taa.repository.ProductBacklogRepository;
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
@RequestMapping("ProductBacklog")
public class ProductBacklogResource {

    @Autowired
    private ProductBacklogRepository productBacklogRepository;

    /**
     * POST  /productbacklogs -> Create a new productbacklog.
     */
    @RequestMapping(value = "/productbacklogs",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductBacklog> create(@RequestBody ProductBacklog productBacklog) throws URISyntaxException {
        if (productBacklog.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new productbacklogs cannot already have an ID").body(null);
        }
        ProductBacklog result = productBacklogRepository.save(productBacklog);
        return ResponseEntity.created(new URI("/api/productbacklogs/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("productBacklog", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /productbacklogs -> Updates an existing productBacklog.
     */
    @RequestMapping(value = "/productbacklogs",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductBacklog> update(@RequestBody ProductBacklog productBacklog) throws URISyntaxException {
        if (productBacklog.getId() == null) {
            return create(productBacklog);
        }
        ProductBacklog result = productBacklogRepository.save(productBacklog);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("productBacklog", productBacklog.getId().toString()))
                .body(result);
    }

    /**
     * GET  /productbacklogs -> get all the productbacklogs.
     */
    @RequestMapping(value = "/productbacklogs",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductBacklog> getAll() {
        return productBacklogRepository.findAll();
    }

    /**
     * GET  /productbacklogs/:id -> get the "id" productBacklog.
     */
    @RequestMapping(value = "/productbacklogs/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductBacklog> get(@PathVariable Long id) {
        return Optional.ofNullable(productBacklogRepository.findOne(id))
                .map(productBacklog -> new ResponseEntity<>(
                        productBacklog,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /productbacklogs/:id -> delete the "id" productBacklog.
     */
    @RequestMapping(value = "/productbacklogs/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productBacklogRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("productBacklog", id.toString())).build();
    }
}
