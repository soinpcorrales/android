
package com.labs.soin.pokedex.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Move {

    private List<VersionGroupDetail> versionGroupDetails = null;
    private Move_ move;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<VersionGroupDetail> getVersionGroupDetails() {
        return versionGroupDetails;
    }

    public void setVersionGroupDetails(List<VersionGroupDetail> versionGroupDetails) {
        this.versionGroupDetails = versionGroupDetails;
    }

    public Move_ getMove() {
        return move;
    }

    public void setMove(Move_ move) {
        this.move = move;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
