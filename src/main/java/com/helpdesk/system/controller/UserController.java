package com.helpdesk.system.controller;

import com.helpdesk.system.entity.User;
import com.helpdesk.system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контроллер для управления пользователями системы.
 * <p>
 * Предоставляет API интерфейс для выполнения CRUD операций над сущностью {@link User}.
 * Поддерживает получение списка, создание и удаление пользователей.
 * </p>
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    /**
     * Конструктор контроллера с внедрением зависимости сервиса.
     *
     * @param userService сервис для работы с бизнес-логикой пользователей
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получает список всех зарегистрированных пользователей.
     * <p>
     * Выполняет GET запрос к базе данных и возвращает полный список.
     * </p>
     *
     * @return список объектов {@link User}
     */
    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    /**
     * Создает нового пользователя.
     * <p>
     * Ожидает JSON с полями username и email. Выполняет валидацию входящих данных.
     * </p>
     *
     * @param user объект пользователя, полученный из тела запроса
     * @return сохраненный объект пользователя с присвоенным ID
     */
    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    /**
     * Удаляет пользователя по его уникальному идентификатору.
     * <p>
     * При удалении пользователя также каскадно удаляются все его заявки.
     * </p>
     *
     * @param id уникальный идентификатор пользователя (Long)
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }
}