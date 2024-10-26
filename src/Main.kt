fun main() {
    val alphabet = charArrayOf(
        'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И',
        'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т',
        'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ы', 'Ъ',
        'Э', 'Ю', 'Я')
//Ассоциация символов с их позициями
    val positions = alphabet.indices.associate { alphabet[it] to it + 1 }

    println("Выберите действие: зашифровать (1) или расшифровать (2):")
    val action = readLine()

    println("Введите ключевое слово:")
    val keyword = readLine()!!.uppercase()

    println("Введите текст:")
    val text = readLine()!!.uppercase()

    val result = when (action) {
        "1" -> shifr(text, keyword, positions)
        "2" -> deshifr(text, keyword, positions)
        else -> "Неверный выбор действия."
    }
    println("Результат: $result")
}

fun shifr(text: String, keyword: String, positions: Map<Char, Int>): String {
    val shiftedText = StringBuilder()
    val keywordLength = keyword.length

    for ((index, char) in text.withIndex()) {
        if (positions.containsKey(char)) { // Проверка, является ли символ буквой алфавита
            val shift = positions[keyword[index % keywordLength]]!! //сдвиг относительно ключевого слова
            val originalPosition = positions[char]!!
            val newPosition = (originalPosition + shift - 1) % 33 + 1
            shiftedText.append(positions.entries.first { it.value == newPosition }.key)
        } else {
            shiftedText.append(char)
        }
    }
    return shiftedText.toString()
}

fun deshifr(text: String, keyword: String, positions: Map<Char, Int>): String {
    val shiftedText = StringBuilder()
    val keywordLength = keyword.length

    for ((index, char) in text.withIndex()) {
        if (positions.containsKey(char)) { //проверка
            val shift = positions[keyword[index % keywordLength]]!! //сдвиг
            val originalPosition = positions[char]!!
            val newPosition = (originalPosition - shift - 1 + 33) % 33 + 1
            shiftedText.append(positions.entries.first { it.value == newPosition }.key)
        } else {
            shiftedText.append(char)
        }
    }
    return shiftedText.toString()
}