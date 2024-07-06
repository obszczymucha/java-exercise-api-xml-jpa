package org.obszczymucha.tradereportingengine.service.utils;

public class NameSorter {
    public static String sort(final String name) {
        if (name == null) {
            return null;
        }

        final char[] chars = name.toCharArray();
        java.util.Arrays.sort(chars);
        return new String(chars);
    }
}
