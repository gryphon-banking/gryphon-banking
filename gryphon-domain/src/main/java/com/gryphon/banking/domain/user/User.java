package com.gryphon.banking.domain.user;

public class User {

    public static final String DEFAULT_NAME = "Unknown";
    private String id;
    private String name;
    
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public User() {
        this(null, DEFAULT_NAME);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
