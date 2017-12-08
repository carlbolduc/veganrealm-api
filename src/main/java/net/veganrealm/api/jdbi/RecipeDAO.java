package net.veganrealm.api.jdbi;

import net.veganrealm.api.api.Recipe;
import net.veganrealm.api.api.RecipeMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by carl on 2017-06-20.
 */
@RegisterMapper(RecipeMapper.class)
public interface RecipeDAO {

    @SqlQuery("SELECT id, author, title, link, image_link, ingredients, published_at FROM recipes WHERE weighted_tsv @@ plainto_tsquery(:keyword)")
    List<Recipe> findAllRecipes(@Bind("keyword") String keyword);

    @SqlQuery("SELECT id, author, title, link, image_link, ingredients, published_at FROM recipes WHERE weighted_tsv @@ plainto_tsquery(:keyword) ORDER BY published_at DESC")
    List<Recipe> findAllRecipesSortedByDate(@Bind("keyword") String keyword);

    @SqlQuery("SELECT COUNT(*) FROM recipes")
    int countAllRecipes();

}
