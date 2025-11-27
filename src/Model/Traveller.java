package Model;

public class Traveller {
    String name, mail, phone, nationality, passportNumber;
    Integer age;

    public Traveller(String name, String mail, String phone, String nationality, String passportNumber, Integer age) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.nationality = nationality;
        this.passportNumber = passportNumber;
        this.age = age;
    }
}
