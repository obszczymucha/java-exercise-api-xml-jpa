package org.obszczymucha.tradereportingengine.repository.filteringstrategy;

import org.obszczymucha.tradereportingengine.repository.FilteringStrategy;
import org.obszczymucha.tradereportingengine.repository.entity.TradeDataEntity;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class AnagramFilteringStrategy implements FilteringStrategy {
    @Override
    public Predicate toPredicate(final Root<TradeDataEntity> root, final CriteriaQuery<?> query,
            final CriteriaBuilder cb) {
        return cb.notEqual(root.get("sortedSeller"), root.get("sortedBuyer"));
    }
}
