package be.ucll.gerechten.repository;

import be.ucll.gerechten.model.DagMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DagMenuRepository extends JpaRepository<DagMenu, LocalDate> {

}
