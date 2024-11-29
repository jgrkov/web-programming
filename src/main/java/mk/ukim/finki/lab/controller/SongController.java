package mk.ukim.finki.lab.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mk.ukim.finki.lab.model.Album;
import mk.ukim.finki.lab.model.Song;
import mk.ukim.finki.lab.service.AlbumService;
import mk.ukim.finki.lab.service.SongService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songs;
    private final AlbumService albums;

    public SongController(SongService songs, AlbumService albums) {
        this.songs = songs;
        this.albums = albums;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {

        if (error != null) {
            model.addAttribute(error, model);
            return "listSongs";
        }

        model.addAttribute("songList", songs.listSongs());
        return "listSongs";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        model.addAttribute("albums", this.albums.findAll());
        return "add-song";
    }

    @PostMapping("/add")
    public String postMethodName(
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam Integer releaseYear,
            @RequestParam Long albumId,
            @RequestParam Boolean update,
            @RequestParam(required = false) Long id) {

        Album album = this.albums.findAll().stream().filter(al -> al.getId().equals(albumId)).findAny().get();

        if (update) {
            this.songs.updateSong(id, title, genre, releaseYear, album);
            return "redirect:/songs";

        }

        this.songs.createSong(title, genre, releaseYear, new ArrayList<>(), album);
        return "redirect:/songs";
    }

    @PostMapping("/edit/{id}")
    public String updateEvent(
            @PathVariable Long id, Model model) {

        Song song = this.songs.findByTrackId(id);
        model.addAttribute("song", song);
        model.addAttribute("albums", this.albums.findAll());
        model.addAttribute("update", "has");

        return "add-song";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(
            @PathVariable Long id) {
        this.songs.removeSong(id);
        return "redirect:/songs";
    }

}
