package org.obszczymucha.tradereportingengine.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lombok.val;

class ReportingServiceTest {
    @ParameterizedTest
    @CsvSource(value = {
            "Implement me!",
    }, delimiter = '|')
    void shouldReport(String expected) {
        // Given
        val service = new ReportingService();

        // When
        val result = service.report();

        // Then
        assertThat(result).isEqualTo(expected);
    }
}
