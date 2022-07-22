buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependency.GradlePlugin.android)
        classpath(Dependency.GradlePlugin.kotlin)
        classpath(Dependency.GradlePlugin.hilt)
        classpath(Dependency.GradlePlugin.service)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}