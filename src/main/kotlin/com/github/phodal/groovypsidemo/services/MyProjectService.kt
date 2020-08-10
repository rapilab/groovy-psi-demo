package com.github.phodal.groovypsidemo.services

import com.intellij.openapi.project.Project
import com.github.phodal.groovypsidemo.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
