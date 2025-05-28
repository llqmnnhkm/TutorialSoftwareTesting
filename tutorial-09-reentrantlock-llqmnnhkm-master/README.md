[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/2CJ6hL0W)
## Your Info:
1. Matric Number:294854
1. Name:Muhammad Luqmannulhakim Bin Rosly

## Instruction:

1.	You must develop a simplified ticket booking system for a cinema with multiple counters for ticket sales. The cinema has a single screen with a seating capacity of 50 seats. Each seat is uniquely identified by a seat number. Customers can book seats at any counter, but no two customers should be able to book the same seat simultaneously. Write a Java program called `CinemaHall` that implements `CinemaInterface` and uses `ReentrantLock` to ensure that the seat booking process is thread-safe and that no two threads can book the same seat simultaneously. The system should also avoid potential deadlocks and ensure fairness in seat allocation. Given are the `TicketCounter` and `MyMain` classes that set up the cinema hall and start multiple threads (ticket counters) that attempt to book the same seat to demonstrate the locking mechanism.

1. Screenshot the result and upload to this repo too.

1. If you have a problem with the Maven Project, watch the video for the configuration: [IntelliJ IDEA | Creating a new configuration for running Java program](https://youtu.be/h2DT2SsPX1M)
1. Watch the video to clone, add, commit and push a repository to GitHub: [IntelliJ IDEA | How to clone, add, commit and push a repository to GitHub easily](https://youtu.be/RXV3Yusr0SI)

## Example of the output
```
Seat 25 successfully booked by Counter 1
Seat 25 is already booked. Counter 2 could not book it.
Seat 25 is already booked. Counter 3 could not book it.
```

## Your Output/Result
