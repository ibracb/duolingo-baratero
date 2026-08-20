# Testing strategy

## Approach

The project is tested through **unit tests isolated by layers** ([models](../duolingoBaratero/src/test/java/umu/pds/duolingoBaratero/models), [services](../duolingoBaratero/src/test/java/umu/pds/duolingoBaratero/services) and [controllers](../duolingoBaratero/src/test/java/umu/pds/duolingoBaratero/controllers)). Integration tests with the database and graphical interface (Swing) tests are out of scope.

In total there are **178 tests** distributed across **18 test files**.

## Framework and tools

- **JUnit 5 (Jupiter):** unit testing framework.
- **Mockito:** mocking of dependencies to isolate units (used in 15 of the 18 files).
- **Execution:** `mvn test`.

## Test structure

The tests are organised in [`src/test/java/umu/pds/duolingoBaratero/`](../duolingoBaratero/src/test/java/umu/pds/duolingoBaratero/), as a mirror of the main package [`src/main/java/umu/pds/duolingoBaratero/`](../duolingoBaratero/src/main/java/umu/pds/duolingoBaratero/):

```
src/test/java/umu/pds/duolingoBaratero/
├── controllers/    (5 files)
├── models/         (7 files)
└── services/       (6 files, includes filters/)
```

Each test class takes the name of the class it tests, adding the `Test` suffix (e.g. `ControladorUsuarioTest`, `CursoPlantillaTest`).

## Mocking strategy

To isolate each layer and test only its logic:

- **Controllers:** they are isolated by mocking the services they depend on.
- **Services:** they are isolated by mocking the DAOs.
- **Models:** the business logic is tested directly, without mocking dependencies. However, the question classes depend on Swing (`JPanel`) for their rendering (`crearPanel()`), so they are not completely pure.

Mocks are created with Mockito (`@Mock` or `mock()`), setting the behaviour with `when(...)` and verifying the calls with `verify(...)`.

## Testing levels

| Level | What it covers | Status |
|-------|---------------|--------|
| Unit ([models](../duolingoBaratero/src/test/java/umu/pds/duolingoBaratero/models)) | JPA entities, enums, business logic | 64 tests |
| Unit ([services](../duolingoBaratero/src/test/java/umu/pds/duolingoBaratero/services)) | Services, filters, serialisation | 39 tests |
| Unit ([controllers](../duolingoBaratero/src/test/java/umu/pds/duolingoBaratero/controllers)) | Facades, View→Service mediation | 75 tests |
| Integration | DAOs + database | Out of scope |
| UI/Swing | Windows and components | Out of scope |