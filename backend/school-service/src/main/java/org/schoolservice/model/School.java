package org.schoolservice.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "school_name", nullable = false, unique = true)
    private String schoolName;
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;
    private String city;
    @Column(name = "tax_id")
    private int taxId;
    @Column(name = "professional_tax")
    private int professionalTax;
    @Column(name = "cnss_affiliation")
    private int cnssAffiliation;
    @Column(name = "commercial_register")
    private int commercialRegister;
    @Column(name = "common_business_identifier")
    private Long commonBusinessIdentifier;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "is_ready")
    private boolean isReady;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public int getProfessionalTax() {
        return professionalTax;
    }

    public void setProfessionalTax(int professionalTax) {
        this.professionalTax = professionalTax;
    }

    public int getCnssAffiliation() {
        return cnssAffiliation;
    }

    public void setCnssAffiliation(int cnssAffiliation) {
        this.cnssAffiliation = cnssAffiliation;
    }

    public int getCommercialRegister() {
        return commercialRegister;
    }

    public void setCommercialRegister(int commercialRegister) {
        this.commercialRegister = commercialRegister;
    }

    public Long getCommonBusinessIdentifier() {
        return commonBusinessIdentifier;
    }

    public void setCommonBusinessIdentifier(Long commonBusinessIdentifier) {
        this.commonBusinessIdentifier = commonBusinessIdentifier;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}





























