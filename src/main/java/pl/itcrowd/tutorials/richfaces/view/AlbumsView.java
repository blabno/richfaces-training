package pl.itcrowd.tutorials.richfaces.view;

import pl.itcrowd.tutorials.richfaces.dao.AlbumDAO;
import pl.itcrowd.tutorials.richfaces.domain.Album;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@ManagedBean
public class AlbumsView implements Serializable {

    @ManagedProperty("#{albumDAO}")
    private AlbumDAO albumDAO;

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
}
