package be.ucll.gerechten.model;
import be.ucll.gerechten.repository.DagMenuRepository;
import be.ucll.gerechten.repository.GerechtRepository;
import be.ucll.gerechten.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MyService {
    @Autowired
    GerechtRepository gerechtRepository;

    @Autowired
    DagMenuRepository dagMenuRepository;

    @Autowired
    MenuRepository weekMenuRepository;

    public MyService() {
    }

//WeekMenu

    public List<WeekMenu> getWeekMenus() {
        return weekMenuRepository.findAll();
    }

    public Optional<WeekMenu> getWeekMenuById(int id) {
        return weekMenuRepository.findById(id);
    }

    //Dagmenu

    public List<DagMenu> getDagmenus() {
        return dagMenuRepository.findAll();
    }

    public void addDagMenu(DagMenu dagMenu) {
        commitToDatabase(dagMenu);

        // check if we need a new week or not
        int weekMenuId = dagMenu.getYearAndWeekNumber();
        // if we don't get a week from the database, return a brand new week
        WeekMenu weekMenu = weekMenuRepository.findById(weekMenuId).orElse(new WeekMenu());

        if(weekMenu.getWeekMenuId() == 0){ // new week
            weekMenu.setWeekMenuId(weekMenuId);

            Map<Integer, DagMenu> weekMenuMap = new HashMap<Integer, DagMenu>();
            weekMenuMap.put(dagMenu.getDayOfWeek(), dagMenu);
            weekMenu.setDagMenus(weekMenuMap);
        }
        else {
            weekMenu.addDagmenu(dagMenu); // don't forget to add the day to the week if we have a week already!
        }

        weekMenuRepository.save(weekMenu); // always save the week, otherwise nothing shows in the database!
    }

    public void changeDagMenu(LocalDate date, DagMenu changedDagMenu) {
        // need to use the date given in the url so the user can't fiddle with that
        changedDagMenu.setDate(date);

        // need to use the day from the previous version so the user can't fiddle with that
        DagMenu previousVersionOfDagMenu = dagMenuRepository.findById(date).orElseThrow(IllegalArgumentException::new);
        changedDagMenu.setDayName(previousVersionOfDagMenu.getDayName());
        commitToDatabase(changedDagMenu);
    }

    private void commitToDatabase(DagMenu dagMenu) {
        // need to do this first because it checks if the course already exists or not
        // and so you don't get doubles in your database
        addGerecht(dagMenu.getSoep());
        addGerecht(dagMenu.getDagschotel());
        addGerecht(dagMenu.getVeggie());

        dagMenuRepository.save(dagMenu);
    }

    public void deleteDagMenu(LocalDate date) {
        // get the day from the database
        DagMenu dagMenu = dagMenuRepository.findById(date).orElseThrow(IllegalArgumentException::new);
        // get the week
        int weekMenuId = dagMenu.getYearAndWeekNumber();
        WeekMenu weekMenu = weekMenuRepository.findById(weekMenuId).orElseThrow(IllegalArgumentException::new);
        weekMenu.deleteDagmenu(dagMenu);
        // check if the week still contains days, if not, delete it!
        if(weekMenu.dagMenusIsEmpty()){
            weekMenuRepository.delete(weekMenu);
        }
        else {
            weekMenuRepository.save(weekMenu); // need to save week to see the changes in the db
        }
        dagMenuRepository.deleteById(date);
    }

    //Gerechten

    public List<Gerecht> getAllGerechten() {
        return gerechtRepository.findAll();
    }

    public Gerecht findGerechtByName(String name) {
        return gerechtRepository.findByName(name);
    }

    public Gerecht addGerecht(Gerecht gerecht) {
        // see if we can fin the course, if we can, we need to set the id
        // otherwise, a new course will be added to the database
        // this is for updating via the REST controller!
        Gerecht gerechtToAdd = gerechtRepository.findByName(gerecht.getName());
        if(gerechtToAdd != null){
            gerecht.setId(gerechtToAdd.getId());
        }
        return gerechtRepository.save(gerecht);
    }

    public void deleteGerecht(Gerecht gerecht) {
        gerechtRepository.delete(gerecht);
    }

    public void updateGerecht(Gerecht gerecht) {
        gerechtRepository.save(gerecht);
    }
}