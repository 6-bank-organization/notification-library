package com.sixbank.notificationlibrary.shared.events.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Abstract base class for domain events providing common functionality.
 *
 * <p>This class implements the {@link DomainEvent} interface and
 * provides default handling for core event fields such as unique event ID,
 * event type, aggregate identification, timestamp, correlation ID, and version.</p>
 *
 * <p><b>Usage:</b></p>
 * <pre>
 * public class UserCreatedEvent extends BaseEvent {
 *     private final String userName;
 *
 *     public UserCreatedEvent(String aggregateId, String userName) {
 *         super("UserCreated", aggregateId, "User");
 *         this.userName = userName;
 *     }
 *
 *     public String getUserName() {
 *         return userName;
 *     }
 * }
 * </pre>
 *
 * <p>By extending this base class, you automatically get consistent
 * event metadata handling and utility methods.</p>
 */
public abstract class BaseEvent implements DomainEvent {

    private final String eventId;
    private final String eventType;
    private final String aggregateId;
    private final String aggregateType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private final Instant occurredAt;

    private final String correlationId;
    private final Integer version;

    /**
     * Constructs a BaseEvent with default version 1 and auto-generated correlationId.
     *
     * @param eventType Type of the event.
     * @param aggregateId ID of the aggregate related to the event.
     * @param aggregateType Type name of the aggregate.
     */
    protected BaseEvent(String eventType, String aggregateId, String aggregateType) {
        this(eventType, aggregateId, aggregateType, null, 1);
    }

    /**
     * Constructs a BaseEvent with specified correlationId and version.
     *
     * @param eventType Type of the event.
     * @param aggregateId ID of the aggregate related to the event.
     * @param aggregateType Type name of the aggregate.
     * @param correlationId Correlation ID for tracing.
     * @param version Event version number.
     */
    protected BaseEvent(String eventType, String aggregateId, String aggregateType,
                        String correlationId, Integer version) {
        this.eventId = UUID.randomUUID().toString();
        this.eventType = eventType;
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        this.occurredAt = Instant.now();
        this.correlationId = correlationId != null ? correlationId : UUID.randomUUID().toString();
        this.version = version;
    }

    @Override
    public String getEventId() {
        return eventId;
    }

    @Override
    public String getEventType() {
        return eventType;
    }

    @Override
    public String getAggregateId() {
        return aggregateId;
    }

    @Override
    public String getAggregateType() {
        return aggregateType;
    }

    @Override
    public Instant getOccurredAt() {
        return occurredAt;
    }

    @Override
    public String getCorrelationId() {
        return correlationId;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEvent baseEvent = (BaseEvent) o;
        return Objects.equals(eventId, baseEvent.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }

    @Override
    public String toString() {
        return "BaseEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", aggregateId='" + aggregateId + '\'' +
                ", occurredAt=" + occurredAt +
                '}';
    }
}

