package com.hargeliya.utils;

import com.hargeliya.models.ThemeOption;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class OptionRowMapping implements RowMapper<ThemeOption> {
    @Override
    public ThemeOption mapRow(ResultSet rs, int i) throws SQLException {
        return new ThemeOption(rs.getLong("idOption"),
                rs.getString("nameOption"),
                rs.getInt("quantity"),
                rs.getInt("idTheme"));
    }
}
