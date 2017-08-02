package net.veganrealm.api.Resources;

import net.veganrealm.api.api.Recipe;
import net.veganrealm.api.jdbi.RecipeDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    @Path("/{keyword}")
    public List<Recipe> getRecipes(@PathParam("keyword") String keyword) {
        List<Recipe> recipes = recipeDAO.findAllRecipes(keyword);
        return recipes;
    }

}
