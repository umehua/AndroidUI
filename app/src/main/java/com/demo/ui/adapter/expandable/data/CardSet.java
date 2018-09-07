package com.demo.ui.adapter.expandable.data;

import android.os.Parcel;
import android.os.Parcelable;

public class CardSet implements Parcelable {

    private String name;
    private int cardCount;

    public CardSet(String name, int cardCount) {
        this.name = name;
        this.cardCount = cardCount;
    }

    public String getName() {
        return name;
    }

    public int getCardCount() {
        return cardCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardSet)) return false;

        CardSet other = (CardSet) o;

        if (cardCount != other.cardCount) return false;
        return getName() != null ? getName().equals(other.getName()) : other.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result;
        return result;
    }

    protected CardSet(Parcel in) {
        name = in.readString();
        cardCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(cardCount);
    }

    public static final Creator<CardSet> CREATOR = new Creator<CardSet>() {
        @Override
        public CardSet createFromParcel(Parcel in) {
            return new CardSet(in);
        }

        @Override
        public CardSet[] newArray(int size) {
            return new CardSet[size];
        }
    };
}
