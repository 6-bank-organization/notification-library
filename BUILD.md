# Notification Shared Library

This is a shared Java library containing common classes, enums, DTOs, and utilities used across all notification-related microservices.

## Building the Library

### Prerequisites
- Java 17+
- Maven 3.8+

### Build Commands

```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package the library
mvn package

# Install to local repository
mvn install

# Deploy to remote repository
mvn deploy
```

### Including in Other Projects

Add this dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.company.notification</groupId>
    <artifactId>notification-shared-lib</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage Examples

### Creating Notification Events

```java
import com.company.notification.shared.dto.request.CreateNotificationRequest;
import com.company.notification.shared.enums.NotificationEventType;
import com.company.notification.shared.enums.NotificationPriority;

// Create a notification request
CreateNotificationRequest request = CreateNotificationRequest.builder()
    .eventType(NotificationEventType.TRANSACTION_ALERT)
    .customerId("cust_123456")
    .priority(NotificationPriority.HIGH)
    .sourceService("payment-service")
    .payload(Map.of(
        "amount", 1500.00,
        "merchant", "Amazon",
        "currency", "USD"
    ))
    .build();
```

### Working with Domain Events

```java
import com.company.notification.shared.events.NotificationCreatedEvent;

// Create and publish domain event
NotificationCreatedEvent event = new NotificationCreatedEvent(
    notificationId,
    customerId,
    NotificationEventType.TRANSACTION_ALERT,
    NotificationPriority.HIGH,
    "payment-service",
    payloadMap
);

// Event will have auto-generated ID, timestamp, and correlation ID
String eventId = event.getEventId();
Instant occurredAt = event.getOccurredAt();
```

### Using Utility Classes

```java
import com.company.notification.shared.util.NotificationUtils;
import com.company.notification.shared.util.JsonUtils;

// Generate IDs
String notificationId = NotificationUtils.generateNotificationId();
String eventId = NotificationUtils.generateEventId();

// JSON operations
String json = JsonUtils.toJson(request);
CreateNotificationRequest parsed = JsonUtils.fromJson(json, CreateNotificationRequest.class);

// Priority-based configuration
Duration timeout = NotificationUtils.getTimeoutForPriority(NotificationPriority.CRITICAL);
int maxRetries = NotificationUtils.getMaxRetriesForPriority(NotificationPriority.HIGH);
```

### Validation

```java
import com.company.notification.shared.validation.ValidCustomerId;

public class ServiceClass {
    
    public void processNotification(@ValidCustomerId String customerId) {
        // customerId will be automatically validated
    }
}
```

### Working with Constants

```java
import com.company.notification.shared.constants.NotificationConstants;
import com.company.notification.shared.constants.TopicConstants;

// Kafka topics
String topic = TopicConstants.Input.NOTIFICATION_EVENTS;
String outputTopic = TopicConstants.Output.NOTIFICATION_DELIVERED;

// Headers
String correlationHeader = NotificationConstants.HEADER_CORRELATION_ID;

// Timeouts and retries
Duration timeout = NotificationConstants.CRITICAL_TIMEOUT;
int maxRetries = NotificationConstants.DEFAULT_MAX_RETRIES;
```

## Key Classes and Enums

### Core Enums
- `NotificationEventType` - All supported event types (transaction, security, account, etc.)
- `NotificationPriority` - Priority levels with associated timeouts and retry configs
- `NotificationStatus` - Lifecycle status of notifications
- `ChannelType` - Available delivery channels (EMAIL, SMS, PUSH, etc.)
- `ChannelProvider` - External service providers (SendGrid, Twilio, Firebase, etc.)
- `DeliveryStatus` - Status of individual delivery attempts

### DTOs
- `CreateNotificationRequest` - Request for creating notifications
- `NotificationResponse` - Response with notification details
- `NotificationEventDto` - Kafka event payload
- `DeliveryStatusResponse` - Delivery tracking information

### Domain Events
- `NotificationCreatedEvent` - Fired when notification is created
- `NotificationDeliveredEvent` - Fired when successfully delivered
- `NotificationFailedEvent` - Fired when delivery fails

### Value Objects
- `CustomerInfo` - Customer data for notifications
- `DeliveryAttempt` - Details of a delivery attempt

### Utilities
- `NotificationUtils` - Helper methods for IDs, timeouts, retries
- `JsonUtils` - JSON serialization/deserialization
- `ValidationUtils` - Validation helper methods

### Constants
- `NotificationConstants` - General constants (timeouts, limits, headers)
- `TopicConstants` - Kafka topic names and configuration
- `ErrorConstants` - Error codes and messages

## Versioning Strategy

This library follows semantic versioning:
- **Major version** (1.x.x) - Breaking API changes
- **Minor version** (x.1.x) - New features, backward compatible
- **Patch version** (x.x.1) - Bug fixes, backward compatible

### Migration Guide

When upgrading major versions, check:
1. Enum value changes or additions
2. DTO field modifications
3. Method signature changes
4. Package reorganization

## Contributing

1. All changes must maintain backward compatibility for minor/patch versions
2. New event types should be added to `NotificationEventType` enum
3. New channel types require corresponding provider mappings
4. All public APIs must include JavaDoc
5. Unit tests required for new functionality

## Support

For questions or issues:
- Create GitHub issue
- Contact: notification-team@company.com