import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.powerShell

version = "2023.05"

project {
    buildType(Build)
}

fun createBuildType(name: String): BuildType {
    return BuildType({
        this.name = name

        params {
            param("env.BUILD_ID", "%teamcity.build.id%")
        }

        steps {
            powerShell {
                name = "Test"
                scriptMode = script {
                    content = """Write-Host "OK""""
                }
            }
        }
    })
}

object Build = createBuildType("Build")
object AnotherBuild = createBuildType("AnotherBuild")
