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
include(":feature:create")
include(":feature:entries")
include(":feature:more")
include(":feature:analytics")
include(":feature:calendar")
include(":core:navigation")
