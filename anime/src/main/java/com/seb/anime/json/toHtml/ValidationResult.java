package com.seb.anime.json.toHtml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationResult implements Serializable{
    private final List<Paragraph> words;
    private final List<Paragraph> errors;
    private final List<Paragraph> debugInfo;

    public ValidationResult() {
        this.words = new ArrayList<Paragraph>();
        this.errors = new ArrayList<Paragraph>();
        debugInfo = new ArrayList<Paragraph>();
    }

    public List<Paragraph> getWords() {
        return words;
    }

    public List<Paragraph> getErrors() {
        return errors;
    }

    public boolean addWord(Paragraph paragraph) {
        return words.add(paragraph);
    }

    public boolean addError(Paragraph paragraph) {
        return errors.add(paragraph);
    }

    public boolean addDebugInfo(Paragraph paragraph) {
        return debugInfo.add(paragraph);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ValidationResult{");
        sb.append("\nwords=").append(words);
        sb.append("\nerrors=").append(errors);
        sb.append("\ndebugInfo=").append(debugInfo);
        sb.append('}');
        return sb.toString();
    }
}
