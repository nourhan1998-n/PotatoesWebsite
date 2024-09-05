

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.DAOs.UserDAO;
import org.example.entities.User;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    private static EntityManagerFactory emf;
    private static UserDAO userDAO;

    @BeforeAll
    public static void setUpClass() {
        // Initialize EntityManagerFactory and DAO
        emf = Persistence.createEntityManagerFactory("noura");
        userDAO = new UserDAO();
    }

    @AfterAll
    public static void tearDownClass() {
        if (emf != null) {
            emf.close();
        }
    }

    @AfterEach
    public void setUp() {
        // Clean up before each test
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM User").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setPassword("password123");
        user.setJob("Engineer");
        user.setCredit("100");
        user.setCity("Wonderland");
        user.setStreet("Main St");

        userDAO.save(user);

        assertNotNull(user.getId());  // ID should be auto-generated
        User foundUser = userDAO.findById(user.getId());
        assertNotNull(foundUser);
        assertEquals("Alice", foundUser.getName());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("Bob");
        user.setEmail("bob@example.com");
        user.setPassword("password123");
        user.setJob("Developer");
        user.setCredit("200");
        user.setCity("Metropolis");
        user.setStreet("Broadway");

        userDAO.save(user);

        user.setName("Bob Updated");
        userDAO.update(user);

        User updatedUser = userDAO.findById(user.getId());
        assertEquals("Bob Updated", updatedUser.getName());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setName("Charlie");
        user.setEmail("charlie@example.com");
        user.setPassword("password123");
        user.setJob("Designer");
        user.setCredit("300");
        user.setCity("City X");
        user.setStreet("Sunset Blvd");

        userDAO.save(user);
        assertNotNull(userDAO.findById(user.getId()));

        userDAO.delete(user);
        assertNull(userDAO.findById(user.getId()));
    }

    @Test
    public void testFindAllUsers() {
        User user1 = new User();
        user1.setName("Dave");
        user1.setEmail("dave@example.com");
        user1.setPassword("password123");
        user1.setJob("Artist");
        user1.setCredit("400");
        user1.setCity("Art City");
        user1.setStreet("Brush St");

        User user2 = new User();
        user2.setName("Eve");
        user2.setEmail("eve@example.com");
        user2.setPassword("password123");
        user2.setJob("Manager");
        user2.setCredit("500");
        user2.setCity("Manager City");
        user2.setStreet("Boss Blvd");

        userDAO.save(user1);
        userDAO.save(user2);

        List<User> users = userDAO.findAll();
        assertEquals(2, users.size());
    }
}
