package com.gildedrose;

import com.gildedrose.strategy.UpdateItemStrategy;
import com.gildedrose.strategy.UpdateItemStrategyResolver;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class GildedRoseTest {
    String dexterityVest = "+5 Dexterity Vest";
    String elixirOfMongoose = "Elixir of the Mongoose";
    String agedBrie = "Aged Brie";
    String sulfuras = "Sulfuras, Hand of Ragnaros";
    String backstage = "Backstage passes to a TAFKAL80ETC concert";
    String conjured = "Conjured Mana Cake";

    @Test
    void test_quality_degrades_twice_after_date_passed() {
        int sellIn = 5;
        Item elixirOfMongooseItem = new Item(elixirOfMongoose, sellIn, 7);
        Item dexterityVestItem = new Item(dexterityVest, sellIn, 20);
        Item[] items = new Item[]{dexterityVestItem, elixirOfMongooseItem};

        GildedRose gildedRose = new GildedRose(items);

        IntStream.rangeClosed(1, sellIn).forEach(i -> {
            int currentQualityForDexterityVest = dexterityVestItem.quality;
            int currentQualityForElixirOfMongoose = elixirOfMongooseItem.quality;

            gildedRose.updateQuality();
            assertEquals(currentQualityForDexterityVest - 1, dexterityVestItem.quality);
            assertEquals(currentQualityForElixirOfMongoose - 1, elixirOfMongooseItem.quality);
        });

        int currentQualityForDexterityVest = dexterityVestItem.quality;
        int currentQualityForElixirOfMongoose = elixirOfMongooseItem.quality;

        gildedRose.updateQuality();

        assertEquals(currentQualityForDexterityVest - 2, dexterityVestItem.quality);
        assertEquals(currentQualityForElixirOfMongoose - 2, elixirOfMongooseItem.quality);
        printItems(gildedRose);

    }

    @Test
    void test_quality_never_negative() {
        int quality = 7;
        int sellIn = 5;
        Item elixirOfMongooseItem = new Item(elixirOfMongoose, sellIn, quality);
        Item conjuredItem = new Item(conjured, sellIn, quality);
        Item dexterityVestItem = new Item(dexterityVest, sellIn, quality);
        Item backstageItem = new Item(backstage, sellIn, quality);

        GildedRose gildedRose = new GildedRose(new Item[]{elixirOfMongooseItem, conjuredItem, dexterityVestItem, backstageItem});
        IntStream.rangeClosed(1, quality + sellIn).forEach(i -> gildedRose.updateQuality());
        assertEquals(0, elixirOfMongooseItem.quality);
        assertEquals(0, conjuredItem.quality);
        assertEquals(0, dexterityVestItem.quality);
        assertEquals(0, backstageItem.quality);
        printItems(gildedRose);

    }

    @Test
    void test_aged_brie_increases_quality_the_older_it_gets() {
        int quality = 0;
        int sellIn = 5;
        Item agedBrieItem = new Item(agedBrie, sellIn, quality);
        GildedRose gildedRose = new GildedRose(new Item[]{agedBrieItem});
        IntStream.rangeClosed(1, sellIn).forEach(i -> gildedRose.updateQuality());

        assertEquals(sellIn, agedBrieItem.quality);
        gildedRose.updateQuality();

        assertEquals(sellIn + 2, agedBrieItem.quality);
        printItems(gildedRose);

    }

    @Test
    void test_quality_never_exceeds_50() {
        int quality = 0;
        int sellIn = 50;
        Item agedBrieItem = new Item(agedBrie, sellIn, quality);
        GildedRose gildedRose = new GildedRose(new Item[]{agedBrieItem});
        IntStream.rangeClosed(1, sellIn + 1).forEach(i -> gildedRose.updateQuality());

        assertEquals(50, agedBrieItem.quality);
        printItems(gildedRose);

    }

    @Test
    void test_sulfuras_never_sold_or_decrease_quality() {
        int quality = 80;
        int sellIn = 10;
        Item sulfurasItem = new Item(sulfuras, sellIn, quality);
        GildedRose gildedRose = new GildedRose(new Item[]{sulfurasItem});

        IntStream.rangeClosed(1, 10).forEach(i -> gildedRose.updateQuality());

        assertEquals(quality, sulfurasItem.quality);
        assertEquals(sellIn, sulfurasItem.sellIn);
        printItems(gildedRose);

    }

    @Test
    void test_sulfuras_has_quality_of_80() {
        int quality = 80;
        int sellIn = 0;
        Item sulfurasItem = new Item(sulfuras, sellIn, quality);
        GildedRose gildedRose = new GildedRose(new Item[]{sulfurasItem});

        IntStream.rangeClosed(1, 10).forEach(i -> gildedRose.updateQuality());

        assertEquals(quality, sulfurasItem.quality);
        assertEquals(sellIn, sulfurasItem.sellIn);
        printItems(gildedRose);

    }

    @Test
    void test_backstage_passes_increase_quality_by_2_when_10_days_or_less() {
        int quality = 20;
        Item backstageItem = new Item(backstage, 15, quality);

        GildedRose gildedRose = new GildedRose(new Item[]{backstageItem});
        IntStream.rangeClosed(1, 5).forEach(i -> gildedRose.updateQuality()); // 10 days
        assertEquals(10, backstageItem.sellIn);
        assertEquals(quality + 5, backstageItem.quality);
        quality = backstageItem.quality;
        gildedRose.updateQuality(); // 9 days
        assertEquals(9, backstageItem.sellIn);
        assertEquals(quality + 2, backstageItem.quality);
        printItems(gildedRose);

    }

    @Test
    void test_backstage_passes_increase_quality_by_3_when_5_days_or_less() {
        int quality = 20;
        Item backstageItem = new Item(backstage, 15, quality);

        GildedRose gildedRose = new GildedRose(new Item[]{backstageItem});
        IntStream.rangeClosed(1, 9).forEach(i -> gildedRose.updateQuality()); // 6 days
        assertEquals(6, backstageItem.sellIn);
        quality = backstageItem.quality;
        gildedRose.updateQuality(); // 5 days
        assertEquals(5, backstageItem.sellIn);
        assertEquals(quality + 2, backstageItem.quality);
        quality = backstageItem.quality;
        gildedRose.updateQuality(); // 4 days
        assertEquals(4, backstageItem.sellIn);
        assertEquals(quality + 3, backstageItem.quality);
        printItems(gildedRose);

    }

    @Test
    void test_backstage_drops_increase_quality_after_concert() {
        int quality = 20;
        int beforeConcert = 15;
        Item backstageItem = new Item(backstage, beforeConcert, quality);

        GildedRose gildedRose = new GildedRose(new Item[]{backstageItem});
        IntStream.rangeClosed(1, beforeConcert + 1).forEach(i -> gildedRose.updateQuality()); // after the concert
        assertEquals(0, backstageItem.quality);
        printItems(gildedRose);

    }

    @Test
    void test_conjured_quality_degrade_twice_as_fast_as_normal_items() {

        int sellIn = 5;
        Item conjuredItem = new Item(conjured, sellIn, 20);
        Item[] items = new Item[]{conjuredItem};

        GildedRose gildedRose = new GildedRose(items);

        IntStream.rangeClosed(1, sellIn).forEach(i -> {
            int currentQuality = conjuredItem.quality;
            gildedRose.updateQuality();
            assertEquals(currentQuality - 2, conjuredItem.quality);
        });

        int currentQuality = conjuredItem.quality;

        gildedRose.updateQuality();

        assertEquals(currentQuality - 4, conjuredItem.quality);
        printItems(gildedRose);

    }

    @Test
    void test_update_quality_with_mock_strategy_resolver() {
        UpdateItemStrategyResolver mock = Mockito.mock(UpdateItemStrategyResolver.class);
        UpdateItemStrategy mockedStrategy = Mockito.mock(UpdateItemStrategy.class);

        Mockito.when(mock.resolve(anyString())).thenReturn(mockedStrategy);
        Mockito.when(mockedStrategy.updateSellIn(any(Item.class))).thenReturn(1);
        Mockito.when(mockedStrategy.updateQuality(any(Item.class))).thenReturn(1);

        int sellIn = 5;
        Item item = new Item("+5 Dexterity Vest", sellIn, 20);
        Item[] items = new Item[]{item};

        GildedRose gildedRose = new GildedRose(items, mock);
        gildedRose.updateQuality();
        assertEquals(1, item.sellIn);
        assertEquals(1, item.quality);
        verify(mock, times(1)).resolve(eq("+5 Dexterity Vest"));
        verify(mockedStrategy, times(1)).updateQuality(eq(item));
        verify(mockedStrategy, times(1)).updateSellIn(eq(item));
        verifyNoMoreInteractions(mock, mockedStrategy);
        printItems(gildedRose);
    }

    @Test
    void test_update_quality_null_items() {
        UpdateItemStrategyResolver mock = Mockito.mock(UpdateItemStrategyResolver.class);
        UpdateItemStrategy mockedStrategy = Mockito.mock(UpdateItemStrategy.class);

        Mockito.when(mock.resolve(anyString())).thenReturn(mockedStrategy);
        Mockito.when(mockedStrategy.updateSellIn(any(Item.class))).thenReturn(1);
        Mockito.when(mockedStrategy.updateQuality(any(Item.class))).thenReturn(1);

        int sellIn = 5;
        Item item = new Item(backstage, sellIn, 20);
        Item[] items = new Item[]{item, null};

        GildedRose gildedRose = new GildedRose(items, mock);
        gildedRose.updateQuality();

        assertEquals(1, item.sellIn);
        assertEquals(1, item.quality);

        verify(mock, times(1)).resolve(eq(backstage));
        verify(mockedStrategy, times(1)).updateQuality(eq(item));
        verify(mockedStrategy, times(1)).updateSellIn(eq(item));
        verifyNoMoreInteractions(mock, mockedStrategy);
        printItems(gildedRose);
    }

    private void printItems(GildedRose gildedRose) {
        Stream.of(gildedRose.items)
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }
}
