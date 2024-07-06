package org.obszczymucha.tradereportingengine.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.obszczymucha.tradereportingengine.service.model.TradeData;
import org.obszczymucha.tradereportingengine.service.xmlparser.XmlParser;
import org.springframework.core.io.DefaultResourceLoader;

import lombok.val;

class ReportingServiceTest {
    @Test
    void shouldProcessAllEventFiles() throws IOException {
        // Given
        val loader = new DefaultResourceLoader();
        val service = new ReportingService(new XmlParser(), new ResourceFileLister(loader));
        val expected = List.<TradeData>of(
                new TradeData("EMU_BANK", "BISON_BANK", "150.00", "AUD"),
                new TradeData("LEFT_BANK", "EMU_BANK", "100.00", "AUD"));

        // When
        val result = service.report();

        // Then
        assertThat(result).containsAll(expected);
    }
}
