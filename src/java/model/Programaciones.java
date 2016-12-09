/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ficha1020611
 */
@Entity
@Table(name = "Programaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programaciones.findAll", query = "SELECT p FROM Programaciones p"),
    @NamedQuery(name = "Programaciones.findById", query = "SELECT p FROM Programaciones p WHERE p.id = :id"),
    @NamedQuery(name = "Programaciones.findByHoraInicio", query = "SELECT p FROM Programaciones p WHERE p.horaInicio = :horaInicio"),
    @NamedQuery(name = "Programaciones.findByHoraFin", query = "SELECT p FROM Programaciones p WHERE p.horaFin = :horaFin")})
public class Programaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "horaInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @Column(name = "horaFin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programacionId")
    private Collection<Ambientes> ambientesCollection;

    public Programaciones() {
    }

    public Programaciones(Integer id) {
        this.id = id;
    }

    public Programaciones(Integer id, Date horaInicio, Date horaFin) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    @XmlTransient
    public Collection<Ambientes> getAmbientesCollection() {
        return ambientesCollection;
    }

    public void setAmbientesCollection(Collection<Ambientes> ambientesCollection) {
        this.ambientesCollection = ambientesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programaciones)) {
            return false;
        }
        Programaciones other = (Programaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Programaciones[ id=" + id + " ]";
    }
    
}
