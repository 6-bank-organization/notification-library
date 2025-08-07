package com.sixbank.notificationlibrary.shared.events;


import com.sixbank.notificationlibrary.shared.enums.ChannelType;
import com.sixbank.notificationlibrary.shared.events.base.BaseEvent;

import java.time.Instant;

/**
 * Domain event fired when a notification delivery attempt fails.
 * <p>
 * This event contains details about the failure, including the channel, provider,
 * error messages and codes, timestamps, retry attempt info, and the scheduled time for the next retry (if any).
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 * NotificationFailedEvent event = new NotificationFailedEvent(
 *     "notif-1234",
 *     "customer-5678",
 *     ChannelType.SMS,
 *     "Twilio",
 *     "Network timeout",
 *     "TIMEOUT_001",
 *     Instant.now(),
 *     2,
 *     true,
 *     Instant.now().plusSeconds(60 * 5)
 * );
 * </pre>
 *
 * <p>This event can be used to trigger alerts, update dashboards, or schedule retries for failed notification deliveries.</p>
 */
public class NotificationFailedEvent extends BaseEvent {

    private final String customerId;
    private final ChannelType channelType;
    private final String provider;
    private final String errorMessage;
    private final String errorCode;
    private final Instant failedAt;
    private final int attemptNumber;
    private final boolean willRetry;
    private final Instant nextRetryAt;

    /**
     * Constructs a NotificationFailedEvent with failure details.
     *
     * @param notificationId Unique identifier for the notification (aggregateId).
     * @param customerId Identifier for the customer targeted by the notification.
     * @param channelType The delivery channel (e.g., SMS, EMAIL).
     * @param provider The external service or provider used for delivery.
     * @param errorMessage Description of the failure.
     * @param errorCode Error code returned by the provider or system.
     * @param failedAt Timestamp when the failure occurred.
     * @param attemptNumber The number of delivery attempts so far.
     * @param willRetry Flag indicating if the system plans to retry delivery.
     * @param nextRetryAt Scheduled time for the next retry attempt; null if no retry planned.
     */
    public NotificationFailedEvent(String notificationId,
                                   String customerId,
                                   ChannelType channelType,
                                   String provider,
                                   String errorMessage,
                                   String errorCode,
                                   Instant failedAt,
                                   int attemptNumber,
                                   boolean willRetry,
                                   Instant nextRetryAt) {
        super("NotificationFailed", notificationId, "Notification");
        this.customerId = customerId;
        this.channelType = channelType;
        this.provider = provider;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.failedAt = failedAt;
        this.attemptNumber = attemptNumber;
        this.willRetry = willRetry;
        this.nextRetryAt = nextRetryAt;
    }

    public String getCustomerId() { return customerId; }
    public ChannelType getChannelType() { return channelType; }
    public String getProvider() { return provider; }
    public String getErrorMessage() { return errorMessage; }
    public String getErrorCode() { return errorCode; }
    public Instant getFailedAt() { return failedAt; }
    public int getAttemptNumber() { return attemptNumber; }
    public boolean isWillRetry() { return willRetry; }
    public Instant getNextRetryAt() { return nextRetryAt; }
}

