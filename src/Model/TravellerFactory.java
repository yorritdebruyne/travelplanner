package Model;

import java.util.UUID;

/**
 * A factory class for creating instances of the Traveller class,
 * including its specific subclasses like AdminTraveller and GuestTraveller.
 */
public class TravellerFactory {

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
