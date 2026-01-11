package todoapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import todoapp.entity.Priority;
import todoapp.entity.Task;
import todoapp.entity.User;
import todoapp.service.TaskService;

import java.time.LocalDate;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public String createTask(
            @RequestParam String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) LocalDate dueDate,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) String category,
            @AuthenticationPrincipal User user) {

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setPriority(priority != null ? priority : Priority.MEDIUM);
        task.setCategory(category);
        task.setUser(user);

        taskService.create(task, user);

        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}/complete")
    public String toggleComplete(
            @PathVariable Long id,
            @RequestParam("completed") String completedStr,
            @AuthenticationPrincipal User user) {

        System.out.println("toggleComplete called for task id: " + id);
        System.out.println("completedStr received: " + completedStr);

        boolean completed = "true".equalsIgnoreCase(completedStr.trim());
        System.out.println("Converted to boolean: " + completed);

        taskService.toggleComplete(id, completed, user);

        return "redirect:/tasks";
    }
}
