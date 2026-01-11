package todoapp.service;

import todoapp.entity.Notification;
import todoapp.entity.Task;
import todoapp.entity.User;
import todoapp.repository.NotificationRepository;
import todoapp.repository.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationService {

    private final TaskRepository taskRepository;
    private final NotificationRepository notificationRepository;

    public NotificationService(TaskRepository taskRepository,
                               NotificationRepository notificationRepository) {
        this.taskRepository = taskRepository;
        this.notificationRepository = notificationRepository;
    }

    @Scheduled(cron = "0 */5 * * * *")
    @Transactional
    public void generateReminders() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        List<Task> tasksToNotify = taskRepository.findPendingAndDueBefore(tomorrow);

        for (Task task : tasksToNotify) {
            String message;

            if (task.getDueDate().isBefore(today)) {
                message = "OVERDUE: \"" + task.getTitle() + "\" was due on " + task.getDueDate();
            } else if (task.getDueDate().equals(tomorrow)) {
                message = "Tomorrow: \"" + task.getTitle() + "\" is due";
            } else {
                continue;
            }

            boolean alreadyNotified = notificationRepository
                    .findByTask(task)
                    .stream()
                    .anyMatch(n -> n.getMessage().equals(message));

            if (!alreadyNotified) {
                Notification notification = new Notification();
                notification.setMessage(message);
                notification.setTask(task);
                notification.setUser(task.getUser());

                notificationRepository.save(notification);
            }
        }
    }

    @Transactional(readOnly = true)
    public List<Notification> getUserNotifications(User user) {
        return notificationRepository.findByUser(user);
    }

    @Transactional
    public void markAllAsSeen(User user) {
        notificationRepository.deleteByUser(user);
    }

    @Transactional(readOnly = true)
    public long countUserNotifications(User user) {
        return notificationRepository.countByUser(user);
    }

    @Transactional
    public void clearUserNotifications(User user) {
        notificationRepository.deleteByUser(user);
    }
}
