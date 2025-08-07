package com.sixbank.notificationlibrary.shared.events;



import com.sixbank.notificationlibrary.shared.enums.NotificationEventType;
import com.sixbank.notificationlibrary.shared.enums.NotificationPriority;
import com.sixbank.notificationlibrary.shared.events.base.BaseEvent;

import java.util.Map;

/**
 * Domain event fired when a new notification is created in the system.
 * <p>
 * This event encapsulates the core details related to the notification such as the
 * customer it is for, the type of notification event, delivery priority, originating service,
 * and any additional payload data.
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 * Map&lt;String, Object&gt; payload = Map.of("message", "Your payment was successful");
 * NotificationCreatedEvent event = new NotificationCreatedEvent(
 *     "notif-1234",
 *     "customer-5678",
 *     NotificationEventType.PAYMENT_CONFIRMATION,
 *     NotificationPriority.HIGH,
 *     "payment-service",
 *     payload
 * );
 * </pre>
 *
 * <p>This event can be published to event streams or handled internally
 * to trigger notification dispatch workflows.</p>
 */
public class NotificationCreatedEvent extends BaseEvent {

    private final String customerId;
    private final NotificationEventType notificationEventType;
    private final NotificationPriority priority;
    private final String sourceService;
    private final Map<String, Object> payload;

    /**
     * Constructs a NotificationCreatedEvent with the specified details.
     *
     * @param notificationId Unique identifier for the notification (aggregateId).
     * @param customerId Identifier for the customer receiving the notification.
     * @param notificationEventType Type/category of the notification event.
     * @param priority Priority level influencing delivery urgency and retries.
     * @param sourceService Name of the service that created the notification.
     * @param payload Additional data payload relevant to the notification.
     */
    public NotificationCreatedEvent(String notificationId,
                                    String customerId,
                                    NotificationEventType notificationEventType,
                                    NotificationPriority priority,
                                    String sourceService,
                                    Map<String, Object> payload) {
        super("NotificationCreated", notificationId, "Notification");
        this.customerId = customerId;
        this.notificationEventType = notificationEventType;
        this.priority = priority;
        this.sourceService = sourceService;
        this.payload = payload;
    }

    public String getCustomerId() { return customerId; }
    public NotificationEventType getNotificationEventType() { return notificationEventType; }
    public NotificationPriority getPriority() { return priority; }
    public String getSourceService() { return sourceService; }
    public Map<String, Object> getPayload() { return payload; }
}

