package org.obszczymucha.tradereportingengine.service;

import java.io.IOException;
import java.util.List;

import org.obszczymucha.tradereportingengine.service.model.TradeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import lombok.val;

@Service
public class ReportingService implements CommandLineRunner {
    private final ParsingService parsingService;
    private final PersistenceService persistenceService;
    private final QueryingService queryingService;

    @Autowired
    public ReportingService(final ParsingService parsingService, final PersistenceService persistenceService,
            final QueryingService queryingService) {
        this.parsingService = parsingService;
        this.persistenceService = persistenceService;
        this.queryingService = queryingService;
    }

    public void run(final String... args) throws Exception {
        val trades = parsingService.parse();
        val entities = trades.stream().map(trade -> TradeData.toEntity(trade)).toList();
        persistenceService.save(entities);
    }

    public List<TradeData> report() throws IOException {
        val results = queryingService.query();
        return results.stream().map(entity -> TradeData.fromEntity(entity)).toList();
    }
}
