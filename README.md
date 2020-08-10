# groovy-psi-demo

# groovy-psi-demo

## Documents


**Plugin Dependency**

 - [Dependency Declaration in plugin.xml](https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_dependencies.html#2-project-setup)
 - [Plugin Compatibility with IntelliJ Platform Products](https://jetbrains.org/intellij/sdk/docs/basics/getting_started/plugin_compatibility.html)

List:

 - com.intellij.modules.platform
 - com.intellij.modules.lang
 - com.intellij.modules.xml
 - com.intellij.modules.vcs
 - com.intellij.modules.xdebugger

Modules Specific to Functionality:

 - com.intellij.modules.java
 - com.intellij.modules.androidstudio
 - com.intellij.modules.appcode
 - com.intellij.modules.cidr.lang
 - com.intellij.modules.cidr.debugger
 - com.intellij.modules.clion
 - com.intellij.database
 - com.intellij.modules.go
 - com.intellij.modules.python
 - com.intellij.modules.rider
 - com.intellij.modules.ruby
 - com.intellij.modules.ultimate
 - com.jetbrains.php
 - JavaScript

<!-- Plugin description -->
This Fancy IntelliJ Platform Plugin is going to be your implementation of the brilliant ideas that you have.

This specific section is a source for the [plugin.xml](/src/main/resources/META-INF/plugin.xml) file which will be
extracted by the [Gradle](/build.gradle.kts) during the build process.

To keep everything working, do not remove `<!-- ... -->` sections.
<!-- Plugin description end -->


## Simliar project

 - [JUnit to Spock Converter](https://github.com/masooh/intellij-junit-to-spock-converter) JUnit to Spock Converter IntelliJ Plugin: Converts JUnit to Spock.

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template

## Documents

Groovy Code

```groovy
plugins {
    id 'java'
}

group 'org.example'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'
}
```

Tree logs:

```bash
0 = {GrMethodCallExpressionImpl@37378} "Method call"
1 = {LeafPsiElement@37379} "PsiElement(new line)"
2 = {GrApplicationStatementImpl@37380} "Call expression"
3 = {LeafPsiElement@37381} "PsiElement(new line)"
4 = {GrApplicationStatementImpl@37382} "Call expression"
5 = {LeafPsiElement@37383} "PsiElement(new line)"
6 = {GrMethodCallExpressionImpl@37384} "Method call"
7 = {LeafPsiElement@32963} "PsiElement(new line)"
8 = {GrMethodCallExpressionImpl@32962} "Method call"
```
