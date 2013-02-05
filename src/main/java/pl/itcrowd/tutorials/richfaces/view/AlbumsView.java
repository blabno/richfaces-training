package pl.itcrowd.tutorials.richfaces.view;

import pl.itcrowd.tutorials.richfaces.dao.AlbumDAO;
import pl.itcrowd.tutorials.richfaces.domain.Album;
import pl.itcrowd.tutorials.richfaces.domain.AlbumTranslation;

import javax.faces.application.FacesMessage;
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Saving album"));
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
