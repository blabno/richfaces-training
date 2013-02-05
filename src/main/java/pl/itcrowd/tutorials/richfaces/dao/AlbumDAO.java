package pl.itcrowd.tutorials.richfaces.dao;

import pl.itcrowd.tutorials.richfaces.domain.Album;
import pl.itcrowd.tutorials.richfaces.domain.AlbumTranslation;
import pl.itcrowd.tutorials.richfaces.domain.Artist;
import pl.itcrowd.tutorials.richfaces.domain.Ensemble;
import pl.itcrowd.tutorials.richfaces.domain.Track;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@ApplicationScoped
@ManagedBean
public class AlbumDAO {

    private final List<Album> albums;

    private final Random random = new Random();

    private long ALBUM_ID_SEQUENCE = 1;

    private long ALBUM_TRANSLATION_ID_SEQUENCE = 1;

    private long TRACK_ID_SEQUENCE = 1;

    @ManagedProperty("#{artistDAO}")
    private ArtistDAO artistDAO;

    @ManagedProperty("#{ensembleDAO}")
    private EnsembleDAO ensembleDAO;

    public AlbumDAO()
    {
        albums = new ArrayList<Album>();
    }

    public List<Album> getAllAlbums()
    {
        return Collections.unmodifiableList(albums);
    }

    public ArtistDAO getArtistDAO()
    {
        return artistDAO;
    }

    public void setArtistDAO(ArtistDAO artistDAO)
    {
        this.artistDAO = artistDAO;
    }

    public EnsembleDAO getEnsembleDAO()
    {
        return ensembleDAO;
    }

    public void setEnsembleDAO(EnsembleDAO ensembleDAO)
    {
        this.ensembleDAO = ensembleDAO;
    }

    @PostConstruct
    public void postConstruct()
    {
        final List<Artist> artists = artistDAO.getAllArtists();
        final List<Ensemble> ensembles = ensembleDAO.getAllEnsemble();
        generateAlbum(artists, ensembles, "Pearls of passion");
        generateAlbum(artists, ensembles, "Enter sandman");
    }

    public void save(Album album)
    {
        if (album.getId() == null) {
            album.setId(ALBUM_ID_SEQUENCE++);
        }
        for (Iterator<AlbumTranslation> iterator = album.getTranslations().values().iterator(); iterator.hasNext(); ) {
            AlbumTranslation translation = iterator.next();
            final String translationText = translation.getTitle();
            if (translationText == null || "".equals(translationText.trim())) {
                iterator.remove();
            } else if (translation.getId() == null) {
                translation.setId(ALBUM_TRANSLATION_ID_SEQUENCE++);
            }
        }
        for (Track track : album.getTracks()) {
            if (track.getId() == null) {
                track.setId(TRACK_ID_SEQUENCE++);
            }
        }
        if (!albums.contains(album)) {
            albums.add(album);
        }
    }

    private void generateAlbum(List<Artist> artists, List<Ensemble> ensembles, String title)
    {
        final Album album = new Album();
        if (random.nextBoolean()) {
            album.setArtist(getRandomElement(artists));
        }
        if (random.nextBoolean()) {
            album.setEnsemble(getRandomElement(ensembles));
        }
        final String language = Locale.ENGLISH.getISO3Language();
        album.getTranslations().put(language, new AlbumTranslation(album, null, language, title));
        save(album);
    }

    private <T> T getRandomElement(List<T> list)
    {
        return list.isEmpty() ? null : list.get(random.nextInt(list.size()));
    }
}