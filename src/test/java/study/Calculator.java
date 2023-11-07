package study;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private static String DELIM = ",|:";
    private static final String CUSTOM_DELIM = "//(.*)\n(.*)";

    // 문자열 더하는 함수
    public int add(String text) {
        if (isEmpty(text)) {
            return 0;
        }
        String[] values = split(text);
        return Arrays.stream(values)
                .mapToInt(this::toPositive)
                .sum();
    }

    // 빈 문자열인지 체크하는 함수
    private boolean isEmpty(String text) {
        return text == null || text.isEmpty();
    }

    // 패턴으로 문자열 분리
    private String[] split(String text) {
        // 커스텀 구분자가 있으면 기존 구분자에 추가
        if(text.charAt(0) ==  '/') {
            DELIM += "|" + text.charAt(2);
            text = text.substring(4);
        }

        String[] arr = text.split(DELIM);
        return arr;
    }

    // 문자열을 양의 정수로 변환
    private int toPositive(String value) {
        // 변환된 숫자가 음수거나 숫자가 아니라면 예외 발생
        if (!Character.isDigit(value.charAt(0)) || Integer.parseInt(value) < 0) {
            throw new RuntimeException("음수는 들어갈 수 없습니다.");
        }
        return Integer.parseInt(value);
    }

}
