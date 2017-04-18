package com.hargeliya.repository;

import com.hargeliya.models.Theme;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface ThemeRepository extends CrudRepository<Theme,Long>{
    /**
     * Find the theme by its nameTheme
     * @param theme - nameTheme in DataBase
     * @return Theme
     */
    @Query("select T from Theme T where T.nameTheme = :theme")
    Theme getByName(@Param("theme") String theme);

    /**
     * Find all opened themes
     * @return the set of opened Themes
     */
    @Query("select T from Theme T where T.urlTheme is not null and T.endDate is null and T.startDate < current_timestamp")
    Set<Theme> getOpenedThemes();
}
