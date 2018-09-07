package com.demo.ui.adapter.expandable.data;

import java.util.Arrays;
import java.util.List;

public class FakeDataGenerator {
    public static List<Category> makeCategories() {
        Category c1 = new Category("c1", makeC1CardSet());
        Category c2 = new Category("c2", makeC2CardSet());
        return Arrays.asList(c1, c2);
    }

    public static List<CardSet> makeC1CardSet() {
        CardSet s1 = new CardSet("a1", 10);
        CardSet s2 = new CardSet("a2", 12);
        return Arrays.asList(s1, s2);
    }

    public static List<CardSet> makeC2CardSet() {
        CardSet s1 = new CardSet("b1", 10);
        CardSet s2 = new CardSet("b2", 1);
        CardSet s3 = new CardSet("b3", 2);
        return Arrays.asList(s1, s2, s3);
    }
}
