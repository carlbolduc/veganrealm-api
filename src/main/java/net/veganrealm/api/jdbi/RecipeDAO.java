package net.veganrealm.api.jdbi;

import net.veganrealm.api.api.Recipe;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

/**
 * Created by carl on 2017-06-20.
 */
public interface RecipeDAO {

    @SqlQuery("SELECT id, author, title, link, image_link, ingredients, published_at FROM recipes WHERE weighted_tsv @@ plainto_tsquery(:keyword)")
    List<Recipe> findAllRecipes(@Bind("keyword") String keyword);

    @SqlQuery("SELECT id, author, title, link, image_link, ingredients, published_at FROM recipes WHERE weighted_tsv @@ plainto_tsquery(:keyword) ORDER BY published_at DESC")
    @RegisterBeanMapper(Recipe.class)
    List<Recipe> findAllRecipesSortedByDate(@Bind("keyword") String keyword);

    @SqlQuery("SELECT COUNT(*) FROM recipes")
    int countAllRecipes();

}
