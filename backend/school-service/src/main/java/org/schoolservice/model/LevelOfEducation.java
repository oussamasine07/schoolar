package org.schoolservice.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "levels_of_education")
public class LevelOfEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String educationalStage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducationalStage() {
        return educationalStage;
    }

    public void setEducationalStage(String educationalStage) {
        this.educationalStage = educationalStage;
    }

}
