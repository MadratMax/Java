package madrat;

public class Main {

    public static void main(String[] args) {

        var adress = new Adress.Builder()
                .city("St-Petersburg")
                .street("Admirala Konovalova")
                .buildingNumber(2)
                .build();

        var contact = new Contact.Builder()
                .name("Max")
                .lastName("Gaydideev")
                .age(35)
                .adress(adress)
                .build();

        contact.info();
    }
}
