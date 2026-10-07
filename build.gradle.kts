plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.27.7")
}

tasks.test {
    useJUnitPlatform {
        includeTags("smoke")
    }
    finalizedBy(tasks.named("notifyTestsOver"))
}

tasks.register("runAllTests") {
    group = "verification"
    dependsOn(tasks.test)
}

tasks.register("notifyTestsOver") {
    group = "verification"
    doLast {
        println("Test run is over")
    }
}

