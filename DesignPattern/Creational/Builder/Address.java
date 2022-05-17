package DesignPattern.Creational.Builder;


public class Address {
    
    private String houseNumber;
    private String street;
    private String city;
    private String zipcode;
    private String state;

    public String getHouseNumber(){
        return this.houseNumber;
    }

    public void setHouseNumber(String house_number){
        this.houseNumber=house_number;
    }

    public String getStreet(){
        return this.street;
    }

    public void setStreet(String street){
        this.street=street;
    }

    public String getCity(){
        return this.city;
    }

    public void setCity(String city){
        this.city=city;
    }

    public String getZipCode(){
        return this.zipcode;
    }

    public void setZipCode(String zip_code){
        this.zipcode=zip_code;
    }

    public String getState(){
        return this.state;
    }

    public void setState(String state){
        this.state=state;
    }

    
}
