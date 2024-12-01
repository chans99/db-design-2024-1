import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation(project(":db"))
    implementation(project(":clients"))
}


tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}