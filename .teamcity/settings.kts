import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.powerShell

version = "2023.05"

project {
    buildType(Build)
}

fun createBuildType(name: String, scriptContent: String): BuildType {
    return BuildType({
        this.name = name

        params {
            param("env.BUILD_ID", "%teamcity.build.id%")
        }

        steps {
            powerShell {
                name = "Test"
                scriptMode = script {
                    content = scriptContent
                }
            }
        }
    })
}

object Build : BuildType by createBuildType("Build", """Write-Host "OK"""")
object AnotherBuild : BuildType by createBuildType("AnotherBuild", """Write-Host "Another OK"""")
