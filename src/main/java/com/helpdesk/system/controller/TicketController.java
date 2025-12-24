package com.helpdesk.system.controller;

import com.helpdesk.system.entity.Ticket;
import com.helpdesk.system.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST-контроллер для управления заявками (тикетами).
 * <p>
 * Обеспечивает взаимодействие с клиентской частью для создания, чтения,
 * обновления и удаления заявок в системе HelpDesk.
 * </p>
 */
@RestController
@RequestMapping("/api/tickets")
@CrossOrigin
public class TicketController {

    private final TicketService ticketService;

    /**
     * Конструктор для внедрения сервиса заявок.
     * @param ticketService сервис бизнес-логики заявок
     */
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Возвращает список всех заявок в системе.
     *
     * @return список объектов {@link Ticket}
     */
    @GetMapping
    public List<Ticket> getAll() {
        return ticketService.findAll();
    }

    /**
     * Создает новую заявку.
     * <p>
     * Принимает JSON с заголовком, описанием и ID пользователя-автора.
     * Статус по умолчанию устанавливается как "OPEN".
     * </p>
     *
     * @param data карта данных, содержащая title, description и userId
     * @return созданный объект Ticket
     */
    @PostMapping
    public Ticket create(@Valid @RequestBody Map<String, Object> data) {
        Ticket ticket = new Ticket();
        ticket.setTitle((String) data.get("title"));
        ticket.setDescription((String) data.get("description"));
        Long userId = Long.valueOf(data.get("userId").toString());

        return ticketService.save(ticket, userId);
    }

    /**
     * Обновляет данные существующей заявки.
     * <p>
     * Позволяет изменить заголовок, описание и статус заявки.
     * </p>
     *
     * @param id идентификатор обновляемой заявки
     * @param data новые данные для обновления (title, description, status)
     * @return обновленный объект Ticket
     */
    @PutMapping("/{id}")
    public Ticket update(@PathVariable Long id, @RequestBody Map<String, Object> data) {
        Ticket ticket = new Ticket();
        ticket.setTitle((String) data.get("title"));
        ticket.setDescription((String) data.get("description"));
        ticket.setStatus((String) data.get("status"));

        return ticketService.update(id, ticket);
    }

    /**
     * Удаляет заявку из системы.
     *
     * @param id уникальный идентификатор заявки
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ticketService.deleteById(id);
    }
}