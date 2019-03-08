package be.ucll.gerechten.model;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class WeekMenu {

    private List<DagMenu> menus = new ArrayList<DagMenu>();

    public WeekMenu(){
        DagMenu menu1 = new DagMenu("MONDAY", "04-03-2019");
        DagMenu menu2 = new DagMenu("TUESDAY", "05-03-2019");
        menu1.voegDagschotelToe(new Gerecht(5, "Spaghetti", "dagschotel"));
        menu1.voegSoepToe(new Gerecht(2.5,"Tomatensoep met balletjes","soep" ));
        menu1.voegVeggieToe(new Gerecht(5.5, "Vol-aux-vents","veggie"));
        menu2.voegDagschotelToe(new Gerecht(5, "Spaghetti", "dagschotel"));
        menu2.voegSoepToe(new Gerecht(2.5,"Tomatensoep met balletjes","soep" ));
        menu2.voegVeggieToe(new Gerecht(5.5, "Vol-aux-vents","veggie"));
        menus.add(menu1);
        menus.add(menu2);
    }

    public void voegMenuToe(DagMenu menu){
        menus.add(menu);
    }

    public void verwijderMenu(DagMenu menu){
        menus.remove(menu);
    }

    public List<DagMenu> getAllMenus(){
        return menus;
    }

    public void updateDagmenu(String date, DagMenu dagmenu) {
        for (DagMenu menu: menus){
            if (menu.getDate().equals(date)){
                menu.setSoep(dagmenu.getSoep());
                menu.setVeggie(dagmenu.getVeggie());
                menu.setDagschotel(dagmenu.getDagschotel());
            }
        }
    }
}
