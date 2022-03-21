package ru.netology.manager;

import ru.netology.domain.TicketPromo;
import ru.netology.repository.TicketRepository;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(TicketPromo item) {
        repository.save(item);
    }

    public void delete(TicketPromo item) {
        repository.removeById(String.valueOf(item.getId()));
    }

    public TicketPromo[] shouldReturnTicketsList() {
        TicketPromo[] result = repository.findAll();
        return result;
    }

    public TicketPromo[] searchByBothAirports(String departureAirport, String arrivalAirport) {
        TicketPromo[] result = new TicketPromo[0]; 
        for (TicketPromo item : repository.findAll()) { //для каждого элемента массива TicketPromo применяется метод найти все
            if (matchesBothAirports(item, departureAirport, arrivalAirport)) { //при условии, что метод совпадение выполняется, создается новый репо и он на одну ячейку больше
                TicketPromo[] temporaryTicketsList = new TicketPromo[result.length + 1];
                for (int i = 0; i < result.length; i++) { //копируем всё из result в tmp
                    temporaryTicketsList[i] = result[i];
                }
                temporaryTicketsList[temporaryTicketsList.length - 1] = item; //в последнюю ячейку кладем новый элемент
                result = temporaryTicketsList;
            }
        }
        return result;
    }

    public static boolean matchesBothAirports(TicketPromo item, String departureAirport, String arrivalAirport) {
            if (item.getArrivalAirport().contains(arrivalAirport) && item.getDepartureAirport().contains(departureAirport) )
            {
                    return true;
                }
            return false;
        }

    public TicketPromo[] searchByAirports(String departureAirport, String arrivalAirport) {
        TicketPromo[] result = new TicketPromo[0]; //создаем новую переменную типа массив и говорим, что там 0 ячеек
        for (TicketPromo item : repository.findAll()) { //для каждого элемента массива TicketPromo применяется метод найти все
            if (matches(item, departureAirport, arrivalAirport)) { //при условии, что метод совпадение выполняется, создается новый репо и он на одну ячейку больше
                TicketPromo[] temporaryTicketsList = new TicketPromo[result.length + 1];
                for (int i = 0; i < result.length; i++) { //копируем всё из result в tmp
                    temporaryTicketsList[i] = result[i];
                }
                temporaryTicketsList[temporaryTicketsList.length - 1] = item; //в последнюю ячейку кладем новый элемент
                result = temporaryTicketsList;
            }
        }
        return result;
    }

    public static boolean matches(TicketPromo item, String departureAirport, String arrivalAirport) {
            if (item.getArrivalAirport().contains(arrivalAirport) || item.getDepartureAirport().contains(departureAirport) )  {
                return true;
            }
            return false;
        }
}

