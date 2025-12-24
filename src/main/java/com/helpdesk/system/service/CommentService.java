package com.helpdesk.system.service;

import com.helpdesk.system.entity.Comment;
import com.helpdesk.system.entity.Ticket;
import com.helpdesk.system.repository.CommentRepository;
import com.helpdesk.system.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для управления комментариями.
 * <p>
 * Отвечает за сохранение комментариев и привязку их к конкретным заявкам.
 * </p>
 */
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;

    /**
     * Конструктор с внедрением необходимых репозиториев.
     *
     * @param commentRepository репозиторий комментариев
     * @param ticketRepository репозиторий заявок (нужен для поиска заявки при создании комментария)
     */
    public CommentService(CommentRepository commentRepository,
                          TicketRepository ticketRepository) {
        this.commentRepository = commentRepository;
        this.ticketRepository = ticketRepository;
    }

    /**
     * Получить список всех комментариев.
     *
     * @return список сущностей Comment
     */
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    /**
     * Сохранить новый комментарий и привязать его к заявке.
     * <p>
     * Метод сначала находит заявку по ticketId. Если заявка не найдена,
     * будет выброшено исключение (NoSuchElementException).
     * </p>
     *
     * @param comment  сущность комментария (текст)
     * @param ticketId ID заявки, к которой относится комментарий
     * @return сохраненный комментарий
     */
    public Comment save(Comment comment, Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        comment.setTicket(ticket);
        return commentRepository.save(comment);
    }

    /**
     * Удалить комментарий по ID.
     *
     * @param id идентификатор комментария
     */
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}