# Smart House Project

## Description
The **Smart House** project offers a comprehensive simulation of energy supply and consumption in a smart home environment. It incorporates advanced features to manage smart devices, energy sources, and their interactions, providing real-time insights through simulation and logging. Built with Java, this project showcases cutting-edge concurrency and multithreading capabilities, while also offering robust exception handling and interactive user interfaces for seamless management.

---

## Key Features and Requirements

### Concurrency and Interactive Features
- An interactive user interface (CLI) implemented in `Main.java` enables streamlined management of devices and energy sources.
- Real-time simulation in `HouseManager` showcases the simultaneous energy generation and consumption process, with thread-safe concurrency ensured by the `TaskScheduler` class.
- Advanced task scheduling facilitates dynamic operations, allowing concurrent energy resource allocation and device management without conflict.

### Simulation and Logs
- Detailed simulation logs are captured in the `Logger` class, tracking energy source states, device consumption, and runtime events, providing valuable insights.
- Exception handling through the `ExceptionHandler` class safeguards the system, ensuring it remains resilient against runtime errors and interruptions.

### Initial Settings
- Users can dynamically upload and load initial settings for devices and energy sources through file-handling capabilities in the `FileHandler` class.

### Unit Testing
- Extensive unit tests validate the functionality of each subsystem, ensuring the robustness and reliability of the entire project.

### Simulation of Energy and Device Management
- The simulation, implemented in `HouseManager` and supported by the `EnergyController` and `ApplianceController`, effectively models energy generation from Solar, Wind, and Water sources, each with unique regeneration rates.
- Devices like Lights (`Light.java`), Air Conditioners (`AirConditioner.java`), and Televisions (`Television.java`) dynamically consume energy, mimicking real-world behavior.
- The system balances energy demand and supply, dynamically allocating energy resources to active devices.

### Logging and Error Handling
- **Logger**: Implemented in `Logger.java` to capture simulation logs, track errors, and maintain detailed operational records.
- **ExceptionHandler**: Handles runtime errors effectively, providing a seamless user experience even in unexpected scenarios.

### File Handling
- Device and energy source configurations are initialized from `initial_energy.txt`, supporting user-defined setups for maximum flexibility via the `FileHandler` class.

### Unit Testing
- A dedicated test suite, including `HouseManagerTest.java`, `EnergyControllerTest.java`, and `ApplianceControllerTest.java`, validates the functionality of each module, ensuring high-quality code and robust system behavior.
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
  - `Logger.java`.
- **Testing**:
  - `ApplianceControllerTest.java`.
  - `FileHandlerTest.java` (appliance-specific).
  - `LoggerTest.java`.

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
Screenshots showcasing:
- CLI interface in action.
  ![Screenshot 2024-11-27 010110](https://github.com/user-attachments/assets/d67d9ac2-585b-4f7f-bdbb-76c2cb46ea1c)
- Successful unit test execution.
  ![Screenshot 2024-11-27 010018](https://github.com/user-attachments/assets/7e5285b4-75c0-4ef5-a546-1ce45ce6de50)
- Real-time simulation results.
  ![Screenshot 2024-11-27 005916](https://github.com/user-attachments/assets/9a1c57b7-8137-47f0-9fbc-eb50a1b35ceb)
- UML Diagram
  <img width="1106" alt="Screenshot 2024-11-27 at 00 07 11" src="https://github.com/user-attachments/assets/f6269ef9-5e19-46f2-8885-9fe34aaa6254">
- Component Diagram
  ![ZLN1RK~1](https://github.com/user-attachments/assets/f78f7f1f-fa51-4f3a-b17f-4fab2db2be68)



## [Link to demo](https://fhdoprod-my.sharepoint.com/:v:/g/personal/maede_eskandariborujerdi001_stud_fh-dortmund_de/EQi5UlQof0lGmgYNFzpQsnMBxxReJgLjj-AOApW1YXw9xw?e=eM3dov&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D)
## [Link to Presentation file](https://fhdoprod-my.sharepoint.com/:p:/g/personal/maede_eskandariborujerdi001_stud_fh-dortmund_de/EWAgu42CE_pFnc-PoJUoVwYB_gmMzZXLBh1I0MZzzp7vJQ?e=1cFirV)
## [Link to requirement file](https://fhdoprod-my.sharepoint.com/:w:/g/personal/maede_eskandariborujerdi001_stud_fh-dortmund_de/EQdTqnDo31ROsS_jM3fOYMkBsGPUHaKOcjtfWrmx4tYrag?e=XTMO4c)
---

## Acknowledgments
This project was developed as the Capstone project for Dr. Prof. Galyna Tabunshchyk’s course.

**Team Members**:
- **Vida Bahrami** - 7221533
- **Zahra Mahdion** - 7221957
- **Maede Eskandari Borujerdi** - 7221435

Thank you for your attention!
