plugins {
    id("java")
    // ShadowJar (https://github.com/johnrengelman/shadow/releases)
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.zax71"
version = "1.0.2"

repositories {
    mavenCentral()
}

dependencies {
    // Minestom
    implementation("net.minestom:minestom:2026.04.13-1.21.11")

    // Polar world lib
    implementation("dev.hollowcube:polar:1.14.7")

    // Guava https://github.com/google/guava
    implementation("com.google.guava:guava:33.6.0-jre")

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "me.zax71.polarConversionUtil.Main"
        }
    }
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        mergeServiceFiles()
        archiveClassifier.set("") // Prevent the -all suffix
    }
}