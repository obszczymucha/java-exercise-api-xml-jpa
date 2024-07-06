package org.obszczymucha.tradereportingengine.service.xmlparser;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lombok.val;

class XmlParserTest {
    @ParameterizedTest
    @CsvSource(value = {
            "event0.xml,LEFT_BANK,EMU_BANK,100.00,AUD",
            "event6.xml,EMU_BANK,BISON_BANK,150.00,AUD",
    })
    void shouldParse(String filename, String buyer, String seller, String amount, String currency) {
        // Given
        val parser = new XmlParser();
        val stream = getClass().getClassLoader().getResourceAsStream("data/" + filename);

        // When
        val result = parser.parse(stream);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getBuyerParty()).isEqualTo(buyer);
        assertThat(result.getSellerParty()).isEqualTo(seller);
        assertThat(result.getPremiumAmount()).isEqualTo(amount);
        assertThat(result.getPremiumCurrency()).isEqualTo(currency);
    }

    @Test
    void shouldThrowIfTheStreamIsNull() {
        // Given
        val parser = new XmlParser();

        // When
        assertThatThrownBy(() -> parser.parse(null)).isInstanceOf(XmlParsingException.class);
    }
}
