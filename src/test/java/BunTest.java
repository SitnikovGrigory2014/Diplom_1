import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.*;

public class BunTest {

    @Test
    public void testGetName() {
        Bun bun = new Bun("black bun", 100);
        assertEquals("black bun", bun.getName());
    }

    @Test
    public void testGetPrice() {
        Bun bun = new Bun("white bun", 200);
        assertEquals(200, bun.getPrice(), 0.0);
    }

    @Test
    public void testConstructor() {
        Bun bun = new Bun("red bun", 300);
        assertEquals("red bun", bun.name);
        assertEquals(300, bun.price, 0.0);
    }
}
