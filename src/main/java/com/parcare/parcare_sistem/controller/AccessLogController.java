package com.parcare.parcare_sistem.controller;

import com.parcare.parcare_sistem.model.AccessLog;
import com.parcare.parcare_sistem.repository.AccessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/access")
public class AccessLogController {

    @Autowired
    private AccessLogRepository accessLogRepository;

    @PostMapping
    public ResponseEntity<?> logAccess(@RequestBody Map<String, String> body) {
        String plate = body.get("licensePlate");

        AccessLog log = new AccessLog();
        log.setLicensePlate(plate);
        log.setAccessTime(LocalDateTime.now());

        accessLogRepository.save(log);

        return ResponseEntity.ok("Access log saved.");
    }

    @GetMapping
    public List<AccessLog> getAllLogs() {
        return accessLogRepository.findAllByOrderByAccessTimeDesc();
    }
}
