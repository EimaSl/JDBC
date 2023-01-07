package teamWork.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guest {

    private int id;
    private String name;
    private int age;
    private String emailAddress;
    private String nationality;

    public Guest(String name, int age, String emailAddress, String nationality) {
        this.name = name;
        this.age = age;
        this.emailAddress = emailAddress;
        this.nationality = nationality;
    }
}
