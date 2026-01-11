package todoapp.service;

import todoapp.entity.Role;
import todoapp.entity.Task;
import todoapp.entity.User;
import todoapp.entity.Status;
import todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task, User user) {
        task.setUser(user);
        task.setStatus(Status.PENDING);
        return taskRepository.save(task);
    }

    public List<Task> getAllByUser(User user) {
        return taskRepository.findByUser(user);
    }

    public Task toggleComplete(Long id, boolean completed, User currentUser) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(currentUser.getId()) &&
                currentUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("You don't have permission");
        }

        task.setStatus(completed ? Status.COMPLETED : Status.PENDING);
        return taskRepository.save(task);
    }
}
