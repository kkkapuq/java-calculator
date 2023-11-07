package study;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("SetCollection 테스트")
public class SetCollectionTest {
    // given, when
    private Set<Integer> numbers;
    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @Test
    @DisplayName("size()를 활용해 set의 크기(3)을 구한다.")
    void GetSizeWithSizeMethod() {
        // then
        assertThat(numbers.size()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("contains()를 통해 1,2,3의 값이 있는지 확인한다.")
    void IsSetContainsValues(int input){
        // then
        assertThat(numbers.contains(input)).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:true", "3:true", "4:false", "5:false"}, delimiter = ':')
    @DisplayName("1, 2, 3의 contains()는 true지만, 4, 5값을 넣으면 false가 반환된다.")
    void IsSetContainsValuesIfInputEachValues(String input, String expected){
        // when
        String actualValue = Boolean.toString(numbers.contains(Integer.parseInt(input)));
        // then
        assertThat(actualValue).isEqualTo(expected);
    }

}
