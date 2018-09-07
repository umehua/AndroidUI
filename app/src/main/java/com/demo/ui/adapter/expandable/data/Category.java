package com.demo.ui.adapter.expandable.data;

import android.os.Parcel;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Category extends ExpandableGroup<CardSet> {
    private String name;

    public Category(String name, List<CardSet> items) {
        super(name, items);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Category(Parcel in) {
        super(in);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category other = (Category) o;

        return getName().equals(other.getName());

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
