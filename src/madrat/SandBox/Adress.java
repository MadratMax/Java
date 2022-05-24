package madrat.SandBox;

public class Adress {

    private final String city;
    private final String street;
    private final int buildingNumber;

    public Adress(Builder builder){

        this.city = builder.city;
        this.street = builder.street;
        this.buildingNumber = builder.buildingNumber;
    }

    public void info(){
        System.out.println("Adress:");
        System.out.println(this.city);
        System.out.println(this.street);
        System.out.println(this.buildingNumber);
    }

    public static class Builder{

        private String city;
        private String street;
        private int buildingNumber;

        public Boolean notCompleted(){
            if(this.city == ""){
                return true;
            }
            if(this.street == ""){
                return true;
            }
            if(this.buildingNumber == 0){
                return true;
            }
            return false;
        }

        public Builder city(String city){
            this.city = city;
            return this;
        }

        public Builder street(String street){
            this.street = street;
            return this;
        }

        public Builder buildingNumber(int buildingNumber){
            this.buildingNumber = buildingNumber;
            return this;
        }

        public Adress build(){
            return new Adress(this);
        }
    }
}
