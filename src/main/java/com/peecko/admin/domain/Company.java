package com.peecko.admin.domain;

import com.peecko.admin.domain.enumeration.CompanyState;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "country", nullable = false)
    private String country;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private CompanyState state;

    @Column(name = "license")
    private String license;

    @Column(name = "start")
    private Instant start;

    @Column(name = "jhi_end")
    private Instant end;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "billing_phone")
    private String billingPhone;

    @Column(name = "billing_email")
    private String billingEmail;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "vatin")
    private String vatin;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Company id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return this.country;
    }

    public Company country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return this.name;
    }

    public Company name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyState getState() {
        return this.state;
    }

    public Company state(CompanyState state) {
        this.setState(state);
        return this;
    }

    public void setState(CompanyState state) {
        this.state = state;
    }

    public String getLicense() {
        return this.license;
    }

    public Company license(String license) {
        this.setLicense(license);
        return this;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Instant getStart() {
        return this.start;
    }

    public Company start(Instant start) {
        this.setStart(start);
        return this;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEnd() {
        return this.end;
    }

    public Company end(Instant end) {
        this.setEnd(end);
        return this;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    public String getEmail() {
        return this.email;
    }

    public Company email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public Company phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public Company address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBillingPhone() {
        return this.billingPhone;
    }

    public Company billingPhone(String billingPhone) {
        this.setBillingPhone(billingPhone);
        return this;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public String getBillingEmail() {
        return this.billingEmail;
    }

    public Company billingEmail(String billingEmail) {
        this.setBillingEmail(billingEmail);
        return this;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public String getBillingAddress() {
        return this.billingAddress;
    }

    public Company billingAddress(String billingAddress) {
        this.setBillingAddress(billingAddress);
        return this;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getVatin() {
        return this.vatin;
    }

    public Company vatin(String vatin) {
        this.setVatin(vatin);
        return this;
    }

    public void setVatin(String vatin) {
        this.vatin = vatin;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }
        return id != null && id.equals(((Company) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Company{" +
            "id=" + getId() +
            ", country='" + getCountry() + "'" +
            ", name='" + getName() + "'" +
            ", state='" + getState() + "'" +
            ", license='" + getLicense() + "'" +
            ", start='" + getStart() + "'" +
            ", end='" + getEnd() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", billingPhone='" + getBillingPhone() + "'" +
            ", billingEmail='" + getBillingEmail() + "'" +
            ", billingAddress='" + getBillingAddress() + "'" +
            ", vatin='" + getVatin() + "'" +
            "}";
    }
}
