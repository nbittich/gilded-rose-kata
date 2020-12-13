package com.gildedrose;

import com.gildedrose.strategy.UpdateItemStrategy;
import com.gildedrose.strategy.UpdateItemStrategyResolver;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

class GildedRose {
    Item[] items;
    private final UpdateItemStrategyResolver updateItemStrategyResolver;

    public GildedRose(Item[] items) {
        this.items = items;
        this.updateItemStrategyResolver = new UpdateItemStrategyResolver();
    }

    public GildedRose(Item[] items, UpdateItemStrategyResolver updateItemStrategyResolver) {
        this.items = items;
        this.updateItemStrategyResolver = updateItemStrategyResolver;
    }

    public void updateQuality() {
        Optional.ofNullable(items)
                .map(Arrays::stream)
                .orElseGet(Stream::of)
                .filter(Objects::nonNull)
                .forEach(this::updateQuality);
    }

    public void updateQuality(Item item) {
        UpdateItemStrategy strategy = updateItemStrategyResolver.resolve(item.name);
        item.quality = strategy.updateQuality(item);
        item.sellIn = strategy.updateSellIn(item);
    }
}