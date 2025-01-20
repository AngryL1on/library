package dev.angryl1on.library.core;

import dev.angryl1on.library.core.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListeners {
    private final NotificationService notificationService;

    @Autowired
    public RabbitMQListeners(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}

