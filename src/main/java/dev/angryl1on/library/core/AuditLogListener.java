package dev.angryl1on.library.core;

import dev.angryl1on.library.core.configs.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class AuditLogListener {
    @RabbitListener(queues = RabbitMQConfig.AUDIT_LOGS_QUEUE)
    public void handleAuditLog(String message) {
        System.out.println("Received audit log: " + message);
        logToFile("logs/audit_logs.txt", message);
    }

    private void logToFile(String relativePath, String message) {
        try {
            File logFile = new File(relativePath);
            logFile.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(logFile, true)) {
                writer.write(LocalDateTime.now() + " - " + message + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
