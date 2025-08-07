package com.sixbank.notificationlibrary.shared.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Set;

/**
 * Enum representing the various notification delivery channels supported by the system.
 *
 * <p>This enum provides metadata for each channel such as:
 * - A unique code (used for serialization and lookup)
 * - A user-friendly display name
 * - A description of the channel
 * - The content types it supports (e.g., plain text, HTML, audio)
 *
 * <p>It also includes helper methods to:
 * - Determine if a channel is real-time
 * - Check if it is a digital channel
 * - Check if the channel supports rich content
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * ChannelType channel = ChannelType.fromCode("EMAIL");
 * System.out.println(channel.getDisplayName()); // Output: Email
 *
 * if (channel.isRealTime()) {
 *     // Trigger real-time delivery logic
 * }
 * }</pre>
 */
public enum ChannelType {

    EMAIL("EMAIL", "Email", "Electronic mail notifications", Set.of("text/html", "text/plain")),
    SMS("SMS", "SMS", "Short message service notifications", Set.of("text/plain")),
    PUSH("PUSH", "Push Notification", "Mobile push notifications", Set.of("application/json")),
    IN_APP("IN_APP", "In-App", "In-application notifications", Set.of("application/json")),
    WEBHOOK("WEBHOOK", "Webhook", "HTTP webhook notifications", Set.of("application/json")),
    VOICE("VOICE", "Voice Call", "Voice call notifications", Set.of("audio/wav")),
    LETTER("LETTER", "Physical Letter", "Physical mail notifications", Set.of("text/plain")),
    FAX("FAX", "Fax", "Fax notifications", Set.of("text/plain"));

    /**
     * Unique identifier for the channel (used in serialization and deserialization).
     */
    private final String code;

    /**
     * Human-readable name for the channel.
     */
    private final String displayName;

    /**
     * Description of what the channel represents or how it is used.
     */
    private final String description;

    /**
     * Set of MIME content types supported by this channel.
     */
    private final Set<String> supportedContentTypes;

    /**
     * Constructor for the enum values.
     *
     * @param code the unique code
     * @param displayName the user-friendly name
     * @param description the description of the channel
     * @param supportedContentTypes supported content MIME types
     */
    ChannelType(String code, String displayName, String description, Set<String> supportedContentTypes) {
        this.code = code;
        this.displayName = displayName;
        this.description = description;
        this.supportedContentTypes = supportedContentTypes;
    }

    /**
     * Returns the unique code used for JSON serialization.
     *
     * @return the unique code
     */
    @JsonValue
    public String getCode() {
        return code;
    }

    /**
     * Returns a human-readable name of the channel.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns a brief description of the channel.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the list of supported MIME types by the channel.
     *
     * @return a set of supported content types
     */
    public Set<String> getSupportedContentTypes() {
        return supportedContentTypes;
    }

    /**
     * Factory method used during JSON deserialization to convert a string code to a ChannelType.
     *
     * @param code the code string (e.g., "EMAIL")
     * @return the corresponding ChannelType enum
     * @throws IllegalArgumentException if the code does not match any known channel
     */
    @JsonCreator
    public static ChannelType fromCode(String code) {
        for (ChannelType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown channel type: " + code);
    }

    /**
     * Determines if the channel is typically used for real-time delivery.
     *
     * @return true if real-time, false otherwise
     */
    public boolean isRealTime() {
        return this == PUSH || this == IN_APP || this == SMS;
    }

    /**
     * Determines if the channel is digital (non-physical).
     *
     * @return true if the channel is digital
     */
    public boolean isDigital() {
        return this != LETTER && this != VOICE && this != FAX;
    }

    /**
     * Indicates if the channel supports rich content (HTML or JSON).
     *
     * @return true if the channel supports rich content
     */
    public boolean supportsRichContent() {
        return this == EMAIL || this == IN_APP || this == PUSH;
    }
}

