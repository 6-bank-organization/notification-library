package com.sixbank.notificationlibrary.shared.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the types of notification events supported across the platform.
 *
 * <p>
 * Each enum constant defines a semantic category of notification, such as financial events,
 * account changes, compliance requirements, or system-level issues. These are used across the
 * system to categorize notifications, drive routing logic, determine templates, and
 * influence prioritization.
 * </p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * NotificationEventType type = NotificationEventType.PAYMENT_CONFIRMATION;
 * String display = type.getDisplayName(); // "Payment Confirmation"
 * }</pre>
 */
public enum NotificationEventType {

    // === Financial Events ===

    /** Triggered when a transaction alert is generated. */
    TRANSACTION_ALERT("TRANSACTION_ALERT", "Transaction Alert", "Financial transaction notifications"),

    /** Triggered after a successful payment confirmation. */
    PAYMENT_CONFIRMATION("PAYMENT_CONFIRMATION", "Payment Confirmation", "Payment processing confirmations"),

    /** Triggered when a payment fails. */
    PAYMENT_FAILED("PAYMENT_FAILED", "Payment Failed", "Failed payment notifications"),

    /** Triggered when a refund is processed. */
    REFUND_PROCESSED("REFUND_PROCESSED", "Refund Processed", "Refund processing notifications"),

    // === Security Events ===

    /** Issued for a general security alert. */
    SECURITY_ALERT("SECURITY_ALERT", "Security Alert", "Security-related alerts"),

    /** Raised when a login attempt occurs. */
    LOGIN_ATTEMPT("LOGIN_ATTEMPT", "Login Attempt", "Login attempt notifications"),

    /** Sent when a password is changed. */
    PASSWORD_CHANGE("PASSWORD_CHANGE", "Password Change", "Password change confirmations"),

    /** Triggered on new device registration. */
    DEVICE_REGISTRATION("DEVICE_REGISTRATION", "Device Registration", "New device registration"),

    // === Account Events ===

    /** Notification for account information updates. */
    ACCOUNT_UPDATE("ACCOUNT_UPDATE", "Account Update", "Account information updates"),

    /** Triggered when a new account is created. */
    ACCOUNT_CREATED("ACCOUNT_CREATED", "Account Created", "New account creation"),

    /** Triggered when an account is suspended. */
    ACCOUNT_SUSPENDED("ACCOUNT_SUSPENDED", "Account Suspended", "Account suspension notifications"),

    /** Triggered when an account is closed. */
    ACCOUNT_CLOSED("ACCOUNT_CLOSED", "Account Closed", "Account closure notifications"),

    // === KYC / Compliance Events ===

    /** Triggered when KYC (Know Your Customer) details are updated. */
    KYC_UPDATE("KYC_UPDATE", "KYC Update", "Know Your Customer updates"),

    /** Sent when additional documents are required. */
    DOCUMENT_REQUIRED("DOCUMENT_REQUIRED", "Document Required", "Required document notifications"),

    /** General compliance-related alert. */
    COMPLIANCE_ALERT("COMPLIANCE_ALERT", "Compliance Alert", "Compliance-related notifications"),

    // === Fraud Events ===

    /** Triggered when fraudulent activity is detected. */
    FRAUD_ALERT("FRAUD_ALERT", "Fraud Alert", "Fraud detection alerts"),

    /** Triggered for suspicious account behavior. */
    SUSPICIOUS_ACTIVITY("SUSPICIOUS_ACTIVITY", "Suspicious Activity", "Suspicious activity notifications"),

    // === Statement Events ===

    /** Triggered when a new statement is ready. */
    STATEMENT_READY("STATEMENT_READY", "Statement Ready", "Account statement notifications"),

    /** Monthly summary of activity. */
    MONTHLY_SUMMARY("MONTHLY_SUMMARY", "Monthly Summary", "Monthly account summary"),

    // === Marketing Events ===

    /** Generic promotional content. */
    PROMOTIONAL("PROMOTIONAL", "Promotional", "Marketing and promotional content"),

    /** Product-related announcements. */
    PRODUCT_ANNOUNCEMENT("PRODUCT_ANNOUNCEMENT", "Product Announcement", "New product announcements"),

    /** Updates on product features. */
    FEATURE_UPDATE("FEATURE_UPDATE", "Feature Update", "Platform feature updates"),

    // === System Events ===

    /** Notification of planned maintenance. */
    SYSTEM_MAINTENANCE("SYSTEM_MAINTENANCE", "System Maintenance", "System maintenance notifications"),

    /** Service outage or downtime. */
    SERVICE_OUTAGE("SERVICE_OUTAGE", "Service Outage", "Service outage notifications"),

    /** Scheduled downtime communication. */
    SCHEDULED_DOWNTIME("SCHEDULED_DOWNTIME", "Scheduled Downtime", "Scheduled maintenance notifications"),

    // === Support Events ===

    /** Created when a support ticket is opened. */
    TICKET_CREATED("TICKET_CREATED", "Support Ticket Created", "Support ticket notifications"),

    /** Updated when a support ticket is modified. */
    TICKET_UPDATED("TICKET_UPDATED", "Support Ticket Updated", "Support ticket updates"),

    /** Sent when a support ticket is resolved. */
    TICKET_RESOLVED("TICKET_RESOLVED", "Support Ticket Resolved", "Support ticket resolution");

    private final String code;
    private final String displayName;
    private final String description;

    NotificationEventType(String code, String displayName, String description) {
        this.code = code;
        this.displayName = displayName;
        this.description = description;
    }

    /**
     * Returns the unique code associated with this event type.
     *
     * @return internal event code
     */
    @JsonValue
    public String getCode() {
        return code;
    }

    /**
     * Returns the user-facing display name for this event.
     *
     * @return event display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns a brief description of the event type.
     *
     * @return event description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Resolves the {@code NotificationEventType} from its string code.
     *
     * @param code the string code
     * @return the matching event type
     * @throws IllegalArgumentException if code is invalid
     */
    @JsonCreator
    public static NotificationEventType fromCode(String code) {
        for (NotificationEventType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown notification event type: " + code);
    }

    /**
     * Returns true if the event type is security-related.
     *
     * @return true if related to security
     */
    public boolean isSecurityRelated() {
        return this == SECURITY_ALERT || this == LOGIN_ATTEMPT ||
                this == PASSWORD_CHANGE || this == DEVICE_REGISTRATION ||
                this == FRAUD_ALERT || this == SUSPICIOUS_ACTIVITY;
    }

    /**
     * Returns true if the event type is financial in nature.
     *
     * @return true if related to financial events
     */
    public boolean isFinancialRelated() {
        return this == TRANSACTION_ALERT || this == PAYMENT_CONFIRMATION ||
                this == PAYMENT_FAILED || this == REFUND_PROCESSED;
    }

    /**
     * Returns true if the event type belongs to the marketing category.
     *
     * @return true if marketing-related
     */
    public boolean isMarketing() {
        return this == PROMOTIONAL || this == PRODUCT_ANNOUNCEMENT ||
                this == FEATURE_UPDATE;
    }
}

