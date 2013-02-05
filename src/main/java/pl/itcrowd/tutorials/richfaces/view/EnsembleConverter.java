package pl.itcrowd.tutorials.richfaces.view;

import pl.itcrowd.tutorials.richfaces.dao.EnsembleDAO;
import pl.itcrowd.tutorials.richfaces.domain.Ensemble;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class EnsembleConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        return getEnsembleDAO(context).getEnsembleById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        return value == null ? null : "" + ((Ensemble) value).getId();
    }

    public EnsembleDAO getEnsembleDAO(FacesContext context)
    {
        return context.getApplication().evaluateExpressionGet(context, "#{ensembleDAO}", EnsembleDAO.class);
    }
}