package fr.istic.taa.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Antoine Brezillon on 21/09/15.
 */

@Entity
public class Task {

    private long id;
    private Developer developer;
    private int priority;
    private String title;
    private String description;
    private UserStory userStory;

    public Task(){}

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    //  @JsonSerialize(using = CustomDepartmentSerializer.class)
    @JsonIgnore
    public Developer getDeveloper() {
        return developer;
    }

    @JsonIgnore
    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    public UserStory getUserStory() {
        return userStory;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }
}
