package org.obszczymucha.tradereportingengine.repository;

import org.obszczymucha.tradereportingengine.repository.entity.TradeDataEntity;
import org.springframework.data.jpa.domain.Specification;

public interface FilteringStrategy extends Specification<TradeDataEntity> {
}
