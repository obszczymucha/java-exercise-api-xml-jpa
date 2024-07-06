package org.obszczymucha.tradereportingengine.repository;

import org.obszczymucha.tradereportingengine.repository.entity.TradeDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TradeDataRepository extends JpaRepository<TradeDataEntity, Long>, JpaSpecificationExecutor<TradeDataEntity> {
}
