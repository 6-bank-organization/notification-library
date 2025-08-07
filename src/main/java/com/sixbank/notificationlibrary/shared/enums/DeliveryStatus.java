package com.sixbank.notificationlibrary.shared.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration representing the status of a delivery attempt in the notification system.
 * <p>
 * Each status includes a code used for serialization, a user-friendly display name,
 * and a descriptive message explaining the status meaning.
 * </p>
 */
public enum DeliveryStatus {
    /** Delivery is pending and has not yet started. */
    PENDING("PENDING", "Pending", "Delivery is pending"),

    /** Message has been sent to the provider but not confirmed delivered. */
    SENT("SENT", "Sent", "Message has been sent to provider"),

    /** Message was successfully delivered to the recipient. */
    DELIVERED("DELIVERED", "Delivered", "Message has been delivered"),

    /** Delivery attempt failed. */
    FAILED("FAILED", "Failed", "Delivery failed"),

    /** Message bounced back from the recipient server. */
    BOUNCED("BOUNCED", "Bounced", "Message bounced back"),

    /** Delivery rate limited by the provider or system. */
    RATE_LIMITED("RATE_LIMITED", "Rate Limited", "Delivery rate limited"),

    /** Delivery attempt expired without success or failure. */
    EXPIRED("EXPIRED", "Expired", "Delivery attempt expired");

    /** Internal code for serialization and lookup */
    private final String code;

    /** User-friendly display name for UI or logs */
    private final String displayName;

    /** Description providing more details about the status */
    private final String description;

    /**
     * Constructor for delivery status enum.
     *
     * @param code         internal status code
     * @param displayName  human-readable status name
     * @param description  detailed description of the status
     */
    DeliveryStatus(String code, String displayName, String description) {
        this.code = code;
        this.displayName = displayName;
        this.description = description;
    }

    /**
     * Gets the internal code for JSON serialization.
     *
     * @return the status code string
     */
    @JsonValue
    public String getCode() {
        return code;
    }

    /**
     * Returns the user-friendly display name of the status.
     *
     * @return display name string
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns a descriptive message about the status.
     *
     * @return description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Parses a status from its code string.
     *
     * @param code the status code to parse
     * @return the matching DeliveryStatus enum constant
     * @throws IllegalArgumentException if no matching status is found
     */
    @JsonCreator
    public static DeliveryStatus fromCode(String code) {
        for (DeliveryStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown delivery status: " + code);
    }

    /**
     * Checks if the delivery status is terminal, meaning no further attempts should be made.
     *
     * @return true if terminal status, false otherwise
     */
    public boolean isTerminal() {
        return this == DELIVERED || this == FAILED || this == BOUNCED || this == EXPIRED;
    }

    /**
     * Checks if the delivery was successful.
     *
     * @return true if delivered, false otherwise
     */
    public boolean isSuccessful() {
        return this == DELIVERED;
    }

    /**
     * Checks if the delivery attempt can be retried.
     *
     * @return true if retryable, false otherwise
     */
    public boolean canRetry() {
        return this == FAILED || this == RATE_LIMITED;
    }
}

