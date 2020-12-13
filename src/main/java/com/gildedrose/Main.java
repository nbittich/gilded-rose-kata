package com.gildedrose;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.lang.System.out;
import static java.util.Optional.of;

public class Main {
    static Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Conjured Mana Cake", 3, 6)};

    public static void main(String[] args) {
        out.println("OMGHAI!");

        GildedRose app = new GildedRose(items);

        Integer days = of(args)
                .filter(a -> a.length > 0)
                .map(a -> Integer.parseInt(a[0]) + 1).orElse(2);

        IntStream.range(0, days)
                .peek(i -> out.println("-------- day " + i + " --------"))
                .peek(i -> out.println("name, sellIn, quality"))
                .peek(i -> Arrays.stream(items).forEach(out::println))
                .forEach(i -> app.updateQuality());
    }
}
