package net.veganrealm.api;

import de.thomaskrille.dropwizard_template_config.TemplateConfigBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import net.veganrealm.api.Resources.RecipeResource;
import net.veganrealm.api.Resources.StatisticsResource;
import net.veganrealm.api.jdbi.RecipeDAO;
import io.dropwizard.db.DataSourceFactory;

import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.jdbi.v3.core.Jdbi;


import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class VeganRealmApplication extends io.dropwizard.Application<VeganRealmConfiguration> {

    public static void main(String[] args) throws Exception {
        new VeganRealmApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<VeganRealmConfiguration> bootstrap) {

        // Makes Dropwwizard unwrap JDBI exceptions are return intelligent values to clients
        bootstrap.addBundle(new JdbiExceptionsBundle());

        // Support templates inside the config files
        bootstrap.addBundle(new TemplateConfigBundle());

        bootstrap.addBundle(new MigrationsBundle<VeganRealmConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(VeganRealmConfiguration config) {
                return config.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(VeganRealmConfiguration config, Environment environment) throws Exception {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
        final RecipeDAO recipeDAO = jdbi.onDemand(RecipeDAO.class);

        setupCors(environment);

        environment.jersey().register(new RecipeResource(recipeDAO));
        environment.jersey().register(new StatisticsResource(recipeDAO));
    }

    private void setupCors(Environment environment) {
        final FilterRegistration.Dynamic filterRegistration = environment.servlets().addFilter("crossOriginRequests", CrossOriginFilter.class);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        filterRegistration.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filterRegistration.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "*");
        filterRegistration.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "PUT,GET,POST,DELETE,OPTIONS");
    }

}
