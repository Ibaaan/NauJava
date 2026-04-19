package ru.ivan.NauJava.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.thymeleaf.TemplateEngine;
import ru.ivan.NauJava.model.Report;
import ru.ivan.NauJava.model.ReportStatus;
import ru.ivan.NauJava.model.User;
import ru.ivan.NauJava.repository.ReportRepository;
import ru.ivan.NauJava.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ReportServiceImplTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TemplateEngine templateEngine;

    @InjectMocks
    private ReportServiceImpl reportService;

    @Test
    void createReport_shouldSaveReportWithCreatedStatus() {
        Report savedReport = new Report();
        savedReport.setId(1L);

        when(reportRepository.save(any(Report.class))).thenReturn(savedReport);

        long id = reportService.createReport();

        assertEquals(0L, id);

        ArgumentCaptor<Report> captor = ArgumentCaptor.forClass(Report.class);
        verify(reportRepository).save(captor.capture());

        assertEquals(ReportStatus.CREATED, captor.getValue().getStatus());
    }

    @Test
    void getReportData_shouldReturnStatus_whenNotFinished() {
        Report report = new Report();
        report.setStatus(ReportStatus.CREATED);

        when(reportRepository.findById(1L)).thenReturn(Optional.of(report));

        String result = reportService.getReportData(1L);

        assertEquals(ReportStatus.CREATED.toString(), result);
    }

    @Test
    void getReportData_shouldReturnData_whenFinished() {
        Report report = new Report();
        report.setStatus(ReportStatus.FINISHED);
        report.setReportData("HTML");

        when(reportRepository.findById(1L)).thenReturn(Optional.of(report));

        String result = reportService.getReportData(1L);

        assertEquals("HTML", result);
    }

    @Test
    void formReport_shouldGenerateAndSaveReport() throws Exception {
        Report report = new Report();
        report.setId(1L);

        when(reportRepository.findById(1L)).thenReturn(Optional.of(report));

        when(userRepository.count()).thenReturn(2L);
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));

        when(templateEngine.process(eq("report"), any())).thenReturn("<html>report</html>");

        CompletableFuture<Void> future = reportService.formReport(1L);
        future.join();

        verify(reportRepository, atLeastOnce()).save(report);

        assertEquals(ReportStatus.FINISHED, report.getStatus());
        assertEquals("<html>report</html>", report.getReportData());
    }
}