package dev.angryl1on.library.core;

import dev.angryl1on.library.core.configs.RabbitMQConfig;
import dev.angryl1on.library.core.services.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListeners {
    private final NotificationService notificationService;

    @Autowired
    public RabbitMQListeners(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = RabbitMQConfig.NEW_BOOKS_QUEUE)
    public void handleNewBookNotification(String message) {
        notificationService.sendNotification("/topic/new-books", message);
    }

    @RabbitListener(queues = RabbitMQConfig.NEW_LIBRARIES_QUEUE)
    public void handleNewLibraryNotification(String message) {
        notificationService.sendNotification("/topic/new-libraries", message);
    }
}

