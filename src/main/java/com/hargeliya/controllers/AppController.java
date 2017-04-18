package com.hargeliya.controllers;

import com.hargeliya.models.Theme;
import com.hargeliya.models.ThemeOption;
import com.hargeliya.repository.ThemeOptionRepository;
import com.hargeliya.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.logging.Logger;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * Controller class that handles all GET and POST requests
 */
@RestController

public class AppController {
    @Autowired
    private ThemeRepository themeRepo;

    @Autowired
    private ThemeOptionRepository themeOptionRepo;

    /**
     * This handles GET /theme
     * It returns all the theme details
     */
    @RequestMapping(value = "/themes", method = RequestMethod.GET)
    public Iterable<Theme> getAllThemes() {
        return themeRepo.findAll();
    }

    /**
     * This handles POST /theme
     * It adds a theme detail given in the request body to the Theme table
     */
    @RequestMapping(value = "/themes", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Theme addTheme(@RequestBody @Validated Theme theme) {
        Theme themeInDataBase = themeRepo.getByName(theme.getNameTheme());
        for (ThemeOption option : theme.getThemeOptions()) {
            option.setQuantity(0);
        }
        return themeRepo.save(theme);
    }

    /**
     * This handles GET /theme/get?id=<give the id of theme>
     * This returns the theme detail with the given id
     */
    @GetMapping("/themes/{id}")
    public Theme getThemeById(@RequestParam("id") long id) {
        return themeRepo.findOne(id);
    }

    @GetMapping("/themes/{id}/open")
    public void openTheme(@PathVariable("id") long id) {
        Theme theme = themeRepo.findOne(id);
        Date startDate = theme.getStartDate();
        Date endDate = theme.getEndDate();
        Link link = linkTo(methodOn(AppController.class).getThemeById(id)).withSelfRel();
        theme.setUrlTheme(link.getHref());
        theme.setStartDate(new Date());
        themeRepo.save(theme);
    }

    @GetMapping("/themes/{id}/close")
    public void getCloseTheme(@PathVariable("id") long id) {
        Theme theme = themeRepo.findOne(id);
        Date startDate = theme.getStartDate();
        Date endDate = theme.getEndDate();
        theme.setEndDate(new Date());
        themeRepo.save(theme);
    }

    /**
     * Opened themes in DataBase
     *
     * @return the set of all opened themes
     */
    @RequestMapping(value = "/opened", method = RequestMethod.GET)
    public Set<Theme> getAllOpenedThemes() {
        return themeRepo.getOpenedThemes();
    }
    /**
     * @param id - the ID of the Theme
     * @param idOoption - the ID of the Option
     * @return the Theme of its Options
     */
    @PutMapping("/themes/{id}/{idOption}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Theme voteRegistration(@PathVariable("id") long id, @PathVariable("idOption") long idOoption) {
        Theme theme = themeRepo.findOne(id);
        ThemeOption themeOption = themeOptionRepo.findOne(idOoption);

        themeOption.setQuantity(themeOption.getQuantity() + 1);
        themeOptionRepo.save(themeOption);
        return theme;
    }


    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public Map<String, String> showStatistics(@PathVariable("id") long id) {
        Theme theme = themeRepo.findOne(id);
        Map<String, String> result = new HashMap<>();
        result.put("Theme", theme.getNameTheme());
        for (ThemeOption themeOption : theme.getThemeOptions()) {
            result.put(themeOption.getNameOption(), themeOption.getQuantity().toString());
        }
        return result;
    }

    /**
     * Opens the JSP page with a new Theme add form
     * @return ModelAndView
     */
    @RequestMapping(value = "/addtheme", method = RequestMethod.GET)
    public ModelAndView openAddForm() {
        return new ModelAndView("/addTheme");
    }


}