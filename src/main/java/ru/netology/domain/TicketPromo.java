package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketPromo implements Comparable<TicketPromo> {
 private String id;
 private int price;
 private String departureAirport;
 private String arrivalAirport;
 private int flightTime;

 @Override
 public int compareTo(TicketPromo o) {
  TicketPromo p = (TicketPromo) o;
  return this.price - p.price;
 }
}