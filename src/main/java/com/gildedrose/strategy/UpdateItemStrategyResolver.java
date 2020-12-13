package com.gildedrose.strategy;


import static java.util.Optional.ofNullable;

public class UpdateItemStrategyResolver {

    public static final String SULFURA = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED = "Conjured Mana Cake";
    public static final String DEXTERITY = "+5 Dexterity Vest";
    public static final String ELIXIR = "Elixir of the Mongoose";

    private final AgedItemUpdateStrategy agedItemUpdateStrategy = new AgedItemUpdateStrategy();
    private final BackstageItemUpdateStrategy backstageItemUpdateStrategy = new BackstageItemUpdateStrategy();
    private final LegendaryItemUpdateStrategy legendaryItemUpdateStrategy = new LegendaryItemUpdateStrategy();
    private final NormalItemUpdateStrategy normalItemUpdateStrategy = new NormalItemUpdateStrategy();
    private final ConjuredUpdateStrategy conjuredUpdateStrategy = new ConjuredUpdateStrategy();

    public UpdateItemStrategy resolve(String itemName) {
        switch (ofNullable(itemName).orElseThrow(StrategyNotFoundException::new)) {
            case SULFURA:
                return legendaryItemUpdateStrategy;
            case AGED_BRIE:
                return agedItemUpdateStrategy;
            case BACKSTAGE:
                return backstageItemUpdateStrategy;
            case CONJURED:
                return conjuredUpdateStrategy;
            case DEXTERITY:
            case ELIXIR:
            default:
                return normalItemUpdateStrategy;
        }
    }

}
