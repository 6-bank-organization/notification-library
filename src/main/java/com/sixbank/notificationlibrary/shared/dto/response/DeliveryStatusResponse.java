package com.sixbank.notificationlibrary.shared.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sixbank.notificationlibrary.shared.enums.NotificationStatus;
import com.sixbank.notificationlibrary.shared.model.DeliveryAttempt;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * Response DTO that encapsulates the delivery status information of a notification.
 * It contains summary counts of attempts, success/failure stats, timestamps,
 * and details of individual delivery attempts.
 */
public class DeliveryStatusResponse {

    /** Unique identifier of the notification */
    private String notificationId;

    /** Customer ID receiving the notification */
    private String customerId;

    /** Overall notification delivery status */
    private NotificationStatus overallStatus;

    /** Total number of delivery attempts made */
    private int totalAttempts;

    /** Count of successful delivery attempts */
    private int successfulDeliveries;

    /** Count of failed delivery attempts */
    private int failedDeliveries;

    /** Timestamp of the most recent delivery attempt */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant lastAttemptAt;

    /** Timestamp for the next retry attempt, if any */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant nextRetryAt;

    /** List of individual delivery attempts */
    private List<DeliveryAttempt> deliveryAttempts;

    /** Last error message encountered during delivery attempts */
    private String lastError;

    /**
     * Default constructor.
     */
    public DeliveryStatusResponse() {}

    /**
     * Constructs a DeliveryStatusResponse with the given notification ID, customer ID,
     * overall status, and list of delivery attempts.
     * It calculates summary statistics such as total attempts, success/failure counts,
     * last attempt time, next retry time, and last error message.
     *
     * @param notificationId the notification's unique ID
     * @param customerId the customer ID receiving the notification
     * @param overallStatus the aggregated status of the notification delivery
     * @param deliveryAttempts list of detailed delivery attempt records
     */
    public DeliveryStatusResponse(String notificationId, String customerId,
                                  NotificationStatus overallStatus, List<DeliveryAttempt> deliveryAttempts) {
        this.notificationId = notificationId;
        this.customerId = customerId;
        this.overallStatus = overallStatus;
        this.deliveryAttempts = deliveryAttempts;
        this.totalAttempts = deliveryAttempts != null ? deliveryAttempts.size() : 0;

        if (deliveryAttempts != null && !deliveryAttempts.isEmpty()) {
            // Count successful deliveries
            this.successfulDeliveries = (int) deliveryAttempts.stream()
                    .filter(DeliveryAttempt::isSuccessful)
                    .count();

            // Count failed deliveries
            this.failedDeliveries = (int) deliveryAttempts.stream()
                    .filter(DeliveryAttempt::isFailed)
                    .count();

            // Find the most recent delivery attempt timestamp
            deliveryAttempts.stream()
                    .map(DeliveryAttempt::getAttemptedAt)
                    .filter(Objects::nonNull)
                    .max(Instant::compareTo)
                    .ifPresent(instant -> this.lastAttemptAt = instant);

            // Find the earliest next retry timestamp (next scheduled retry)
            deliveryAttempts.stream()
                    .map(DeliveryAttempt::getNextRetryAt)
                    .filter(Objects::nonNull)
                    .min(Instant::compareTo)
                    .ifPresent(instant -> this.nextRetryAt = instant);

            // Get the error message from the latest failed attempt, if any
            deliveryAttempts.stream()
                    .filter(DeliveryAttempt::isFailed)
                    .reduce((first, second) -> second)  // Get last failed attempt
                    .map(DeliveryAttempt::getErrorMessage)
                    .ifPresent(error -> this.lastError = error);
        }
    }

    // Getters and setters

    public String getNotificationId() { return notificationId; }
    public void setNotificationId(String notificationId) { this.notificationId = notificationId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public NotificationStatus getOverallStatus() { return overallStatus; }
    public void setOverallStatus(NotificationStatus overallStatus) { this.overallStatus = overallStatus; }

    public int getTotalAttempts() { return totalAttempts; }
    public void setTotalAttempts(int totalAttempts) { this.totalAttempts = totalAttempts; }

    public int getSuccessfulDeliveries() { return successfulDeliveries; }
    public void setSuccessfulDeliveries(int successfulDeliveries) { this.successfulDeliveries = successfulDeliveries; }

    public int getFailedDeliveries() { return failedDeliveries; }
    public void setFailedDeliveries(int failedDeliveries) { this.failedDeliveries = failedDeliveries; }

    public Instant getLastAttemptAt() { return lastAttemptAt; }
    public void setLastAttemptAt(Instant lastAttemptAt) { this.lastAttemptAt = lastAttemptAt; }

    public Instant getNextRetryAt() { return nextRetryAt; }
    public void setNextRetryAt(Instant nextRetryAt) { this.nextRetryAt = nextRetryAt; }

    public List<DeliveryAttempt> getDeliveryAttempts() { return deliveryAttempts; }
    public void setDeliveryAttempts(List<DeliveryAttempt> deliveryAttempts) { this.deliveryAttempts = deliveryAttempts; }

    public String getLastError() { return lastError; }
    public void setLastError(String lastError) { this.lastError = lastError; }

    /**
     * Returns true if there has been at least one successful delivery.
     */
    public boolean hasSuccessfulDelivery() {
        return successfulDeliveries > 0;
    }

    /**
     * Returns true if there have been any failed delivery attempts.
     */
    public boolean hasFailures() {
        return failedDeliveries > 0;
    }

    /**
     * Returns true if a retry is scheduled for a future time.
     */
    public boolean willRetry() {
        return nextRetryAt != null && nextRetryAt.isAfter(Instant.now());
    }
}

