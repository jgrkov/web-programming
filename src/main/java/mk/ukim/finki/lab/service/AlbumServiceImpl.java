package mk.ukim.finki.lab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mk.ukim.finki.lab.model.Album;
import mk.ukim.finki.lab.repository.AlbumRepository;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albums;

    public AlbumServiceImpl(AlbumRepository albums) {
        this.albums = albums;
    }

    @Override
    public List<Album> findAll() {
        return this.albums.findAll();
    }

}
