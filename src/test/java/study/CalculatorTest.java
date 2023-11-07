package study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class CalculatorTest {
    //given
    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @CsvSource(value = {"1;1", "1,2;3", "1,2:3;6", "1,2,3:4,5:6,7:8,9:10;55"}, delimiter = ';')
    @DisplayName("쉼표(,)와 콜론(:)을 구분자로 삼아 구분한다.")
    public void seperatedByDefaultDelim(String input, int expected) {
        // when
        int sum = calculator.add(input);
        // then
        assertThat(sum).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1;1", "'//%\n1%2';3", "'//!\n2!3!1';6", "'//@\n1@2@3@4@5';15"}, delimiter = ';')
    @DisplayName("커스텀 구분자로 구분한다.")
    public void seperatedByCustomDelim(String input, int expected) {
        // when
        int sum = calculator.add(input);
        // then
        assertThat(sum).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1;1", "'1,2';3", "'//!\n1!2:3';6", "'//@\n1@2@3,4,5@6,7:8,9:10';55"}, delimiter = ';')
    @DisplayName("쉼표(,)와 콜론(:), 커스텀 구분자로 구분한다.")
    public void seperatedByMixedDelim(String input, int expected) {
        // when
        int sum = calculator.add(input);
        // then
        assertThat(sum).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"'-1';1", "'1,-2';3", "'1,-2:3';6", "'//@\n1@2@3,-4,-5@6,7:8,9:10';55"}, delimiter = ';')
    @DisplayName("음수가 있으면 RuntimeException 발생")
    public void throwExceptionIfContainNegative(String input, int expected) {
        // when, then
        assertThatThrownBy(() ->
                calculator.add(input)
        ).isInstanceOf(RuntimeException.class).hasMessageContaining("음수는 들어갈 수 없습니다.");
    }
}
