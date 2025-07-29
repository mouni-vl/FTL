package com.example.fantasy.domain.service;

import com.example.fantasy.domain.model.Player;

/**
 * A domain service for Player domain.
 * It does not perform any database saves or deletes—only returns a new/modified Player if needed.
 */
public class PricingService {
    /**
     * Compute initial prices for all players using last season’s points.
     *
     * @param pointsList     a list of HistoricalPoints (playerId → points)
     * @param totalBudget    e.g. 100.0 (total sum of all prices)
     * @param minPrice       e.g. 4.0
     * @param maxPrice       e.g. 12.0
     * @param roundingUnit   e.g. 0.1 (round to nearest 0.1)
     * @return a list of PlayerPriceResult (playerId → initialPrice)
     */
//    public List<PlayerPriceResult> calculateInitialPrices(
//            List<HistoricalPoints> pointsList,
//            double totalBudget,
//            double minPrice,
//            double maxPrice,
//            double roundingUnit) { … }


    /**
     * Given a player's current price and their TransferStats,
     * compute the updated price according to thresholds T_up and T_down.
     *
     * @param currentPrice   the player’s existing price (e.g. in 0.1M units)
     * @param transferStats  contains transfersIn, transfersOut, ownersCount
     * @param thresholds     holds T_up, T_down, minPrice, maxPrice
     * @return PlayerPriceResult containing (playerId, newPrice)
     */
//    public PlayerPriceResult computeNewPrice(
//            Long playerId,
//            double currentPrice,
//            TransferStats transferStats,
//            PriceThresholds thresholds) { … }
}


