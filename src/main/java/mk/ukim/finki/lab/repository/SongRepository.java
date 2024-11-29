package mk.ukim.finki.lab.repository;

import org.springframework.stereotype.Repository;

import mk.ukim.finki.lab.model.Album;
import mk.ukim.finki.lab.model.Artist;
import mk.ukim.finki.lab.model.Song;
import mk.ukim.finki.lab.service.AlbumService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {
    private final List<Song> songs;
    private final AlbumService albums;

    public SongRepository(AlbumService albums) {
        this.albums = albums;
        this.songs = new ArrayList<>(Arrays.asList(
                new Song("Sao Paulo", "Brazilian funk", 2024, new ArrayList<>(), this.albums.findAll().get(0)),
                new Song("Shawn", "Pop-rock", 2024, new ArrayList<>(), this.albums.findAll().get(1)),
                new Song("Fear When You Fly", "R&B", 2024, new ArrayList<>(), this.albums.findAll().get(2)),
                new Song("Mi Camino", "Latin Pop", 2024, new ArrayList<>(), this.albums.findAll().get(3)),
                new Song("DUSK", "Electronic", 2024, new ArrayList<>(), this.albums.findAll().get(4))));
    }

    public List<Song> findAll() {
        return songs;
    }

    public Song findByTrackId(Long trackId) {
        for (Song song : songs) {
            if (song.getId().equals(trackId)) {
                return song;
            }
        }
        return null;
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        return artist;
    }

    public Song createSong(Song song) {

        if (this.songs.stream().anyMatch(e -> e.getId().equals(song.getId()))) {
            return song;
        }
        this.songs.add(song);
        return song;
    }

    public Song updateSong(Long id, String title, String genre, Integer releaseDate, Album album) {

        Song toUpdate = this.songs
                .stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);

        if (toUpdate != null) {
            toUpdate.setTitle(title);
            toUpdate.setGenre(genre);
            toUpdate.setReleaseYear(releaseDate);
            toUpdate.setAlbum(album);
        }

        return toUpdate;
    }

    public boolean removeSong(Long id) {

        Optional<Song> toDelete = this.songs
                .stream()
                .filter(event -> event.getId().equals(id))
                .findAny();

        return toDelete.isPresent() ? this.songs.remove(toDelete.get()) : false;
    }
}