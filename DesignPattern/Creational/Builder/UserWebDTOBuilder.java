package DesignPattern.Creational.Builder;

import java.time.LocalDate;
import java.time.Period;

public class UserWebDTOBuilder implements UserDTOBuilder{

    private String firstName;
    private String lastName;
    @Override
    public UserDTO build() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDTO getUserDTO() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDTOBuilder withAddress(Address address) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDTOBuilder withBirthday(LocalDate date) {
        Period ageInYears=Period.between(date, LocalDate.now());
        return null;
    }

    @Override
    public UserDTOBuilder withFirstName(String fname) {
        this.firstName=fname;
        return this;
    }

    @Override
    public UserDTOBuilder withLastName(String lname) {
        this.lastName=lname;
        return this;
    }

}
