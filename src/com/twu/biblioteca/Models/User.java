package com.twu.biblioteca.Models;

public class User {

    private String libraryNumber;
    private String password;

    public User(String libraryNumber, String password) {
        this.password = password;
        this.libraryNumber = libraryNumber;
    }

    public String getLibraryNumber() {
        return this.libraryNumber;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }

        User thatUser = (User) o;

        return this.libraryNumber == thatUser.libraryNumber && this.password == thatUser.password;
    }
}

