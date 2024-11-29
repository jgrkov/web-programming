package mk.ukim.finki.lab.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab.model.Artist;
import mk.ukim.finki.lab.service.ArtistService;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ArtistListServlet", urlPatterns = { "/artist/artistsList" })
public class ArtistListServlet extends HttpServlet {

    private final ArtistService artistService;
    private final SpringTemplateEngine templateEngine;

    public ArtistListServlet(ArtistService artistService, SpringTemplateEngine templateEngine) {
        this.artistService = artistService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artist> artistList;
        artistList = artistService.listArtists();

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("artistList", artistList);
        templateEngine.process("artistsList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId;
        List<Artist> artistList;
        artistList = artistService.listArtists();

        if (req.getParameter("songRadio") != null) {
            trackId = req.getParameter("songRadio");
        } else {
            trackId = "-";
        }

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("trackId", trackId);
        context.setVariable("artistList", artistList);
        templateEngine.process("artistsList.html", context, resp.getWriter());
    }
}
