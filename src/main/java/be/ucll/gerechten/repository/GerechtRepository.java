package be.ucll.gerechten.repository;

import be.ucll.gerechten.model.Gerecht;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GerechtRepository extends JpaRepository<Gerecht, Integer> {
    Gerecht findByName(String name);
}
