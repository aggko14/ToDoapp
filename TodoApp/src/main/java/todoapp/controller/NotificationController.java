package todoapp.controller;

import todoapp.entity.Notification;
import todoapp.entity.User;
import todoapp.service.NotificationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public String showNotifications(
            @AuthenticationPrincipal User user,
            Model model) {
        List<Notification> notifications = notificationService.getUserNotifications(user);
        model.addAttribute("notifications", notifications);
        model.addAttribute("notificationCount", notifications.size());
        notificationService.markAllAsSeen(user);
        return "notifications";
    }
}