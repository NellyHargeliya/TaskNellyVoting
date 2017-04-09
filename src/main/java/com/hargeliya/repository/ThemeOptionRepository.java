package com.hargeliya.repository;

import com.hargeliya.models.ThemeOption;

import java.util.List;

public interface ThemeOptionRepository {
    public int getThemeOptionQuantity(int id);

    public void addOptions(ThemeOption themeOption);

    public void addVote(int id);

    public List<ThemeOption> getAllOptionsByIdTheme(int idTheme);
}
