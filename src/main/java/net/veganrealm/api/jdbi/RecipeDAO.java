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

    @SqlQuery("SELECT id, title, link, image_link, ingredients FROM recipes WHERE weighted_tsv @@ plainto_tsquery(:keyword)")
    List<Recipe> findAllRecipes(@Bind("keyword") String keyword);

}
