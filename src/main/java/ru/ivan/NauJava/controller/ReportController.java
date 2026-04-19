package ru.ivan.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ivan.NauJava.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping
    public long createReport() {
        long reportId = reportService.createReport();
        reportService.formReport(reportId);
        return reportId;
    }

    @GetMapping("/{reportId}")
    public String getReportData(@PathVariable long reportId) {
        return reportService.getReportData(reportId);
    }

}
