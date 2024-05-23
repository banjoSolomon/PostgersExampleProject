package org.banjoSolomon;

import org.banjoSolomon.repository.UserRepository;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

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