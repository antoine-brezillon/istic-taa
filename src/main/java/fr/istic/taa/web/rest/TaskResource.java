package fr.istic.taa.web.rest;

import fr.istic.taa.domain.Task;
import fr.istic.taa.repository.TaskRepository;
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
@RequestMapping("Task")
public class TaskResource {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * POST  /tasks -> Create a new task.
     */
    @RequestMapping(value = "/tasks",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Task> create(@RequestBody Task task) throws URISyntaxException {
        if (task.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new task cannot already have an ID").body(null);
        }
        Task result = taskRepository.save(task);
        return ResponseEntity.created(new URI("/api/tasks/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("task", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /tasks -> Updates an existing task.
     */
    @RequestMapping(value = "/tasks",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Task> update(@RequestBody Task task) throws URISyntaxException {
        if (task.getId() == null) {
            return create(task);
        }
        Task result = taskRepository.save(task);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("task", task.getId().toString()))
                .body(result);
    }

    /**
     * GET  /tasks -> get all the tasks.
     */
    @RequestMapping(value = "/tasks",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    /**
     * GET  /tasks/:id -> get the "id" task.
     */
    @RequestMapping(value = "/tasks/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Task> get(@PathVariable Long id) {
        return Optional.ofNullable(taskRepository.findOne(id))
                .map(task -> new ResponseEntity<>(
                        task,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /tasks/:id -> delete the "id" task.
     */
    @RequestMapping(value = "/tasks/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("task", id.toString())).build();
    }
}
