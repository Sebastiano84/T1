package com.seb.anime.json;

import com.seb.anime.AbstractSpringMVCTest;
import com.seb.anime.json.toHtml.ValidationResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by efreseb on 03/04/2017.
 * JsonFileLoader test class
 */
@RunWith(SpringRunner.class)
public class JsonFileLoaderTest extends AbstractSpringMVCTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileLoaderTest.class);
    @Autowired
    protected JsonFileLoader jsonFileLoader;

    @Test
    public void testRefreshJsonData() throws FileNotFoundException {
        jsonFileLoader.refreshJsonData();
        assertNotNull(jsonFileLoader.getWordsList());
        assertThat(jsonFileLoader.getWordsList().stream().count(),
                greaterThan(0l));
    }

    @Test(expected = FileNotFoundException.class)
    public void testRefreshJsonDataWithNotExistingFile() throws FileNotFoundException {
        jsonFileLoader.refreshJsonData(new File("not_exists"));
    }

    @Test
    public void testRefreshJsonDataWithExistingFile() throws FileNotFoundException {
        jsonFileLoader.refreshJsonData(getClass().getResource("/basic.json").getFile());
        assertNotNull(jsonFileLoader.getWordsList());
        assertThat(jsonFileLoader.getWordsList().stream().count(),
                greaterThan(0l));
    }

    @Test
    public void testRefreshJsonDataWithEmptyFile() throws FileNotFoundException {
        jsonFileLoader.refreshJsonData(getClass().getResource("/jsonTestCases/emptyJsonTest1.json").getFile());
        assertNull(jsonFileLoader.getWordsList());

    }

    @Test
    public void testValidateText() {
        ValidationResult validationResult = jsonFileLoader.validateText("create ball");
        assertNotNull(validationResult);
        assertEquals(0, validationResult.getErrors().size());
    }


    @Test
    public void testValidateTextWith1Error() {
        ValidationResult validationResult = jsonFileLoader.validateText("create balla");
        assertNotNull(validationResult);
        assertEquals(1, validationResult.getErrors().size());
    }

    @Test
    public void testExecution() throws FileNotFoundException {
        jsonFileLoader.refreshJsonData(getClass().getResource("/jsonTestCases/test1.json").getFile());
        ValidationResult validationResult = jsonFileLoader.executeLine("create ball");
    }


    @Test
    public void testValidateTextWith1Error_2() throws FileNotFoundException {
        jsonFileLoader.refreshJsonData(getClass().getResource("/jsonTestCases/test1.json").getFile());

        ValidationResult validationResult = jsonFileLoader.validateText("create ball create");
        LOGGER.debug(validationResult.toString());
        assertNotNull(validationResult);
        assertEquals(1, validationResult.getErrors().size());
    }


}
