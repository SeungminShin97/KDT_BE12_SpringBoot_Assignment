package org.example.assignment.common;

import java.security.SecureRandom;

public class CodeGenerator {
    private static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom random = new SecureRandom();

    /**
     * 랜덤 코드를 반환하는 메서드 입니다. <br>
     * 코드는 숫자, 대문자 영어로 이루어져 있습니다.
     * @param length 랜덤 코드의 길이
     * @return 랜덤 코드
     */
    public static String generateCode(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        return sb.toString();
    }

}
