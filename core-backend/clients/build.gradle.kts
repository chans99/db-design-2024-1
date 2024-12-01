import org.springframework.boot.gradle.tasks.bundling.BootJar


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    // JWT
    implementation("com.auth0:java-jwt:4.4.0")

}


tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}