plugins {
	java
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.furkan"
version = "0.0.1-SNAPSHOT"
description = "E-commerce project with modular monolith and Hexagonal Architecture"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

repositories {
	mavenCentral()
}

dependencies {
    // Spring dependencies
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.postgresql:postgresql:42.7.7")

    // Batch
    implementation(project(":common:cron-jobs"))

    // User-management dependencies
    implementation(project(":user-management:user-api"))
    implementation(project(":user-management:user-infra"))

    // Customer-management dependencies
    implementation(project(":customer-management:customer-api"))
    implementation(project(":customer-management:customer-infra"))

	// Product-management dependencies
    implementation(project(":product-management:product-api"))
    implementation(project(":product-management:product-infra"))

    // Category-management dependencies
    implementation(project(":category-management:category-api"))
    implementation(project(":category-management:category-infra"))

    // Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
