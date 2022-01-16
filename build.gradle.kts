import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	application
	kotlin("jvm") version "1.6.10"
}

group = "jp.ne.icchannel.batch"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("com.amazonaws:aws-java-sdk-lambda:1.11.455")
	implementation("com.amazonaws:aws-lambda-java-core:1.2.0")
	implementation("com.googlecode.json-simple:json-simple:1.1.1")

	implementation("com.rometools:rome:1.18.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks{
	val buildZip by creating(Zip::class) {
		from(compileKotlin)
		from(processResources)
		into("lib") {
			from(configurations.runtimeClasspath)
		}
	}
	compileKotlin {
		kotlinOptions {
			jvmTarget = "11"
		}
	}
	compileTestKotlin {
		kotlinOptions {
			jvmTarget = "11"
		}
	}
}

application {
	mainClass.set("jp.ne.icchannel.batch.MainKt")
}