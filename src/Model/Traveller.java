package Model;

public abstract class Traveller {
    // Traveller for association to trips, not name/email because they can change.
    // Keep id constant across updates
    protected String id;
    protected String name, mail, phone, nationality, passportNumber;
    protected int age;

    public Traveller(String id, String name, String mail, String phone, String nationality, String passportNumber, int age) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.nationality = nationality;
        this.passportNumber = passportNumber;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " (" + nationality + ", age " + age + ")";
    }

    public String getDetails() {
        return "Name: " + name +
                "\nEmail: " + mail +
                "\nPhone: " + phone +
                "\nNationality: " + nationality +
                "\nPassport: " + passportNumber +
                "\nAge: " + age;
    }

}
