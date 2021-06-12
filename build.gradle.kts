plugins {
    id("groovy")
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.github.node-gradle.node") version "3.0.1"
}
group = "com.cars.trip.onlinetrips"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
repositories {
    mavenCentral()
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.boot:spring-boot-starter-data-rest")
    implementation(group = "io.jsonwebtoken", name = "jjwt", version = "0.9.0")
    testImplementation ("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation (group= "org.mariadb.jdbc", name = "mariadb-java-client", version= "2.7.3")


    testImplementation("org.codehaus.groovy:groovy")
    testImplementation ("commons-beanutils:commons-beanutils:1.9.4")
    testImplementation ("org.apache.commons:commons-lang3:3.12.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2")
    testImplementation("org.mockito:mockito-core")

    runtimeOnly("org.postgresql:postgresql")


}
//tasks.register<com.github.gradle.node.npm.task.NpmTask>("appNpmInstall") {
//    description = "Installs all dependencies from package.json"
//    workingDir.set(file("${project.projectDir}/src/main/web"))
//    args.set(listOf("install"))
//}
//tasks.register<com.github.gradle.node.npm.task.NpmTask>("appNpmBuild") {
//    dependsOn("appNpmInstall")
//    description = "Builds project"
//    workingDir.set(file("${project.projectDir}/src/main/web"))
//    args.set(listOf("run", "build"))
//}
//tasks.register<Copy>("copyWebApp") {
//    dependsOn("appNpmBuild")
//    description = "Copies built project to where it will be served"
//    from("src/main/web/build")
//    into("build/resources/main/static/.")
//}
//node {
//    download.set(true)
//    version.set("12.18.3")
//    npmVersion.set("")
//    workDir.set(file("${project.buildDir}/nodejs"))
//    npmWorkDir.set(file("${project.buildDir}/npm"))
//}
//tasks.withType<JavaCompile> {
//    dependsOn("copyWebApp")
//}
//tasks.withType<Test> {
//    useJUnitPlatform()
//}