package com.example.fantasy.batch.processor;

import com.example.fantasy.batch.reader.PlayerCsvItem;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Groups multiple PlayerCsvItem rows into a single list of rows for the same player.
 * This simple implementation returns each item in a list; actual aggregation happens in writer.
 */
@Component
public class PlayerAggregationProcessor implements ItemProcessor<PlayerCsvItem, List<PlayerCsvItem>> {

    @Override
    public List<PlayerCsvItem> process(PlayerCsvItem item) {
        // Simple approach: just return each item in a list; actual aggregation happens in writer
        return Collections.singletonList(item);
    }
}
