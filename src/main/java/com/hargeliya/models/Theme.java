package com.hargeliya.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Theme {
    private Integer idTheme;
    private String nameTheme;
    private Date startVoting;
    private Date endVoting;
    private String urlTheme;

    public Theme() {
    }

    public Theme(Integer idTheme, String nameTheme, Date startVoting, Date endVoting, String urlTheme) {
    }

    public Integer getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public String getNameTheme() {
        return nameTheme;
    }

    public void setNameTheme(String nameTheme) {
        this.nameTheme = nameTheme;
    }

    public Date getStartVoting() {
        return startVoting;
    }

    public void setStartVoting(Date startVoting) {
        this.startVoting = startVoting;
    }

    public Date getEndVoting() {
        return endVoting;
    }

    public void setEndVoting(Date endVoting) {
        this.endVoting = endVoting;
    }

    public String getUrlTheme() {
        return urlTheme;
    }

    public void setUrlTheme(String urlTheme) {
        this.urlTheme = urlTheme;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Theme)) return false;
        Theme theme = (Theme) obj;
        if (getIdTheme() != null ? !getIdTheme().equals(theme.getIdTheme()) : theme.getIdTheme() != null) return false;
        if (getNameTheme() != null ? !getNameTheme().equals(theme.getNameTheme()) : theme.getNameTheme() != null)
            return false;
        if (getStartVoting() != null ? !getStartVoting().equals(theme.getStartVoting()) : theme.getStartVoting() != null)
            return false;
        if (getEndVoting() != null ? !getEndVoting().equals(theme.getEndVoting()) : theme.getEndVoting() != null)
            return false;
        return getUrlTheme() != null ? !getUrlTheme().equals(theme.getUrlTheme()) : theme.getUrlTheme() == null;
    }

}
