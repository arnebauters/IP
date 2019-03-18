package be.ucll.gerechten.model;

import be.ucll.gerechten.repository.GerechtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GerechtService {
   @Autowired
    GerechtRepository gerechtRepository;

    // hardcode some values, definitively not the way to go !!!!
    public GerechtService() {
    }

    // just return the whole list, JSP page takes care of presentation
    public List<Gerecht> getAllGerechten() {
        return gerechtRepository.findAll();
    }

    // look for a feedback by name (see controller)
    public Gerecht findGerechtById(String name) {
        return gerechtRepository.findById(name).orElseThrow(IllegalArgumentException::new);
    }

    public void addGerecht(Gerecht gerecht) {
        gerechtRepository.save(gerecht);
    }

    public void deleteGerecht(Gerecht gerecht) {
        gerechtRepository.delete(gerecht);
    }

    public void updateGerecht(String naam, Gerecht gerecht) {
        gerecht.setName(naam);
        Gerecht gerecht1 = this.findGerechtById(naam);
        gerecht.setType(gerecht1.getType());
        gerecht.setPrice(gerecht1.getPrice());
        gerechtRepository.save(gerecht);
    }
}
