package com.github.phodal.groovypsidemo.action

import com.github.phodal.groovypsidemo.support.Constants
import com.google.common.base.Charsets
import com.google.common.io.Files
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.io.File

class PsiMerge : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project


        // Rewrite the build file so that it contains a different minSdkVersion.
        val buildFile = File(project?.basePath, Constants.FN_BUILD_GRADLE)
        val result = Files.asCharSource(buildFile, Charsets.UTF_8).read()

        GradlePsiMerge().mergeFile(project, result)
    }
}
