package org.schoolservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "levels_of_education")
public class LevelOfEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "educational_stage")
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
