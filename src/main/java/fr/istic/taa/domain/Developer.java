package fr.istic.taa.domain;

import javax.persistence.Entity;import javax.persistence.FetchType;import javax.persistence.GeneratedValue;import javax.persistence.Id;import javax.persistence.OneToMany;
import java.lang.String;import java.util.List;

/**
 * Created by Antoine Brezillon on 21/09/15.
 */
@Entity
public class Developer {

    private Long id;
    private String name;
    private List<Task> currentTasks;

    public Developer() {
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "developer", fetch = FetchType.EAGER)
    public List<Task> getCurrentTasks() {
        return currentTasks;
    }

    public void setCurrentTasks(List<Task> currentTasks) {
        this.currentTasks = currentTasks;
    }

}
