package mk.ukim.finki.lab.model;

import lombok.Data;
import lombok.Generated;

@Data
public class Album {

    private static Long ID_CNT = 0L;

    @Generated
    private Long id;

    private String name;
    private String genre;
    private String releaseYear;

    public Album(String name, String genre, String releaseYear) {
        this.id = ID_CNT++;
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

}