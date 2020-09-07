package com.kalashnyk.denys.movaapp.ui.navigator.model

enum class Pages(val text: String) {

    SEARCH_HISTORY("search_history"),
    IMAGE_DETAIL("image_detail"),
    UNKNOWN("unknown");

    override fun toString(): String {
        return text
    }

    companion object {
        @JvmStatic
        fun fromString(input: String?): Pages {
            return when {
                input == null || input.isEmpty() -> UNKNOWN
                else -> values().firstOrNull {
                    it.text.equals(
                        input,
                        ignoreCase = true
                    )
                } ?: UNKNOWN
            }
        }
    }
}