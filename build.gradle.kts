plugins {
	java
	id("jacoco")
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.app"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val mapstructVersion = "1.5.5.Final"
val mapstructProcessorVersion = "1.5.5.Final"
val lombokMapstructBindingVersion = "0.2.0"
val awaitility = "4.2.0"
val openApiWebMvcUiVersion = "2.0.2"
val swaggerUiVersion = "5.18.0"
val swaggerVersion = "2.2.8"

extra["springCloudVersion"] = "2024.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.mapstruct:mapstruct:${mapstructVersion}")
	implementation("org.springframework:spring-context-support")
	implementation("com.github.ben-manes.caffeine:caffeine")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("org.webjars:swagger-ui:${swaggerUiVersion}")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${openApiWebMvcUiVersion}")
	implementation("io.swagger.core.v3:swagger-annotations:${swaggerVersion}")
	implementation ("org.springframework.retry:spring-retry")
	implementation ("org.springframework.boot:spring-boot-starter-aop")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructProcessorVersion")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBindingVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.awaitility:awaitility:$awaitility")

}
dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}
tasks.withType<Test> {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	reports {
		xml.required.set(true)
		html.required.set(true)
	}
}
tasks.getByName<Jar>("jar") { enabled = false }
jacoco {
	toolVersion = "0.8.11"
}

val jacocoExclude = listOf("com/app/domain/**", "com/app/infrastructure/restapi/metadata/**", "com/app/infrastructure/restapi/mapper/**")

tasks.withType<JacocoReport> {
	dependsOn(tasks.test)

	reports {
		xml.required.set(true)
		csv.required.set(true)
		html.required.set(true)
	}

	afterEvaluate {
		classDirectories.setFrom(files(classDirectories.files.map {
			fileTree(it).exclude(jacocoExclude)
		}))
	}
}