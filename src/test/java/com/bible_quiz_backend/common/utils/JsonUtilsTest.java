package com.bible_quiz_backend.common.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JsonUtilsTest {

    @Test
    void parseOptions_json문자열로_입력하면_List로반환한다2() {
        // given
        String json = "[\"자기 집에\", \"유다의 집에\", \"친구 아둘람 사람 히라의 집에\", \"아버지 집에\"]";

        // when
        List<String> result = JsonUtils.parseOptions(json);

        // then
        assertThat(result).hasSize(4)
                .containsExactly(
                        "자기 집에",
                        "유다의 집에",
                        "친구 아둘람 사람 히라의 집에",
                        "아버지 집에"
                );
    }

    @Test
    void parseOptions_json문자열로_입력하면_List로반환한다() {
        // given
        String json = "[\"A\", \"B\", \"C\", \"D\"]";

        // when
        List<String> result = JsonUtils.parseOptions(json);

        // then
        assertThat(result).hasSize(4)
                .containsExactly("A", "B", "C", "D");
    }

    @Test
    void parseOptions_빈_문자열_입력시_빈리스트반환() {
        // given
        String empty = "";

        // when
        List<String> result = JsonUtils.parseOptions(empty);

        // then
        assertThat(result).isEmpty();
    }


    @Test
    void parseOptions_null입력시_빈리스트반환() {
        // when
        List<String> result = JsonUtils.parseOptions(null);

        // then
        assertThat(result).isEmpty();
    }
}