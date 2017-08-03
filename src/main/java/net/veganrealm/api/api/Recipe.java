package net.veganrealm.api.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by carl on 2017-06-20.
 */
public class Recipe {

    private int id;
    private String author;
    private String imageLink;
    private String[] ingredients;
    private String link;
    private String title;

    public Recipe(int id, String author, String title, String link, String imageLink, String joinedIngredients) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.link = link;
        this.imageLink = imageLink;
        this.ingredients = joinedIngredients.split("\\|");
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

}
