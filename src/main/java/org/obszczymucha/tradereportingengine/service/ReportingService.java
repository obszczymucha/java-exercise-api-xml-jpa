package org.obszczymucha.tradereportingengine.service;

import java.io.IOException;
import java.util.List;

import org.obszczymucha.tradereportingengine.service.model.TradeData;
import org.obszczymucha.tradereportingengine.service.xmlparser.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingService {
    private static final String DATA_DIR = "data";
    private final XmlParser xmlParser;
    private final ResourceFileLister resourceFileLister;

    @Autowired
    public ReportingService(final XmlParser xmlParser, final ResourceFileLister resourceFileLister) {
        this.xmlParser = xmlParser;
        this.resourceFileLister = resourceFileLister;
    }

    public List<TradeData> report() throws IOException {
        return resourceFileLister.listFiles(DATA_DIR).stream()
                .map(resource -> {
                    try {
                        return resource.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .map(xmlParser::parse)
                .toList();
    }
}
