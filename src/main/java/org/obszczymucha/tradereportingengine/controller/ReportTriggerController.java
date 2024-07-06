package org.obszczymucha.tradereportingengine.controller;

import java.io.IOException;
import java.util.List;

import org.obszczymucha.tradereportingengine.service.model.TradeData;
import org.obszczymucha.tradereportingengine.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportTriggerController {
    private final ReportingService service;

    @Autowired
    public ReportTriggerController(final ReportingService service) {
        this.service = service;
    }

    @GetMapping("/report")
    public List<TradeData> triggerReport() throws IOException {
        return service.report();
    }
}
