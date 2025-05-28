package com.ideasapp.randomcompliments

import android.util.Log
import org.brotli.dec.BrotliInputStream
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.util.zip.GZIPInputStream

object HtmlParser {
    suspend fun fetchCompliments(): List<String> {
        return try {
            val url = "https://www.stylecraze.com/articles/compliments-for-girls/"

            // 1. Загружаем данные через Jsoup (получаем сырые байты)
            val response = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .ignoreContentType(true)
                .execute()

            Log.d("CallCompliment", "Headers: ${response.headers()}")

            // 2. Определяем кодировку и тип сжатия
            val contentEncoding = response.header("Content-Encoding") ?: ""
            val bytes = response.bodyAsBytes()

            // 3. Распаковываем в зависимости от Content-Encoding
            val html = when {
                contentEncoding.contains("br") -> decodeBrotli(bytes)
                contentEncoding.contains("gzip") -> decodeGzip(bytes)
                else -> String(bytes, Charsets.UTF_8) // Если сжатия нет
            }

            // 4. Парсим HTML
            val doc = Jsoup.parse(html)
            doc.select("li")
                .map { it.text().trim() }
                .filter { it.length > 10 }

        } catch (e: Exception) {
            Log.e("CallCompliment", "Error fetching compliments", e)
            emptyList()
        }
    }

    // Распаковка Brotli
    private fun decodeBrotli(bytes: ByteArray): String {
        return BrotliInputStream(bytes.inputStream()).bufferedReader().use { it.readText() }
    }

    // Распаковка GZIP (оставлено для совместимости)
    private fun decodeGzip(bytes: ByteArray): String {
        return GZIPInputStream(bytes.inputStream()).bufferedReader().use { it.readText() }
    }
}