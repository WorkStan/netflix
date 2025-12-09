package ru.netology.rakchaev;

import java.util.Objects;

public final class Actor {
    private final String name;

    private Actor(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name can't be empty");
        } else {
            this.name = name.trim();
        }
    }

    public static Actor of(String name) {
        return new Actor(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor country = (Actor) o;
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
