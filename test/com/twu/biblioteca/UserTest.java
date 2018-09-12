package com.twu.biblioteca;

import com.twu.biblioteca.Models.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class UserTest {

    public String libNum = "123-1234";
    public String pw = "123";

    @Test
    public void shouldCreateUserIfGivenValidLibNumber() throws Exception {
        User user = new User(libNum, pw);
        assertEquals(libNum, user.getLibraryNumber());
        assertEquals(pw, user.getPassword());
    }

    @Test
    public void shouldCreateUserIfGivenInvalidLibNumber() {
        String invalidNum = "12-abcd";

        try {
            User user = new User(invalidNum, pw);
            fail("Should throw exception for invalid library number");
        } catch (Exception e) {
            assert(true);
        }
    }

    @Test
    public void testForEqualsMethod() throws Exception {
        User user1 = new User(libNum, pw);
        User user2 = new User(libNum, pw);
        assertTrue(user1.equals(user2));
    }
}
