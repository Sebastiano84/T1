
package com.seb.anime.json.keywords;

import java.util.List;

public class Keyword {


    private final static long serialVersionUID = -5666348671894287072L;
    private String name;
    private String code;
    private KeywordTypes type;
    private FollowedBy followedBy;
    private String paramName = null;
    private List<String> tags = null;

    public FollowedBy getFollowedBy() {
        return followedBy;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getParamName() {
        return paramName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Keyword{");
        sb.append("name='").append(name).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", type=").append(type);
        sb.append(", followedBy=").append(followedBy);
        sb.append(", paramName=").append(paramName);
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }

    public enum KeywordTypes {
        Action, Object
    }
}
