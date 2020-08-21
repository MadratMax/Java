package madrat;

public class Contact {

    private final String name;
    private final String lastname;
    private final int age;
    private final Adress adress;

    private Contact(Builder builder){
        if(builder.notCompleted()){
            throw new NullPointerException("Not completed");
        }
        this.name = builder.name;
        this.lastname = builder.lastname;
        this.age = builder.age;
        this.adress = builder.adress;
    }

    public void info(){
        System.out.println("---------------------------------------------------");
        System.out.println("Contact info:");
        System.out.println(this.name);
        System.out.println(this.lastname);
        System.out.println(this.age);
        System.out.println("---------------------------------------------------");
        adress.info();
        System.out.println("---------------------------------------------------");
    }

    public static class Builder{

        private String name;
        private String lastname;
        private int age;
        private Adress adress;

        public Boolean notCompleted(){
            if(this.name.isEmpty()){
                return true;
            }
            if(this.lastname.isEmpty()){
                return true;
            }
            if(this.age == 0){
                return true;
            }
            if(this.adress == null){
                return true;
            }
            if(this.age == 0){
                return true;
            }
            return false;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder lastName(String lastname){
            this.lastname = lastname;
            return this;
        }

        public Builder age(int age){
            this.age = age;
            return this;
        }

        public Builder adress(Adress adress){
            this.adress = adress;
            return this;
        }

        public Contact build(){
            return new Contact(this);
        }
    }
}
