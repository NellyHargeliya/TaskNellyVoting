package com.hargeliya.repository;

import com.hargeliya.models.Theme;
import com.hargeliya.repository.*;
import com.hargeliya.utils.ThemeRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * This is the implementation of the repository methods This contains all the
 * logic that must be done when the methods are called for a particular request
 */

@Component
public class ThemeRepositoryImlp implements com.hargeliya.repository.ThemeRepository {
    private static final String FIND_ALL_THEME = "SELECT * FROM theme";
    private static final String INSERT_THEME = "INSERT INTO theme (nameTheme, startVoting, endVoting, urlTheme) VALUES( ?, ?, ?, ?)";
    private static final String FIND_BY_ID_THEME = "SELECT * FROM theme WHERE idTheme = ?";
    private static final String FIND_OPEN_THEME = "SELECT * FROM theme WHERE endVoting > ?";
    private static final String FIND_CLOSE_THEME = "SELECT * FROM theme WHERE endVoting < ?";

    private final JdbcOperations jdbc;
    private final SimpleJdbcInsertOperations insert;
    private final ThemeRowMapper themeRowMapper;
    private Date date = new Date();

    /**
     * This does the initialization for Jdbc operations and RowMapping
     */
    public ThemeRepositoryImlp(ThemeRowMapper themeRowMapper, DataSource ds) {
        this.jdbc = new JdbcTemplate(ds);
        this.insert = new SimpleJdbcInsert(ds).withTableName("Theme");
        this.themeRowMapper = themeRowMapper;
    }

    /**
     * This has the logic to get all the theme details in Theme table
     */
    @Override
    public List<Theme> getAllThemes() {
        return jdbc.query(FIND_ALL_THEME, themeRowMapper);
    }

    /**
     * This has the logic to get the theme with open for the voting
     */
    @Override
    public List<Theme> getOpenTheme() {

        return jdbc.query(FIND_OPEN_THEME, new Object[]{date}, themeRowMapper);
    }

    /**
     * This has the logic to get the theme with close for the voting
     */
    @Override
    public List<Theme> getCloseTheme() {
        return jdbc.query(FIND_CLOSE_THEME, new Object[]{date}, themeRowMapper);
    }

    /**
     * This has the logic to get the theme detail for a particular id
     */
    @Override
    public Theme getThemeById(Integer id) {
        return jdbc.queryForObject(FIND_BY_ID_THEME, new Object[]{id}, themeRowMapper);
    }

    /**
     * This has the logic to add a theme details to Theme table
     */
    @Override
    public void addTheme(Theme theme) {
        jdbc.update(INSERT_THEME, new Object[]{theme.getNameTheme(), theme.getStartDate(), theme.getEndDate(), theme.getUrlTheme()});

    }
}
