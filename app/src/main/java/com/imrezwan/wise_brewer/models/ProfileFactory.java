package com.imrezwan.wise_brewer.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileFactory {
    public static final List<Profile> ITEMS = new ArrayList<Profile>();
    public static final Map<String, Profile> ITEM_MAP = new HashMap<String, Profile>();

    static {
        addItem(new Profile("1", "Default", ""));
        addItem(new Profile("2", "Profile 1", ""));
        addItem(new Profile("3", "Profile 2", ""));
        addItem(new Profile("4", "Profile 3", ""));
        addItem(new Profile("5", "Profile 4", ""));
    }

    private static void addItem(Profile item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class Profile {
        public final String id;
        public final String content;
        public final String details;

        public Profile(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}