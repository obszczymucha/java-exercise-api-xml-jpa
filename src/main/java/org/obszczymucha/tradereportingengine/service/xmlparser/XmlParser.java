package org.obszczymucha.tradereportingengine.service.xmlparser;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.obszczymucha.tradereportingengine.service.model.TradeData;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import lombok.val;

@Component
public class XmlParser {
    private final XPath xpath;

    public XmlParser() {
        val xPathFactory = XPathFactory.newInstance();
        this.xpath = xPathFactory.newXPath();
    }

    public TradeData parse(final InputStream inputStream) throws XmlParsingException {
        try {
            val factory = DocumentBuilderFactory.newInstance();
            val builder = factory.newDocumentBuilder();
            val document = builder.parse(inputStream);

            return TradeData.builder()
                    .buyerParty(parseField(document, "//buyerPartyReference/@href"))
                    .sellerParty(parseField(document, "//sellerPartyReference/@href"))
                    .premiumAmount(parseField(document, "//paymentAmount/amount"))
                    .premiumCurrency(parseField(document, "//paymentAmount/currency"))
                    .build();
        } catch (Exception e) {
            throw new XmlParsingException(e);
        }
    }

    private String parseField(final Document document, final String expression) throws XPathExpressionException {
        return (String) xpath.evaluate(expression, document, XPathConstants.STRING);
    }
}
