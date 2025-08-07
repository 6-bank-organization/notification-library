package com.sixbank.notificationlibrary.shared.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Set;

/**
 * Enumeration of external service providers used for notification delivery.
 * <p>
 * Each provider supports one or more notification channels such as email, SMS, push, or webhook.
 * This enum helps map provider codes to display names, descriptions, and their supported channels.
 * </p>
 */
public enum ChannelProvider {
    // Email Providers
    /** SendGrid email service provider */
    SENDGRID("SENDGRID", "SendGrid", "SendGrid email service", Set.of(ChannelType.EMAIL)),

    /** Amazon Simple Email Service (SES) */
    AWS_SES("AWS_SES", "Amazon SES", "Amazon Simple Email Service", Set.of(ChannelType.EMAIL)),

    /** Mailgun email service provider */
    MAILGUN("MAILGUN", "Mailgun", "Mailgun email service", Set.of(ChannelType.EMAIL)),

    // SMS Providers
    /** Twilio SMS and Voice service */
    TWILIO("TWILIO", "Twilio", "Twilio SMS service", Set.of(ChannelType.SMS, ChannelType.VOICE)),

    /** Amazon Simple Notification Service (SNS) supporting SMS and push notifications */
    AWS_SNS("AWS_SNS", "Amazon SNS", "Amazon Simple Notification Service", Set.of(ChannelType.SMS, ChannelType.PUSH)),

    // Push Notification Providers
    /** Firebase Cloud Messaging service */
    FIREBASE("FIREBASE", "Firebase", "Firebase Cloud Messaging", Set.of(ChannelType.PUSH)),

    /** Apple Push Notification Service */
    APNS("APNS", "Apple Push", "Apple Push Notification Service", Set.of(ChannelType.PUSH)),

    // Multi-channel Providers
    /** Custom multi-channel notification provider */
    CUSTOM("CUSTOM", "Custom Provider", "Custom implementation",
            Set.of(ChannelType.EMAIL, ChannelType.SMS, ChannelType.PUSH, ChannelType.WEBHOOK));

    /** Internal provider code used for serialization and lookup */
    private final String code;

    /** Human-readable provider name for UI or logging */
    private final String displayName;

    /** Description of the provider */
    private final String description;

    /** Supported notification channels for the provider */
    private final Set<ChannelType> supportedChannels;

    /**
     * Constructs a ChannelProvider enum constant.
     *
     * @param code              unique provider code
     * @param displayName       user-friendly name
     * @param description       detailed description
     * @param supportedChannels set of supported notification channels
     */
    ChannelProvider(String code, String displayName, String description, Set<ChannelType> supportedChannels) {
        this.code = code;
        this.displayName = displayName;
        this.description = description;
        this.supportedChannels = supportedChannels;
    }

    /**
     * Returns the code for JSON serialization.
     *
     * @return provider code string
     */
    @JsonValue
    public String getCode() {
        return code;
    }

    /**
     * Returns the display name of the provider.
     *
     * @return human-readable name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the description of the provider.
     *
     * @return description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the set of notification channels supported by this provider.
     *
     * @return immutable set of supported channels
     */
    public Set<ChannelType> getSupportedChannels() {
        return supportedChannels;
    }

    /**
     * Creates a ChannelProvider enum from its string code.
     *
     * @param code the code of the provider
     * @return the matching ChannelProvider constant
     * @throws IllegalArgumentException if the code is unknown
     */
    @JsonCreator
    public static ChannelProvider fromCode(String code) {
        for (ChannelProvider provider : values()) {
            if (provider.code.equals(code)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("Unknown channel provider: " + code);
    }

    /**
     * Checks if this provider supports the given notification channel.
     *
     * @param channelType the notification channel to check
     * @return true if supported, false otherwise
     */
    public boolean supports(ChannelType channelType) {
        return supportedChannels.contains(channelType);
    }
}

