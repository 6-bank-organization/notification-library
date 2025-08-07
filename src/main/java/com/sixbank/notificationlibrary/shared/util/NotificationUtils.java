package com.sixbank.notificationlibrary.shared.util;

import com.sixbank.notificationlibrary.shared.constants.NotificationConstants;
import com.sixbank.notificationlibrary.shared.enums.NotificationEventType;
import com.sixbank.notificationlibrary.shared.enums.NotificationPriority;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

/**
 * Utility class providing common helper methods for notification processing.
 * <p>
 * This includes ID generation, timeout and retry logic, payload handling,
 * logging sanitization, and processing priority decisions.
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 * String id = NotificationUtils.generateNotificationId();
 * Duration timeout = NotificationUtils.getTimeoutForPriority(NotificationPriority.HIGH);
 * Instant nextRetry = NotificationUtils.calculateNextRetry(2, NotificationConstants.DEFAULT_RETRY_DELAY);
 * </pre>
 */
public final class NotificationUtils {

    /**
     * Generates a unique notification ID prefixed with "notif_".
     *
     * @return a unique string like "notif_abcd1234..."
     */
    public static String generateNotificationId() {
        return "notif_" + UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Generates a unique event ID prefixed with "evt_".
     *
     * @return a unique string like "evt_abcd1234..."
     */
    public static String generateEventId() {
        return "evt_" + UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Returns the timeout {@link Duration} for a given {@link NotificationPriority}.
     *
     * @param priority the notification priority (CRITICAL, HIGH, MEDIUM, LOW)
     * @return timeout duration based on the priority level
     */
    public static Duration getTimeoutForPriority(NotificationPriority priority) {
        return switch (priority) {
            case CRITICAL -> NotificationConstants.CRITICAL_TIMEOUT;
            case HIGH -> NotificationConstants.HIGH_TIMEOUT;
            case MEDIUM -> NotificationConstants.MEDIUM_TIMEOUT;
            case LOW -> NotificationConstants.LOW_TIMEOUT;
        };
    }

    /**
     * Returns the maximum number of retry attempts allowed for a given priority.
     *
     * @param priority the notification priority
     * @return the number of retry attempts allowed
     */
    public static int getMaxRetriesForPriority(NotificationPriority priority) {
        return priority == NotificationPriority.CRITICAL ?
                NotificationConstants.CRITICAL_MAX_RETRIES :
                NotificationConstants.DEFAULT_MAX_RETRIES;
    }

    /**
     * Calculates the next retry time using exponential backoff.
     *
     * @param attemptNumber the current attempt number (starting from 1)
     * @param baseDelay the base delay duration between retries
     * @return the computed time (Instant) of the next retry
     */
    public static Instant calculateNextRetry(int attemptNumber, Duration baseDelay) {
        double multiplier = Math.pow(NotificationConstants.RETRY_BACKOFF_MULTIPLIER, attemptNumber - 1);
        long delayMs = Math.min(
                (long) (baseDelay.toMillis() * multiplier),
                NotificationConstants.MAX_RETRY_DELAY.toMillis()
        );
        return Instant.now().plusMillis(delayMs);
    }

    /**
     * Extracts the customer name from the payload map using a known variable key.
     *
     * @param payload a map containing notification variables
     * @return the customer name or a fallback value like "Valued Customer"
     */
    public static String extractCustomerName(Map<String, Object> payload) {
        Object name = payload.get(NotificationConstants.VAR_CUSTOMER_NAME);
        return name != null ? name.toString() : "Valued Customer";
    }

    /**
     * Determines whether the given event type should trigger immediate processing.
     *
     * @param eventType the type of notification event
     * @return true if the event is security-related or critical, false otherwise
     */
    public static boolean requiresImmediateProcessing(NotificationEventType eventType) {
        return eventType.isSecurityRelated() ||
                eventType == NotificationEventType.FRAUD_ALERT ||
                eventType == NotificationEventType.SYSTEM_MAINTENANCE;
    }

    /**
     * Generates a correlation ID to be used for tracing or linking events.
     *
     * @return a unique correlation ID string prefixed with "corr_"
     */
    public static String generateCorrelationId() {
        return "corr_" + UUID.randomUUID().toString();
    }

    /**
     * Sanitizes sensitive strings for logging by partially masking them.
     *
     * @param value the original string (e.g. email, name, ID)
     * @return the masked version of the string, or "***" if too short
     */
    public static String sanitizeForLogging(String value) {
        if (value == null || value.length() <= 4) {
            return "***";
        }
        return value.substring(0, 2) + "***" + value.substring(value.length() - 2);
    }

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class.
     */
    private NotificationUtils() {
        throw new IllegalStateException("Utility class");
    }
}

