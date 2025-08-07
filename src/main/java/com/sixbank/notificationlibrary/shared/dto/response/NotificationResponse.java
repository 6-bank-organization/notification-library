package com.sixbank.notificationlibrary.shared.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sixbank.notificationlibrary.shared.enums.NotificationEventType;
import com.sixbank.notificationlibrary.shared.enums.NotificationPriority;
import com.sixbank.notificationlibrary.shared.enums.NotificationStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * DTO class representing the response of a notification operation.
 * This class is typically used when returning notification information to API clients.
 */
public class NotificationResponse {

    /** Unique identifier for the notification */
    private String id;

    /** Event identifier related to the notification */
    private String eventId;

    /** Type of the event triggering the notification */
    private NotificationEventType eventType;

    /** ID of the customer who receives the notification */
    private String customerId;

    /** Priority level of the notification */
    private NotificationPriority priority;

    /** Current status of the notification (e.g., SENT, DELIVERED, READ) */
    private NotificationStatus status;

    /** Name of the microservice that initiated the notification */
    private String sourceService;

    /** Timestamp when the notification was created */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant createdAt;

    /** Timestamp when the notification was processed */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant processedAt;

    /** Timestamp when the notification was successfully delivered */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant deliveredAt;

    /** Timestamp when the user read the notification */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant readAt;

    /** Timestamp when the notification is scheduled to be sent */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant scheduledAt;

    /** Default constructor */
    public NotificationResponse() {}

    /** Full-argument constructor */
    public NotificationResponse(String id, String eventId, NotificationEventType eventType, String customerId,
                                NotificationPriority priority, NotificationStatus status, String sourceService,
                                Instant createdAt, Instant processedAt, Instant deliveredAt, Instant readAt, Instant scheduledAt) {
        this.id = id;
        this.eventId = eventId;
        this.eventType = eventType;
        this.customerId = customerId;
        this.priority = priority;
        this.status = status;
        this.sourceService = sourceService;
        this.createdAt = createdAt;
        this.processedAt = processedAt;
        this.deliveredAt = deliveredAt;
        this.readAt = readAt;
        this.scheduledAt = scheduledAt;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public NotificationEventType getEventType() {
        return eventType;
    }

    public void setEventType(NotificationEventType eventType) {
        this.eventType = eventType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public NotificationPriority getPriority() {
        return priority;
    }

    public void setPriority(NotificationPriority priority) {
        this.priority = priority;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public String getSourceService() {
        return sourceService;
    }

    public void setSourceService(String sourceService) {
        this.sourceService = sourceService;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(Instant processedAt) {
        this.processedAt = processedAt;
    }

    public Instant getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Instant deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public Instant getReadAt() {
        return readAt;
    }

    public void setReadAt(Instant readAt) {
        this.readAt = readAt;
    }

    public Instant getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(Instant scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationResponse)) return false;
        NotificationResponse that = (NotificationResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(eventId, that.eventId) &&
                eventType == that.eventType &&
                Objects.equals(customerId, that.customerId) &&
                priority == that.priority &&
                status == that.status &&
                Objects.equals(sourceService, that.sourceService) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(processedAt, that.processedAt) &&
                Objects.equals(deliveredAt, that.deliveredAt) &&
                Objects.equals(readAt, that.readAt) &&
                Objects.equals(scheduledAt, that.scheduledAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, eventType, customerId, priority, status,
                sourceService, createdAt, processedAt, deliveredAt, readAt, scheduledAt);
    }
}

