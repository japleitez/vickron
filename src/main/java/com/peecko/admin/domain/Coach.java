package com.peecko.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.peecko.admin.domain.enumeration.Type;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Coach.
 */
@Entity
@Table(name = "coach")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Coach implements Serializable {

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
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "resume")
    private String resume;

    @Column(name = "contact")
    private String contact;

    @Column(name = "lang")
    private String lang;

    @OneToMany(mappedBy = "coach")
    @JsonIgnoreProperties(value = { "category", "coach" }, allowSetters = true)
    private Set<Video> videos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Coach id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return this.country;
    }

    public Coach country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return this.name;
    }

    public Coach name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public Coach surname(String surname) {
        this.setSurname(surname);
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Type getType() {
        return this.type;
    }

    public Coach type(Type type) {
        this.setType(type);
        return this;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getEmail() {
        return this.email;
    }

    public Coach email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public Coach phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResume() {
        return this.resume;
    }

    public Coach resume(String resume) {
        this.setResume(resume);
        return this;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getContact() {
        return this.contact;
    }

    public Coach contact(String contact) {
        this.setContact(contact);
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLang() {
        return this.lang;
    }

    public Coach lang(String lang) {
        this.setLang(lang);
        return this;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Set<Video> getVideos() {
        return this.videos;
    }

    public void setVideos(Set<Video> videos) {
        if (this.videos != null) {
            this.videos.forEach(i -> i.setCoach(null));
        }
        if (videos != null) {
            videos.forEach(i -> i.setCoach(this));
        }
        this.videos = videos;
    }

    public Coach videos(Set<Video> videos) {
        this.setVideos(videos);
        return this;
    }

    public Coach addVideo(Video video) {
        this.videos.add(video);
        video.setCoach(this);
        return this;
    }

    public Coach removeVideo(Video video) {
        this.videos.remove(video);
        video.setCoach(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coach)) {
            return false;
        }
        return id != null && id.equals(((Coach) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Coach{" +
            "id=" + getId() +
            ", country='" + getCountry() + "'" +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", type='" + getType() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", resume='" + getResume() + "'" +
            ", contact='" + getContact() + "'" +
            ", lang='" + getLang() + "'" +
            "}";
    }
}
