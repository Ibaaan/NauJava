package ru.ivan.NauJava.service;

import ru.ivan.NauJava.model.Report;

import java.util.concurrent.CompletableFuture;

public interface ReportService {
    String getReportData(long reportId);
    long createReport();
    CompletableFuture<Void> formReport(long reportId);
}
