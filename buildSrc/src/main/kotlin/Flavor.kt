enum class Flavor(
    val applicationIdSuffix: String,
    val versionNameSuffix: String,
    val buildConfigFields: Map<String, String>,
    val dimension: String = "tier"
) {

    // dev - Development
    dev(
        applicationIdSuffix = ".dev",
        versionNameSuffix = "-dev",
        buildConfigFields = mapOf(
        ),
    ),

    // qa - Manual/Automated Testing
    qa(
        applicationIdSuffix = ".qa",
        versionNameSuffix = "-qa",
        buildConfigFields = mapOf(
        ),
    ),

    // staging - Integration Testing
    staging(
        applicationIdSuffix = ".staging",
        versionNameSuffix = "-staging",
        buildConfigFields = mapOf(
        ),
    ),

    // production - Release
    prod(
        applicationIdSuffix = ".prod",
        versionNameSuffix = "-prod",
        buildConfigFields = mapOf(
        ),
    ),
}