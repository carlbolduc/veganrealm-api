package net.veganrealm.api.api;

import java.util.List;

public class Results {

    private String query;
    private List<Recipe> recipes;
    private List<Facet> facets;

    public Results(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Facet> getFacets() {
        return facets;
    }

    public void setFacets(List<Facet> facets) {
        this.facets = facets;
    }

}
