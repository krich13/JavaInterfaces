package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.TicketPromo;
import ru.netology.manager.TicketManager;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ProductManagerTests {
    TicketManager ticketManager = new TicketManager(new TicketRepository());
    TicketPromo firstTicket = new TicketPromo("1", 10000, "LET", "SVO", 240);
    TicketPromo secondTicket = new TicketPromo("2", 12000, "AER", "KZN", 360);
    TicketPromo thirdTicket = new TicketPromo("3", 7000, "KZN", "LET", 360);
    TicketPromo forthTicket = new TicketPromo("4", 11500, "LET", "SVO", 240);
    TicketPromo fifthTicket = new TicketPromo("5", 6780, "AER", "SVO", 430);
    TicketPromo sixthTicket = new TicketPromo("6", 5000, "LET", "PKC", 600);

    @BeforeEach
    public void setUp() {
        ticketManager.add(firstTicket);
        ticketManager.add(secondTicket);
        ticketManager.add(thirdTicket);
        ticketManager.add(forthTicket);
        ticketManager.add(fifthTicket);
        ticketManager.add(sixthTicket);
    }

    @Test
    void shouldSaveTicketsInCorrectOrderFromCheapest() {
        TicketPromo[] expected = new TicketPromo[]{sixthTicket, fifthTicket, thirdTicket, firstTicket, forthTicket, secondTicket};
        TicketPromo[] actual = ticketManager.shouldReturnTicketsList();
        Arrays.sort(actual);
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldFindTicketByDepartureAirportAndArrivalAirport() { //поиск билетов, если оба аэропорта соответствуют фильтрам
        TicketPromo[] expected = new TicketPromo[]{firstTicket, forthTicket};
        TicketPromo[] actual = ticketManager.searchByBothAirports("LET", "SVO");
        Arrays.sort(actual);
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldNotFindTicketByAirportIfFiltersDoNotMatch() { //поиск не работает, если фильтры не совпадают
        TicketPromo[] expected = new TicketPromo[]{};
        TicketPromo[] actual = ticketManager.searchByBothAirports("KZN", "SVO");
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindTicketByDepartureAirportOrArrivalAirport() { //поиск билетов, если один из аэропортов соответствуют фильтрам
        TicketPromo[] expected = new TicketPromo[]{sixthTicket, fifthTicket, firstTicket, forthTicket};
        TicketPromo[] actual = ticketManager.searchByAirports("LET", "SVO");
        Arrays.sort(actual);
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldDeleteTicketFromRepo() { //тест на удаление билета из репозитория
        ticketManager.delete(fifthTicket);
        TicketPromo[] expected = new TicketPromo[]{};
        TicketPromo[] actual = ticketManager.searchByBothAirports("AER", "SVO");
        assertArrayEquals(actual, expected);
    }


}

