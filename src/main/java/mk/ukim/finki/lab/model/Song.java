package mk.ukim.finki.lab.model;

import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
public class Song {

    private static Long ID_CNT = 0L;

    @Generated
    private Long id;

    private String title;
    private String genre;
    private Integer releaseYear;
    private Album album;
    private List<Artist> performers;

    public Song(String title, String genre, Integer releaseYear, List<Artist> performers, Album album) {
        this.id = ID_CNT++;

        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = performers;
        this.album = album;
    }

    public void addPerformer(Artist artist) {
        performers.add(artist);
    }

}