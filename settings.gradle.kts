enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "INUgram"
include(":app")
include(":data")
include(":lib:videoplayer")
include(":feature:certification")
include(":core:designsystem")
include(":core:ui")
include(":feature:home")
include(":feature:mypage")
include(":core:model")
include(":feature:upload")
