# 🚀 jain-slee.diameter-NG 7.4.3 - Diameter Resource Adaptors

> **JAIN SLEE Diameter | 15 Interfaces | SLg Fixed | Bundle Plugin Updated**

[![jain-slee.diameter-NG](https://img.shields.io/badge/jain--slee.diameter--NG-7.4.3-blue.svg)](https://github.com/nhanth87/jain-slee.diameter)
[![Interfaces](https://img.shields.io/badge/Interfaces-15-green.svg)](https://github.com/nhanth87/jain-slee.diameter)
[![Felix](https://img.shields.io/badge/Flex%20Bundle-5.1.9-orange.svg)](https://felix.apache.org)

---

## 💡 Overview

**jain-slee.diameter-NG** provides comprehensive Diameter Resource Adaptors for JAIN SLEE, supporting 15+ interfaces including Gx, Rx, S6a, S13, SLg, and more.

### Supported Diameter Interfaces

| Interface | 3GPP Standard | Use Case |
|-----------|---------------|----------|
| **Gx** | TS 29.212 | Policy & Charging Control (PCC) |
| **Rx** | TS 29.214 | Policy for Multimedia Services |
| **S6a** | TS 29.272 | HSS-MAA Interface (LTE Auth) |
| **S13** | TS 29.272 | EIR Interface (IMEI check) |
| **SLg** | TS 29.172 | Location Services (LCS) |
| **SLh** | TS 29.173 | LCS Routing |
| **Sh** | TS 29.328 | User Data handling |
| **Rf** | TS 32.240 | Offline Charging |
| **Ro** | TS 32.240 | Online Charging (OCS) |
| **Cx/Dx** | TS 29.228 | IMS User Registration |
| **Gq** | TS 29.209 | Policy for IMS |
| **CCA** | RFC 4006 | Credit Control |

---

## 🎯 Key Features

### 1. SLg Interface Fixed
```java
// Fixed: InternalException handling
public ProvideLocationRequest createProvideLocationRequest() {
    try {
        return (ProvideLocationRequest) createSlgMessage(...);
    } catch (InternalException e) {
        throw new IllegalStateException("Failed to create message", e);
    }
}
```

### 2. Felix Bundle Plugin 5.1.9
```xml
<!-- Updated for Java 11+ compatibility -->
<plugin>
    <groupId>org.apache.felix</groupId>
    <artifactId>maven-bundle-plugin</artifactId>
    <version>5.1.9</version>
</plugin>
```

### 3. Multi-Interface Support
- 15 Diameter interfaces in single package
- Consistent RA API across all interfaces
- Shared Diameter base functionality

---

## 📊 Architecture

```
┌─────────────────────────────────────────────────────┐
│           JAIN SLEE Container                        │
├─────────────────────────────────────────────────────┤
│  Diameter Base RA  │  Gx RA  │  Rx RA  │  S6a RA    │
│       │            │    │    │    │    │    │       │
│       └────────────┴────┴────┴────┴────┴────┘       │
│              │                                       │
│  ┌───────────┼───────────┐                          │
│  │           │           │                          │
│ S13 RA    SLg RA     SLh RA                        │
│  │           │           │                          │
│  └───────────┴───────────┘                          │
│              │                                       │
│  ┌───────────▼───────────┐                          │
│  │     jdiameter MUX     │                          │
│  │   (Diameter Stack)    │                          │
│  └────────────────────────┘                          │
└─────────────────────────────────────────────────────┘
```

---

## 🛠️ Building

### Maven Build
```bash
mvn clean install -DskipTests -Dcheckstyle.skip=true
```

### Modules (100+)
- `diameter-base` - Common Diameter functionality
- `diameter-gx` - Gx interface RA
- `diameter-rx` - Rx interface RA
- `diameter-s6a` - S6a interface RA
- `diameter-s13` - S13 interface RA
- `diameter-slg` - SLg interface RA (Fixed!)
- `diameter-slh` - SLh interface RA
- `hss-client` - HSS client enabler

---

## 🔧 Key Fixes

### SLgMessageFactoryImpl Fix
```java
// Problem: Interface doesn't declare throws InternalException
// Solution: Wrap in RuntimeException

public ProvideLocationRequest createProvideLocationRequest() {
    try {
        return (ProvideLocationRequest) createSlgMessage(...);
    } catch (InternalException e) {
        throw new IllegalStateException(
            "Failed to create Provide-Location-Request", e);
    }
}
```

### SupportedFeaturesAvpImpl Import Fix
```java
// Fixed imports in:
// - ProvideLocationRequestImpl.java
// - ProvideLocationAnswerImpl.java
// - LocationRequestImpl.java
// - LocationAnswerImpl.java

// Changed from:
// import static ...SLgAvpCodes.SupportedFeaturesAvpImpl;

// To:
// import ...SupportedFeaturesAvpImpl;
```

### _3GPP_VENDOR_ID Constant
```java
// Added constant instead of importing from removed class
private static final long _3GPP_VENDOR_ID = 10415L;
```

---

## 📦 Felix Bundle Plugin Update

### Why Upgrade?
- Old: 2.3.4 (incompatible with Java 11+)
- New: 5.1.9 (Java 11/17/21 compatible)

### Changes
```xml
<!-- In all 54 resource module poms -->
<plugin>
    <groupId>org.apache.felix</groupId>
    <artifactId>maven-bundle-plugin</artifactId>
    <version>5.1.9</version>
</plugin>
```

---

## 📝 Changelog

### v7.4.3 (Current)
- ✅ Fixed: SLgMessageFactoryImpl InternalException handling
- ✅ Fixed: SupportedFeaturesAvpImpl imports
- ✅ Updated: Felix Bundle Plugin 2.3.4 → 5.1.9

### v7.4.2
- ✅ Version bump

### v7.4.1
- ✅ Build fixes

### v7.4.0
- ✅ Initial migration fixes

---

## 📄 License

GNU Lesser General Public License v2.1

---

**Maintained by:** nhanth87  
**Diameter Stack:** Restcomm jdiameter  
**Bundle Plugin:** Apache Felix 5.1.9  
**For:** 4G/5G Policy, Charging, and Location Services
