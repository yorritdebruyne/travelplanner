package Model;

import java.util.UUID;

/**
 * A factory class for creating instances of the Traveller class,
 * including its specific subclasses like AdminTraveller and GuestTraveller.
 */
public class TravellerFactory {

    // Main method for creating new travellers with random id generation
    public static Traveller createTraveller(
            TravellerType type,
            String name,
            String mail,
            String phone,
            String nationality,
            String passportNumber,
            int age
    ){
        String id = UUID.randomUUID().toString(); // Centralized unique id generation
        return createTraveller(type, id, name, mail, phone, nationality, passportNumber, age);
    }

    // Overloaded method if you already have an id (e.g. for updates)
    public static Traveller createTraveller(
            TravellerType type,
            String id,
            String name,
            String mail,
            String phone,
            String nationality,
            String passportNumber,
            int age
    ){
        return switch (type){
            case ADMIN -> new AdminTraveller(id, name, mail, phone, nationality, passportNumber, age);
            case GUEST -> new GuestTraveller(id, name, mail, phone, nationality, passportNumber, age);
        };
    }
}
