package ru.netology.rakchaev;

import java.util.Objects;

public final class Category {
    private final String name;

    private Category(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name can't be empty");
        } else {
            this.name = name.trim();
        }
    }

    public static Category of(String name) {
        return new Category(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category country = (Category) o;
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
