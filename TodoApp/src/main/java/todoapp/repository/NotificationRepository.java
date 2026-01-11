package todoapp.repository;

import todoapp.entity.Notification;
import todoapp.entity.Task;
import todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user);
    List<Notification> findByTask(Task task);
    void deleteByUser(User user);
    long countByUser(User user);
}
