package org.obszczymucha.tradereportingengine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeData {
    private String buyerParty;
    private String sellerParty;
    private String premiumAmount;
    private String premiumCurrency;
}
