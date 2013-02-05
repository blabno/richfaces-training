package pl.itcrowd.tutorials.richfaces.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Album {

    private Artist artist;

    private Ensemble ensemble;

    private Long id;

    private Set<Track> tracks = new HashSet<Track>();

    private Map<String, AlbumTranslation> translations = new HashMap<String, AlbumTranslation>();

    public Artist getArtist()
    {
        return artist;
    }

    public void setArtist(Artist artist)
    {
        if (artist != null) {
            ensemble = null;
        }
        this.artist = artist;
    }

    public Ensemble getEnsemble()
    {
        return ensemble;
    }

    public void setEnsemble(Ensemble ensemble)
    {
        if (ensemble != null) {
            artist = null;
        }
        this.ensemble = ensemble;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Set<Track> getTracks()
    {
        return tracks;
    }

    public void setTracks(Set<Track> tracks)
    {
        this.tracks = tracks;
    }

    public Map<String, AlbumTranslation> getTranslations()
    {
        return translations;
    }

    public void setTranslations(Map<String, AlbumTranslation> translations)
    {
        this.translations = translations;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Album)) {
            return false;
        }

        Album album = (Album) o;

        return !(id != null ? !id.equals(album.id) : album.id != null);
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
