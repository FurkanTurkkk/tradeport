plugins {
    id("java")
}

group = "com.furkan"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security:3.5.6")

    // Aspect
    implementation("org.springframework:spring-aop:6.1.5")
    implementation("org.aspectj:aspectjweaver:1.9.22")

    // Servlet API
    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // Test
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}