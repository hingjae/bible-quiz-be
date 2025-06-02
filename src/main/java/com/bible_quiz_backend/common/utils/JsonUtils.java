package com.bible_quiz_backend.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<String> parseOptions(String rawOptions) {
        try {
            return objectMapper.readValue(rawOptions, new TypeReference<>() {});
        } catch (Exception e) {
            log.warn("parse options failed {}", rawOptions);
            return List.of();
        }
    }
}
