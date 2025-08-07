package com.sixbank.notificationlibrary.shared.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the various states a notification can be in throughout its lifecycle.
 *
 * <p>This enum is used to track and manage the state transitions of notifications
 * within the system. Each state includes a unique code, a display name for UI rendering,
 * and a description for better understanding of the state.</p>
 *
 * <p>Supports JSON serialization and deserialization via Jackson annotations.</p>
 *
 * <h3>Example usage:</h3>
 * <pre>{@code
 * NotificationStatus status = NotificationStatus.fromCode("DELIVERED");
 * if (status.isTerminal()) {
 *     System.out.println("Notification is in a final state.");
 * }
 * }</pre>
 */
public enum NotificationStatus {

    /**
     * The notification has been created but not yet queued.
     */
    CREATED("CREATED", "Created", "Notification has been created"),

    /**
     * The notification has been queued for further processing.
     */
    QUEUED("QUEUED", "Queued", "Notification is queued for processing"),

    /**
     * The notification is currently being processed for delivery.
     */
    PROCESSING("PROCESSING", "Processing", "Notification is being processed"),

    /**
     * The notification was successfully delivered.
     */
    DELIVERED("DELIVERED", "Delivered", "Notification has been delivered"),

    /**
     * The notification could not be delivered due to a failure.
     */
    FAILED("FAILED", "Failed", "Notification delivery failed"),

    /**
     * The notification was returned undelivered (e.g., invalid recipient).
     */
    BOUNCED("BOUNCED", "Bounced", "Notification was bounced back"),

    /**
     * The notification was read by the recipient.
     */
    READ("READ", "Read", "Notification has been read by recipient"),

    /**
     * The notification was not delivered in time and has expired.
     */
    EXPIRED("EXPIRED", "Expired", "Notification has expired");

    private final String code;
    private final String displayName;
    private final String description;

    NotificationStatus(String code, String displayName, String description) {
        this.code = code;
        this.displayName = displayName;
        this.description = description;
    }

    /**
     * Returns the code used for JSON serialization.
     *
     * @return the status code
     */
    @JsonValue
    public String getCode() {
        return code;
    }

    /**
     * Gets the human-readable name for this status.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets a description of this status.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Parses a string code into a {@link NotificationStatus} enum constant.
     *
     * @param code the code to parse
     * @return the corresponding {@link NotificationStatus}
     * @throws IllegalArgumentException if no matching status is found
     */
    @JsonCreator
    public static NotificationStatus fromCode(String code) {
        for (NotificationStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown notification status: " + code);
    }

    /**
     * Determines whether this status is terminal (i.e., no further transitions expected).
     *
     * @return true if the status is terminal; false otherwise
     */
    public boolean isTerminal() {
        return this == DELIVERED || this == FAILED || this == BOUNCED ||
                this == READ || this == EXPIRED;
    }

    /**
     * Checks if the status indicates a successful delivery or interaction.
     *
     * @return true if successful; false otherwise
     */
    public boolean isSuccessful() {
        return this == DELIVERED || this == READ;
    }

    /**
     * Determines whether the notification can be retried based on the current status.
     *
     * @return true if retry is possible; false otherwise
     */
    public boolean canRetry() {
        return this == FAILED || this == BOUNCED;
    }
}

