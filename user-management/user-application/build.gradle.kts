plugins {
    id("java")
}

group = "com.furkan"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Layer
    implementation(project(":user-management:user-domain"))

    // For Exception
    implementation(project(":common:exception"))

    implementation(project(":common:rabbitmq"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}