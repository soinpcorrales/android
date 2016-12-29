
package com.labs.soin.pokedex.models;

import java.util.HashMap;
import java.util.Map;

public class Stat {

    private Stat_ stat;
    private Integer effort;
    private Integer baseStat;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Stat_ getStat() {
        return stat;
    }

    public void setStat(Stat_ stat) {
        this.stat = stat;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public Integer getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(Integer baseStat) {
        this.baseStat = baseStat;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
