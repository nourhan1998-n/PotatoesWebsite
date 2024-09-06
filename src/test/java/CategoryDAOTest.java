
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.DAOs.CategoryDAO;
import org.example.entities.Category;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryDAOTest {

    private static EntityManagerFactory emf;
    private static CategoryDAO categoryDAO;

    @BeforeAll
    public static void setUpClass() {
        // Initialize EntityManagerFactory and DAO
        emf = Persistence.createEntityManagerFactory("vegesfood");
        categoryDAO = new CategoryDAO();
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
        em.createQuery("DELETE FROM Category").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testSaveCategory() {
        // Create and set up a Category instance
        Category category = new Category();
        category.setName("Dairy");

        // Save the Category
        categoryDAO.save(category);

        // Check that the ID is not null and the Category can be found
        assertNotNull(category.getId());
        Category foundCategory = categoryDAO.findById(category.getId());
        assertNotNull(foundCategory);
        assertEquals("Dairy", foundCategory.getName());
    }

    @Test
    public void testUpdateCategory() {
        // Create and save a Category
        Category category = new Category();
        category.setName("Beverages");

        categoryDAO.save(category);

        // Update the Category
        category.setName("Soft Drinks");
        categoryDAO.update(category);

        // Check that the update is reflected in the database
        Category updatedCategory = categoryDAO.findById(category.getId());
        assertEquals("Soft Drinks", updatedCategory.getName());
    }

    @Test
    public void testDeleteCategory() {
        // Create and save a Category
        Category category = new Category();
        category.setName("Bakery");

        categoryDAO.save(category);
        assertNotNull(categoryDAO.findById(category.getId()));

        // Delete the Category
        categoryDAO.delete(category);
        assertNull(categoryDAO.findById(category.getId()));
    }

    @Test
    public void testFindAllCategories() {
        // Create and save multiple Categories
        Category category1 = new Category();
        category1.setName("Snacks");

        Category category2 = new Category();
        category2.setName("Frozen Foods");

        categoryDAO.save(category1);
        categoryDAO.save(category2);

        // Retrieve all Categories and check the list size
        List<Category> categories = categoryDAO.findAll();
        assertEquals(2, categories.size());
    }
}
