package com.nuramov.hw01Questionnaire.CsvParser;

import java.io.InputStream;
import java.util.Map;

/**
 *
 */
public interface CsvParser {

    /**
     *
     * @return
     */
    Map<String, String> getFileFromResourceAsMap();
}
