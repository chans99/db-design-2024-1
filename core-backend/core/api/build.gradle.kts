dependencies {
    implementation(project(":db"))
    implementation(project(":core:enums"))
    implementation(project(":infra"))
    implementation(project(":clients"))
    implementation(project(":batch"))

    // JWT
    implementation("com.auth0:java-jwt:4.4.0")

    // Security
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    // sentry
    implementation("io.sentry:sentry-spring-boot-starter-jakarta:7.16.0")

    // h2
    runtimeOnly("com.h2database:h2")
}


tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

