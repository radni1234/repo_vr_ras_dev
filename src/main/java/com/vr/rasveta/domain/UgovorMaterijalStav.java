package com.vr.rasveta.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UgovorMaterijalStav.
 */
@Entity
@Table(name = "ugovor_materijal_stav")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UgovorMaterijalStav implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "opis")
    private String opis;

    @Column(name = "cena")
    private Double cena;

    @ManyToOne
    private UgovorMaterijal ugovorMaterijal;

    @ManyToOne
    private MaterijalTip materijalTip;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public UgovorMaterijalStav opis(String opis) {
        this.opis = opis;
        return this;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getCena() {
        return cena;
    }

    public UgovorMaterijalStav cena(Double cena) {
        this.cena = cena;
        return this;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public UgovorMaterijal getUgovorMaterijal() {
        return ugovorMaterijal;
    }

    public UgovorMaterijalStav ugovorMaterijal(UgovorMaterijal ugovorMaterijal) {
        this.ugovorMaterijal = ugovorMaterijal;
        return this;
    }

    public void setUgovorMaterijal(UgovorMaterijal ugovorMaterijal) {
        this.ugovorMaterijal = ugovorMaterijal;
    }

    public MaterijalTip getMaterijalTip() {
        return materijalTip;
    }

    public UgovorMaterijalStav materijalTip(MaterijalTip materijalTip) {
        this.materijalTip = materijalTip;
        return this;
    }

    public void setMaterijalTip(MaterijalTip materijalTip) {
        this.materijalTip = materijalTip;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UgovorMaterijalStav ugovorMaterijalStav = (UgovorMaterijalStav) o;
        if (ugovorMaterijalStav.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ugovorMaterijalStav.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UgovorMaterijalStav{" +
            "id=" + getId() +
            ", opis='" + getOpis() + "'" +
            ", cena=" + getCena() +
            "}";
    }
}
