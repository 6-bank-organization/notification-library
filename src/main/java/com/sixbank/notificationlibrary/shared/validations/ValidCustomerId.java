package com.sixbank.notificationlibrary.shared.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Custom annotation for validating customer IDs.
 * <p>
 * This annotation can be applied to fields or method parameters to ensure that the value
 * provided matches the expected format of a valid customer ID, as defined in the
 * {@link CustomerIdValidator} class.
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 * public class NotificationRequest {
 *     &#64;ValidCustomerId
 *     private String customerId;
 *     // other fields...
 * }
 * </pre>
 *
 * <p>The validation logic is implemented in the {@code CustomerIdValidator} class.</p>
 *
 * <p>Make sure that the validation logic inside {@code CustomerIdValidator} handles null checks
 * and any specific format rules (e.g., UUID, alphanumeric, etc.).</p>
 *
 * @see CustomerIdValidator
 */
@Documented
@Constraint(validatedBy = CustomerIdValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCustomerId {

    /**
     * The default message to be returned when validation fails.
     *
     * @return the error message
     */
    String message() default "Invalid customer ID format";

    /**
     * Allows the specification of validation groups to which this constraint belongs.
     *
     * @return array of groups
     */
    Class<?>[] groups() default {};

    /**
     * Can be used by clients to assign custom payload objects to the constraint.
     *
     * @return array of payload types
     */
    Class<? extends Payload>[] payload() default {};
}
