package com.sixbank.notificationlibrary.shared.constants;

/**
 * Kafka topic names and configuration constants used by the Notification Service.
 *
 * <p>This utility class organizes topic names into logical groups:
 * <ul>
 *   <li>{@code Input} - Topics that the Notification Service listens to (incoming events)</li>
 *   <li>{@code Output} - Topics that the Notification Service publishes to (outgoing events)</li>
 *   <li>{@code DeadLetter} - Topics used to store failed or undeliverable messages</li>
 *   <li>{@code Config} - Default topic configuration constants</li>
 * </ul>
 *
 * <p>This structure promotes consistency, reuse, and ease of maintenance when dealing with topic
 * names or Kafka topic settings across the service.
 *
 * <p><strong>Usage Example:</strong>
 * <pre>{@code
 *     String topic = TopicConstants.Input.NOTIFICATION_EVENTS;
 *     kafkaTemplate.send(topic, message);
 * }</pre>
 *
 * <p>To prevent instantiation, this class is marked as {@code final} and has a private constructor.
 */
public final class TopicConstants {

    /**
     * Contains constants for Kafka topics from which the Notification Service consumes messages.
     */
    public static final class Input {

        /** Topic for receiving generic notification events (e.g., trigger to send a notification). */
        public static final String NOTIFICATION_EVENTS = "notification-events";

        /** Topic for receiving updates to user notification preferences (e.g., mute SMS, allow email). */
        public static final String USER_PREFERENCE_UPDATES = "user-preference-updates";

        /** Topic for receiving updates to message templates (e.g., changes in message content or structure). */
        public static final String TEMPLATE_UPDATES = "template-updates";

        /** Topic for updates related to notification channel configurations (e.g., provider credentials). */
        public static final String CHANNEL_CONFIGURATION_UPDATES = "channel-configuration-updates";
    }

    /**
     * Contains constants for Kafka topics to which the Notification Service publishes messages.
     */
    public static final class Output {

        /** Topic used after a new notification has been created and is ready for processing. */
        public static final String NOTIFICATION_CREATED = "notification-created";

        /** Topic used when a notification has been successfully processed (e.g., resolved template, ready to deliver). */
        public static final String NOTIFICATION_PROCESSED = "notification-processed";

        /** Topic used when a notification has been successfully delivered to the target channel. */
        public static final String NOTIFICATION_DELIVERED = "notification-delivered";

        /** Topic used when notification delivery has failed (e.g., due to network or provider errors). */
        public static final String NOTIFICATION_FAILED = "notification-failed";

        /** Topic used to publish retry requests for failed notifications. */
        public static final String NOTIFICATION_RETRY = "notification-retry";

        /** Topic for broadcasting delivery status updates (e.g., delivered, failed, retried). */
        public static final String DELIVERY_STATUS_UPDATES = "delivery-status-updates";
    }

    /**
     * Contains constants for dead letter queue (DLQ) topics, used to handle message processing failures.
     */
    public static final class DeadLetter {

        /** DLQ for failed incoming notification events. */
        public static final String NOTIFICATION_EVENTS_DLQ = "notification-events-dlq";

        /** DLQ for failed deliveries that couldn’t be retried or recovered. */
        public static final String DELIVERY_FAILURES_DLQ = "delivery-failures-dlq";
    }

    /**
     * Contains Kafka topic configuration constants.
     */
    public static final class Config {

        /** Default number of partitions for Kafka topics. */
        public static final int DEFAULT_PARTITIONS = 10;

        /** Default replication factor for Kafka topics. */
        public static final short DEFAULT_REPLICATION_FACTOR = 3;

        /** Kafka cleanup policy that deletes old log segments. */
        public static final String CLEANUP_POLICY_DELETE = "delete";

        /** Kafka cleanup policy that compacts logs based on keys (used for state change events). */
        public static final String CLEANUP_POLICY_COMPACT = "compact";

        /** Retention period for 7 days in milliseconds. */
        public static final long RETENTION_MS_7_DAYS = 604_800_000L;

        /** Retention period for 30 days in milliseconds. */
        public static final long RETENTION_MS_30_DAYS = 2_592_000_000L;
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private TopicConstants() {
        throw new IllegalStateException("Utility class");
    }
}

