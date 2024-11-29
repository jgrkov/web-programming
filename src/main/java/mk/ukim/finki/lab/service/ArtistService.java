package mk.ukim.finki.lab.service;

import java.util.List;

import mk.ukim.finki.lab.model.Artist;

public interface ArtistService {
    List<Artist> listArtists();

    Artist findById(Long id);
}
