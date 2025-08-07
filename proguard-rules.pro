# ---- Library Configuration ----

# Enable shrinking, optimization, and obfuscation
-dontoptimize
-dontpreverify
-repackageclasses ''
-allowaccessmodification
-renamesourcefileattribute SourceFile
-keepattributes *Annotation*,EnclosingMethod,InnerClasses,Signature,Exceptions,Deprecated,SourceFile,LineNumberTable,LocalVariableTable

# ---- Keep public API ----

# Keep all public classes in com.contractlibs.* that are meant to be used by library users
-keep public class com.contractlibs.dto.** { *; }
-keep public class com.contractlibs.enums.** { *; }
-keep public class com.contractlibs.utility.** { *; }

# Keep public constructors, static builders, and factory methods
-keepclassmembers class * {
    public <init>(...);
    public static * build(...);
    public static **Builder builder(...);
}

# Keep domain events (if used externally via Kafka or serialization)
-keep public class com.contractlibs.events.** { *; }

# ---- Obfuscate everything else ----

# Obfuscate class names, method names, field names — full obfuscation
-overloadaggressively
-useuniqueclassmembernames
-dontusemixedcaseclassnames
-adaptclassstrings

# Keep only the names required for reflection (e.g., if Jackson is used)
# If you use Jackson or any other JSON library:
-keepclassmembers class * {
    @com.fasterxml.jackson.annotation.* <fields>;
    @com.fasterxml.jackson.annotation.* <methods>;
}
-keepattributes RuntimeVisibleAnnotations

# (Optional) Obfuscate Kotlin metadata too
-keep class kotlin.Metadata { *; }

# ---- Optional Logging ----
# Print mapping file for traceability
-printmapping build/proguard/mapping.txt
