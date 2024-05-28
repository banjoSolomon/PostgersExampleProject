package org.banjoSolomon;

import org.banjoSolomon.models.User;
import org.banjoSolomon.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class UserRepositoryTest {
    private final UserRepository userRepository = new UserRepository();

    @Test
    public void saveUserTest(){
        User user = new User();
        user.setWalletId(1L);
        User savedUser = userRepository.saveUser(user);
        assertNotNull(savedUser);

    }


    @Test
    public void testUpdateUser(){
        Long userId = 2L;
        Long walletId = 200L;
        User user = userRepository.updateUser(userId, walletId);
        assertNotNull(user);

        assertEquals(200L, user.getWalletId());

    }
    @Test
    public void testDeleteUser() {
        userRepository.deleteUser(1L);
        Optional<User> user = userRepository.findById(1L);
        assertTrue(user.isEmpty());

    }
    @Test
    public void testFindUserById(){
        User user = (User) userRepository.findById(2L).orElseThrow();
        assertNotNull(user);
        assertEquals(2L,user.getId());
 }

 @Test
    public void testFindAll(){
        List<User> users = userRepository.findAll();
        assertNotNull(users);
        assertEquals(4,users.size());
 }

}
