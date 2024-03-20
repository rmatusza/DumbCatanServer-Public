package com.dumbcatan.dao;

import com.dumbcatan.entity.BugReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface BugReportRepository extends JpaRepository<BugReport, Integer> {
    @Override
    BugReport save(BugReport report);

    @Override
    ArrayList<BugReport> findAll();
}
