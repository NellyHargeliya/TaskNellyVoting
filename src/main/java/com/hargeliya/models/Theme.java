package com.hargeliya.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
public class Theme implements Serializable {
    private static final long serialVersionUID = 20160328085401L;
    @Id
    @Column(name = "idTheme", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTheme;
    @Column(name = "nameTheme", nullable = false, length = 50, unique = true)
    @NotEmpty
    @Size(max = 50)
    private String nameTheme;
    @OneToMany(mappedBy = "theme", cascade = javax.persistence.CascadeType.ALL)
    @JsonManagedReference
    private List<ThemeOption> themeOptions;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "startDate")
    private Date startDate;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "endDate")
    private Date endDate;
    @Column(name = "urlTheme", length = 100)
    @Size(max = 100)
    private String urlTheme;

    public Theme(Long idTheme, String nameTheme, Date startVoting, Date endVoting, String urlTheme) {
    }

    public Theme() {
    }

    public Long getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Long idTheme) {
        this.idTheme = idTheme;
    }

    public String getNameTheme() {
        return nameTheme;
    }

    public void setNameTheme(String nameTheme) {
        this.nameTheme = nameTheme;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<ThemeOption> getThemeOptions() {
        return themeOptions;
    }

    public void setThemeOptions(List<ThemeOption> themeOptions) {
        this.themeOptions = themeOptions;
    }

    public String getUrlTheme() {
        return urlTheme;
    }

    public void setUrlTheme(String urlTheme) {
        this.urlTheme = urlTheme;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.idTheme);
        hash = 73 * hash + Objects.hashCode(this.nameTheme);
        hash = 73 * hash + Objects.hashCode(this.themeOptions);
        hash = 73 * hash + Objects.hashCode(this.startDate);
        hash = 73 * hash + Objects.hashCode(this.endDate);
        hash = 73 * hash + Objects.hashCode(this.urlTheme);
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
        final Theme other = (Theme) obj;
        if (!Objects.equals(this.nameTheme, other.nameTheme)) {
            return false;
        }
        if (!Objects.equals(this.themeOptions, other.themeOptions)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        return Objects.equals(this.urlTheme, other.urlTheme);
    }

    @Override
    public String toString() {
        String themeOptionToString = "{";
        for (ThemeOption option : themeOptions) {
            themeOptionToString += option.toString();
            themeOptionToString += "; ";
        }
        themeOptionToString += "}";
        return "Theme{" + "idTheme=" + idTheme +
                ", nameTheme=" + nameTheme +
                ", themeOptions=" + themeOptionToString +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", urlTheme=" + urlTheme + '}';
    }
}


