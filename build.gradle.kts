plugins {
    id("java")
}

group = "cl.framework.mobile"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Selenium
    implementation("org.seleniumhq.selenium:selenium-java:4.24.0")

    // Appium
    implementation("io.appium:java-client:9.5.0")

    // Cucumber
    testImplementation("io.cucumber:cucumber-java:7.28.0")
    testImplementation("io.cucumber:cucumber-junit:7.28.0")

    // JUnit 4
    testImplementation("junit:junit:4.13.2")

    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.13.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnit()

    testLogging {
        showStandardStreams = true
    }
}