package com.dumbcatan.service;

import com.dumbcatan.entity.BugReport;
import com.dumbcatan.entity.Game;

import java.util.ArrayList;
import java.util.List;

public interface BugReportService {
    BugReport findById(int id);
    BugReport save(BugReport report);
    ArrayList<BugReport> findAll();
    void deleteById(int id);
}
