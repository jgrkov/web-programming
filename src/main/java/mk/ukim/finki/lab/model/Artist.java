package mk.ukim.finki.lab.model;

import lombok.Data;
import lombok.Generated;

@Data
public class Artist {
    private static Long ID_CNT = 0L;

    @Generated
    private Long id;

    private String firstName;
    private String lastName;
    private String bio;

    public Artist(String firstName, String lastName, String bio) {
        this.id = ID_CNT++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

}