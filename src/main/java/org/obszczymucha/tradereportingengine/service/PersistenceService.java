package org.obszczymucha.tradereportingengine.service;

import java.util.List;

import org.obszczymucha.tradereportingengine.repository.TradeDataRepository;
import org.obszczymucha.tradereportingengine.repository.entity.TradeDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistenceService {
    private static final Logger logger = LoggerFactory.getLogger(PersistenceService.class);
    private final TradeDataRepository repository;

    @Autowired
    public PersistenceService(final TradeDataRepository repository) {
        this.repository = repository;
    }

    public void save(final List<TradeDataEntity> entities) {
        logger.info("Storing data in the database...");
        entities.forEach(entity -> repository.save(entity));
    }
}
