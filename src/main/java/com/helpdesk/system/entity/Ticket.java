package com.helpdesk.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * Сущность "Заявка" (Ticket).
 * <p>
 * Соответствует таблице <b>tickets</b>.
 * Представляет собой проблему или задачу, созданную пользователем.
 * </p>
 */
@Entity
@Table(name = "tickets")
public class Ticket {

    /**
     * Уникальный идентификатор заявки.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Заголовок заявки (Краткое описание).
     */
    @NotBlank
    private String title;

    /**
     * Полное описание проблемы.
     */
    private String description;

    /**
     * Статус заявки (например, OPEN, CLOSED).
     * Значение по умолчанию: "OPEN".
     */
    private String status = "OPEN";

    /**
     * Дата и время создания заявки.
     * Заполняется автоматически в момент создания объекта.
     */
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Пользователь, создавший заявку.
     * Связь: Много заявок -> Один пользователь.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // --- Геттеры и Сеттеры ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}