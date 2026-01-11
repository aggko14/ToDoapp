package todoapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import todoapp.entity.Notification;
import todoapp.entity.User;
import todoapp.service.NotificationService;
import todoapp.service.TaskService;

import java.util.List;

@Controller
public class DashboardController {

    private final TaskService taskService;
    private final NotificationService notificationService;

    public DashboardController(TaskService taskService, NotificationService notificationService) {
        this.taskService = taskService;
        this.notificationService = notificationService;
    }

    @GetMapping("/tasks")
    public String showDashboard(
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("tasks", taskService.getAllByUser(user));
        List<Notification> notifications = notificationService.getUserNotifications(user);
        model.addAttribute("notifications", notifications);
        model.addAttribute("notificationCount", notifications.size());
        notificationService.markAllAsSeen(user);
        return "tasks";
    }
}