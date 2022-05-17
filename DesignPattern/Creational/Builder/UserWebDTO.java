package DesignPattern.Creational.Builder;

public class UserWebDTO implements UserDTO{

    private String name;

    private String address;

    private String age;

    public UserWebDTO(String name, String address, String age){
        this.name=name;
        this.address=address;
        this.age=age;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    public String getAge(){
        return this.age;
    }
    
}
