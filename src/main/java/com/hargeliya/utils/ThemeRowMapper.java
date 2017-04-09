package com.hargeliya.utils;

import com.hargeliya.models.Theme;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ThemeRowMapper implements RowMapper<Theme> {

    @Override
    public Theme mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Theme(rs.getInt("idTheme"),
                rs.getString("nameTheme"),
                rs.getDate("startVoting"),
                rs.getDate("endVoting"),
                rs.getString("urlTheme"));
    }
}
