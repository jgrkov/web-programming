package mk.ukim.finki.lab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mk.ukim.finki.lab.model.Album;
import mk.ukim.finki.lab.model.Artist;
import mk.ukim.finki.lab.model.Song;
import mk.ukim.finki.lab.repository.ArtistRepository;
import mk.ukim.finki.lab.repository.SongRepository;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Song findByTrackId(Long trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public Song createSong(String title, String genre, Integer releaseYear, List<Artist> performers, Album album) {
        return this.songRepository.createSong(new Song(title, genre, releaseYear, performers, album));
    }

    @Override
    public boolean removeSong(Long id) {
        return this.songRepository.removeSong(id);
    }

    @Override
    public Song updateSong(Long id, String title, String genre, Integer releaseYear, Album album) {
        return this.songRepository.updateSong(id, title, genre, releaseYear, album);
    }

}
