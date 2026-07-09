package com.sisencodigital.TaskSphere.controllers;

import com.sisencodigital.TaskSphere.dtos.responcedtos.DashbordSummeryResponse;
import com.sisencodigital.TaskSphere.services.DashbordService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@RestController
@RequestMapping("/dashbord")
@RequiredArgsConstructor
public class DashbordController {
    private final DashbordService dashbordService;

    @GetMapping("/summary")
    public ResponseEntity<DashbordSummeryResponse> getSummary(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate weekStartDate) {

        LocalDate weekStart = resolveWeekStart(weekStartDate);
        return ResponseEntity.ok(dashbordService.getSummary(weekStart));
    }


    private LocalDate resolveWeekStart(LocalDate weekStartDate) {
        if (weekStartDate != null) {
            return weekStartDate;
        }

        return LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }
}
