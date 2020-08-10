package com.github.phodal.groovypsidemo.action

import com.github.phodal.groovypsidemo.support.Constants
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.psi.PsiFileFactory
import org.jetbrains.plugins.groovy.GroovyFileType
import org.jetbrains.plugins.groovy.lang.psi.GroovyFile

class PsiMerge : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project

        val source = "".trim({ it <= ' ' })
        val dest = "".trim({ it <= ' ' })

        val templateBuildFile = PsiFileFactory.getInstance(project).createFileFromText(
                Constants.FN_BUILD_GRADLE, GroovyFileType.GROOVY_FILE_TYPE, source) as GroovyFile
        val existingBuildFile = PsiFileFactory.getInstance(project).createFileFromText(
                Constants.FN_BUILD_GRADLE, GroovyFileType.GROOVY_FILE_TYPE, dest) as GroovyFile

    }
}
