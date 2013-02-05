package pl.itcrowd.tutorials.richfaces.view;

import pl.itcrowd.tutorials.richfaces.dao.AlbumDAO;
import pl.itcrowd.tutorials.richfaces.dao.ArtistDAO;
import pl.itcrowd.tutorials.richfaces.dao.EnsembleDAO;
import pl.itcrowd.tutorials.richfaces.domain.Album;
import pl.itcrowd.tutorials.richfaces.domain.AlbumTranslation;
import pl.itcrowd.tutorials.richfaces.domain.Artist;
import pl.itcrowd.tutorials.richfaces.domain.Ensemble;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@ViewScoped
@ManagedBean
public class AlbumsView implements Serializable {

    private Album album = new Album();

    @ManagedProperty("#{albumDAO}")
    private AlbumDAO albumDAO;

    @ManagedProperty("#{artistDAO}")
    private ArtistDAO artistDAO;

    private List<Artist> artists;

    @ManagedProperty("#{ensembleDAO}")
    private EnsembleDAO ensembleDAO;

    private List<Ensemble> ensembles;

    private List<Locale> locales;

    public AlbumsView()
    {
        setupTranslations();
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

    public void save()
    {
        albumDAO.save(album);
        album = new Album();
        setupTranslations();
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
