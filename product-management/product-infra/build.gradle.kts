plugins {
    id("java")
}

group = "com.furkan"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.5.4")
    implementation("org.springframework.boot:spring-boot-starter-security:3.5.6")

    implementation(project(":product-management:product-domain"))
    implementation(project(":common:exception"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}