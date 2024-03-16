
public class Person {
    private String name;
    private String surName;
    private String email;
    
    public Person(String name, String surName, String email ){
        this.name=name;
        this.surName=surName;
        this.email=email;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surName;
    }
    public String getEmail(){
        return email;
    }
}


