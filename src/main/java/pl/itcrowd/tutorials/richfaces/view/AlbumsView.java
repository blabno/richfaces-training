package pl.itcrowd.tutorials.richfaces.view;

import pl.itcrowd.tutorials.richfaces.dao.AlbumDAO;
import pl.itcrowd.tutorials.richfaces.dao.ArtistDAO;
import pl.itcrowd.tutorials.richfaces.dao.EnsembleDAO;
import pl.itcrowd.tutorials.richfaces.dao.RecordingDAO;
import pl.itcrowd.tutorials.richfaces.domain.Album;
import pl.itcrowd.tutorials.richfaces.domain.AlbumTranslation;
import pl.itcrowd.tutorials.richfaces.domain.Artist;
import pl.itcrowd.tutorials.richfaces.domain.Ensemble;
import pl.itcrowd.tutorials.richfaces.domain.Recording;
import pl.itcrowd.tutorials.richfaces.domain.Track;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@ViewScoped
@ManagedBean
public class AlbumsView implements Serializable {

    private Album album;

    @ManagedProperty("#{albumDAO}")
    private AlbumDAO albumDAO;

    private List<Track> albumTracks;

    @ManagedProperty("#{artistDAO}")
    private ArtistDAO artistDAO;

    private List<Artist> artists;

    @ManagedProperty("#{ensembleDAO}")
    private EnsembleDAO ensembleDAO;

    private List<Ensemble> ensembles;

    private List<Locale> locales;

    @ManagedProperty("#{recordingDAO}")
    private RecordingDAO recordingDAO;

    private Map<Recording, Boolean> recordingSelection = new HashMap<Recording, Boolean>();

    private List<Recording> recordings;

    public AlbumsView()
    {
        edit(new Album());
    }

    public Album getAlbum()
    {
        return album;
    }

    public AlbumDAO getAlbumDAO()
    {
        return albumDAO;
    }

    public void setAlbumDAO(AlbumDAO albumDAO)
    {
        this.albumDAO = albumDAO;
    }

    public List<Track> getAlbumTracks()
    {
        if (albumTracks == null) {
            albumTracks = new ArrayList<Track>();
            albumTracks.addAll(album.getTracks());
            Collections.sort(albumTracks);
        }
        return albumTracks;
    }

    public List<Album> getAlbums()
    {
        return albumDAO.getAllAlbums();
    }

    public ArtistDAO getArtistDAO()
    {
        return artistDAO;
    }

    public void setArtistDAO(ArtistDAO artistDAO)
    {
        this.artistDAO = artistDAO;
    }

    public List<Artist> getArtists()
    {
        if (artists == null) {
            artists = artistDAO.getAllArtists();
        }
        return artists;
    }

    public EnsembleDAO getEnsembleDAO()
    {
        return ensembleDAO;
    }

    public void setEnsembleDAO(EnsembleDAO ensembleDAO)
    {
        this.ensembleDAO = ensembleDAO;
    }

    public List<Ensemble> getEnsembles()
    {
        if (ensembles == null) {
            ensembles = ensembleDAO.getAllEnsemble();
        }
        return ensembles;
    }

    public List<Locale> getLanguages()
    {
        if (locales == null) {
            locales = new ArrayList<Locale>();
            final Iterator<Locale> localeIterator = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
            while (localeIterator.hasNext()) {
                locales.add(localeIterator.next());
            }
        }
        return locales;
    }

    public RecordingDAO getRecordingDAO()
    {
        return recordingDAO;
    }

    public void setRecordingDAO(RecordingDAO recordingDAO)
    {
        this.recordingDAO = recordingDAO;
    }

    public Map<Recording, Boolean> getRecordingSelection()
    {
        return recordingSelection;
    }

    public List<Recording> getRecordings()
    {
        if (recordings == null) {
            recordings = recordingDAO.getAllRecordings();
        }
        return recordings;
    }

    public void edit(Album album)
    {
        this.album = album;
        albumTracks = null;
        setupTranslations();
    }

    public void addSelectedRecordings()
    {
        for (Map.Entry<Recording, Boolean> entry : recordingSelection.entrySet()) {
            if (entry.getValue()) {
                final Track track = new Track();
                track.setAlbum(album);
                track.setPosition(album.getTracks().size() + 1);
                track.setRecording(entry.getKey());
                album.getTracks().add(track);
            }
        }
        albumTracks = null;
    }

    public void newAlbum()
    {
        edit(new Album());
    }

    public void save()
    {
        albumDAO.save(album);
        edit(new Album());
    }

    private void setupTranslations()
    {
        for (Locale locale : getLanguages()) {
            final Map<String, AlbumTranslation> translations = album.getTranslations();
            final String language = locale.getISO3Language();
            if (!translations.containsKey(language)) {
                translations.put(language, new AlbumTranslation(album, null, language, null));
            }
        }
    }
}
