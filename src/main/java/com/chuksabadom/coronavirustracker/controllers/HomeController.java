package com.chuksabadom.coronavirustracker.controllers;

import com.chuksabadom.coronavirustracker.models.LocationStats;
import com.chuksabadom.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStatus = coronaVirusDataService.getAllStatus();
        int totalReportedCases = allStatus.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        int totalNewCases = allStatus.stream().mapToInt(LocationStats::getDiffFromPreviousDay).sum();
        model.addAttribute("locationStats", allStatus);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
