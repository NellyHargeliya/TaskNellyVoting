package com.hargeliya.repository;

import com.hargeliya.models.ThemeOption;
import com.hargeliya.utils.OptionRowMapping;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
@Component
public class ThemeOptionsRepositoryImpl implements ThemeOptionRepository {
    private static final String FIND_QUANTITY_BY_ID = "SELECT quantity FROM theme_option WHERE idOption=?";
    private static final String INSERT_OPTION = "INSERT INTO theme_option (nameOption, quantity, idTheme) VALUES( ?, ?, ?)";
    private static final String ADD_VOTE = "UPDATE theme_option SET quantity=quantity + 1 WHERE IDoption= ?";
    private static final String FIND_All_BY_ID_THEME = "SELECT * FROM theme_option WHERE idtheme = ?";


    private final JdbcOperations jdbc;
    private final SimpleJdbcInsertOperations insert;
    private final OptionRowMapping optionRowMapping;

    /**
     * This does the initialization for Jdbc operations and RowMapping
     */

    public ThemeOptionsRepositoryImpl(OptionRowMapping optionRowMapping, DataSource ds) {
        this.jdbc = new JdbcTemplate(ds);
        this.insert = new SimpleJdbcInsert(ds).withTableName("Theme_option");
        this.optionRowMapping = optionRowMapping;
    }

    @Override
    public Integer getThemeOptionQuantity(Integer id) {
        return jdbc.queryForObject(FIND_QUANTITY_BY_ID, Integer.class, id);

    }

    @Override
    public void addOptions(ThemeOption themeOption) {
        jdbc.update(INSERT_OPTION, new Object[]{themeOption.getNameOption(), themeOption.getQuantity(), themeOption.getTheme()});

    }

    @Override
    public void addVote(Integer id) {
        jdbc.update(ADD_VOTE, new Object[]{id});

    }

    @Override
    public List<ThemeOption> getAllOptionsByIdTheme(Integer idTheme) {
        return jdbc.query(FIND_All_BY_ID_THEME, new Object[]{idTheme}, optionRowMapping);

    }
}
