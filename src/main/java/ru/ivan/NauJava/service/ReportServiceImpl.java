package ru.ivan.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.ivan.NauJava.model.Report;
import ru.ivan.NauJava.model.ReportStatus;
import ru.ivan.NauJava.model.User;
import ru.ivan.NauJava.repository.ReportRepository;
import ru.ivan.NauJava.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final TemplateEngine templateEngine;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, UserRepository userRepository, TemplateEngine templateEngine) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.templateEngine = templateEngine;
    }

    @Override
    public String getReportData(long reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow();
        if (report.getStatus() != ReportStatus.FINISHED){
            return report.getStatus().toString();
        }
        return report.getReportData();
    }

    @Override
    public long createReport() {
        Report report = new Report();
        report.setStatus(ReportStatus.CREATED);
        reportRepository.save(report);
        return report.getId();
    }

    @Override
    public CompletableFuture<Void> formReport(long reportId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return makeReport();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }).thenAccept(reportData -> {
            Report report = reportRepository.findById(reportId).orElseThrow();

            report.setStatus(ReportStatus.FINISHED);
            report.setReportData(reportData);

            reportRepository.save(report);
        }).exceptionally(ex -> {
            Report report = reportRepository.findById(reportId).orElseThrow();

            report.setStatus(ReportStatus.ERROR);

            reportRepository.save(report);
            return null;
        });
    }

    private String makeReport() throws InterruptedException {
        AtomicInteger userCount = new AtomicInteger();
        final List<User>[] users = new List[]{Collections.synchronizedList(new ArrayList<>())};

        AtomicLong userCountTime = new AtomicLong();
        AtomicLong usersTime = new AtomicLong();

        Thread userCountThread = new Thread(() -> {
            long start = System.currentTimeMillis();
            userCount.set((int) userRepository.count());
            long end = System.currentTimeMillis();
            userCountTime.set(end - start);
        });

        Thread usersThread = new Thread(() -> {
            long start = System.currentTimeMillis();
            users[0] = (List<User>) userRepository.findAll();
            long end = System.currentTimeMillis();
            usersTime.set(end - start);
        });

        userCountThread.start();
        usersThread.start();

        userCountThread.join();
        usersThread.join();

        long totalTime = usersTime.get() + userCountTime.get();

        return generateHTMLPage(userCount.get(), users[0], userCountTime.get(), usersTime.get(), totalTime);
    }

    private String generateHTMLPage(int userCount, List<User> users, long userCountTime, long usersTime, long totalTime) {
        Context context = new Context();

        context.setVariable("userCount", userCount);
        context.setVariable("userTime", userCountTime);
        context.setVariable("entityTime", usersTime);
        context.setVariable("totalTime", totalTime);
        context.setVariable("entities", users);

        return templateEngine.process("report", context);
    }

}
