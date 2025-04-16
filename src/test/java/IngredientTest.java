import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;

public class IngredientTest {

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        assertEquals("cutlet", ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "chili sauce", 300);
        assertEquals(300, ingredient.getPrice(), 0.0);
    }

    @Test
    public void testConstructor() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "dinosaur", 200);
        assertEquals(IngredientType.FILLING, ingredient.type);
        assertEquals("dinosaur", ingredient.name);
        assertEquals(200, ingredient.price, 0.0);
    }
}
