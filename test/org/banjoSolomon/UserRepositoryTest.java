package org.banjoSolomon;

import org.banjoSolomon.models.User;
import org.banjoSolomon.repository.UserRepository;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        assertEquals(userId, user.getId());
    }

}
