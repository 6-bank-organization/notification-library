package com.sixbank.notificationlibrary.shared.constants;

import java.time.Duration;

/**
 * Centralized constants used across notification services, including Kafka configuration,
 * headers, timeouts, retry logic, rate limiting, template variables, and defaults.
 *
 * <p>This class is not intended to be instantiated.</p>
 */
public final class NotificationConstants {

    // ================= Kafka Topics =================

    /** Topic for all domain notification events (e.g., created, delivered, failed). */
    public static final String TOPIC_NOTIFICATION_EVENTS = "notification-events";

    /** Topic for newly created notifications. */
    public static final String TOPIC_NOTIFICATION_CREATED = "notification-created";

    /** Topic for successfully delivered notifications. */
    public static final String TOPIC_NOTIFICATION_DELIVERED = "notification-delivered";

    /** Topic for failed notifications. */
    public static final String TOPIC_NOTIFICATION_FAILED = "notification-failed";

    /** Topic for retryable notification events. */
    public static final String TOPIC_NOTIFICATION_RETRY = "notification-retry";

    /** Topic for broadcasting delivery status updates to consumers. */
    public static final String TOPIC_DELIVERY_STATUS = "delivery-status-updates";

    // ================= Kafka Consumer Groups =================

    /** Consumer group ID for the main notification service. */
    public static final String CONSUMER_GROUP_NOTIFICATION_SERVICE = "notification-service";

    /** Consumer group ID for audit tracking. */
    public static final String CONSUMER_GROUP_AUDIT_SERVICE = "audit-service";

    /** Consumer group ID for analytics processing. */
    public static final String CONSUMER_GROUP_ANALYTICS_SERVICE = "analytics-service";

    // ================= Header Keys =================

    /** Correlation ID used for request tracing across services. */
    public static final String HEADER_CORRELATION_ID = "X-Correlation-ID";

    /** Trace ID used for distributed tracing. */
    public static final String HEADER_TRACE_ID = "X-Trace-ID";

    /** Unique customer identifier. */
    public static final String HEADER_CUSTOMER_ID = "X-Customer-ID";

    /** Type of event being handled (e.g., NOTIFICATION_CREATED). */
    public static final String HEADER_EVENT_TYPE = "X-Event-Type";

    /** Priority level of the notification (e.g., HIGH, MEDIUM). */
    public static final String HEADER_PRIORITY = "X-Priority";

    /** Identifier of the service that originated the request. */
    public static final String HEADER_SOURCE_SERVICE = "X-Source-Service";

    /** Number of retry attempts that have been made. */
    public static final String HEADER_RETRY_COUNT = "X-Retry-Count";

    /** Maximum number of retry attempts allowed. */
    public static final String HEADER_MAX_RETRIES = "X-Max-Retries";

    // ================= Timeout Configurations =================

    /** Default timeout for general operations (30 seconds). */
    public static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);

    /** Timeout for critical operations (10 seconds). */
    public static final Duration CRITICAL_TIMEOUT = Duration.ofSeconds(10);

    /** Timeout for high-priority flows. */
    public static final Duration HIGH_TIMEOUT = Duration.ofSeconds(30);

    /** Timeout for medium-priority flows. */
    public static final Duration MEDIUM_TIMEOUT = Duration.ofMinutes(5);

    /** Timeout for low-priority flows. */
    public static final Duration LOW_TIMEOUT = Duration.ofMinutes(30);

    // ================= Retry Configuration =================

    /** Default number of maximum retry attempts. */
    public static final int DEFAULT_MAX_RETRIES = 3;

    /** Maximum retry attempts for critical messages. */
    public static final int CRITICAL_MAX_RETRIES = 5;

    /** Initial delay between retries. */
    public static final Duration DEFAULT_RETRY_DELAY = Duration.ofMinutes(1);

    /** Maximum allowed delay between retries. */
    public static final Duration MAX_RETRY_DELAY = Duration.ofHours(6);

    /** Exponential backoff multiplier used for retry delays. */
    public static final double RETRY_BACKOFF_MULTIPLIER = 2.0;

    // ================= Rate Limiting =================

    /** Default rate limit per minute per channel/provider. */
    public static final int DEFAULT_RATE_LIMIT_PER_MINUTE = 100;

    /** Higher rate limit for critical flows. */
    public static final int CRITICAL_RATE_LIMIT_PER_MINUTE = 1000;

    // ================= Template Variables =================

    /** Placeholder for customer's name in templates. */
    public static final String VAR_CUSTOMER_NAME = "customerName";

    /** Placeholder for customer's email address in templates. */
    public static final String VAR_CUSTOMER_EMAIL = "customerEmail";

    /** Placeholder for customer's phone number in templates. */
    public static final String VAR_CUSTOMER_PHONE = "customerPhone";

    /** Placeholder for transaction amount in templates. */
    public static final String VAR_TRANSACTION_AMOUNT = "transactionAmount";

    /** Placeholder for transaction date in templates. */
    public static final String VAR_TRANSACTION_DATE = "transactionDate";

    /** Placeholder for merchant's name in templates. */
    public static final String VAR_MERCHANT_NAME = "merchantName";

    /** Placeholder for account number in templates. */
    public static final String VAR_ACCOUNT_NUMBER = "accountNumber";

    // ================= Validation Constraints =================

    /** Minimum length for a valid customer ID. */
    public static final int MIN_CUSTOMER_ID_LENGTH = 3;

    /** Maximum length for a valid customer ID. */
    public static final int MAX_CUSTOMER_ID_LENGTH = 100;

    /** Maximum size of payload allowed (1 MB). */
    public static final int MAX_PAYLOAD_SIZE_BYTES = 1048576;

    /** Maximum size of a rendered template (64 KB). */
    public static final int MAX_TEMPLATE_SIZE_BYTES = 65536;

    // ================= Default Values =================

    /** Default language for notifications. */
    public static final String DEFAULT_LANGUAGE = "en";

    /** Default timezone used for timestamp formatting. */
    public static final String DEFAULT_TIMEZONE = "UTC";

    /** Default "from" email address. */
    public static final String DEFAULT_FROM_EMAIL = "noreply@company.com";

    /** Placeholder for unknown or missing source service. */
    public static final String UNKNOWN_SOURCE_SERVICE = "unknown";

    /**
     * Private constructor to prevent instantiation.
     */
    private NotificationConstants() {
        throw new IllegalStateException("Utility class");
    }
}

