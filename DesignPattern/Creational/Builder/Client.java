package DesignPattern.Creational.Builder;

import java.time.LocalDate;

public class Client {

    public static void main(String[] args) {
        User user=createUser();
        UserDTOBuilder builder=new UserWebDTOBuilder();

        UserDTO dto=directBuild(builder, user);
        System.out.println(dto.getName());
        System.out.println(dto.getAge());
        System.out.println(dto.getAddress());
    }

    private static UserDTO directBuild(UserDTOBuilder builder, User user){       
        return builder.withFirstName(user.getFirstName())
                        .withLastName(user.getLastName())
                        .withBirthday(user.getBirthday())
                        .withAddress(user.getAddress())
                        .build();       //method chaining
        
        
    }

    public static User createUser(){
        User user=new User();
        user.setBirthday(LocalDate.of(2000,2,15));
        user.setFirstName("Nam");
        user.setLastName("Tran");
        Address address=new Address();
        address.setHouseNumber("100");
        address.setStreet("State Street");
        address.setCity("Pawnee");
        address.setState("Indiana");
        address.setZipCode("47998");
        user.setAddress(address);
        return user;
    }
    
}
