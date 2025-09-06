plugins {
    id("java")
    // ShadowJar (https://github.com/johnrengelman/shadow/releases)
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.zax71"
version = "1.0.2"

repositories {
    mavenCentral()
}

dependencies {
    // Minestom
    implementation("net.minestom:minestom:2025.08.29-1.21.8")

    // Polar world lib
    implementation("dev.hollowcube:polar:1.14.7")

    // Guava https://github.com/google/guava
    implementation("com.google.guava:guava:33.2.1-jre")

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