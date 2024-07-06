package org.obszczymucha.tradereportingengine.repository.filteringstrategy;

import org.obszczymucha.tradereportingengine.repository.FilteringStrategy;
import org.obszczymucha.tradereportingengine.repository.entity.TradeDataEntity;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.val;

@Component
public class SellerPartyFilteringStrategy implements FilteringStrategy {
    private static final String SELLER_PARTY = "sellerParty";
    private static final String PREMIUM_CURRENCY = "premiumCurrency";

    @Override
    public Predicate toPredicate(final Root<TradeDataEntity> root, final CriteriaQuery<?> query,
            final CriteriaBuilder cb) {
        val emuBank = cb.and(
                cb.equal(root.get(SELLER_PARTY), "EMU_BANK"),
                cb.equal(root.get(PREMIUM_CURRENCY), "AUD"));

        val bisonBank = cb.and(
                cb.equal(root.get(SELLER_PARTY), "BISON_BANK"),
                cb.equal(root.get(PREMIUM_CURRENCY), "AUD"));

        return cb.or(emuBank, bisonBank);
    }
}
