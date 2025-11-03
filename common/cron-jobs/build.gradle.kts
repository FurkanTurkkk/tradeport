plugins {
    id("java")
}

group = "com.furkan"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Web (RestTemplate, WebClient)
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.6")

    // JAXB (XML <-> Java nesnesi dönüşümü için)
    implementation("org.glassfish.jaxb:jaxb-runtime:4.0.2")

    // JSON (otomatik geldi aslında, ama Jackson kullanımı için)
    implementation("com.fasterxml.jackson.core:jackson-databind")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.5.4")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}