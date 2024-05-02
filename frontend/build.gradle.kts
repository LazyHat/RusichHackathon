plugins {
   alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
}

repositories {
    mavenCentral()
}

kotlin {
   js {
       browser()
       binaries.executable()
   }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }

//        commonMain.dependencies {
//            implementation(compose.runtime)
//            implementation(compose.foundation)
//            implementation(compose.material3)
//            implementation(compose.components.resources)
//            implementation(compose.components.uiToolingPreview)
//            implementation(libs.voyager.navigator)
//            implementation(libs.composeImageLoader)
//            implementation(libs.napier)
//            implementation(libs.kotlinx.coroutines.core)
//            implementation(libs.ktor.core)
//            implementation(libs.kotlinx.serialization.json)
//        }
//
        jsMain.dependencies {
            project(":shared")
            implementation(compose.html.core)
            implementation(libs.ktor.client.js)
        }
    }
}

compose.experimental {
    web.application {
    }
}