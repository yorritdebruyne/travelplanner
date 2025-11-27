package Model;

public class Traveller {
    String name, mail, phone, nationality;
    Integer age, passportNumber;

    public Traveller(String name, String mail, String phone, String nationality, Integer age, Integer passportNumber) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.nationality = nationality;
        this.age = age;
        this.passportNumber = passportNumber;
    }
}
