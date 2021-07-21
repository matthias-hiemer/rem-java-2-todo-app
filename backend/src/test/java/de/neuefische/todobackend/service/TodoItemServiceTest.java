package de.neuefische.todobackend.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.repository.TodoItemRepository;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class TodoItemServiceTest {

    @Mock
    TodoItemRepository todoItemRepository;

    @InjectMocks
    TodoItemService todoItemService;

    public void saveMock_whenValidData_success() {
        TodoItem todoItem = TodoItem.builder()
            .id(null)
            .version(null)
            .status("OPEN")
            .description("")
            .build();

        when(todoItemRepository.save(any())).thenReturn(
            todoItem
                .withId(1L)
                .withVersion(0L)
        );

        TodoItem persistedTodoItem = todoItemService.save(todoItem);

        Assertions.assertNotNull(persistedTodoItem);
        Assertions.assertEquals(1L, persistedTodoItem.getId());
        Assertions.assertEquals(0L, persistedTodoItem.getVersion());
    }

}
