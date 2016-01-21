package fr.istic.taa.domain;

import javax.persistence.CascadeType;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.Id;import javax.persistence.OneToMany;
import java.lang.String;import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine Brezillon on 21/09/15.
 */
@Entity
public class ProductBacklog {

    private long id;
    private String title;
    private String description;
    private List<UserStory> userStories;

    public ProductBacklog() {
        this.userStories = new ArrayList<UserStory>();
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @OneToMany(mappedBy = "productBacklog",cascade = CascadeType.REMOVE)
    public List<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(List<UserStory> userStories) {
        this.userStories = userStories;
    }

}
