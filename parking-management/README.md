# Smart Parking System 🅿️

This project is a core backend simulation for a smart parking lot management system. It's built using modern Java, focusing on clean architecture and best practices in Object-Oriented Programming (OOP) and SOLID principles. The system handles vehicle entry/exit, assigns appropriate parking spots, and calculates fees based on a flexible pricing strategy.

## ✨ Core Concepts & Design

The system is designed to be robust, maintainable, and extensible.

-   **Object-Oriented Programming:** Utilizes **Encapsulation**, **Inheritance**, and **Composition** to model the real-world relationships between vehicles, spots, and the parking lot itself.

-   **SOLID Principles:**

    -   **Single Responsibility:** Each class has one clear purpose (e.g., `PricingStrategy` only calculates fees).

    -   **Open/Closed:** The system is open to new pricing models via the `PricingStrategy` interface without modifying existing code (Strategy Pattern).

    -   **Dependency Inversion:** The `ParkingLot` depends on abstractions (interfaces), not concrete implementations, allowing for flexible and testable code.

-   **Modern Java Features (8+):**

    -   **Java `time` API:** For precise and immutable date/time handling.

    -   **Streams & `Optional`:** For clean, declarative, and null-safe data processing.

    -   **Records:** For creating concise, immutable data-transfer objects like `Ticket`.


----------

## 📁 Project Structure

The project follows a clean package-by-feature structure to separate concerns.

```
src
└── com
    └── parking
        ├── app
        │   └── Main.java              # Application entry point & simulation
        ├── model
        │   ├── spot
        │   │   └── ParkingSpot.java   # Represents a single parking spot
        │   ├── ticket
        │   │   └── Ticket.java        # Represents a parking ticket (record)
        │   └── vehicle
        │       ├── Vehicle.java       # Abstract vehicle class
        │       ├── Car.java           # Concrete vehicle types...
        │       ├── Motorcycle.java
        │       ├── Truck.java
        │       └── VehicleType.java   # Enum for vehicle types
        └── service
            ├── pricing
            │   ├── PricingStrategy.java      # Interface for fee calculation
            │   └── HourlyPricingStrategy.java # An hourly pricing implementation
            └── ParkingLot.java          # Main service orchestrating all operations
```

----------

## 🚀 Getting Started

Follow these steps to compile and run the simulation.

### Prerequisites

-   **Java Development Kit (JDK) 14** or higher (required for `record` types).


### Compilation & Execution

1.  Navigate to the `src` directory in your terminal.

2.  Compile the project:

    Bash

    ```
    javac com/parking/app/Main.java
    ```

3.  Run the application simulation:

    Bash

    ```
    java com.parking.app.Main
    ```

4.  The console will display the simulation output, including vehicle entries, exits, and calculated fees.