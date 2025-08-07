package com.sixbank.notificationlibrary.shared.validations;

import com.sixbank.notificationlibrary.shared.constants.NotificationConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Validator for customer ID fields annotated with {@link ValidCustomerId}.
 * <p>
 * This validator checks whether a given customer ID string:
 * <ul>
 *     <li>Is not null or blank</li>
 *     <li>Is within the allowed length range defined in {@link NotificationConstants}</li>
 *     <li>Matches a specific pattern: only alphanumeric characters, underscores, and hyphens</li>
 * </ul>
 * </p>
 *
 * Example usage:
 * <pre>
 * public class CustomerRequest {
 *
 *     {@literal @}ValidCustomerId
 *     private String customerId;
 *
 *     // other fields...
 * }
 * </pre>
 */
public class CustomerIdValidator implements ConstraintValidator<ValidCustomerId, String> {

    /**
     * Regex pattern allowing only alphanumeric characters, underscores, and hyphens.
     */
    private static final Pattern CUSTOMER_ID_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$");

    /**
     * Validates the given customer ID based on format and length constraints.
     *
     * @param customerId the customer ID string to validate
     * @param context    the validator context (not used directly here)
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isValid(String customerId, ConstraintValidatorContext context) {
        if (customerId == null || customerId.trim().isEmpty()) {
            return false;
        }

        String trimmed = customerId.trim();

        // Check minimum and maximum allowed length from NotificationConstants
        if (trimmed.length() < NotificationConstants.MIN_CUSTOMER_ID_LENGTH ||
                trimmed.length() > NotificationConstants.MAX_CUSTOMER_ID_LENGTH) {
            return false;
        }

        // Ensure the customer ID matches the allowed pattern
        return CUSTOMER_ID_PATTERN.matcher(trimmed).matches();
    }
}

