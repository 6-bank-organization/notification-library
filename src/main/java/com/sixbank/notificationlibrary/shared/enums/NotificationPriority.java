package com.sixbank.notificationlibrary.shared.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the priority levels for notifications which affect delivery order, timing,
 * and retry behavior in the notification system.
 * <p>
 * Each priority level includes metadata such as:
 * - Display name
 * - Description
 * - Delay before delivery (in milliseconds)
 * - Timeout threshold (in milliseconds)
 * <p>
 * These parameters help determine how fast and persistently a notification is delivered.
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * NotificationPriority priority = NotificationPriority.HIGH;
 * long delay = priority.getDelayMs();  // e.g., 1000 ms
 * }</pre>
 */
public enum NotificationPriority {

    /**
     * Critical notifications require immediate attention and have no delay.
     * Timeout after 30 seconds.
     */
    CRITICAL(1, "Critical", "Immediate delivery required", 0, 30000),

    /**
     * High priority notifications are delivered quickly with a small delay and a timeout of 1 minute.
     */
    HIGH(2, "High", "High priority delivery", 1000, 60000),

    /**
     * Medium priority is the default delivery option for most standard notifications.
     * Timeout after 5 minutes.
     */
    MEDIUM(3, "Medium", "Normal priority delivery", 5000, 300000),

    /**
     * Low priority notifications may be delivered with a longer delay.
     * Timeout after 1 hour.
     */
    LOW(4, "Low", "Low priority delivery", 30000, 3600000);

    private final int level;
    private final String displayName;
    private final String description;
    private final long delayMs;
    private final long timeoutMs;

    /**
     * Constructor for {@link NotificationPriority}.
     *
     * @param level        Integer value representing importance (lower is more important)
     * @param displayName  User-friendly name for the priority
     * @param description  Description of the priority behavior
     * @param delayMs      Milliseconds to wait before delivering the notification
     * @param timeoutMs    Maximum allowable time for delivery
     */
    NotificationPriority(int level, String displayName, String description,
                         long delayMs, long timeoutMs) {
        this.level = level;
        this.displayName = displayName;
        this.description = description;
        this.delayMs = delayMs;
        this.timeoutMs = timeoutMs;
    }

    /**
     * Returns the enum name as a string for JSON serialization.
     *
     * @return Enum name as string
     */
    @JsonValue
    public String toValue() {
        return name();
    }

    /**
     * Converts a string into a {@link NotificationPriority}.
     * Case-insensitive.
     *
     * @param value Priority name
     * @return Corresponding {@link NotificationPriority}
     * @throws IllegalArgumentException If input does not match any priority
     */
    @JsonCreator
    public static NotificationPriority fromString(String value) {
        try {
            return valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid priority: " + value);
        }
    }

    /**
     * Gets the integer level used to compare priorities.
     *
     * @return Integer priority level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the human-readable name of the priority.
     *
     * @return Display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets a short description of the priority's behavior.
     *
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the delay before the notification is delivered.
     *
     * @return Delay in milliseconds
     */
    public long getDelayMs() {
        return delayMs;
    }

    /**
     * Gets the timeout duration after which the notification will fail if undelivered.
     *
     * @return Timeout in milliseconds
     */
    public long getTimeoutMs() {
        return timeoutMs;
    }

    /**
     * Compares this priority to another to check if it is of a higher urgency.
     *
     * @param other The other priority to compare with
     * @return {@code true} if this priority is higher than the other
     */
    public boolean isHigherThan(NotificationPriority other) {
        return this.level < other.level;
    }
}

