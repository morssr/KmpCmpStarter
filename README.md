# KmpCmpStarter

KmpCmpStarter is an easy-to-use Kotlin Multiplatform (KMP) Compose Multiplatform (CMP) template aimed at simplifying the process of creating new multiplatform applications. The project consists of two branches:

## Branches

### 1. `main` - Template Branch
This branch serves as a foundational template, providing essential building blocks for quickly starting new KMP projects. It includes setup for:
- Shared business logic
- Shared UI using Compose Multiplatform
- Dependency Injection using Koin
- Networking using Ktor
- Local data storage with SQLDelight
- Multiplatform UI development (Compose for Android, SwiftUI for iOS)

### 2. `sample-app` - Example Application
This branch demonstrates how to practically utilize the template by showcasing:
- Implementation of simple networking requests and responses
- Local storage integration
- Basic UI components and navigation patterns
- Interaction between shared business logic and platform-specific UI
- Use of shared UI components built with Compose Multiplatform

## Project Structure
- **Shared Module**: Contains shared business logic, shared UI components using Compose Multiplatform, networking, data repositories, and database integration.
- **Android Module (`androidApp`)**: Built using Jetpack Compose and integrates the shared UI.
- **iOS Module (`iosApp`)**: Built using SwiftUI and connects to the shared logic.

## Quick Start

Clone the repository:
```bash
git clone https://github.com/morssr/KmpCmpStarter.git
```

Switch branches based on your need:
```bash
# For the template branch
git checkout main

# For the sample application branch
git checkout sample-app
```

Open the project:
- **Android**: Open the project in Android Studio.
- **iOS**: Open the `iosApp` directory in Xcode.

## Resources
- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- [Ktor](https://ktor.io/)
- [Koin](https://insert-koin.io/)
- [SQLDelight](https://cashapp.github.io/sqldelight/)

## Contributions
Feel free to open issues or submit pull requests for improvements or feature additions.

---

Happy coding!
