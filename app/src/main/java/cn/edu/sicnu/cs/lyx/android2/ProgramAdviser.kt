package cn.edu.sicnu.cs.lyx.android2

class ProgramAdviser {

    private val knowledgeBase = mapOf(
        "kotlin" to R.string.advice_kotlin,
        "java" to R.string.advice_java,
        "android" to R.string.advice_android,
        "mvc" to R.string.advice_mvc
    )

    fun getAdviceRes(query: String): Int {
        val key = query.trim().lowercase()
        return knowledgeBase[key] ?: R.string.advice_not_found
    }
}
