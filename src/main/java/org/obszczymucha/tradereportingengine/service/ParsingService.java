package org.obszczymucha.tradereportingengine.service;

import java.io.IOException;
import java.util.List;

import org.obszczymucha.tradereportingengine.service.model.TradeData;
import org.obszczymucha.tradereportingengine.service.xmlparser.XmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.obszczymucha.tradereportingengine.service.utils.ResourceFileLister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParsingService {
    private static final Logger logger = LoggerFactory.getLogger(ParsingService.class);
    private static final String DATA_DIR = "data";
    private final XmlParser xmlParser;
    private final ResourceFileLister resourceFileLister;

    @Autowired
    public ParsingService(final XmlParser xmlParser, final ResourceFileLister resourceFileLister) {
        this.xmlParser = xmlParser;
        this.resourceFileLister = resourceFileLister;
    }

    public List<TradeData> parse() throws IOException {
        logger.info("Parsing XML files...");

        return resourceFileLister.listFiles(DATA_DIR).stream()
                .map(resource -> {
                    try {
                        return resource.getInputStream();
                    } catch (IOException e) {
                        return null;
                    }
                })
                .filter(stream -> stream != null)
                .map(xmlParser::parse)
                .toList();
    }
}
