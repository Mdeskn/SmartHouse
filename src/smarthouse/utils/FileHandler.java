package smarthouse.utils;

import smarthouse.models.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String INITIAL_FILE_PATH = "src/smarthouse/resources/logs/initial_energy.txt";

    // Load energy sources from file
    public static List<EnergySource> loadEnergySources() {
        List<EnergySource> energySources = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INITIAL_FILE_PATH))) {
            String line;
            boolean readingEnergySources = true;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Switch to appliances section
                if (line.equalsIgnoreCase("# Appliances")) {
                    readingEnergySources = false;
                    continue;
                }

                // Skip comments and empty lines
                if (line.isEmpty() || line.startsWith("#")) continue;

                if (readingEnergySources) {
                    String[] parts = line.split(":");
                    if (parts.length != 3) {
                        ExceptionHandler.handleFileParsingError("initial_energy.txt", line);
                        continue;
                    }
                    try {
                        String name = parts[0].trim();
                        double energyGenerated = Double.parseDouble(parts[1].trim());
                        double generationRate = Double.parseDouble(parts[2].trim());

                        EnergySource source;
                        switch (name) {
                            case "SolarSource" -> source = new SolarSource(name, energyGenerated, generationRate);
                            case "WindSource" -> source = new WindSource(name, energyGenerated, generationRate);
                            case "WaterSource" -> source = new WaterSource(name, energyGenerated, generationRate);
                            default -> {
                                // Dynamically create a custom energy source
                                source = new EnergySource(name, energyGenerated, generationRate) {
                                    @Override
                                    public String getType() {
                                        return "Custom";
                                    }
                                };
                            }
                        }
                        energySources.add(source);
                        System.out.printf("Loaded %s: Initial=%.2f, Regenerate=%.2f%n", source.getType(), energyGenerated, generationRate);
                    } catch (NumberFormatException e) {
                        ExceptionHandler.handleException("FileHandler.loadEnergySources", e);
                    }
                }
            }
        } catch (IOException e) {
            ExceptionHandler.handleException("FileHandler.loadEnergySources", e);
        }
        return energySources;
    }

    // Load appliances from file
    public static List<Appliance> loadAppliances() {
        List<Appliance> appliances = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INITIAL_FILE_PATH))) {
            String line;
            boolean readingAppliances = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Switch to appliances section
                if (line.equalsIgnoreCase("# Appliances")) {
                    readingAppliances = true;
                    continue;
                }

                // Skip comments and empty lines
                if (line.isEmpty() || line.startsWith("#")) continue;

                if (readingAppliances) {
                    String[] parts = line.split(":");
                    if (parts.length != 2) {
                        ExceptionHandler.handleFileParsingError("initial_energy.txt", line);
                        continue;
                    }
                    try {
                        String name = parts[0].trim();
                        double energyConsumption = Double.parseDouble(parts[1].trim());

                        Appliance appliance;
                        switch (name) {
                            case "Light" -> appliance = new Light(name, energyConsumption);
                            case "Television" -> appliance = new Television(name, energyConsumption);
                            case "AirConditioner" -> appliance = new AirConditioner(name, energyConsumption);
                            default -> {
                                // Dynamically create a custom appliance
                                appliance = new Appliance(name, energyConsumption) {
                                    @Override
                                    public String getType() {
                                        return "Custom";
                                    }
                                };
                            }
                        }
                        appliances.add(appliance);
                        System.out.printf("Loaded %s: Consumption=%.2f%n", appliance.getType(), energyConsumption);
                    } catch (NumberFormatException e) {
                        ExceptionHandler.handleException("FileHandler.loadAppliances", e);
                    }
                }
            }
        } catch (IOException e) {
            ExceptionHandler.handleException("FileHandler.loadAppliances", e);
        }
        return appliances;
    }
}
