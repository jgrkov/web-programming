package mk.ukim.finki.lab.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import mk.ukim.finki.lab.model.Album;

@Repository
public class AlbumRepository {
    private final List<Album> albums;

    public AlbumRepository() {
        this.albums = List.of(
                new Album("Arcade Fire", "Pop", "2024"),
                new Album("Boyz II Men", "Jazz", "1997"),
                new Album("Nick of time", "Pop-rock", "1989"),
                new Album("Heart like a wheel", "Rock", "2003"),
                new Album("The Stooges", "Pop", "1969"));
    }

    public List<Album> findAll() {
        return albums;
    }

    public Optional<Album> findById(Long id) {
        return albums.stream().filter(album -> album.getId().equals(id)).findAny();
    }

}
