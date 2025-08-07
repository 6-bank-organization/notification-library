package com.sixbank.notificationlibrary.shared.exceptions;

/**
 * Base exception class for all notification-related errors.
 * <p>
 * This exception encapsulates an error code and optional arguments to provide
 * detailed context for failures occurring within the notification system.
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 * // Throw with a simple message
 * throw new NotificationException("Notification delivery failed");
 *
 * // Throw with an error code and message
 * throw new NotificationException("DELIVERY_FAILED", "Failed to deliver notification to user {}", userId);
 *
 * // Throw with cause
 * try {
 *     sendNotification();
 * } catch (SomeException ex) {
 *     throw new NotificationException("DELIVERY_FAILED", "Notification service error", ex, userId);
 * }
 * </pre>
 *
 * <p>This exception can be caught globally or specifically to handle notification errors in a consistent way.</p>
 */
public class NotificationException extends RuntimeException {
    private final String errorCode;
    private final Object[] args;

    public NotificationException(String message) {
        super(message);
        this.errorCode = "NOTIFICATION_ERROR";
        this.args = new Object[0];
    }

    public NotificationException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "NOTIFICATION_ERROR";
        this.args = new Object[0];
    }

    public NotificationException(String errorCode, String message, Object... args) {
        super(message);
        this.errorCode = errorCode;
        this.args = args;
    }

    public NotificationException(String errorCode, String message, Throwable cause, Object... args) {
        super(message, cause);
        this.errorCode = errorCode;
        this.args = args;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args.clone();
    }
}
