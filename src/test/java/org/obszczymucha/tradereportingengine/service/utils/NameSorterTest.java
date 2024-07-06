package org.obszczymucha.tradereportingengine.service.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class NameSorterTest {
    @ParameterizedTest
    @CsvSource(value = {
            "BISON_BANK,ABBIKNNOS_",
            "OTHER_BANK,ABEHKNORT_",
            "EMU_BANK,ABEKMNU_",
            ",",
            "'',''",
            "' ',' '",
            "null,null"
    }, nullValues = "null")
    void shouldSort(String given, String expected) {
        assertThat(NameSorter.sort(given)).isEqualTo(expected);
    }
}
