package com.github.phodal.groovypsidemo.action

import com.github.phodal.groovypsidemo.support.Constants
import com.google.common.base.Charsets
import com.google.common.io.Files
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.codeStyle.CodeStyleManager
import org.jetbrains.plugins.groovy.GroovyFileType
import org.jetbrains.plugins.groovy.lang.psi.GroovyFile
import org.jetbrains.plugins.groovy.lang.psi.GroovyPsiElementFactory
import java.io.File
import java.util.*

class PsiMerge : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project


        // Rewrite the build file so that it contains a different minSdkVersion.
        val buildFile = File(project?.basePath, Constants.FN_BUILD_GRADLE)
        val result = Files.asCharSource(buildFile, Charsets.UTF_8).read()

        mergeFile(project, result)
    }

    private fun mergeFile(project: Project?, buildFile: String): String? {
        val source = buildFile.trim({ it <= ' ' })
        val dest = "".trim({ it <= ' ' })

        val templateBuildFile = PsiFileFactory.getInstance(project).createFileFromText(
                Constants.FN_BUILD_GRADLE, GroovyFileType.GROOVY_FILE_TYPE, source) as GroovyFile
        val existingBuildFile = PsiFileFactory.getInstance(project).createFileFromText(
                Constants.FN_BUILD_GRADLE, GroovyFileType.GROOVY_FILE_TYPE, dest) as GroovyFile

        return WriteCommandAction.writeCommandAction(project, existingBuildFile).withName("Merged Gradle Files").compute<String, RuntimeException> {
            // Make sure that the file we are merging in to has a trailing new line. This ensures that any added elements
            // appear at the bottom of the file, it also keeps consistency with how projects created with the Wizards look.
            addTrailingNewLine(existingBuildFile)
            mergePsi(templateBuildFile, existingBuildFile, project, null)
            val formatted = CodeStyleManager.getInstance(project!!).reformat(existingBuildFile)
            formatted.text
        }
    }

    private fun mergePsi(fromRoot: PsiElement, toRoot: PsiElement, project: Project?, nothing: Nothing?) {
        val destinationChildren: Set<PsiElement> = HashSet(Arrays.asList(*toRoot.children))
        for (destinationChild in destinationChildren) {

        }
    }

    private fun addTrailingNewLine(file: GroovyFile) {
        val newLineElement: PsiElement = getNewLineElement(file, 1)
        file.addAfter(newLineElement, file.getLastChild())
    }

    private fun getNewLineElement(context: PsiElement, length: Int): PsiElement {
        val factory = GroovyPsiElementFactory.getInstance(context.project)
        return factory.createLineTerminator(length)
    }
}
