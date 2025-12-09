package ru.netology.rakchaev.model;

import java.util.Objects;

public final class Country {
    private final String name;

    private Country(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Country name can't be empty");
        } else {
            this.name = name.trim();
        }
    }

    public static Country of(String name) {
        return new Country(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return name.equalsIgnoreCase(country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public String toString() {
        return name;
    }
}
