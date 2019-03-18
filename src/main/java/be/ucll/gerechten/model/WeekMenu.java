package be.ucll.gerechten.model;

import be.ucll.gerechten.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class WeekMenu {
    @Autowired
    MenuRepository menuRepository;

    public WeekMenu() {
    }

    public void addMenu(DagMenu menu) {
        menuRepository.save(menu);
    }

    public void deleteMenu(DagMenu menu) {
        menuRepository.delete(menu);
    }

    public List<DagMenu> getAllMenus() {
        return menuRepository.findAll();
    }

    public DagMenu findMenuByDate(String date) {
        return menuRepository.findById(date).orElseThrow(IllegalArgumentException::new);
    }

    public void updateDagmenu(String date, DagMenu dagmenu) {
        DagMenu menu = findMenuByDate(date);
        menu.setSoep(dagmenu.getSoep());
        menu.setVeggie(dagmenu.getVeggie());
        menu.setDagschotel(dagmenu.getDagschotel());
    }
}
