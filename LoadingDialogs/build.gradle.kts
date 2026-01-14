plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.0"
}

android {
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        targetSdk = 36

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    publishing {
        singleVariant("release") {
            // Optional: mit Sources und Javadoc
            withSourcesJar()
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    namespace = "bayern.kickner.loadingdialogs"
}

composeCompiler {
    enableStrongSkippingMode.set(true)
    reportsDestination.set(layout.buildDirectory.dir("compose_compiler"))
}

dependencies {
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")

    implementation(platform("androidx.compose:compose-bom:2025.12.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3-android")
    debugImplementation("androidx.compose.ui:ui-tooling:1.10.0")
}

kotlin {
    jvmToolchain(17)
}

afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "nexus421Maven"
                url = uri("https://maven.kickner.bayern/releases")
                credentials(PasswordCredentials::class)
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
        }
        publications {
            create<MavenPublication>("maven") {
                groupId = "bayern.kickner"
                artifactId = "ComposeLoadingDialog"
                version = "2.1.2"
                from(components["release"])
            }
        }
    }
}