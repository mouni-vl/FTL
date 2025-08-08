package com.example.fantasy.batch.processor.player;

import com.example.fantasy.batch.reader.player.PlayerCsvReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Groups multiple PlayerCsvItem rows into a single list of rows for the same player.
 * This simple implementation returns each item in a list; actual aggregation happens in writer.
 */
@Component
public class PlayerAggregationProcessor implements ItemProcessor<PlayerCsvReader, List<PlayerCsvReader>> {

    @Override
    public List<PlayerCsvReader> process(PlayerCsvReader item) {
        // Simple approach: just return each item in a list; actual aggregation happens in writer
        return Collections.singletonList(item);
    }
}
