import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    val awsSdkVersion = "2.27.17"
    implementation("software.amazon.awssdk:bom:$awsSdkVersion")
    implementation("software.amazon.awssdk:s3:$awsSdkVersion")

    // spring reactive
    implementation("org.springframework.boot:spring-boot-starter-webflux")
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}