package com.sixbank.notificationlibrary.shared.events;



import com.sixbank.notificationlibrary.shared.enums.ChannelType;
import com.sixbank.notificationlibrary.shared.events.base.BaseEvent;

import java.time.Instant;

/**
 * Domain event fired when a notification has been successfully delivered to the recipient.
 * <p>
 * This event captures details about the delivery channel, provider used,
 * timestamp of delivery, delivery attempt count, and any external system identifiers.
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 * NotificationDeliveredEvent event = new NotificationDeliveredEvent(
 *     "notif-1234",
 *     "customer-5678",
 *     ChannelType.EMAIL,
 *     "SendGrid",
 *     Instant.now(),
 *     1,
 *     "external-message-id-9876"
 * );
 * </pre>
 *
 * <p>This event can be published after the notification service confirms delivery,
 * enabling downstream systems to track notification status and analytics.</p>
 */
public class NotificationDeliveredEvent extends BaseEvent {

    private final String customerId;
    private final ChannelType channelType;
    private final String provider;
    private final Instant deliveredAt;
    private final int attemptNumber;
    private final String externalId;

    /**
     * Constructs a NotificationDeliveredEvent with delivery details.
     *
     * @param notificationId Unique identifier for the notification (aggregateId).
     * @param customerId Identifier for the customer who received the notification.
     * @param channelType Delivery channel used (e.g., EMAIL, SMS).
     * @param provider Name of the external provider or service used for delivery.
     * @param deliveredAt Timestamp when the notification was delivered.
     * @param attemptNumber Number of attempts taken to deliver this notification.
     * @param externalId External system ID referencing the delivered message.
     */
    public NotificationDeliveredEvent(String notificationId,
                                      String customerId,
                                      ChannelType channelType,
                                      String provider,
                                      Instant deliveredAt,
                                      int attemptNumber,
                                      String externalId) {
        super("NotificationDelivered", notificationId, "Notification");
        this.customerId = customerId;
        this.channelType = channelType;
        this.provider = provider;
        this.deliveredAt = deliveredAt;
        this.attemptNumber = attemptNumber;
        this.externalId = externalId;
    }

    public String getCustomerId() { return customerId; }
    public ChannelType getChannelType() { return channelType; }
    public String getProvider() { return provider; }
    public Instant getDeliveredAt() { return deliveredAt; }
    public int getAttemptNumber() { return attemptNumber; }
    public String getExternalId() { return externalId; }
}

