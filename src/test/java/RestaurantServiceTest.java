import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    // REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void setup() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    private int getInitialNumberOfRestaurants() {
        return service.getRestaurants().size();
    }

    private void addRestaurantAndVerifySize(String name, String location, LocalTime openingTime,
            LocalTime closingTime) {
        service.addRestaurant(name, location, openingTime, closingTime);
        assertEquals(getInitialNumberOfRestaurants() + 1, service.getRestaurants().size());
    }

    private void removeRestaurantAndVerifySize(String restaurantName) throws restaurantNotFoundException {
        service.removeRestaurant(restaurantName);
        assertEquals(getInitialNumberOfRestaurants() - 1, service.getRestaurants().size());
    }

    // >>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() {
        /*
         * since we are testing for an existing restaurant it should not throw an
         * exception so I changed the function delaration.
         */
        Restaurant foundRestaurant = service.findRestaurantByName("Amelie's cafe");
        assertNotNull(foundRestaurant);
        assertEquals("Amelie's cafe", foundRestaurant.getName());
    }

    // You may watch the video by Muthukumaran on how to write exceptions in Course
    // 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {

        assertThrows(restaurantNotFoundException.class, () -> service.findRestaurantByName("Pantry d'or"));
    }

    // <<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

    // >>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING
    // RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        removeRestaurantAndVerifySize("Amelie's cafe");
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
        addRestaurantAndVerifySize("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"),
                LocalTime.parse("23:00:00"));
    }
    // <<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING
    // RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}