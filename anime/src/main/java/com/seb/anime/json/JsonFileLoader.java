package com.seb.anime.json;

import bsh.EvalError;
import bsh.Interpreter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seb.anime.json.keywords.GenericEnumAdapter;
import com.seb.anime.json.keywords.Keyword;
import com.seb.anime.json.keywords.WordsList;
import com.seb.anime.json.toHtml.Paragraph;
import com.seb.anime.json.toHtml.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.seb.anime.json.keywords.Keyword.*;

@Component
public class JsonFileLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileLoader.class);

    @SuppressWarnings({"HardcodedFileSeparator", "HardcodedLineSeparator"})
    private static final String LINE_SEPARATOR = "\\r?\\n";
    public static final String BASIC_JSON = "/basic.json";
    private final Interpreter interpreter;
    private WordsList wordsList;


    public JsonFileLoader(@Autowired final Interpreter interpreter) throws java.io.FileNotFoundException {

        LOGGER.info("START");
        this.interpreter = interpreter;
        refreshJsonData();
    }

    public void refreshJsonData() throws FileNotFoundException {
        URL url = getClass().getResource(BASIC_JSON);
        if(url == null) {
            LOGGER.error("Cannot find file {}",BASIC_JSON);
            return;
        }
        refreshJsonData(url.getFile());
    }

    public void refreshJsonData(String filePath) throws FileNotFoundException {
        refreshJsonData(new File(filePath));
    }

    public void refreshJsonData(File file) throws FileNotFoundException {
        loadJsonData(file);
    }

    private void loadJsonData(File file) throws FileNotFoundException {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(KeywordTypes.class, new GenericEnumAdapter());
        final Gson gson = gsonBuilder.create();
        wordsList = gson.fromJson(new FileReader(file), WordsList.class);
        if (wordsList != null) {
            wordsList.forEach(w -> LOGGER.debug(w.toString()));
        } else {
            LOGGER.debug("{} was empty or not valid", file);
        }
    }

    public ValidationResult validateText(String text) {
        LOGGER.debug(text);

        List<String> lines = Arrays.asList(text.split(LINE_SEPARATOR, -1));
        ValidationResult validationResults = new ValidationResult();
        lines.forEach(line -> validateLine(line, validationResults));
        return validationResults;
    }

    private void validateLine(String line, ValidationResult validationResult) {
        AtomicReference<Keyword> atomicReference = new AtomicReference<Keyword>();
        Arrays.asList(line.split("\\s+")).forEach(word -> {

            Optional<Keyword> foundKeyword = wordsList.findByName(word);
            if (foundKeyword.isPresent()) {
                validationResult.addDebugInfo(new Paragraph(String.format("Found %s", word), Color.blue));
                Keyword lastKeyword = atomicReference.get();
                Keyword keyword = foundKeyword.get();
                boolean isValid = true;
                if (lastKeyword != null) {
                    if (lastKeyword.getFollowedBy() != null && lastKeyword.getFollowedBy().getExcluded() != null) {
                        isValid = lastKeyword.getFollowedBy().getExcluded().stream().filter((e) -> keyword.getName().equals(e)).count() == 0;
                    }
                    String exclusionFilter;
                    if (isValid && lastKeyword.getFollowedBy() != null && (exclusionFilter = lastKeyword.getFollowedBy().getExclusionFilter()) != null) {
                        isValid = wordsList.stream().noneMatch(w -> {
                                    String function = "import " + Keyword.class.getName() + ";" + exclusionFilter;
                                    try {
                                        interpreter.set("word", w);
                                        Object eval = interpreter.eval(function);
                                        Objects.requireNonNull(eval, "eval must not be null");
                                        return (boolean) eval;
                                    } catch (EvalError | NullPointerException evalError) {
                                        LOGGER.error(String.format("Error during evaluation of [%s] with param [%s]", function, w), evalError);
                                        return false;
                                    }
                                }
                        );
                        if (isValid && lastKeyword.getFollowedBy() != null && lastKeyword.getFollowedBy().getIncluded() != null) {
                            isValid = lastKeyword.getFollowedBy().getIncluded().stream().filter((e) -> keyword.getName().equals(e)).count() == 1;
                        }
                        String inclusionFilter;
                        if (isValid && lastKeyword.getFollowedBy() != null && (inclusionFilter = lastKeyword.getFollowedBy().getInclusionFilter()) != null) {
                            isValid = wordsList.stream().anyMatch(w -> {
                                        String function = "import " + Keyword.class.getName() + ";" + inclusionFilter;
                                        try {
                                            interpreter.set("word", w);
                                            Object eval = interpreter.eval(function);
                                            Objects.requireNonNull(eval, "eval must not be null");
                                            return (boolean) eval;
                                        } catch (EvalError | NullPointerException evalError) {
                                            LOGGER.error(String.format("Error during evaluation of [%s] with param [%s]", function, w), evalError);
                                            return false;
                                        }
                                    }
                            );
                        }
                    }
                    if (!isValid) {
                        validationResult.addWord(new Paragraph(word, Color.red));
                        validationResult.addError(new Paragraph(keyword.getName() + " not valid", Color.red));
                    } else {
                        validationResult.addWord(new Paragraph(word, Color.green));
                    }
                } else {
                    validationResult.addWord(new Paragraph(word, Color.black));
                }
                atomicReference.set(keyword);
            } else {
                validationResult.addWord(new Paragraph(word, Color.yellow));
                validationResult.addError(new Paragraph(String.format("%s not found", word), Color.red));
            }
        });


    }


    WordsList getWordsList() {
        return wordsList;
    }

    public ValidationResult executeLine(String line) {
        StringBuilder sb = new StringBuilder(line);
        ValidationResult validationResult = new ValidationResult();
        String[] array = line.split("\\s+");
        Collections.reverse(Arrays.asList(array));
        AtomicReference<Object> eval = new AtomicReference<>();
        Arrays.asList(array).stream().anyMatch(word -> {
            Optional<Keyword> foundKeyword = wordsList.findByName(word);
            if (foundKeyword.isPresent()) {
                Keyword keyword = foundKeyword.get();
                String codeString = keyword.getCode();
                String paramName = keyword.getParamName();
                if (codeString != null) {
                    try {
                        if (paramName != null) {
                            interpreter.set(paramName, eval.get());
                        }
                        eval.set(interpreter.eval(codeString));
                    } catch (EvalError evalError) {
                        LOGGER.error(String.format("Error during evaluation of [%s] with param [%s]", codeString, paramName), evalError);
                        return true;
                    }
                } else {
                    eval.set(keyword);

                }
                return false;
            } else {
                validationResult.addError(new Paragraph(String.format("%s not found", word), Color.red));
                return true;
            }
        });
        return validationResult;
    }
}