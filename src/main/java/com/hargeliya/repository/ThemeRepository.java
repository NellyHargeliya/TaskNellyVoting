package com.hargeliya.repository;

import com.hargeliya.models.Theme;

import java.util.Date;
import java.util.List;

public interface ThemeRepository {
    public List<Theme> getAllThemes();

    public List<Theme> getOpenTheme(Date date);

    public List<Theme> getCloseTheme(Date date);

    public Theme getThemeById(int id);

    public void addTheme(Theme theme);

}
