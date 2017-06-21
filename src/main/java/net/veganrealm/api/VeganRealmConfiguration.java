package net.veganrealm.api;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class VeganRealmConfiguration extends Configuration {

    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

}
