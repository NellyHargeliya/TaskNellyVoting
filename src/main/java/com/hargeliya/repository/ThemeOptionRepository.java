package com.hargeliya.repository;

import com.hargeliya.models.ThemeOption;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ThemeOptionRepository {
    public Integer getThemeOptionQuantity(Integer id);

    public void addOptions(ThemeOption themeOption);

    public void addVote(Integer id);

    public List<ThemeOption> getAllOptionsByIdTheme(Integer idTheme);
}
