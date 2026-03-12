package ru.ivan.NauJava.model;

public class Message {
    private Long id;
    private Long chatId;
    private Long senderId;
    private String text;

    public Message(Long id, Long chatId, Long senderId, String text) {
        this.id = id;
        this.chatId = chatId;
        this.senderId = senderId;
        this.text = text;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", senderId=" + senderId +
                ", text='" + text + '\'' +
                '}';
    }
}
