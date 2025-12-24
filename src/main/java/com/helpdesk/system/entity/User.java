package com.helpdesk.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Сущность "Пользователь" (User).
 * <p>
 * Соответствует таблице <b>users</b> в базе данных.
 * Хранит информацию о зарегистрированных клиентах.
 * </p>
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Уникальный идентификатор пользователя (Primary Key).
     * Генерируется автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя пользователя. Не может быть пустым.
     */
    @NotBlank
    private String username;

    /**
     * Электронная почта пользователя.
     * Должна быть уникальной и соответствовать формату email.
     */
    @Email
    @NotBlank
    private String email;

    // --- Геттеры и Сеттеры ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}