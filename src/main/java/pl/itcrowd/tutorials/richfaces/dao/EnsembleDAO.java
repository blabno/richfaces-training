package pl.itcrowd.tutorials.richfaces.dao;

import pl.itcrowd.tutorials.richfaces.domain.Ensemble;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
@ManagedBean
public class EnsembleDAO {

    private final List<Ensemble> ensembles;

    public EnsembleDAO()
    {
        ensembles = Arrays.asList(new Ensemble(1L, "Roxette"), new Ensemble(2L, "Abba"), new Ensemble(3L, "Stones"));
    }

    public List<Ensemble> getAllEnsemble()
    {
        return Collections.unmodifiableList(ensembles);
    }
}
