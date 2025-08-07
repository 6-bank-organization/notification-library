package com.sixbank.notificationlibrary.shared.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sixbank.notificationlibrary.shared.enums.ChannelType;
import com.sixbank.notificationlibrary.shared.enums.NotificationStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * Immutable value object representing a single delivery attempt of a notification.
 * <p>
 * This class captures details such as the attempt number, communication channel,
 * provider used, delivery status, timestamps, error information, and scheduling for retries.
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 * DeliveryAttempt attempt = DeliveryAttempt.builder()
 *     .attemptNumber(1)
 *     .channelType(ChannelType.EMAIL)
 *     .provider("SendGrid")
 *     .status(NotificationStatus.FAILED)
 *     .attemptedAt(Instant.now())
 *     .errorMessage("SMTP timeout")
 *     .errorCode("TIMEOUT_504")
 *     .externalId("external-msg-id-123")
 *     .durationMs(1500L)
 *     .nextRetryAt(Instant.now().plusSeconds(300))
 *     .build();
 *
 * if (attempt.isSuccessful()) {
 *     // process success logic
 * } else if (attempt.isFailed()) {
 *     // handle retry or error reporting
 * }
 * </pre>
 *
 * <p>This object is useful for audit logs, retry logic, and diagnostics in notification delivery workflows.</p>
 */
public class DeliveryAttempt {

    private final int attemptNumber;
    private final ChannelType channelType;
    private final String provider;
    private final NotificationStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private final Instant attemptedAt;

    private final String errorMessage;
    private final String errorCode;
    private final String externalId;
    private final Long durationMs;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private final Instant nextRetryAt;

    private DeliveryAttempt(Builder builder) {
        this.attemptNumber = builder.attemptNumber;
        this.channelType = builder.channelType;
        this.provider = builder.provider;
        this.status = builder.status;
        this.attemptedAt = builder.attemptedAt;
        this.errorMessage = builder.errorMessage;
        this.errorCode = builder.errorCode;
        this.externalId = builder.externalId;
        this.durationMs = builder.durationMs;
        this.nextRetryAt = builder.nextRetryAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int attemptNumber;
        private ChannelType channelType;
        private String provider;
        private NotificationStatus status;
        private Instant attemptedAt;
        private String errorMessage;
        private String errorCode;
        private String externalId;
        private Long durationMs;
        private Instant nextRetryAt;

        public Builder attemptNumber(int attemptNumber) {
            this.attemptNumber = attemptNumber;
            return this;
        }

        public Builder channelType(ChannelType channelType) {
            this.channelType = channelType;
            return this;
        }

        public Builder provider(String provider) {
            this.provider = provider;
            return this;
        }

        public Builder status(NotificationStatus status) {
            this.status = status;
            return this;
        }

        public Builder attemptedAt(Instant attemptedAt) {
            this.attemptedAt = attemptedAt;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder externalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        public Builder durationMs(Long durationMs) {
            this.durationMs = durationMs;
            return this;
        }

        public Builder nextRetryAt(Instant nextRetryAt) {
            this.nextRetryAt = nextRetryAt;
            return this;
        }

        public DeliveryAttempt build() {
            return new DeliveryAttempt(this);
        }
    }

    // Getters
    public int getAttemptNumber() { return attemptNumber; }
    public ChannelType getChannelType() { return channelType; }
    public String getProvider() { return provider; }
    public NotificationStatus getStatus() { return status; }
    public Instant getAttemptedAt() { return attemptedAt; }
    public String getErrorMessage() { return errorMessage; }
    public String getErrorCode() { return errorCode; }
    public String getExternalId() { return externalId; }
    public Long getDurationMs() { return durationMs; }
    public Instant getNextRetryAt() { return nextRetryAt; }

    public boolean isSuccessful() {
        return status == NotificationStatus.DELIVERED;
    }

    public boolean isFailed() {
        return status == NotificationStatus.FAILED || status == NotificationStatus.BOUNCED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryAttempt that = (DeliveryAttempt) o;
        return attemptNumber == that.attemptNumber &&
                channelType == that.channelType &&
                Objects.equals(provider, that.provider) &&
                Objects.equals(attemptedAt, that.attemptedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attemptNumber, channelType, provider, attemptedAt);
    }
}
