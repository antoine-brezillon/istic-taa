package fr.istic.taa.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Antoine Brezillon on 21/09/15.
 */

@Entity
public class UserStory {

    private Long id;
    private String title;
    private String Description;
    private List<Task> tasks;
    private ProductBacklog productBacklog;

    public UserStory() { }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL)
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
    }
}
