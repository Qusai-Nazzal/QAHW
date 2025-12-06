package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
class StockOperationsTest {

    private ProductStock stock;

    @BeforeEach
    void init() {
        stock = new ProductStock("P1", "L1", 10, 5, 50);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Add normal stock amount")
    void testAddStockNormal() {
        stock.addStock(5);
        assertEquals(15, stock.getOnHand());
    }

    @Test
    @DisplayName("Adding up to max capacity works")
    void testAddStockBoundary() {
        stock.addStock(40);
        assertEquals(50, stock.getOnHand());
    }

    @Test
    @DisplayName("Adding beyond capacity throws error")
    @Tag("regression")
    void testAddTooMuch() {
        assertThrows(IllegalStateException.class, () -> stock.addStock(100));
    }

    @Test
    @DisplayName("Add zero or negative throws")
    void testAddInvalid() {
        assertThrows(IllegalArgumentException.class, () -> stock.addStock(0));
        assertThrows(IllegalArgumentException.class, () -> stock.addStock(-3));
    }

    @Test
    @DisplayName("Removing damaged stock works")
    void testRemoveDamaged() {
        stock.reserve(3);
        stock.removeDamaged(4);

        assertEquals(6, stock.getOnHand());
        assertEquals(3, stock.getReserved());
    }

    @Test
    @DisplayName("Removing more than onHand throws")
    void testRemoveTooMuch() {
        assertThrows(IllegalStateException.class, () -> stock.removeDamaged(500));
    }

    @Test
    void testRemoveDamagedZero() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.removeDamaged(0));
    }

    @Test
    void testRemoveDamagedNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.removeDamaged(-3));
    }

    @Test
    @DisplayName("Change Location to Null")
    void testChangeLocationNull() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.changeLocation(null));
    }

    @Test
    @DisplayName("Change Location to blank")
    void testChangeLocationBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.changeLocation(" "));
    }

    @Test
    @DisplayName("Change Location to invalid")
    void testChangeLocationValid() {
        stock.changeLocation("B3");
        assertEquals("B3", stock.getLocation());
    }

}