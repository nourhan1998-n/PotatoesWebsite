

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.DAOs.ProductDAO;
import org.example.entities.Category;
import org.example.entities.Product;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDAOTest {

    private static EntityManagerFactory emf;
    private static ProductDAO productDAO;

    @BeforeAll
    public static void setUpClass() {
        // Initialize EntityManagerFactory and DAO
        emf = Persistence.createEntityManagerFactory("vegesfood");
        productDAO = new ProductDAO();
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
        em.createQuery("DELETE FROM Product").executeUpdate();
        em.createQuery("DELETE FROM Category").executeUpdate();  // Clean related Category entities
        em.getTransaction().commit();
        em.close();
    }

    private Category createCategory(EntityManager em, String categoryName) {
        em.getTransaction().begin();
        Category category = new Category();
        category.setName(categoryName);
        em.persist(category);
        em.getTransaction().commit();
        return category;
    }

    @Test
    public void testSaveProduct() {
        EntityManager em = emf.createEntityManager();

        // Create a new Category as Product requires it
        Category category = createCategory(em, "Vegetables");

        // Create and set up a Product instance
        Product product = new Product();
        product.setName("Carrot");
        product.setQuantity(new BigDecimal("100"));
        product.setPrice(new BigDecimal("0.99"));
        product.setImg("carrot.png");
        product.setSize("Medium");
        product.setIdcategory(category);

        productDAO.save(product);

        assertNotNull(product.getId());  // ID should be auto-generated
        Product foundProduct = productDAO.findById(product.getId());
        assertNotNull(foundProduct);
        assertEquals("Carrot", foundProduct.getName());
        assertEquals("carrot.png", foundProduct.getImg());
    }

    @Test
    public void testUpdateProduct() {
        EntityManager em = emf.createEntityManager();

        // Create a new Category as Product requires it
        Category category = createCategory(em, "Fruits");

        // Create and save a Product
        Product product = new Product();
        product.setName("Apple");
        product.setQuantity(BigDecimal.valueOf(50));
        product.setPrice(BigDecimal.valueOf(1));
        product.setImg("apple.png");
        product.setSize("Small");
        product.setIdcategory(category);

        productDAO.save(product);

        // Update the Product
        product.setName("Green Apple");
        product.setPrice(BigDecimal.valueOf(2));
        productDAO.update(product);

        Product updatedProduct = productDAO.findById(product.getId());
        assertEquals("Green Apple", updatedProduct.getName());
        assertEquals(new BigDecimal("2"), updatedProduct.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        EntityManager em = emf.createEntityManager();

        // Create a new Category as Product requires it
        Category category = createCategory(em, "Beverages");

        // Create and save a Product
        Product product = new Product();
        product.setName("Orange Juice");
        product.setQuantity(new BigDecimal("30"));
        product.setPrice(new BigDecimal("2.50"));
        product.setImg("orange_juice.png");
        product.setSize("Large");
        product.setIdcategory(category);

        productDAO.save(product);
        assertNotNull(productDAO.findById(product.getId()));

        // Delete the Product
        productDAO.delete(product);
        assertNull(productDAO.findById(product.getId()));
    }

    @Test
    public void testFindAllProducts() {
        EntityManager em = emf.createEntityManager();

        // Create a new Category as Product requires it
        Category category = createCategory(em, "Snacks");

        // Create and save Products
        Product product1 = new Product();
        product1.setName("Chips");
        product1.setQuantity(new BigDecimal("20"));
        product1.setPrice(new BigDecimal("1.25"));
        product1.setImg("chips.png");
        product1.setSize("Small");
        product1.setIdcategory(category);

        Product product2 = new Product();
        product2.setName("Cookies");
        product2.setQuantity(new BigDecimal("15"));
        product2.setPrice(new BigDecimal("1.75"));
        product2.setImg("cookies.png");
        product2.setSize("Medium");
        product2.setIdcategory(category);

        productDAO.save(product1);
        productDAO.save(product2);

        List<Product> products = productDAO.findAll();
        assertEquals(2, products.size());
    }
}

