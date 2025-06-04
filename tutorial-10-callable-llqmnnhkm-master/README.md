[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/JlqoQ6ot)
## Your Info:
1. Matric Number:294854
1. Name:Muhammad Luqmannulhakim bin rosly

## Notes:

1. https://github.com/zhamri/stiw3054_Callable
2. https://github.com/zhamri/Java-Concurrency/blob/master/src/Week_05/MyExecuter.java
   
## Instruction:

You are working on an e-commerce platform that allows users to compare prices of products from various online hotels. The platform aims to provide a feature where users can enter a room type and quickly receive a list of prices from different hotels. Since fetching price information from each hotel involves network calls (which can be time-consuming and are independent of each other), the system needs to perform the operation below to simulate the price.

```
    public double fetchPrice(String roomType) {
        // Mock price fetching logic
        return Math.random() * 100; // Random price for demonstration
    }
```

1. Write a Java program that simulates multiple hotels' asynchronous fetching of product prices. Use the concepts of multithreading and concurrency to achieve this by using `Callable` and `Future` interfaces. Video --> [Java Concurrency | How to implement Callable & Future with ExecutorService](https://youtu.be/oXC8izF1jAI)

1. The program file which has the main method should be named `App.java` and placed in the `src/main/java/my/uum/` folder.

1. Screenshot the result and upload it to this repo too.


## Example of the output
```
Room Type: Standard Room, Hotel: Hotel A, Price: $163.80
Room Type: Standard Room, Hotel: Hotel B, Price: $149.35
Room Type: Standard Room, Hotel: Hotel C, Price: $115.64
```

## Your Output/Result
