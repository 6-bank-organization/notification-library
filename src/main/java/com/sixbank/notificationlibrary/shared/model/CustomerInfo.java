package com.sixbank.notificationlibrary.shared.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

/**
 * Value object representing customer information relevant to notification delivery.
 * <p>
 * Encapsulates identifiers and contact details such as email and phone number,
 * as well as preferences like language and timezone.
 * </p>
 *
 * <p><b>Validation:</b></p>
 * <ul>
 *   <li>customerId is required and must not be blank.</li>
 *   <li>email must follow a valid email format if provided.</li>
 *   <li>phoneNumber must match E.164 international format if provided.</li>
 * </ul>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 * CustomerInfo customer = new CustomerInfo(
 *     "cus-123",
 *     "Jane",
 *     "Doe",
 *     "jane.doe@example.com",
 *     "+1234567890",
 *     "en",
 *     "America/New_York"
 * );
 * System.out.println("Notify " + customer.getFullName() + " at " + customer.getEmail());
 * </pre>
 *
 * <p>This object is typically used to hold recipient details passed to notification services or providers.</p>
 */
public class CustomerInfo {

    @NotBlank(message = "Customer ID is required")
    private final String customerId;

    private final String firstName;
    private final String lastName;

    @Email(message = "Invalid email format")
    private final String email;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private final String phoneNumber;

    private final String preferredLanguage;
    private final String timezone;

    /**
     * Constructs a CustomerInfo object.
     *
     * @param customerId Unique identifier for the customer (required).
     * @param firstName Customer's first name (optional).
     * @param lastName Customer's last name (optional).
     * @param email Customer's email address (optional, must be valid format if present).
     * @param phoneNumber Customer's phone number in E.164 format (optional).
     * @param preferredLanguage Preferred language code, defaults to "en" if null.
     * @param timezone Customer's timezone, defaults to "UTC" if null.
     */
    public CustomerInfo(String customerId, String firstName, String lastName,
                        String email, String phoneNumber, String preferredLanguage, String timezone) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.preferredLanguage = preferredLanguage != null ? preferredLanguage : "en";
        this.timezone = timezone != null ? timezone : "UTC";
    }

    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public String getTimezone() { return timezone; }

    /**
     * Returns the full name by combining first and last name, if available.
     *
     * @return Full name or whichever part is available.
     */
    public String getFullName() {
        if (firstName != null && lastName != null) {
            return firstName + " " + lastName;
        }
        return firstName != null ? firstName : lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInfo that = (CustomerInfo) o;
        return Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}

