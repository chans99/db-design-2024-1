plugins {
    val kotlinVersion = "1.9.23"

    // sentry
    id("io.sentry.jvm.gradle") version "4.12.0"

    // spring boot
    id("org.springframework.boot") version "3.2.5" apply false
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false
    kotlin("plugin.allopen") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    id("java-test-fixtures")
    id("java-library")
    idea

//    /** ktlint **/
//    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"

    /** jacoco **/
    id("jacoco")

}


sentry {
    // Generates a JVM (Java, Kotlin, etc.) source bundle and uploads your source code to Sentry.
    // This enables source context, allowing you to see your source
    // code as part of your stack traces in Sentry.
    includeSourceContext = true

    org = "my4cut"
    projectName = "java-spring-boot"
    authToken = "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"
}

java.sourceCompatibility = JavaVersion.VERSION_21

idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}

allprojects {

    repositories {
        mavenCentral()
    }

}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    tasks.getByName("bootJar") {
        enabled = false
    }

    tasks.getByName("jar") {
        enabled = true
    }
//    apply(plugin = "org.jlleitschuh.gradle.ktlint")


    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

    }
}