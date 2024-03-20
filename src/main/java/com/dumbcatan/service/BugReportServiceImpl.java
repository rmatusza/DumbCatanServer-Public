package com.dumbcatan.service;

import com.dumbcatan.dao.BugReportRepository;
import com.dumbcatan.entity.BugReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class BugReportServiceImpl implements BugReportService{

    @Autowired
    BugReportRepository BRR;

    @Override
    public BugReport findById(int id) {
        Optional<BugReport> br = BRR.findById(id);
        if(br.isPresent()){
            return br.get();
        }
        else{
            return null;
        }
    }

    @Override
    public ArrayList<BugReport> findAll() {
        return BRR.findAll();
    }

    @Override
    public BugReport save(BugReport report) {
        BugReport br = BRR.save(report);
        return br;
    }

    @Override
    public void deleteById(int id) {
        BRR.deleteById(id);
    }
}
