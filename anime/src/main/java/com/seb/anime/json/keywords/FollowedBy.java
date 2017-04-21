
package com.seb.anime.json.keywords;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class FollowedBy {

    private List<String> included;
    private List<String> excluded;
    private String inclusionFilter;
    private String exclusionFilter;

    /**
     * No args constructor for use in serialization
     */
    public FollowedBy() {


    }

    public List<String> getExcluded() {
        return excluded;
    }

    public String getExclusionFilter() {
        return exclusionFilter;
    }

    public List<String> getIncluded() {
        return included;
    }

    public String getInclusionFilter() {
        return inclusionFilter;
    }
}
