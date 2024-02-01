import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.powerShell

version = "2023.05"

project {
    buildType(CommonBuild("Build1"))
    buildType(CommonBuild("Build2"))
}

class CommonBuild(buildName: String) : BuildType ({
    this.id = buildName.toId()
    this.name = buildName

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
