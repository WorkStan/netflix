package ru.netology.rakchaev.model;

public enum ContentType {
    MOVIE("Movie"),
    TV_SHOW("TV Show"),
    EMPTY("empty");

    private final String displayName;

    ContentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ContentType fromString(String type) {
        if (type == null || type.trim().isEmpty()) {
            return EMPTY;
        }

        String cleaned = type.trim().replaceAll("^\"|\"$", "");

        for (ContentType contentType : ContentType.values()) {
            if (contentType.displayName.equalsIgnoreCase(cleaned)) {
                return contentType;
            }
        }

        return EMPTY;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
