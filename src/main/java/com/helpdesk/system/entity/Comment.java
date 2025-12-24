package com.helpdesk.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * Сущность "Комментарий" (Comment).
 * <p>
 * Соответствует таблице <b>comments</b>.
 * Позволяет вести переписку в рамках конкретной заявки.
 * </p>
 */
@Entity
@Table(name = "comments")
public class Comment {

    /**
     * Уникальный идентификатор комментария.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Текст комментария. Не может быть пустым.
     */
    @NotBlank
    private String text;

    /**
     * Дата написания комментария.
     */
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Заявка, к которой относится этот комментарий.
     * Связь: Много комментариев -> Одна заявка.
     */
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    // --- Геттеры и Сеттеры ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }
}