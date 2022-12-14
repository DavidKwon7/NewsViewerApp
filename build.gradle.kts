// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(Dependency.Hilt.HILT_PLUGIN)
        classpath(Dependency.Nav.NAV_PLUGIN)
    }
}

plugins {
    id("com.android.application") version "7.3.0" apply false
    id("com.android.library") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    //id("androidx.navigation.safeargs.kotlin") version "2.5.2" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
