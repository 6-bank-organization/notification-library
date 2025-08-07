# Notification Delivery Library

A Java library providing core models, enums, and utilities for building robust notification delivery systems.  
It supports multi-channel notification providers (email, SMS, push, voice, webhook), delivery status tracking, event processing, and integration with Kafka-based event streaming.

---

## Features

- **Multi-channel notification support** — Email, SMS, Push, Voice, Webhook  
- **Predefined channel providers** — e.g., SendGrid, AWS SES, Twilio, Firebase  
- **Delivery status modeling** with rich enums and delivery attempt tracking  
- **Event DTOs** for Kafka messaging integration  
- **Validation utilities** for customer IDs and event data  
- **JSON serialization/deserialization utilities** supporting Java time types  
- **Retry and backoff calculation helpers**

---

## Getting Started

### Maven dependency

_Add this library to your project `pom.xml` (replace with your repo coordinates):_

```xml
<dependency>
  <groupId>com.company.notification</groupId>
  <artifactId>notification-library</artifactId>
  <version>1.0.0</version>
</dependency>
````

### Gradle dependency

```groovy
implementation 'com.company.notification:notification-library:1.0.0'
```

---

## Usage

### ChannelProvider Enum

Represents external service providers for notification delivery, supporting one or multiple notification channels.

```java
import com.company.notification.shared.enums.ChannelProvider;
import com.company.notification.shared.enums.ChannelType;

public class Example {
    public static void main(String[] args) {
        ChannelProvider provider = ChannelProvider.SENDGRID;
        
        System.out.println("Provider code: " + provider.getCode());                // SENDGRID
        System.out.println("Supports EMAIL? " + provider.supports(ChannelType.EMAIL)); // true
        System.out.println("Supports SMS? " + provider.supports(ChannelType.SMS));     // false
    }
}
```

### Delivery Status

Use the `DeliveryStatus` enum to represent and track delivery attempts and states such as `PENDING`, `DELIVERED`, `FAILED`, or `BOUNCED`.

### NotificationEventDto

Represents a notification event to be sent over Kafka with payload validation and metadata support.

---

## Configuration Constants

The library provides constants for Kafka topic names, headers, retry policies, and rate limiting defaults via `NotificationConstants` and `TopicConstants`.

---

## Validation

Customer IDs and other fields are validated with Jakarta Validation annotations and custom validators, ensuring correctness before processing.

---

## JSON Utilities

The library includes utility methods for JSON serialization/deserialization and conversion between JSON and Java objects, supporting Java Time types.

---

## Contributing

Contributions are welcome! Please open issues and pull requests to improve features, fix bugs, or enhance documentation.

---

## License

MIT License © SIX Bank Engineering

---

## Contact

For questions or support, contact the maintainers at \[[support@yourcompany.com](mailto:nestorabiawuh@gmail.com)].
