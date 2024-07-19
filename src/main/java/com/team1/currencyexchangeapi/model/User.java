package com.team1.currencyexchangeapi.model;

public class User {
    private String username;

    public String getUsername() {
        return  username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null ||
            this.getClass() != obj.getClass()) {
            return false;
        }

        User user = (User) obj;

        return (this.username.equals(user.username));
    }
}