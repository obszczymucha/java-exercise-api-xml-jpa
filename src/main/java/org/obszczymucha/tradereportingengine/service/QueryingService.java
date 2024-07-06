package org.obszczymucha.tradereportingengine.service;

import java.util.List;

import org.obszczymucha.tradereportingengine.repository.FilteringStrategy;
import org.obszczymucha.tradereportingengine.repository.TradeDataRepository;
import org.obszczymucha.tradereportingengine.repository.entity.TradeDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.val;

@Service
public class QueryingService {
    private static final Logger logger = LoggerFactory.getLogger(QueryingService.class);
    private final TradeDataRepository repository;
    private final List<FilteringStrategy> strategies;

    @Autowired
    public QueryingService(final TradeDataRepository repository, final List<FilteringStrategy> strategies) {
        this.repository = repository;
        this.strategies = strategies;
    }

    public List<TradeDataEntity> query() {
        var spec = Specification.<TradeDataEntity>where(null);

        for (val strategy : strategies) {
            logger.info("Applying filtering strategy: {}", strategy.getClass().getSimpleName());
            spec = spec.and(strategy);
        }

        return repository.findAll(spec);
    }
}
