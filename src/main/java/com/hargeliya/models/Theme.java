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
}
