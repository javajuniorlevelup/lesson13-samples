package ru.levelp;

public class ImmutableObject {

    private final String str;

    public ImmutableObject(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
