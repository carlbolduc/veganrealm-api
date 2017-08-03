package net.veganrealm.api.api;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by carl on 2017-06-21.
 */
public class RecipeMapper implements ResultSetMapper<Recipe>{

    public Recipe map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Recipe(r.getInt("id"), r.getString("author"), r.getString("title"), r.getString("link"), r.getString("image_link"), r.getString("ingredients"));
    }
}
