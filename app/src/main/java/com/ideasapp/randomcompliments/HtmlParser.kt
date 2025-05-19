package com.ideasapp.randomcompliments

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


object HtmlParser {
    fun String.extractCompliments(): List<String> {
        val compliments:MutableList<String> = ArrayList()
        val document:Document = Jsoup.parse(this)

        // Select `<li>` elements inside `<ol>` with the class `wp-block-list`
        val listItems:Elements = document.select("ol.wp-block-list > li")

        for(i in listItems.indices) {
            compliments.add(listItems[i].text())
        }

        return compliments
    }
}