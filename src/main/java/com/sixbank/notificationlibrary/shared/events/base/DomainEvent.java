package com.sixbank.notificationlibrary.shared.events.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;

/**
 * Base interface for all domain events in the notification system.
 * <p>
 * Domain events represent state changes or occurrences within the system
 * that other components can react to asynchronously.
 * </p>
 *
 * <p><b>Typical usage:</b>
 * Implement this interface for any event that should be published and consumed
 * across the notification system or its supporting microservices.
 * </p>
 *
 * <p><b>Key attributes:</b></p>
 * <ul>
 *   <li><b>eventId:</b> Unique identifier for the event instance</li>
 *   <li><b>eventType:</b> Logical type or name of the event</li>
 *   <li><b>aggregateId:</b> Identifier of the aggregate (entity) related to the event</li>
 *   <li><b>aggregateType:</b> Type name of the aggregate entity</li>
 *   <li><b>occurredAt:</b> Timestamp when the event happened (in UTC)</li>
 *   <li><b>correlationId:</b> ID used to correlate this event with others in the same workflow or transaction</li>
 *   <li><b>version:</b> Version of the event schema or aggregate state</li>
 * </ul>
 */
public interface DomainEvent {
    /**
     * Returns a unique identifier for this event instance.
     * @return event ID as a String
     */
    String getEventId();

    /**
     * Returns the type or name of this event.
     * @return event type as a String
     */
    String getEventType();

    /**
     * Returns the identifier of the aggregate (entity) related to this event.
     * @return aggregate ID as a String
     */
    String getAggregateId();

    /**
     * Returns the type name of the aggregate entity.
     * @return aggregate type as a String
     */
    String getAggregateType();

    /**
     * Returns the timestamp when this event occurred.
     * The timestamp should be in UTC.
     * @return occurrence time as {@link Instant}
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    Instant getOccurredAt();

    /**
     * Returns the correlation ID to associate this event with related events or transactions.
     * Useful for tracing and diagnostics.
     * @return correlation ID as a String
     */
    String getCorrelationId();

    /**
     * Returns the version number of this event.
     * Useful for handling event schema evolution or aggregate versioning.
     * @return event version as Integer
     */
    Integer getVersion();
}

