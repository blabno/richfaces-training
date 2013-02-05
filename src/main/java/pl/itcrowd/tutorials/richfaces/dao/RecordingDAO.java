package pl.itcrowd.tutorials.richfaces.dao;

import pl.itcrowd.tutorials.richfaces.domain.Artist;
import pl.itcrowd.tutorials.richfaces.domain.Recording;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@ApplicationScoped
@ManagedBean
public class RecordingDAO {

    private final Random random = new Random();

    private final List<Recording> recordings;

    public RecordingDAO()
    {
        recordings = new ArrayList<Recording>();
    }

    public List<Recording> getAllRecordings()
    {
        return Collections.unmodifiableList(recordings);
    }

    @PostConstruct
    public void postConstruct()
    {
        final List<Artist> allArtists = new ArtistDAO().getAllArtists();
        recordings.add(new Recording(getRandomElement(allArtists), 1L, "Title 1"));
        recordings.add(new Recording(getRandomElement(allArtists), 2L, "Chorus"));
        recordings.add(new Recording(getRandomElement(allArtists), 3L, "Dilema"));
    }

    private <T> T getRandomElement(List<T> list)
    {
        return list.isEmpty() ? null : list.get(random.nextInt(list.size()));
    }
}
