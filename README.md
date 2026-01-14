# ComposeLoadingDialog

A collection of beautiful and customizable loading dialogs for Jetpack Compose, including the iconic KITT running light
from Knight Rider!

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Dialog Types](#dialog-types)
- [Screenshots](#screenshots)

## Overview

This library provides a set of ready-to-use loading dialogs for your Android applications built with Jetpack Compose.
The dialogs are highly customizable and easy to integrate into any project.

## Features

- 3 different loading dialog styles: KITT, CIRCLE, and LINE
- Progress indicators with customizable appearance
- Info dialog with up to three buttons
- Easy to implement and customize
- Lightweight and efficient

## Installation

### Step 1: Add the JitPack repository

Add the following to your project's `settings.gradle` file:

```gradle
maven {
    name = "nexus421Maven"
    url = uri("https://maven.kickner.bayern/<repository>")
}
```

### Step 2: Add the dependency

Add the dependency to your module's `build.gradle` file:

```gradle
implementation("bayern.kickner:ComposeLoadingDialog:2.1.2")
```

## Usage

### Basic Loading Dialog

```kotlin
LoadingDialog(
    show = showDialog,
    onDismissRequest = { showDialog = false },
    type = LoadingDialogType.KITT // Default type
)
```

### Loading Dialog with Progress

```kotlin
LoadingDialogWithProgress(
    show = showDialog,
    progress = 0.75f, // 75% progress
    onDismissRequest = { showDialog = false }
)
```

### Info Dialog

```kotlin
InfoDialog(
    show = showDialog,
    title = "Information",
    message = "This is an example message",
    onDismissRequest = { showDialog = false },
    positiveButton = "OK" to { /* action */ }
)
```

## Dialog Types

### KITT Loading Dialog

The KITT loading dialog features a running light animation inspired by the iconic KITT car from Knight Rider. This is
the default and my personal favorite!

### CIRCLE Loading Dialog

A circular loading animation that's perfect for most loading scenarios.

### LINE Loading Dialog

A linear loading animation for a more traditional loading experience.

## Screenshots

<div align="center">
  <img src="https://user-images.githubusercontent.com/24206344/162612627-b1b85866-0455-4742-aba2-0719dd828ce3.png" width="250" alt="KITT Loading Dialog" />
  <img src="https://user-images.githubusercontent.com/24206344/162612629-3a94bb8b-e0fb-4833-a5c3-fbada6f83cb6.png" width="250" alt="Circle Loading Dialog" />
  <img src="https://user-images.githubusercontent.com/24206344/162612630-43902a44-4396-4e72-acce-d7447a0337fc.png" width="250" alt="Line Loading Dialog" />
</div>

<div align="center">
  <img src="https://user-images.githubusercontent.com/24206344/162612632-c0df8ea7-0e3d-4387-b6e9-96b882bb4b57.png" width="250" alt="Progress Dialog" />
  <img src="https://user-images.githubusercontent.com/24206344/162612633-d9284741-49a9-44ac-9b0b-7d284fd95a90.png" width="250" alt="Info Dialog" />
</div>

---

For more detailed information, check the code documentation. Enjoy using these dialogs in your projects!
