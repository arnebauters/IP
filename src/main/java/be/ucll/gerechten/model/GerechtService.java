package be.ucll.gerechten.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GerechtService {
    List<Gerecht> gerechten = new ArrayList<Gerecht>();

    // hardcode some values, definitively not the way to go !!!!
    public GerechtService() {
        gerechten.add(new Gerecht(5, "Spaghetti", "dagschotel"));
        gerechten.add(new Gerecht(2.5,"Tomatensoep met balletjes","dagschotel" ));
        gerechten.add(new Gerecht(5.5, "Vol-aux-vents","dagschotel"));
    }

    // just return the whole list, JSP page takes care of presentation
    public List<Gerecht> getAllGerechten() {
        return gerechten;
    }

    // look for a feedback by id (see controller)
    /*public Feedback findFeedbackById(int id) {
        for(Feedback feedback : feedbacks){
            if(feedback.getId() == id){
                return feedback;
            }
        }
        //return null;
        // beter nog: throw exception!
        throw new IllegalArgumentException("You really messed up your numbers!");
    }*/

    // look for a feedback by name (see controller)
    public Gerecht findGerechtByName(String name) {
        for(Gerecht gerecht : gerechten){
            if(name.equals(gerecht.getName())){
                return gerecht;
            }
        }
        return null;
        // beter nog: throw exception!
    }

    public void addGerecht(Gerecht gerecht) {
        gerechten.add(gerecht);
    }

    public void deleteGerecht(Gerecht gerecht) {
        gerechten.remove(gerecht);
    }

    public void updateGerecht(String naam, Gerecht gerecht) {
        Gerecht oudGerecht = findGerechtByName(naam);
        oudGerecht.setName(gerecht.getName());
        oudGerecht.setPrice(gerecht.getPrice());
        oudGerecht.setType(gerecht.getType());
    }
}
