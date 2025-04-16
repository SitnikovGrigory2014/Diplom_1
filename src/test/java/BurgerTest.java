import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
        burger.setBuns(bun);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {100f, 50f, 50f, 300f},
                {200f, 100f, 150f, 650f},
                {150f, 0f, 0f, 300f}
        });
    }

    @Parameterized.Parameter(0)
    public float bunPrice;

    @Parameterized.Parameter(1)
    public float ingredient1Price;

    @Parameterized.Parameter(2)
    public float ingredient2Price;

    @Parameterized.Parameter(3)
    public float expectedPrice;

    @Test
    public void testGetPrice() {
        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient1.getPrice()).thenReturn(ingredient1Price);
        when(ingredient2.getPrice()).thenReturn(ingredient2Price);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }

    @Test
    public void testSetBuns() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient1, burger.ingredients.get(1));
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void testGetReceipt() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("hot sauce");
        when(ingredient1.getPrice()).thenReturn(100f);
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getName()).thenReturn("cutlet");
        when(ingredient2.getPrice()).thenReturn(100f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String lineSeparator = System.lineSeparator();
        String expectedReceipt = String.format("(==== %s ====)%s", bun.getName(), lineSeparator) +
                String.format("= %s %s =%s", ingredient1.getType().toString().toLowerCase(),
                        ingredient1.getName(), lineSeparator) +
                String.format("= %s %s =%s", ingredient2.getType().toString().toLowerCase(),
                        ingredient2.getName(), lineSeparator) +
                String.format("(==== %s ====)%s", bun.getName(), lineSeparator) +
                String.format("%sPrice: %f%s", lineSeparator, 400f, lineSeparator);

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
