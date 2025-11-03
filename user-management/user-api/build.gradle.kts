plugins {
    id("java")
}

group = "com.furkan"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web:3.5.6")
    implementation("org.springframework.boot:spring-boot-starter-security:3.5.6") // AuthenticationPrincipal anotasyonu için

    implementation(project(":common:exception"))
    implementation(project(":common:rabbitmq"))
    implementation(project(":common:security"))

    implementation(project(":user-management:user-domain"))
    implementation(project(":user-management:user-application"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}