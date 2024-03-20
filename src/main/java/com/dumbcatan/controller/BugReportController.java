package com.dumbcatan.controller;

import com.dumbcatan.entity.BugReport;
import com.dumbcatan.service.BugReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/dumbCatan")
public class BugReportController {

    @Autowired
    BugReportServiceImpl BRS;

    @PostMapping("/bug-report")
    BugReport createBugReport(@RequestBody BugReport br){
        try{
            BRS.save(br);
            br.setStatus(200);
            br.setMessage("Bug report created");
            return br;
        }
        catch(Exception e){
            br.setStatus(500);
            br.setMessage("Error occurred when attempting to create a new bug report");
            return br;
        }
    }

    @GetMapping("/bug-report/all")
    ArrayList<BugReport> getBugReports(){
        try{
            return BRS.findAll();
        }
        catch(Exception e){
            return new ArrayList<>();
        }
    }

    @DeleteMapping("/bug-report/{id}")
    BugReport deleteBugReport(@PathVariable int id){
        BugReport br = new BugReport();
        try{
            BRS.deleteById(id);
            br.setStatus(200);
            return br;
        }
        catch(Exception e){
            br.setStatus(500);
            return br;
        }
    }
}
