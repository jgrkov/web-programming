package mk.ukim.finki.lab.repository;

import org.springframework.stereotype.Repository;

import mk.ukim.finki.lab.model.Artist;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {
    private final List<Artist> artists;

    public ArtistRepository() {
        this.artists = List.of(
                new Artist("Selena", "Gomez", "Artist1_Bio"),
                new Artist("Shawn", "Mendes", "Artist2_Bio"),
                new Artist("Edward", "Sheeran", "Artist3_Bio"),
                new Artist("Taylor", "Swift", "Artist4_Bio"),
                new Artist("Post", "Malone", "Artist5_Bio"));
    }

    public List<Artist> findAll() {
        return artists;
    }

    public Optional<Artist> findById(Long id) {
        for (Artist artist : artists) {
            if (artist.getId().equals(id)) {
                return Optional.of(artist);
            }
        }
        return Optional.empty();
    }

}
