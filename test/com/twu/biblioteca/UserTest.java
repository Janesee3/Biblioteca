package com.twu.biblioteca;

import com.twu.biblioteca.Models.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UserTest {

    @Test
    public void shouldCreateUserIfGivenValidLibNumber() throws Exception {
        String libNum = "123-1234";
        String pw = "123";
        User user = new User(libNum, pw);
        assertEquals(libNum, user.getLibraryNumber());
        assertEquals(pw, user.getPassword());
    }

    @Test
    public void shouldCreateUserIfGivenInvalidLibNumber() {
        String libNum = "12-abcd";
        String pw = "123";

        try {
            User user = new User(libNum, pw);
            fail("Should throw exception for invalid library number");
        } catch (Exception e) {
            assert(true);
        }
    }
}
