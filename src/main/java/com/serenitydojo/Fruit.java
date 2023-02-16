package com.serenitydojo;

public abstract class Fruit {
    public String getName() {
        return this.getClass().getSimpleName();
    };

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    public boolean equals(Object obj) {
        boolean itsFruit = obj instanceof Fruit;
        return itsFruit && this.getName().equals(((Fruit) obj).getName());
    }
}
