package com.github.phodal.groovypsidemo.action

import com.github.phodal.groovypsidemo.support.Constants
import com.intellij.psi.PsiFileFactory
import com.intellij.testFramework.builders.ModuleFixtureBuilder
import com.intellij.testFramework.fixtures.CodeInsightFixtureTestCase
import com.intellij.testFramework.fixtures.ModuleFixture
import org.jetbrains.plugins.groovy.GroovyFileType
import org.jetbrains.plugins.groovy.lang.psi.GroovyFile
import org.junit.jupiter.api.Test

internal open class GradlePsiMergeTest: CodeInsightFixtureTestCase<ModuleFixtureBuilder<ModuleFixture>>() {
    @Test
    fun shouldMergeDependencies() {
        val project = myFixture.project
        // StubUpdatingIndex calls this method very often, so, optimized implementation is required
        val code = "    compile \"joda-time:joda-time:2.2\"\n" +
                "    testCompile group: 'junit', name: 'junit', version: '4.12'\n"

        val templateBuildFile = PsiFileFactory.getInstance(project).createFileFromText(
                Constants.FN_BUILD_GRADLE, GroovyFileType.GROOVY_FILE_TYPE, code) as GroovyFile

        GradlePsiMerge().mergeDependencies(templateBuildFile, project);

    }
}