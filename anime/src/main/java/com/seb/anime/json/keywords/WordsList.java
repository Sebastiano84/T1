package com.seb.anime.json.keywords;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by efreseb on 17/02/2017.
 * class containing all keywords
 */
public class WordsList {
    private List<Keyword> keywords;

    public WordsList(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public final void forEach(Consumer<? super Keyword> action) {
        if (keywords != null) keywords.forEach(action);
    }

    public Stream<Keyword> stream() {
        return keywords.stream();
    }

    public final Optional<Keyword> findByName(final String word) {
        return keywords.stream().filter(e -> e != null && e.getName() != null && e.getName().equals(word)).findFirst();
    }
}
