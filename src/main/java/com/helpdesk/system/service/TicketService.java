package com.helpdesk.system.service;

import com.helpdesk.system.entity.Ticket;
import com.helpdesk.system.entity.User;
import com.helpdesk.system.repository.TicketRepository;
import com.helpdesk.system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис, реализующий бизнес-логику работы с заявками.
 */
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository,
                         UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    /**
     * Найти все заявки в БД.
     * @return список заявок
     */
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    /**
     * Сохранить новую заявку и привязать её к пользователю.
     * @param ticket сущность заявки
     * @param userId ID пользователя-владельца
     * @return сохраненная заявка
     */
    public Ticket save(Ticket ticket, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        ticket.setUser(user);
        return ticketRepository.save(ticket);
    }

    /**
     * Обновить существующую заявку.
     * @param id ID заявки
     * @param ticketDetails новые данные
     * @return обновленная сущность
     */
    public Ticket update(Long id, Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();

        ticket.setTitle(ticketDetails.getTitle());
        ticket.setDescription(ticketDetails.getDescription());
        ticket.setStatus(ticketDetails.getStatus());

        return ticketRepository.save(ticket);
    }

    /**
     * Удалить заявку по ID.
     * @param id ID заявки
     */
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}