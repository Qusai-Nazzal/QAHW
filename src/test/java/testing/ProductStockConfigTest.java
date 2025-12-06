package testing;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class ProductStockConfigTest {
    private ProductStock stock;

    @BeforeEach
    void init() {
        stock = new ProductStock("P", "A1", 10, 5, 50);
    }

    @Test
    @DisplayName("Reorder is needed when available < threshold")
    void testReorderNeeded() {
        stock.reserve(6);
        assertTrue(stock.isReorderNeeded());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Reorder is NOT needed when available >= threshold")
    void testReorderNotNeeded() {
        assertFalse(stock.isReorderNeeded());
    }


    @Test
    @DisplayName("Update reorder threshold")
    void testUpdateThreshold() {
        stock.updateReorderThreshold(3);
        assertEquals(3, stock.getReorderThreshold());
    }
    @Test
    @DisplayName("update max capacity less than threshold")
    void testUpdateMaxCapacityLessThanThreshold() {
        ProductStock p = new ProductStock("P", "A1", 4, 15, 10);
        p.updateMaxCapacity(12);
        assertEquals(12, p.getReorderThreshold());
    }

    @Test
    @DisplayName("Update reorder threshold above max")
    void testUpdateThresholdAboveMax() {
        assertThrows(IllegalArgumentException.class, () -> stock.updateReorderThreshold(60));
    }

    @Test
    @DisplayName("Update reorder threshold negative")
    void testUpdateThresholdNegative() {
        assertThrows(IllegalArgumentException.class, () -> stock.updateReorderThreshold(-1));
    }

    @Test
    @DisplayName("Update max capacity")
    void testUpdateMaxCapacity() {
        stock.updateMaxCapacity(100);
        assertEquals(100, stock.getMaxCapacity());
    }
    @Test
    void testUpdateMaxCapacityZero() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.updateMaxCapacity(0));
    }

    @Test
    void testUpdateMaxCapacityNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.updateMaxCapacity(-10));
    }

    @Test
    @DisplayName("Updating capacity to below onHand fails")
    @Tag("regression")
    void testInvalidCapacityUpdate() {
        assertThrows(IllegalStateException.class, () -> stock.updateMaxCapacity(5));
    }

    @Disabled("Future enhancement not implemented yet")
    @Test
    @DisplayName("future feature")
    void testFutureFeature() {}

}