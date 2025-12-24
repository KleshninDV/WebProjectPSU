package com.helpdesk.system.service;

import com.helpdesk.system.entity.User;
import com.helpdesk.system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис, реализующий бизнес-логику управления пользователями.
 * <p>
 * Служит прослойкой между контроллером и базой данных.
 * </p>
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Конструктор сервиса.
     * @param userRepository репозиторий для доступа к таблице users
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Получить список всех пользователей из базы данных.
     *
     * @return список сущностей User
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Сохранить пользователя в базу данных.
     * <p>
     * Используется как для создания нового, так и для обновления существующего пользователя.
     * </p>
     *
     * @param user объект пользователя
     * @return сохраненный объект (с присвоенным ID)
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Удалить пользователя по его ID.
     *
     * @param id идентификатор пользователя
     */
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}