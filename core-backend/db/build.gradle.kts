import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation(project(":core:enums"))

    // postgresql
    runtimeOnly("org.postgresql:postgresql")
}

tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}