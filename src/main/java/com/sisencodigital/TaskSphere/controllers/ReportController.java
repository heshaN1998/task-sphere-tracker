package com.sisencodigital.TaskSphere.controllers;

import com.sisencodigital.TaskSphere.dtos.requestdtos.WeeklyReportRequestDTO;
import com.sisencodigital.TaskSphere.dtos.responcedtos.WeeklyResponseDTO;
import com.sisencodigital.TaskSphere.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<WeeklyResponseDTO> createReport(@Valid @RequestBody WeeklyReportRequestDTO requestDTO) {
        return new ResponseEntity<>(reportService.createReport(requestDTO), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeeklyResponseDTO> updateReport(@PathVariable Long id, @Valid @RequestBody WeeklyReportRequestDTO requestDTO) {
        return ResponseEntity.ok(reportService.updateReport(id, requestDTO)
        );
    }

    @PutMapping("/{id}/submit")
    public ResponseEntity<WeeklyResponseDTO> submitReport(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.submitReport(id)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeeklyResponseDTO> getReportById(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getReportById(id)
        );
    }

    @GetMapping("/my")
    public ResponseEntity<List<WeeklyResponseDTO>> getMyReports() {
        return ResponseEntity.ok(reportService.getMyReports()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
