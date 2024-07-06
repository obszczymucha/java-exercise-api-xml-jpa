package org.obszczymucha.tradereportingengine.repository.filteringstrategy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.obszczymucha.tradereportingengine.repository.TradeDataRepository;
import org.obszczymucha.tradereportingengine.repository.entity.TradeDataEntity;
import org.obszczymucha.tradereportingengine.service.model.TradeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.val;

@DataJpaTest
class AnagramFilteringStrategyTest {
    @Autowired
    private TradeDataRepository repository;

    @Test
    void shouldApplyFilteringStrategy() {
        // Given
        repository.saveAll(List.of(
                trade("BISON_BANK", "BISON_BANK"),
                trade("OTHER_BANK", "BISON_BANK"),
                trade("EMU_BANK", "MUE_BANK")));

        // When
        val results = repository.findAll(new AnagramFilteringStrategy());

        // Then
        assertThat(results.size()).isEqualTo(1);
        assertThat(results).extracting("sellerParty").containsExactlyInAnyOrder("OTHER_BANK");
        assertThat(results).extracting("buyerParty").containsExactlyInAnyOrder("BISON_BANK");
    }

    private TradeDataEntity trade(final String sellerParty, final String buyerParty) {
        return TradeData.toEntity(TradeData.builder()
                .sellerParty(sellerParty)
                .buyerParty(buyerParty)
                .build());
    }
}
