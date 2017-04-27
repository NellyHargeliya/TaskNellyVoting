package com.hargeliya.repository;

import com.hargeliya.models.Theme;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ThemeRepository {
    public List<Theme> getAllThemes();

    public List<Theme> getOpenTheme();

    public List<Theme> getCloseTheme();

    public Theme getThemeById(Integer id);

    public void addTheme(Theme theme);


}
