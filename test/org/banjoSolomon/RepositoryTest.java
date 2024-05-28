package org.banjoSolomon;

import org.banjoSolomon.repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;

import org.banjoSolomon.repository.db.DatabaseConnection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


public class RepositoryTest {
    @Test
    public void testDatabaseConnection(){
        try(Connection connection = DatabaseConnection.getInstance().getConnection()){
            assertNotNull(connection);
            System.out.println("connection--> "+connection);
        }catch (SQLException ex){
            assertNull(ex);
            ex.printStackTrace();
        }
    }
}