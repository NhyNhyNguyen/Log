package com.company;

import java.util.Objects;

public class Data {
    private int userId;
    private int sourceType;
    private int total;
    private int bonus;

    public Data(int userId, int sourceType, int total, int bonus) {
        this.userId = userId;
        this.sourceType = sourceType;
        this.total = total;
        this.bonus = bonus;
    }

    public Data() {
    }

    public Data(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Data{" +
                "userId=" + userId +
                ", sourceType=" + sourceType +
                ", total=" + total +
                ", bonus=" + bonus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return userId == data.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
