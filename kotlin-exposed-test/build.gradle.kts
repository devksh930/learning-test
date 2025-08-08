plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.exposed:exposed-core:0.52.0")
    implementation("org.jetbrains.exposed:exposed-java-time:0.52.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.52.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.52.0")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")
    implementation("ch.qos.logback:logback-classic:1.4.14") // 이 줄을 추가해야 합니다.

    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.9.3")
    testImplementation(kotlin("test"))

}

application {
    mainClass.set("MainKt")
}
