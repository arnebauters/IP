package be.ucll.gerechten.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class WeekMenu {

    @Id
    private int weekMenuId = 0;

    public int getWeekMenuId() {
        return weekMenuId;
    }

    public void setWeekMenuId(int weekMenuId) {
        this.weekMenuId = weekMenuId;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @MapKeyColumn(name = "day_number")
    private Map<Integer, DagMenu> dagMenus = new HashMap<>();

    public Map<Integer, DagMenu> getDagMenus() {
        return this.dagMenus;
    }

    public void setDagMenus(Map<Integer, DagMenu> week) {
        this.dagMenus = week;
    }


    public void addDagmenu(DagMenu dagMenu) {
        this.dagMenus.put(dagMenu.getDayOfWeek(), dagMenu);
    }

    public void deleteDagmenu(DagMenu dagMenu) {
        this.dagMenus.remove(dagMenu.getDayOfWeek());
    }

    public boolean dagMenusIsEmpty() {
        return dagMenus.isEmpty();
    }
}
