package de.neuefische.todobackend.service;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.repository.TodoItemRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoItemService {

    private final TodoItemRepository repo;

    @Autowired
    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.repo = todoItemRepository;
    }

    public List<TodoItem> findAll(String status) {

        if (status != null) {
            return repo.findByStatus(status);
        } else {
            return repo.findAll();
        }
    }

    public TodoItem save(TodoItem todoItem) {
        return repo.save(todoItem);
    }

    public TodoItem update(TodoItem item) {

        if (item == null) {
            throw new IllegalArgumentException("The given todo item is null!");
        }

        if (!repo.existsById(item.getId())) {
            throw new NoSuchElementException("No element exists with id:" + item.getId());
        }

        return repo.save(item);
    }

    public Optional<TodoItem> findById(Long id) {
        return repo.findById(id);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
