package com.dumbcatan.entity;

import com.dumbcatan.entity.http.ResponseEntityHelper;

import javax.persistence.*;

@Entity
@Table(name="bug_reports")
public class BugReport extends ResponseEntityHelper {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="bug_id")
    private int bugId;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="steps")
    private String steps;
    @Column(name="timestamp")
    private String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public int getBugId() {
        return bugId;
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public BugReport(int bugId, String name, String description, String steps, String timestamp) {
        this.bugId = bugId;
        this.name = name;
        this.description = description;
        this.steps = steps;
        this.timestamp = timestamp;
    }

    public BugReport(){}
}
