package ru.netology.repository;
import ru.netology.domain.TicketPromo;

public class TicketRepository {

    private TicketPromo[] items = new TicketPromo[0];

    public void save(TicketPromo item) {
        int length = items.length + 1;
        TicketPromo[] tmp = new TicketPromo[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public TicketPromo[] findAll() {
        return items;
    }

    public void removeById(String id) {
        int length = items.length - 1;
        TicketPromo[] tmp = new TicketPromo[length];
        int index = 0;
        for (TicketPromo item : items) {
            if (id != item.getId()) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }
}
