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
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

rootProject.name = "MoodTracker"
include(":app")
include(":core:designsystem")
include(":core:giga_chat_network")
include(":core:database")
include(":core:notifications")
include(":core:domain")
include(":core:data")
include(":core:data_ui")
include(":core:analytics")
include(":feature:create")
include(":feature:entries")
include(":feature:more")
include(":feature:analytics")
include(":feature:calendar")
include(":core:navigation")
