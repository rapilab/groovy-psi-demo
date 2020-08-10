package com.github.phodal.groovypsidemo.action

import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class GradleCoordinate(groupId: String, artifactId: String, revisions: List<RevisionComponent>, type: ArtifactType) {
    private var mGroupId: String = groupId
    private var mArtifactId: String = artifactId
    private var mArtifactType: ArtifactType = type
    private var mRevisions: List<RevisionComponent> = ArrayList(3)

    init {
        (this.mRevisions as ArrayList<RevisionComponent>).addAll(revisions)
    }

    abstract class RevisionComponent : Comparable<RevisionComponent?> {
        abstract fun asInteger(): Int
        abstract val isPreview: Boolean
    }


    fun getId(): String? {
        return String.format("%s:%s", mGroupId, mArtifactId)
    }

    companion object {
        fun parseCoordinateString(coordinateString: String?): GradleCoordinate? {
            val MAVEN_PATTERN = Pattern.compile("([\\w\\d\\.-]+):([\\w\\d\\.-]+):([^:@]+)(@[\\w-]+)?")
            val matcher: Matcher = MAVEN_PATTERN.matcher(coordinateString)
            return if (!matcher.matches()) {
                null
            } else {
                val groupId = matcher.group(1)
                val artifactId = matcher.group(2)
                val revision = matcher.group(3)
                val typeString = matcher.group(4)
                var type: ArtifactType? = null
                if (typeString != null) {
                    type = ArtifactType.getArtifactType(typeString.substring(1))
                }
//                    val revisions: List<RevisionComponent> = parseRevisionNumber(revision)
                val revisions: List<RevisionComponent> = ArrayList(0)
                GradleCoordinate(groupId, artifactId, revisions, type!!)
            }
        }
    }

    enum class ArtifactType(private val mId: String) {
        POM("pom"), JAR("jar"), MAVEN_PLUGIN("maven-plugin"), EJB("ejb"), WAR("war"), EAR("ear"), RAR("rar"), PAR("par"), AAR("aar");

        override fun toString(): String {
            return mId
        }

        companion object {
            fun getArtifactType(name: String?): ArtifactType? {
                if (name != null) {
                    val var1: Array<ArtifactType> = ArtifactType.values()
                    val var2 = var1.size
                    for (var3 in 0 until var2) {
                        val type = var1[var3]
                        if (type.mId.equals(name, ignoreCase = true)) {
                            return type
                        }
                    }
                }
                return null
            }
        }
    }
}
