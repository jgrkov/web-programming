package mk.ukim.finki.lab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mk.ukim.finki.lab.model.Artist;
import mk.ukim.finki.lab.repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) {
        return artistRepository.findById(id).orElse(null);
    }
}
