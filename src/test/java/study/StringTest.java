package study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("String 테스트")
public class StringTest {

    @Test
    @DisplayName("'1, 2' 를 split 하면 ['1', '2']로 분리")
    void IsSplitIfTwoElementArrSplit() {
        // given
        String a = "1,2";
        // when
        String[] arr = a.split(",");
        // then
        assertThat(arr).isEqualTo(new String[]{"1", "2"});
    }

    @Test
    @DisplayName("'1,' 를 split 하면 ['1']만 생성")
    void IsOnlyOneOutFromString() {
        // given
        String a = "1,";
        // when
        String[] arr = a.split(",");
        // then
        assertThat(arr).isEqualTo(new String[]{"1"});
    }

    @Test
    @DisplayName("(1,2)를 입력하면 1,2가 반환")
    void RemoveBracket() {
        // given
        String a = "(1,2)";
        // when
        a = a.substring(1, 4);
        // then
        assertThat(a).isEqualTo("1,2");
    }

    @Test
    @DisplayName("charAt()을 써서 특정 위치의 문자를 가져오기")
    void GetCharWithCharAtFromString() {
        // given
        String a = "abc";
        // when
        char c = a.charAt(2);
        // then
        assertThat(c).isEqualTo('c');
    }

    @Test
    @DisplayName("charAt()에 부적합한 인덱스가 들어가면 에러 발생")
    void FailToGetCharIfIndexIsInvalid() {
        // given
        String a = "abc";
        // when, then
        assertThatThrownBy(() ->
                a.charAt(4)
        ).isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("String index out of range: 4");
    }
}
