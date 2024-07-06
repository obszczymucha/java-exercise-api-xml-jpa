package org.obszczymucha.tradereportingengine.repository.filteringstrategy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.obszczymucha.tradereportingengine.repository.TradeDataRepository;
import org.obszczymucha.tradereportingengine.repository.entity.TradeDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.val;

@DataJpaTest
class SellerPartyFilteringStrategyTest {
    @Autowired
    private TradeDataRepository repository;

    @Test
    void shouldApplyFilteringStrategy() {
        // Given
        repository.saveAll(List.of(
                trade("EMU_BANK", "AUD"),
                trade("BISON_BANK", "AUD"),
                trade("OTHER_BANK", "USD"),
                trade("EMU_BANK", "USD")));

        // When
        val results = repository.findAll(new SellerPartyFilteringStrategy());

        // Then
        assertThat(results.size()).isEqualTo(2);
        assertThat(results).extracting("sellerParty").containsExactlyInAnyOrder("EMU_BANK", "BISON_BANK");
    }

    private TradeDataEntity trade(final String sellerParty, final String premiumCurrency) {
        return TradeDataEntity.builder()
                .sellerParty(sellerParty)
                .premiumCurrency(premiumCurrency)
                .build();
    }
}
