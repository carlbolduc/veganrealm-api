package net.veganrealm.api.Resources;

import net.veganrealm.api.jdbi.RecipeDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/statistics")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class StatisticsResource {

    private RecipeDAO recipeDAO;

    public StatisticsResource(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    @GET
    @Path("/recipes-count")
    public int recipesCount() {
        return recipeDAO.countAllRecipes();
    }

}
