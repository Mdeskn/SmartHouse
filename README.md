# Smart House Project

## Description
The **Smart House** project offers a comprehensive simulation of energy supply and consumption in a smart home environment. It incorporates advanced features to manage smart devices, energy sources, and their interactions, providing real-time insights through simulation and logging. Built with Java, this project showcases cutting-edge concurrency and multithreading capabilities, while also offering robust exception handling and interactive user interfaces for seamless management.

---

## Key Features and Requirements

### Concurrency and Interactive Features
- An interactive user interface (CLI) enables streamlined management of devices and energy sources.
- Real-time simulation showcases the simultaneous energy generation and consumption process, with thread-safe concurrency ensuring stability.
- Advanced task scheduling facilitates dynamic operations, allowing concurrent energy resource allocation and device management without conflict.

### Simulation and Logs
- Detailed simulation logs track energy source states, device consumption, and runtime events, providing valuable insights.
- Exception handling safeguards the system, ensuring it remains resilient against runtime errors and interruptions.

### Initial Settings
- Users can dynamically upload and load initial settings for devices and energy sources through the system’s file-handling capabilities.

### Unit Testing
- Extensive unit tests validate the functionality of each subsystem, ensuring the robustness and reliability of the entire project.

### Simulation of Energy and Device Management
- The simulation effectively models energy generation from Solar, Wind, and Water sources, each with unique regeneration rates.
- Devices like Lights, Air Conditioners, and Televisions dynamically consume energy, mimicking real-world behavior.
- The system balances energy demand and supply, dynamically allocating energy resources to active devices.

### User Interaction
- A Command Line Interface (CLI) offers an intuitive way to manage devices and energy sources.
- Interactive menus guide users through adding, removing, and configuring smart devices and energy resources.

### Logging and Error Handling
- **Logger**: Captures simulation logs, tracks errors, and maintains detailed operational records.
- **ExceptionHandler**: Handles runtime errors effectively, providing a seamless user experience even in unexpected scenarios.

### File Handling
- Device and energy source configurations are initialized from `initial_energy.txt`, supporting user-defined setups for maximum flexibility.

### Unit Testing
- A dedicated test suite validates the functionality of each module, ensuring high-quality code and robust system behavior.

---

## System Architecture
- **Main Class**: Acts as the entry point for the CLI and overall system control.
- **Device Controllers**: Manage the addition, removal, and status of smart devices.
- **Energy Controller**: Oversees energy source operations and allocation.
- **House Manager**: Coordinates energy consumption and generation, simulating real-world energy dynamics.
- **Utilities**: Provide essential services like logging, file handling, and error management.

---

## Role Distribution

### Vida Bahrami 7221533
- **Modules**:
  - Device Management in `Main.java`.
  - `ApplianceController` and its related test cases.
  - Appliance-related functionalities in `FileHandler`.
- **Testing**:
  - `ApplianceControllerTest.java`.
  - `FileHandlerTest.java` (appliance-specific).
  - `Logger.java`.

### Zahra Mahdion 7221957
- **Modules**:
  - Energy Management in `Main.java`.
  - `EnergyController` and its related test cases.
  - Energy-related functionalities in `FileHandler`.
  - `EnergySource` and its subclasses.
- **Testing**:
  - `EnergyControllerTest.java`.
  - `FileHandlerTest.java` (energy-specific).

### Maede Eskandari Borujerdi 7221435
- **Modules**:
  - Simulation Section in `Main.java`.
  - `HouseManager` and `TaskScheduler`.
  - `ExceptionHandler`.
- **Concurrency**:
  - Integration of simulation features.
- **Testing**:
  - `HouseManagerTest.java`.
  - `TaskSchedulerTest.java`.
  - `LoggerTest.java`.
  - `ExceptionHandlerTest.java`.

---

## Instructions for Running the Project

### Clone or Download the Repository
```bash
git clone https://github.com/Mdeskn/SmartHouse.git
```

### Import the Project into Eclipse
1. Open Eclipse IDE.
2. Navigate to **File > Import**.
3. Select **General > Existing Projects into Workspace** and click **Next**.
4. Browse to the location of the project folder.
5. Click **Finish** to import the project.

### Run the Program
1. In the Package Explorer, right-click the `Main.java` file.
2. Choose **Run As > Java Application**.
3. Follow the on-screen menu for managing devices, energy sources, and starting simulations.

### Unit Tests
1. In the `test` package, right-click `AllTests.java`.
2. Choose **Run As > JUnit Test**.
3. Verify that all tests pass successfully.

---

## Screenshots
Add screenshots showcasing:
- Real-time simulation results.
- Successful unit test execution.
- CLI interface in action.

## Link to demo
---

## Acknowledgments
This project was developed as part of the Capstone project for Dr. Prof. Galyna Tabunshchyk’s course.

**Team Members**:
- **Vida Bahrami** - 7221533
- **Zahra Mahdion** - 7221957
- **Maede Eskandari Borujerdi** - 7221435

Thank you for your attention!
