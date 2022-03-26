package com.max.movierating.entity;

import com.max.movierating.entity.enums.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private final User user = new User();

    @BeforeEach
    void setUp() {
        user.setId(1L);
        user.setFirstname("firstname_test");
        user.setLastname("lastname_test");
        user.setEmail("email_test");
        user.setUsername("username_test");
        user.setPassword("password_test");
        user.setIsAccountNonLocked(true);
        user.setRoles(null);
        user.setBasket(new Basket());
        user.setPurchaseStorage(new PurchaseStorage());
    }

    @Test
    void testToString() {
        assertNotNull(user.toString());
    }

    @Test
    void testEquals() {
        User test_user1 = new User();
        test_user1.setUsername("test");
        User test_user2 = new User();
        test_user2.setUsername("test");
        assertEquals(test_user1, test_user2);
    }

    @Test
    void getId() {
        assertEquals(1L, user.getId());
    }

    @Test
    void getUsername() {
        assertEquals("username_test", user.getUsername());
    }

    @Test
    void getFirstname() {
        assertEquals("firstname_test", user.getFirstname());
    }

    @Test
    void getLastname() {
        assertEquals("lastname_test", user.getLastname());
    }

    @Test
    void getPassword() {
        assertEquals("password_test", user.getPassword());
    }

    @Test
    void getEmail() {
        assertEquals("email_test", user.getEmail());
    }

    @Test
    void getIsAccountNonLocked() {
        assertEquals(true, user.getIsAccountNonLocked());
    }

    @Test
    void getRoles() {
        assertNull(user.getRoles());
    }

    @Test
    void getBasket() {
        assertEquals(new Basket(), user.getBasket());
    }

    @Test
    void getPurchaseStorage() {
        assertEquals(new PurchaseStorage(), user.getPurchaseStorage());
    }

    @Test
    void setId() {
        user.setId(2L);
        assertEquals(2L, user.getId());
    }

    @Test
    void setUsername() {
        user.setUsername("username_test1");
        assertEquals("username_test1", user.getUsername());
    }

    @Test
    void setFirstname() {
        user.setFirstname("firstname_test1");
        assertEquals("firstname_test1", user.getFirstname());
    }

    @Test
    void setLastname() {
        user.setLastname("lastname_test1");
        assertEquals("lastname_test1", user.getLastname());
    }

    @Test
    void setPassword() {
        user.setPassword("password_test1");
        assertEquals("password_test1", user.getPassword());
    }

    @Test
    void setEmail() {
        user.setEmail("email_test1");
        assertEquals("email_test1", user.getEmail());
    }

    @Test
    void setIsAccountNonLocked() {
        user.setIsAccountNonLocked(false);
        assertEquals(false, user.getIsAccountNonLocked());
    }

    @Test
    void setRoles() {
        user.setRoles(Set.of(new Role(1L, RoleEnum.ROLE_USER.toString())));
        assertEquals(Set.of(new Role(1L, RoleEnum.ROLE_USER.toString())), user.getRoles());
    }

    @Test
    void setBasket() {
        user.setBasket(null);
        assertNull(user.getBasket());
    }

    @Test
    void setPurchaseStorage() {
        user.setPurchaseStorage(null);
        assertNull(user.getPurchaseStorage());
    }

    @Test
    void builder() {
        User user1 = User.builder()
                .id(1L)
                .firstname("firstname_test")
                .lastname("lastname_test")
                .email("email_test")
                .username("username_test")
                .password("password_test")
                .isAccountNonLocked(true)
                .basket(new Basket())
                .purchaseStorage(new PurchaseStorage())
                .roles(null)
                .build();
        assertEquals(user, user1);
    }
}
