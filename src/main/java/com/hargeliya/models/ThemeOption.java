package com.hargeliya.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties
@Entity
public class ThemeOption implements Serializable {
    @Id
    @Column(name = "idOption", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOption;
    @Column(name = "nameOption", nullable = false, length = 30)
    @NotEmpty
    @Size(max = 30)
    private String nameOption;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "idTtheme")
    @JsonBackReference
    private Theme theme;

    public ThemeOption(Long idOption, String nameOption, int quantity, int idTheme) {
    }

    public ThemeOption() {
    }

    public Long getIdOption() {
        return idOption;
    }

    public void setIdOption(Long idOption) {
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

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.idOption);
        hash = 67 * hash + Objects.hashCode(this.nameOption);
        hash = 67 * hash + Objects.hashCode(this.quantity);
        hash = 67 * hash + Objects.hashCode(this.theme);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ThemeOption other = (ThemeOption) obj;
        if (!Objects.equals(this.nameOption, other.nameOption)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        return Objects.equals(this.theme, other.theme);
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
