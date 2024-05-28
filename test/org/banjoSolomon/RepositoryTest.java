package org.banjoSolomon;

import org.banjoSolomon.repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class RepositoryTest {
    @Test
    public void testDatabaseConnection(){
        try(Connection connection = UserRepository.connect()){
            assertNotNull(connection);
            System.out.println("connection -> " + connection);

        }catch (SQLException e){
            assertNotNull(e);
            e.printStackTrace();
        }
    }
}