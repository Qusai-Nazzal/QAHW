package testing;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ConstructorTest {
    @BeforeAll
    static void setupAll() {
        System.out.println("Constructor Tests Start ");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Constructor Tests End ");
    }

    @Test
    @DisplayName("Valid constructor initialize")
    @Tag("sanity")
    void testValidConstructor() {
        ProductStock s = new ProductStock("P1", "WH-1", 10, 5, 50);

        assertAll(
                () -> assertEquals("P1", s.getProductId()),
                () -> assertEquals("WH-1", s.getLocation()),
                () -> assertEquals(10, s.getOnHand()),
                () -> assertEquals(0, s.getReserved()),
                () -> assertEquals(10, s.getAvailable()),
                () -> assertEquals(5, s.getReorderThreshold()),
                () -> assertEquals(50, s.getMaxCapacity())
        );
    }

    @Test
    @DisplayName("Invalid constructor arguments throw exceptions")
    @Tag("regression")
    void testInvalidConstructor() {

        assertThrows(IllegalArgumentException.class, () -> new ProductStock(
                "", "A", 1, 1, 10)
        );

        assertThrows(IllegalArgumentException.class, () -> new ProductStock(
                "P", null, 1, 1, 10)
        );

        assertThrows(IllegalArgumentException.class, () -> new ProductStock(
                "P", "A", -5, 1, 10)
        );

        assertThrows(IllegalArgumentException.class, () -> new ProductStock(
                "P", "A", 10, 5, 3)
        );

    }
}