package com.hargeliya.controllers;

import com.hargeliya.models.Theme;
import com.hargeliya.models.ThemeOption;
import com.hargeliya.repository.ThemeOptionRepository;
import com.hargeliya.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller class that handles all GET and POST requests
 */
@RestController

public class AppController {
    @Autowired
    private ThemeRepository themeRepo;

    @Autowired
    private ThemeOptionRepository themeOptionRepo;


    @Autowired
    public AppController(ThemeRepository themeRepo, ThemeOptionRepository themeOptionRepo) {
        this.themeRepo = themeRepo;
        this.themeOptionRepo = themeOptionRepo;
    }

    /**
     * This handles GET /theme
     * It returns all the theme details
     */
    @RequestMapping(value="/themes", method = RequestMethod.GET)
    public List<Theme> getAllTheme() {
        return  themeRepo.getAllThemes();
    }

    /**
     * This handles POST /theme
     * It adds a theme detail given in the request body to the Theme table
     */
    @RequestMapping(value = "/themes", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Theme addTheme(@RequestBody @Validated Theme theme) {
        themeRepo.addTheme(theme);
        return theme;
    }

    /**
     * This handles GET /theme/get?id=<give the id of theme>
     * This returns the theme detail with the given id
     */
    @GetMapping("/get/{id}")
    public Theme getThemeById(@RequestParam("id") int id) {
        return themeRepo.getThemeById(id);
    }

    @GetMapping("/open/themes")
    public List<Theme> getOpenTheme() {
        return themeRepo.getOpenTheme();
    }

    @GetMapping("/closed/themes")
    public List<Theme> getCloseTheme() {
        return themeRepo.getCloseTheme();
    }

    @GetMapping("/getTheme/{id}")
    public int getThemeOptionQuantity(@RequestParam("id") int id) {
        return themeOptionRepo.getThemeOptionQuantity(id);
    }

    @PostMapping
    public ThemeOption addOptions(@RequestBody @Validated ThemeOption themeOption) {
        themeOptionRepo.addOptions(themeOption);
        return themeOption;
    }

    @GetMapping
    public void addVote(int id) {
        themeOptionRepo.addVote(id);
    }

    @GetMapping("/getOptions/{id}")
    public List<ThemeOption> getAllOptionsByIdTheme(@RequestParam("id")int idTheme) {
        return themeOptionRepo.getAllOptionsByIdTheme(idTheme);
    }

}