package be.ucll.gerechten.repository;

import be.ucll.gerechten.model.WeekMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<WeekMenu, Integer> {
}
