import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.jetbrainsCompose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
        google()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("com.guardsquare:proguard-gradle:7.2.0")
    }
}

plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("kapt") version "1.7.20"
    id("org.jetbrains.compose") version "1.2.2"
    id("java")
    id("idea")
    id("org.jlleitschuh.gradle.ktlint") version "10.1.0"
}

val versionCode = "1.0.0"
group = "de.mobanisto"
version = versionCode

allprojects {
    repositories {
        mavenCentral()
        jetbrainsCompose()
        google()
    }
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.materialIconsExtended)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.register<proguard.gradle.ProGuardTask>("minify") {
    val packageUberJarForCurrentOS by tasks.getting
    dependsOn(packageUberJarForCurrentOS)
    val files = packageUberJarForCurrentOS.outputs.files
    injars(files)
    outjars(files.map { file -> File(file.parentFile, "${file.nameWithoutExtension}.min.jar") })

    val library = if (System.getProperty("java.version").startsWith("1.")) "lib/rt.jar" else "jmods"
    libraryjars("${System.getProperty("java.home")}/$library")

    configuration("proguard-rules.pro")
}

compose.desktop {
    application {
        mainClass = "de.mobanisto.test.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Deb, TargetFormat.Msi)
            packageName = "Test"
            description = "Test project"
            vendor = "Mobanisto"
            copyright = "2022 Mobanisto"
            licenseFile.set(project.file("LICENSE.txt"))
            // The files contained in this directory will be available after installing using
            // the native installation package.
            // On Linux, they will be located at /opt/test-project/lib/app/resources
            // On Windows, they will be located at C:\Program Files\Test\app\resources
            appResourcesRootDir.set(project.layout.projectDirectory.dir("src/appdata"))
            linux {
                packageName = "test-project"
                iconFile.set(project.file("src/main/resources/logo.png"))
                debPackageVersion = versionCode
                appCategory = "comm"
                menuGroup = "Network;Chat;InstantMessaging;"
            }
            windows {
                iconFile.set(project.file("src/main/resources/logo.ico"))
                upgradeUuid = "FD4C39EF-F3A3-4E72-8D4F-41AC5CF6E1AA"
            }
        }
    }
}
