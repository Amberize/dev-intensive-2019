package ru.skillbranch.devintensive.utils

object Utils {

    val translit = mapOf<Char, String>(
        'а' to "a",
        'б' to "b",
        'в' to "v",
        'г' to "g",
        'д' to "d",
        'е' to "e",
        'ё' to "e",
        'ж' to "zh",
        'з' to "z",
        'и' to "i",
        'й' to "i",
        'к' to "k",
        'л' to "l",
        'м' to "m",
        'н' to "n",
        'о' to "o",
        'п' to "p",
        'р' to "r",
        'с' to "s",
        'т' to "t",
        'у' to "u",
        'ф' to "f",
        'х' to "h",
        'ц' to "c",
        'ч' to "ch",
        'ш' to "sh",
        'щ' to "sh'",
        'ъ' to "",
        'ы' to "i",
        'ь' to "",
        'э' to "e",
        'ю' to "yu",
        'я' to "ya"
    )

    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)?.ifBlank { null }
        val lastName = parts?.getOrNull(1)?.ifBlank { null }

        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?) : String? {
        val firstNameInitial = firstName?.firstOrNull()?.toString().orEmpty().capitalize()
        val lastNameInitial = lastName?.firstOrNull()?.toString().orEmpty().capitalize()

        return firstNameInitial.plus(lastNameInitial).ifBlank { null }
    }

    fun transliteration(value: String, divider: String = " ") : String {
        return value.split(' ').joinToString(divider) { word ->
            word.map { char ->
                translit[char] ?: translit[char.toLowerCase()]?.capitalize() ?: char
            }.joinToString("")
        }
    }

}