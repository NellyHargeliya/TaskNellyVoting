package com.hargeliya.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThemeOption {
    private Integer idOption;
    private String nameOption;
    private Integer quantity;
    private Theme theme;

    public ThemeOption(Integer idOption, String nameOption, Integer quantity, Integer idTheme) {
    }

    public Integer getIdOption() {
        return idOption;
    }

    public void setIdOption(Integer idOption) {
        this.idOption = idOption;
    }

    public String getNameOption() {
        return nameOption;
    }

    public void setNameOption(String nameOption) {
        this.nameOption = nameOption;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setIdTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ThemeOption)) return false;

        ThemeOption that = (ThemeOption) obj;

        if (getIdOption() != null ? !getIdOption().equals(that.getIdOption()) : that.getIdOption() != null)
            return false;
        if (getNameOption() != null ? !getNameOption().equals(that.getNameOption()) : that.getNameOption() != null)
            return false;
        return (getQuantity() != null ? getQuantity().equals(that.getQuantity()) : that.getQuantity() == null);

    }

    @Override
    public String toString() {
        return "ThemeOption{" +
                "idOptionId=" + idOption +
                ", nameOption='" + nameOption + '\'' +
                ", quantity=" + quantity +
                ", idTheme=" + theme.getIdTheme() +
                '}';
    }
}
