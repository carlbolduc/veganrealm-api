package net.veganrealm.api.api;

import java.util.List;

public class Facet {

    private String name;
    private List<FacetValue> facetValues;

    public Facet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<FacetValue> getFacetValues() {
        return facetValues;
    }

    public void setFacetValues(List<FacetValue> facetValues) {
        this.facetValues = facetValues;
    }
}
