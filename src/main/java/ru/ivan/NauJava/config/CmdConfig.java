package ru.ivan.NauJava.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ivan.NauJava.command.CommandProcessor;

import java.util.Scanner;

@Configuration
public class CmdConfig {
    @Autowired
    private CommandProcessor commandProcessor;
    @Value("${app.name}")
    private String name;
    @Value("${app.version}")
    private String version;

    @Bean
    public CommandLineRunner commandScanner() {
        return args -> {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Введите команду. " + "'exit' для выхода. " + "'help' для просмотра всех команд.");
                while (true) {
                    System.out.print("> ");
                    String input = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(input.trim())) {
                        System.out.println("Выход из программы...");
                        break;
                    }
                    commandProcessor.processCommand(input);
                }
            }
        };
    }

    @PostConstruct
    private void init() {
        System.out.println(name + " " + version);
    }
}
