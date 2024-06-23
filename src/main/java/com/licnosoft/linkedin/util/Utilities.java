package com.licnosoft.linkedin.util;

import com.licnosoft.linkedin.config.exceptions.AppValidationException;
import com.licnosoft.linkedin.domain.general.PlainEntity;
import com.licnosoft.linkedin.model.general.EntityDTO;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Utilities {
    private static final int DEFAULT_COUNT = 20;

    private static final char[] DICTIONARY_62
            = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private Utilities() {
        throw new IllegalStateException("Utility class");
    }

    public static String encodeBaseX(String value) {
        char[] dictionary = DICTIONARY_62;
        List<Character> result = new ArrayList<>();
        BigInteger base = new BigInteger("" + dictionary.length);
        int exponent = 1;
        BigInteger remaining = new BigInteger(value);
        while (true) {
            BigInteger a = base.pow(exponent);
            BigInteger b = remaining.mod(a);
            BigInteger c = base.pow(exponent - 1);
            BigInteger d = b.divide(c);
            result.add(dictionary[d.intValue()]);
            remaining = remaining.subtract(b);
            if (remaining.equals(BigInteger.ZERO)) {
                break;
            }
            exponent++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = result.size() - 1; i >= 0; i--) {
            sb.append(result.get(i));
        }
        return sb.toString();
    }

    public static String decodeBaseX(String str, boolean lpad) {
        char[] dictionary = DICTIONARY_62;
        char[] chars = new char[str.length()];
        str.getChars(0, str.length(), chars, 0);

        char[] chars2 = new char[str.length()];
        int i = chars2.length - 1;
        for (char c : chars) {
            chars2[i--] = c;
        }

        Map<Character, BigInteger> dictMap = new HashMap<>();
        int j = 0;
        for (char c : dictionary) {
            dictMap.put(c, new BigInteger("" + j++));
        }

        BigInteger bi = BigInteger.ZERO;
        BigInteger base = new BigInteger("" + dictionary.length);
        int exponent = 0;
        for (char c : chars2) {
            BigInteger a = dictMap.get(c);
            BigInteger b = base.pow(exponent).multiply(a);
            bi = bi.add(new BigInteger("" + b));
            exponent++;
        }
        String result = bi.toString();
        if (lpad && result.length() < DEFAULT_COUNT) {
            result = padLeftZeros(result, DEFAULT_COUNT);
        }
        return result;
    }

    public static String encodeMd5(String value) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(value));
        return String.format("%032x", new BigInteger(1, md5.digest()));
    }

    public static String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }

    public static String nullToEmpty(String string) {
        return Objects.toString(string, "");
    }

    public static Integer getLastChars(String value, int number) {
        if (value == null) {
            return null;
        }
        try {
            if (value.length() > number) {
                return Integer.parseInt(value.substring(value.length() - number));
            }
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    public static String randomAlphaNumeric() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        return new Random().ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(20)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static boolean validateEntityDto(EntityDTO dto, boolean insert) {
        return (insert && dto.getId() == null) || (!insert && dto.getId() != null);
    }

    public static void validateUniqueConstraint(PlainEntity<? extends PlainEntity> entity, Optional<? extends PlainEntity> dbResult, String msg) {
        if (!(dbResult.isEmpty() || (entity.getId() != null && dbResult.get().getId().equals(entity.getId())))) {
            throw new AppValidationException(msg);
        }
    }

    public static <T> List<T> concatenate(List<T>... lists) {
        return Stream.of(lists)
                .flatMap(Collection::stream)
                .toList();
    }

    public static String decodeString(String input) {
        Pattern pattern = Pattern.compile("\\\\u[0-9a-fA-F]{4}");
        Matcher matcher = pattern.matcher(input);

        StringBuilder decodedString = new StringBuilder();

        while (matcher.find()) {
            String unicodeSequence = matcher.group();
            char unicodeChar = (char) Integer.parseInt(unicodeSequence.substring(2), 16);
            matcher.appendReplacement(decodedString, Character.toString(unicodeChar));
        }

        matcher.appendTail(decodedString);
        return decodedString.toString();
    }
}