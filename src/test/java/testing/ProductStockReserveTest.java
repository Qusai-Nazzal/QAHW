package testing;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class ProductStockReserveTest {
    private ProductStock stock;

    @BeforeEach
    void init() {
        stock = new ProductStock("P", "A1", 10, 5, 50);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Reserve valid amount")
    void testReserveValid() {
        stock.reserve(4);
        assertEquals(4, stock.getReserved());
        assertEquals(6, stock.getAvailable());
    }

    @Test
    @DisplayName("Reserve more than available fails")
    void testReserveTooMuch() {
        assertThrows(IllegalStateException.class, () -> stock.reserve(50));
    }

    @Test
    @DisplayName("Release reservation")
    void testReleaseReservation() {
        stock.reserve(5);
        stock.releaseReservation(3);

        assertEquals(2, stock.getReserved());
    }

    @Test
    @DisplayName("Release more than reserved fails")
    @Tag("regression")
    void testReleaseTooMuch() {
        stock.reserve(2);
        assertThrows(IllegalStateException.class, () -> stock.releaseReservation(5));
    }

    @Test
    @DisplayName("Ship reserved stock reduces onHand & reserved")
    void testShipReserved() {
        stock.reserve(4);
        stock.shipReserved(4);

        assertEquals(6, stock.getOnHand());
        assertEquals(0, stock.getReserved());
    }

    @Test
    @DisplayName("Shipping more than reserved fails")
    void testShipTooMuch() {
        stock.reserve(2);
        assertThrows(IllegalStateException.class, () -> stock.shipReserved(10));
    }

    @Test
    @DisplayName("reserve zero")
    void testReserveZero() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.reserve(0));
    }

    @Test
    @DisplayName("reserve negative")
    void testReserveNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.reserve(-4));
    }
    @Test
    @DisplayName("Release reservation Zero")
    void testReleaseReservationZero() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.releaseReservation(0));
    }

    @Test
    void testReleaseReservationNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.releaseReservation(-2));
    }
    @Test
    void testShipReservedZero() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.shipReserved(0));
    }

    @Test
    void testShipReservedNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> stock.shipReserved(-1));
    }

}