package com.project.store;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreApplicationTests {

	@Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductVariantDAO productVariantDAO;

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    private Customer testCustomer;
    private Product testProduct;
    private ProductVariant testProductVariant;

    //Setup data to use for tests
    @BeforeEach
    void setUp() {

        // Create a test customer
        testCustomer = new Customer();
        testCustomer.setFirstName("John");
        testCustomer.setLastName("Doe");
        testCustomer.setEmail("john.doe@test.com");
        testCustomer.setAddress("123 Test Street, Test City");
        testCustomer.setBalance(new BigDecimal("100.00"));
        testCustomer.setPasswordHash("hashedPassword123");

        // Create a test product
        testProduct = new Product();
        testProduct.setName("Test Shirt");
        testProduct.setCategory(Category.Shirt);
        testProduct.setDescription("A comfortable test shirt");
        testProduct.setBrand("TestBrand");
        testProduct.setPrice(new BigDecimal("29.99"));
        testProduct.setSold(false);

        // Create a test product variant
        testProductVariant = new ProductVariant();
        testProductVariant.setSize(Size.M);
        testProductVariant.setColor(Color.Blue);
        testProductVariant.setStock(50);
    }


    //Verify that an invalid ID returns null.
    @Test
    void testFindCustomerById_InvalidId() {
        Customer customer = customerDAO.findById(9999);
	        // Act: Find a customer with an invalid ID
        assertNull(customer, "Customer should not be found for invalid ID");
		    // Assert: Verify null is returned
    }

    @Test
    void testFindProductById_InvalidId() {
        Product product = productDAO.findById(9999);
        assertNull(product, "Product should not be found for invalid ID");
    }

    //retrieve list of all product records
    @Test
    void testFindAllProducts_ReturnsAllProducts() {
        // Arrange: Save multiple products
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setCategory(Category.Shirt);
        product1.setDescription("First test product");
        product1.setBrand("Brand1");
        product1.setPrice(new BigDecimal("19.99"));
        product1.setSold(false);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setCategory(Category.Pants);
        product2.setDescription("Second test product");
        product2.setBrand("Brand2");
        product2.setPrice(new BigDecimal("39.99"));
        product2.setSold(false);

        productDAO.save(product1);
        productDAO.save(product2);

        // Act: Retrieve all products
        List<Product> products = productDAO.findAll();

        // Assert: Verify all products are returned
        assertNotNull(products, "Product list should not be null");
        assertTrue(products.size() >= 2, "Should have at least 2 products");
        assertTrue(products.stream().anyMatch(p -> p.getName().equals("Product 1")), "Product 1 should be in the list");
        assertTrue(products.stream().anyMatch(p -> p.getName().equals("Product 2")), "Product 2 should be in the list");
    }

    //return empty list 
    @Test
    void testFindAllProducts_ReturnsValidList() {
        // Act: Retrieve all products
        List<Product> products = productDAO.findAll();

        // Assert: Verify a list is returned (not null)
        assertNotNull(products, "Product list should not be null");
        assertTrue(products instanceof List, "Should return a List");
    }


    // retrieve variants for a product ID
    @Test
    void testFindProductVariantsByProductId_ReturnsVariants() {
        // Arrange: Save a product and its variants
        productDAO.save(testProduct);
        Product savedProduct = productDAO.findById(1);
        assertNotNull(savedProduct, "Product should be saved");

        // Create and save variants
        ProductVariant variant1 = new ProductVariant();
        variant1.setProductId(savedProduct.getProductId());
        variant1.setSize(Size.S);
        variant1.setColor(Color.Red);
        variant1.setStock(25);

        ProductVariant variant2 = new ProductVariant();
        variant2.setProductId(savedProduct.getProductId());
        variant2.setSize(Size.L);
        variant2.setColor(Color.Blue);
        variant2.setStock(30);

        productVariantDAO.save(variant1);
        productVariantDAO.save(variant2);

        // Act: Find all variants for the product
        List<ProductVariant> variants = productVariantDAO.findByProductId(savedProduct.getProductId());

        // Assert: Verify variants are returned and contain correct data
        assertNotNull(variants, "Variant list should not be null");
        assertTrue(variants.size() >= 2, "Should have at least 2 variants");
        
        // Verify variant details
        assertTrue(variants.stream().anyMatch(v -> v.getSize().equals(Size.S) && v.getColor().equals(Color.Red)),
                "Should have Size S Red variant");
        assertTrue(variants.stream().anyMatch(v -> v.getSize().equals(Size.L) && v.getColor().equals(Color.Blue)),
                "Should have Size L Blue variant");
    }

    //Verify that an empty list is returned when no variants exist for a product.

    @Test
    void testFindProductVariantsByProductId_NoVariants() {
        // Act: Find variants for a non-existent product
        List<ProductVariant> variants = productVariantDAO.findByProductId(9999);

        // Assert: Verify an empty list is returned
        assertNotNull(variants, "Variant list should not be null");
        assertTrue(variants.isEmpty(), "Should return empty list when no variants exist");
    }

    // Add a product variant to a customer's cart.
    @Test
    void testInsertCart_AddItemToCart() {
        // Arrange: Save a customer and variant
        customerDAO.save(testCustomer);
        Customer savedCustomer = customerDAO.findByEmail(testCustomer.getEmail());
        assertNotNull(savedCustomer, "Customer should be saved");

        productDAO.save(testProduct);
        Product savedProduct = productDAO.findById(1);
        productVariantDAO.save(testProductVariant);
        List<ProductVariant> variants = productVariantDAO.findByProductId(savedProduct.getProductId());
        assertTrue(variants.size() > 0, "Variant should be saved");
        ProductVariant savedVariant = variants.get(0);

        // Create cart item
        Cart cartItem = new Cart();
        cartItem.setCustomerId(savedCustomer.getCustomerId());
        cartItem.setVariantId(savedVariant.getVariantId());

        // Act: Insert cart item
        boolean result = cartDAO.insert(cartItem);

        // Assert: Verify insert was successful
        assertTrue(result, "Insert operation should return true");

        // Verify the item is in the cart
        List<Cart> cartItems = cartDAO.findByCustomerId(savedCustomer.getCustomerId());
        assertNotNull(cartItems, "Cart items list should not be null");
        assertTrue(cartItems.size() > 0, "Cart should have at least one item");
    }

    //Verify that an empty list is returned when customer has no cart items.

    @Test
    void testFindCartByCustomerId_NoItems() {
        // Act: Find cart items for a customer with no items
        List<Cart> cartItems = cartDAO.findByCustomerId(9999);

        // Assert: Verify empty list is returned
        assertNotNull(cartItems, "Cart items list should not be null");
        assertTrue(cartItems.isEmpty(), "Should return empty list when no items in cart");
    }

    //Verify that deleting with an invalid ID returns false.

    @Test
    void testDeleteCart_InvalidId() {
        // Act: Delete with an invalid cart ID
        boolean result = cartDAO.delete(9999);

        // Assert: Verify false is returned
        assertFalse(result, "Delete with invalid ID should return false");
    }


    //Verify that an empty list is returned when customer has no orders.

    @Test
    void testFindOrderByCustomerId_NoOrders() {
        // Act: Find orders for a customer with no orders
        List<CustomerOrder> orders = customerOrderDAO.findByCustomerId(9999);

        // Assert: Verify empty list is returned
        assertNotNull(orders, "Orders list should not be null");
        assertTrue(orders.isEmpty(), "Should return empty list when no orders exist");
    }
}
