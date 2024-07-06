package org.obszczymucha.tradereportingengine.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trade_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buyerParty;
    private String sellerParty;
    private String premiumAmount;
    private String premiumCurrency;
    private String sortedSeller;
    private String sortedBuyer;
}
