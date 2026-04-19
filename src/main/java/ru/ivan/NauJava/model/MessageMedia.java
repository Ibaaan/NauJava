package ru.ivan.NauJava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "message_media")
public class MessageMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    @Column(name = "media_type", length = 50, nullable = false)
    private String mediaType; // 'image', 'video', 'document', etc.

    @Column(name = "media_path", nullable = false)
    private String mediaPath;

    @Column(name = "media_order")
    private Integer mediaOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public Integer getMediaOrder() {
        return mediaOrder;
    }

    public void setMediaOrder(Integer mediaOrder) {
        this.mediaOrder = mediaOrder;
    }
}