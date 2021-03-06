package com.twistlet.falcon.model.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FalconAppointment generated by hbm2java
 */
@Entity
@Table(name="falcon_appointment"
)
public class FalconAppointment  implements java.io.Serializable {


     private Integer id;
     private FalconStaff falconStaff;
     private FalconLocation falconLocation;
     private FalconService falconService;
     private Date appointmentDate;
     private Date createDate;
     private Date updateDate;
     private String createBy;
     private String updateBy;
     private Date appointmentDateEnd;
     private Character notified;
     private Set<FalconAppointmentPatron> falconAppointmentPatrons = new HashSet<FalconAppointmentPatron>(0);

    public FalconAppointment() {
    }

    public FalconAppointment(FalconStaff falconStaff, FalconLocation falconLocation, FalconService falconService, Date appointmentDate, Date createDate, Date updateDate, String createBy, String updateBy, Date appointmentDateEnd, Character notified, Set<FalconAppointmentPatron> falconAppointmentPatrons) {
       this.falconStaff = falconStaff;
       this.falconLocation = falconLocation;
       this.falconService = falconService;
       this.appointmentDate = appointmentDate;
       this.createDate = createDate;
       this.updateDate = updateDate;
       this.createBy = createBy;
       this.updateBy = updateBy;
       this.appointmentDateEnd = appointmentDateEnd;
       this.notified = notified;
       this.falconAppointmentPatrons = falconAppointmentPatrons;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="staff")
    public FalconStaff getFalconStaff() {
        return this.falconStaff;
    }
    
    public void setFalconStaff(FalconStaff falconStaff) {
        this.falconStaff = falconStaff;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="location")
    public FalconLocation getFalconLocation() {
        return this.falconLocation;
    }
    
    public void setFalconLocation(FalconLocation falconLocation) {
        this.falconLocation = falconLocation;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="service")
    public FalconService getFalconService() {
        return this.falconService;
    }
    
    public void setFalconService(FalconService falconService) {
        this.falconService = falconService;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="appointment_date", length=19)
    public Date getAppointmentDate() {
        return this.appointmentDate;
    }
    
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", length=19)
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date", length=19)
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    
    @Column(name="create_by", length=100)
    public String getCreateBy() {
        return this.createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    
    @Column(name="update_by", length=100)
    public String getUpdateBy() {
        return this.updateBy;
    }
    
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="appointment_date_end", length=19)
    public Date getAppointmentDateEnd() {
        return this.appointmentDateEnd;
    }
    
    public void setAppointmentDateEnd(Date appointmentDateEnd) {
        this.appointmentDateEnd = appointmentDateEnd;
    }

    
    @Column(name="notified", length=1)
    public Character getNotified() {
        return this.notified;
    }
    
    public void setNotified(Character notified) {
        this.notified = notified;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="falconAppointment")
    public Set<FalconAppointmentPatron> getFalconAppointmentPatrons() {
        return this.falconAppointmentPatrons;
    }
    
    public void setFalconAppointmentPatrons(Set<FalconAppointmentPatron> falconAppointmentPatrons) {
        this.falconAppointmentPatrons = falconAppointmentPatrons;
    }




}


