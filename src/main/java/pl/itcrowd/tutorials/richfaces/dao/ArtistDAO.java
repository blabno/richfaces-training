package pl.itcrowd.tutorials.richfaces.dao;

import pl.itcrowd.tutorials.richfaces.domain.Artist;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
@ManagedBean
public class ArtistDAO {

    private final List<Artist> artists;

    public ArtistDAO()
    {
        artists = Arrays.asList(new Artist(1L, "John"), new Artist(2L, "Tom"), new Artist(3L, "Celine"));
    }

    public List<Artist> getAllArtists()
    {
        return Collections.unmodifiableList(artists);
    }
}
