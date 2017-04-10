package com.hargeliya.repository;

import com.hargeliya.models.Theme;

import java.util.Date;
import java.util.List;

public interface ThemeRepository {
    public List<Theme> getAllThemes();

    public List<Theme> getOpenTheme();

    public List<Theme> getCloseTheme();

    public Theme getThemeById(int id);

    public void addTheme(Theme theme);

}
