
package com.labs.soin.pokedex.models;

import java.util.HashMap;
import java.util.Map;

public class Type {

    private Integer slot;
    private Type_ type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Type_ getType() {
        return type;
    }

    public void setType(Type_ type) {
        this.type = type;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
