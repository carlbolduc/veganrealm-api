package net.veganrealm.api.Resources;

import net.veganrealm.api.api.Facet;
import net.veganrealm.api.api.Recipe;
import net.veganrealm.api.api.Results;
import net.veganrealm.api.jdbi.RecipeDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by carl on 2017-06-20.
 */
@Path("/recipes")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class RecipeResource {

    private RecipeDAO recipeDAO;

    public RecipeResource(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    @GET
    public List<Recipe> getRecipes() {
        return new ArrayList<>();
    }

    @GET
    @Path("/{keyword}")
    public Results getResults(@PathParam("keyword") String keyword) {
        Results results = new Results(keyword);
        results.setRecipes(recipeDAO.findAllRecipes(keyword));
        List<Integer> ids = results.getRecipes().stream().map(Recipe::getId).collect(Collectors.toList());
        List<Facet> facets = new ArrayList<>();
        Facet authorFacet = new Facet("author");
        authorFacet.setFacetValues(recipeDAO.listAuthorFacetValues(ids));
        facets.add(authorFacet);
        results.setFacets(facets);
        return results;
    }

    @GET
    @Path("/date/{keyword}")
    public List<Recipe> getRecipesSortedByDate(@PathParam("keyword") String keyword) {
        return recipeDAO.findAllRecipesSortedByDate(keyword);
    }

}
