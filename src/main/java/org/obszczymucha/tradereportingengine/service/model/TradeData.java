package org.obszczymucha.tradereportingengine.service.model;

import org.obszczymucha.tradereportingengine.repository.entity.TradeDataEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.obszczymucha.tradereportingengine.service.utils.NameSorter.sort;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeData {
    private String buyerParty;
    private String sellerParty;
    private String premiumAmount;
    private String premiumCurrency;

    public static TradeDataEntity toEntity(final TradeData data) {
        return TradeDataEntity.builder()
                .buyerParty(data.getBuyerParty())
                .sellerParty(data.getSellerParty())
                .premiumAmount(data.getPremiumAmount())
                .premiumCurrency(data.getPremiumCurrency())
                .sortedBuyer(sort(data.getBuyerParty()))
                .sortedSeller(sort(data.getSellerParty()))
                .build();
    }

    public static TradeData fromEntity(final TradeDataEntity entity) {
        return TradeData.builder()
                .buyerParty(entity.getBuyerParty())
                .sellerParty(entity.getSellerParty())
                .premiumAmount(entity.getPremiumAmount())
                .premiumCurrency(entity.getPremiumCurrency())
                .build();
    }
}
