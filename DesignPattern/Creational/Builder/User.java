package DesignPattern.Creational.Builder;

import java.time.LocalDate;

public class User{

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Address address;

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String first_name){
        this.firstName=first_name;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String last_name){
        this.lastName=last_name;
    }

    public LocalDate getBirthday(){
        return birthday;
    }

    public void setBirthday(LocalDate birthday){
        this.birthday=birthday;
    }

    public Address getAddress(){
        return this.address;
    }

    public void setAddress(Address address){
        this.address.setHouseNumber(address.getHouseNumber());
        this.address.setStreet(address.getStreet());
        this.address.setZipCode(address.getZipCode());
        this.address.setCity(address.getCity());
        this.address.setState(address.getState());
    }

    
}