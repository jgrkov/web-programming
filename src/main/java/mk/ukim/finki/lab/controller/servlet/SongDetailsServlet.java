package mk.ukim.finki.lab.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab.model.Artist;
import mk.ukim.finki.lab.model.Song;
import mk.ukim.finki.lab.service.ArtistService;
import mk.ukim.finki.lab.service.SongService;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "SongDetailsServlet", urlPatterns = "/songDetails")
public class SongDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final SongService songService;
    private final ArtistService artistService;

    public SongDetailsServlet(SpringTemplateEngine templateEngine, SongService songService,
            ArtistService artistService) {
        this.templateEngine = templateEngine;
        this.songService = songService;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Song s = songService.listSongs().stream().findFirst().orElse(null);

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("entity", s);
        templateEngine.process("songDetails.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long trackId = Long.parseLong(req.getParameter("trackId"));
        String artistId = req.getParameter("artistId");
        Song s = songService.listSongs().stream().findFirst().orElse(null);

        if (trackId != null && artistId != null) {
            s = songService.findByTrackId(trackId);
            Artist a = artistService.findById(Long.valueOf(artistId));
            s.addPerformer(a);
        }

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("entity", s);
        templateEngine.process("songDetails.html", context, resp.getWriter());
    }
}
