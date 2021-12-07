package lab.yearnlune.model.type

enum class GradeTypes {

    COMMON,
    UNCOMMON,
    RARE,
    EPIC,
    LEGENDARY,
    ARTIFACT,
    MYTH,
    ESTHER;

    companion object {
        private val map = values().associateBy(GradeTypes::ordinal)
        fun fromValue(type: Int) = map[type] ?: throw IllegalArgumentException()
    }
}