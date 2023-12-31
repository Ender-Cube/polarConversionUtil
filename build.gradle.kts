plugins {
    id("java")
    // ShadowJar (https://github.com/johnrengelman/shadow/releases)
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.zax71"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    // Minestom
    implementation("dev.hollowcube:minestom-ce-snapshots:1_20_4-615248dc5b")

    // Polar world lib
    implementation("dev.hollowcube:polar:1.6.1")

    // Guava https://github.com/google/guava
    implementation("com.google.guava:guava:32.1.1-jre")

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
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