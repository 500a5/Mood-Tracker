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

rootProject.name = "Mood Tracker"
include(":app")
include(":core:designsystem")
include(":core:network")
include(":core:model")
include(":core:database")
include(":core:notifications")
include(":core:domain")
include(":core:data")
include(":core:analytics")
include(":feature:create_mood")
include(":feature:mood_feed")
include(":feature:settings")
