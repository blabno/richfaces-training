package pl.itcrowd.tutorials.richfaces.view;

import pl.itcrowd.tutorials.richfaces.dao.ArtistDAO;
import pl.itcrowd.tutorials.richfaces.domain.Artist;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ArtistConverter implements Converter {

    public ArtistDAO getArtistDAO(FacesContext context)
    {
        return context.getApplication().evaluateExpressionGet(context, "#{artistDAO}", ArtistDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        return getArtistDAO(context).getArtistById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        return value == null ? null : "" + ((Artist) value).getId();
    }
}
