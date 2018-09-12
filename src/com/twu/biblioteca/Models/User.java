package com.twu.biblioteca.Models;

public class User {

    public static final String LIBRARY_NUMBER_FORMAT = "\\d{3}-\\d{4}";
    public static final String INVALID_FORMAT_ERR_MSG = "Invalid library number format!";

    private String libraryNumber;
    private String password;

    public User(String libraryNumber, String password) throws Exception {
        this.password = password;
        if (libraryNumber.matches(LIBRARY_NUMBER_FORMAT)) {
            this.libraryNumber = libraryNumber;
        } else {
            throw new Exception(INVALID_FORMAT_ERR_MSG);
        }
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

