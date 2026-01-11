package todoapp.repository;

import todoapp.entity.Task;
import todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);

    @Query("""
        SELECT t FROM Task t
        WHERE t.user = :user
        AND (:category IS NULL OR LOWER(t.category) LIKE LOWER(CONCAT('%', :category, '%')))
        AND (:dueDate IS NULL OR t.dueDate = :dueDate)
        """)
    List<Task> searchTasks(
            @Param("user") User user,
            @Param("category") String category,
            @Param("dueDate") LocalDate dueDate
    );

    @Query("""
        SELECT t FROM Task t
        WHERE t.status = 'PENDING'
        AND t.dueDate <= :date
        """)
    List<Task> findPendingAndDueBefore(@Param("date") LocalDate date);
}