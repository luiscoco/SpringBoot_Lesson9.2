# SpringBoot_Lesson9

## Propmt for the Code Agent (Codex, Gemini Code Assistant or Copilot)

**Context**:

I am learning to write unit tests for a Spring Boot application using Spring Boot 3.3 and Java 17. 

I want to test a service component in complete isolation from the data layer, without loading any Spring context.

**Task**:

Generate a **JUnit 5 unit test** for a TaskService class.

Known code in the app:

Package names:

Service: com.example.demo.service.TaskService

Entity: com.example.demo.Task

Repository: com.example.demo.TaskRepository (extends JpaRepository<Task, Long> and has List<Task> findByCompleted(boolean completed))

DTO: com.example.demo.dto.TaskDto (record: TaskDto(Long id, String description, boolean completed))

Exception: com.example.demo.exception.ResourceNotFoundException

**TaskService methods**:

List<TaskDto> findAllTasks()

TaskDto getTaskById(long id) — throws ResourceNotFoundException("Task not found with id: " + id) when not found

List<TaskDto> findTasksByStatus(boolean completed)

TaskDto createTask(Task request) — saves a new Task constructed from the incoming request and returns a mapped DTO

**Constraints**:

Mock TaskRepository with Mockito.

Do not load any Spring context (no @SpringBootTest, @ExtendWith(SpringExtension.class), etc.).

Use JUnit 5 and AssertJ for assertions.

Use Mockito JUnit 5 extension (@ExtendWith(MockitoExtension.class)), @Mock, and @InjectMocks.

**Steps**:

Assume the classes and packages above exist exactly as described.

Create src/test/java/com/example/demo/service/TaskServiceTest.java with package com.example.demo.service.

Configure a mocked TaskRepository and an @InjectMocks TaskService.

**Add tests**:

Happy path: getTaskById returns a TaskDto with the expected id/description/completed.

Not found: getTaskById throws ResourceNotFoundException with a message containing the id.

findAllTasks maps entities to DTOs correctly.

findTasksByStatus(false) delegates to findByCompleted(false) and maps results.

createTask saves a new entity and returns a DTO with the generated id and correct fields.

Use Mockito when(...).thenReturn(...) (or thenAnswer(...) where needed) to stub repository calls.

Use AssertJ for all assertions.

**Acceptance Criteria**:

The test class uses @ExtendWith(MockitoExtension.class), @Mock, and @InjectMocks.

No Spring testing annotations are present.

The repository dependency is a mock and all repository methods used by the tests are stubbed to return predictable results.

Assertions use AssertJ and verify DTO fields match expectations.

The test compiles and passes.

**Deliverables**:

The full code for TaskServiceTest.java.

The command to run only this test.

**Run Only This Test**:

Maven: mvn test -Dtest=com.example.demo.service.TaskServiceTest

Gradle (Windows): gradlew.bat test --tests "com.example.demo.service.TaskServiceTest"

Gradle (Unix/macOS): ./gradlew test --tests "com.example.demo.service.TaskServiceTest"
