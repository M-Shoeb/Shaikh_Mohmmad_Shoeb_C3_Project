import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;



class RestaurantTest {
    Restaurant restaurant;
    // REFACTOR ALL THE REPEATED LINES OF CODE

    // for refactoring the code we are introdusing some helper functions

    @BeforeEach
    public void setup() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    private int getInitialMenuSize() {
        return restaurant.getMenu().size();
    }

    private void addItemToMenuAndVerifySize(String name, int price) {
        restaurant.addToMenu(name, price);
        assertEquals(getInitialMenuSize() + 1, restaurant.getMenu().size());
    }

    private void removeItemFromMenuAndVerifySize(String itemName) throws itemNotFoundException {
        restaurant.removeFromMenu(itemName);
        assertEquals(getInitialMenuSize() - 1, restaurant.getMenu().size());
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    // -------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN
    // INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        // WRITE UNIT TEST CASE HERE
        LocalTime openingTime = LocalTime.parse("10:00:00");
        LocalTime closingTime = LocalTime.parse("23:00:00");
        restaurant = new Restaurant("Test Restaurant", "Test Location", openingTime, closingTime);

        assertTrue(restaurant.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        // WRITE UNIT TEST CASE HERE
        LocalTime openingTime = LocalTime.parse("10:00:00");
        LocalTime closingTime = LocalTime.parse("23:00:00");
        restaurant = new Restaurant("Test Restaurant", "Test Location", openingTime, closingTime);

        assertFalse(restaurant.isRestaurantOpen());

    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        addItemToMenuAndVerifySize("Sizzling brownie", 319);
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        removeItemFromMenuAndVerifySize("Vegetable lasagne");
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }
    // <<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // <<<<<<<<<<<<<<<<Calculate Total Cost with TDD>>>>>>>>>>>>>>>
    @Test
    public void calculate_total_cost_should_return_correct_value_for_selected_items() throws itemNotFoundException {

        String[] selectedItems = { "Sweet corn soup", "Vegetable lasagne" };
        List<String> selectedItemsList = Arrays.asList(selectedItems);

        int totalCost = restaurant.calculateTotalCost(selectedItemsList);
        assertEquals(388, totalCost);
    }

}