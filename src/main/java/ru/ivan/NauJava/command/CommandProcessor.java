package ru.ivan.NauJava.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.NauJava.model.Message;
import ru.ivan.NauJava.service.MessageService;

@Component
public class CommandProcessor {
    private final MessageService messageService;

    @Autowired
    public CommandProcessor(MessageService messageService) {
        this.messageService = messageService;
    }

    public void processCommand(String input) {
        String[] cmd = input.split(" ");
        switch (cmd[0]) {
            case "help" -> {
                System.out.println("Команда create принимает три значения " +
                        "chatId, senderId, text");
                System.out.println("Команда find принимает одно значение " +
                        "messageID");
                System.out.println("Команда delete принимает одно значение " +
                        "messageID");
                System.out.println("Команда update принимает одно значение " +
                        "messageID, newText   NewText - может быть с пробелами");
            }
            case "create" -> {
                if (cmd.length != 4) {
                    System.out.println("Команда create принимает три значения " +
                            "chatId, senderId, text");
                } else {
                    messageService.createMessage(
                            Long.valueOf(cmd[1]),
                            Long.valueOf(cmd[2]),
                            cmd[3]);
                    System.out.println("Сообщение успешно добавлено...");
                }
            }
            case "find" -> {
                if (cmd.length != 2) {
                    System.out.println("Команда find принимает одно значение " +
                            "messageID");
                } else {
                    Message m = messageService.findMessage(Long.valueOf(cmd[1]));
                    if (m != null) {
                        System.out.println(m);
                    } else {
                        System.out.println("Такого сообщения нету");
                    }
                }
            }
            case "delete" -> {
                if (cmd.length != 2) {
                    System.out.println("Команда delete принимает одно значение " +
                            "messageID");
                } else {
                    messageService.deleteMessage(Long.valueOf(cmd[1]));
                    System.out.println("Сообщение успешно удалено...");
                }
            }
            case "update" -> {
                if (cmd.length < 3) {
                    System.out.println("Команда update принимает одно значение " +
                            "messageID, newText (NewText может быть с пробелами)");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 2; i < cmd.length; i++) {
                        sb.append(cmd[i]);
                    }

                    messageService.updateMessage(Long.valueOf(cmd[1]), sb.toString());
                    System.out.println("Сообщение успешно обновлено...");
                }
            }
            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}

