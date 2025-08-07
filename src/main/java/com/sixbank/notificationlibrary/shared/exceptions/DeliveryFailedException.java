package com.sixbank.notificationlibrary.shared.exceptions;

import com.sixbank.notificationlibrary.shared.enums.ChannelType;

/**
 * Exception thrown when a notification fails to be delivered through a given provider or channel.
 * <p>
 * This exception allows capturing key information such as the notification channel (e.g., EMAIL, SMS),
 * the specific provider used (e.g., SendGrid, Twilio), the number of delivery attempts, and whether
 * the error is retryable.
 * </p>
 *
 * <p><b>Example usage:</b></p>
 * <pre>{@code
 * try {
 *     notificationSender.send(notification);
 * } catch (Exception e) {
 *     throw new DeliveryFailedException(
 *         "Failed to deliver email notification",
 *         ChannelType.EMAIL,
 *         "SendGrid",
 *         3,
 *         false
 *     );
 * }
 * }</pre>
 */
public class DeliveryFailedException extends NotificationException {

    private final ChannelType channelType;
    private final String provider;
    private final int attemptNumber;
    private final boolean retryable;

    /**
     * Creates a delivery failed exception with default retryable=true and attemptNumber=1.
     *
     * @param message      A human-readable description of the failure.
     * @param channelType  The channel through which delivery was attempted (e.g., EMAIL, SMS).
     * @param provider     The notification provider used for delivery (e.g., Twilio, Firebase).
     */
    public DeliveryFailedException(String message, ChannelType channelType, String provider) {
        super("DELIVERY_FAILED", message);
        this.channelType = channelType;
        this.provider = provider;
        this.attemptNumber = 1;
        this.retryable = true;
    }

    /**
     * Creates a delivery failed exception with full context.
     *
     * @param message       A human-readable description of the failure.
     * @param channelType   The channel through which delivery was attempted.
     * @param provider      The provider used for delivery.
     * @param attemptNumber The number of the delivery attempt.
     * @param retryable     Indicates whether this error should be retried.
     */
    public DeliveryFailedException(String message, ChannelType channelType, String provider,
                                   int attemptNumber, boolean retryable) {
        super("DELIVERY_FAILED", message);
        this.channelType = channelType;
        this.provider = provider;
        this.attemptNumber = attemptNumber;
        this.retryable = retryable;
    }

    /**
     * Creates a delivery failed exception with full context and a cause.
     *
     * @param message       A human-readable message.
     * @param cause         The original cause of the failure (e.g., IOException).
     * @param channelType   The notification channel used.
     * @param provider      The notification provider used.
     * @param attemptNumber The number of the delivery attempt.
     * @param retryable     Indicates whether the failure can be retried.
     */
    public DeliveryFailedException(String message, Throwable cause, ChannelType channelType,
                                   String provider, int attemptNumber, boolean retryable) {
        super("DELIVERY_FAILED", message, cause);
        this.channelType = channelType;
        this.provider = provider;
        this.attemptNumber = attemptNumber;
        this.retryable = retryable;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public String getProvider() {
        return provider;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public boolean isRetryable() {
        return retryable;
    }
}

