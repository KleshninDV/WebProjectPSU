package com.helpdesk.system.controller;

import com.helpdesk.system.entity.Comment;
import com.helpdesk.system.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST-контроллер для управления комментариями к заявкам.
 * <p>
 * Позволяет пользователям добавлять обсуждения к конкретным тикетам.
 * </p>
 */
@RestController
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentController {

    private final CommentService commentService;

    /**
     * Конструктор контроллера комментариев.
     * @param commentService сервис бизнес-логики комментариев
     */
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Получает полный список всех комментариев.
     *
     * @return список объектов {@link Comment}
     */
    @GetMapping
    public List<Comment> getAll() {
        return commentService.findAll();
    }

    /**
     * Добавляет новый комментарий к указанной заявке.
     *
     * @param data карта данных, содержащая текст комментария (text) и ID заявки (ticketId)
     * @return сохраненный комментарий
     */
    @PostMapping
    public Comment create(@Valid @RequestBody Map<String, Object> data) {
        Comment comment = new Comment();
        comment.setText((String) data.get("text"));
        Long ticketId = Long.valueOf(data.get("ticketId").toString());

        return commentService.save(comment, ticketId);
    }

    /**
     * Удаляет комментарий по ID.
     *
     * @param id идентификатор комментария
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        commentService.deleteById(id);
    }
}