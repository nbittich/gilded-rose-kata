package com.gildedrose.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UpdateItemStrategyResolverTest {

    String dexterityVest = "+5 Dexterity Vest";
    String elixirOfMongoose = "Elixir of the Mongoose";
    String agedBrie = "Aged Brie";
    String sulfuras = "Sulfuras, Hand of Ragnaros";
    String backstage = "Backstage passes to a TAFKAL80ETC concert";
    String conjured = "Conjured Mana Cake";

    @Test
    void resolve() {
        UpdateItemStrategyResolver resolver = new UpdateItemStrategyResolver();
        UpdateItemStrategy resolve = resolver.resolve(agedBrie);
        assertEquals(AgedItemUpdateStrategy.class, resolve.getClass());
        resolve = resolver.resolve(backstage);
        assertEquals(BackstageItemUpdateStrategy.class, resolve.getClass());
        resolve = resolver.resolve(conjured);
        assertEquals(ConjuredUpdateStrategy.class, resolve.getClass());
        resolve = resolver.resolve(dexterityVest);
        assertEquals(NormalItemUpdateStrategy.class, resolve.getClass());
        resolve = resolver.resolve(elixirOfMongoose);
        assertEquals(NormalItemUpdateStrategy.class, resolve.getClass());
        resolve = resolver.resolve(sulfuras);
        assertEquals(LegendaryItemUpdateStrategy.class, resolve.getClass());
        assertThrows(StrategyNotFoundException.class, () -> resolver.resolve(null));
    }

}