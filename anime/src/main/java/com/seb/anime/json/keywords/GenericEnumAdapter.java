package com.seb.anime.json.keywords;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by efreseb on 17/02/2017.
 * specific gson adapter for KeywordTypes
 */
public class GenericEnumAdapter implements JsonDeserializer<Keyword.KeywordTypes> {
    /**
     * @param json
     * @param typeOfT
     * @param context
     * @return
     * @throws JsonParseException
     */
    @Override
    public Keyword.KeywordTypes deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        Keyword.KeywordTypes[] scopes = Keyword.KeywordTypes.values();
        for (Keyword.KeywordTypes scope : scopes) {
            if (scope.name().equalsIgnoreCase(json.getAsString()))
                return scope;
        }
        return null;
    }
}