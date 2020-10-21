package com.example.moviez.util

import androidx.databinding.BindingAdapter
import com.example.moviez.R
import com.example.moviez.model.production.ProductionCompany
import com.example.moviez.model.production.ProductionCountry
import com.google.android.material.textview.MaterialTextView
import com.soywiz.klock.DateFormat
import com.soywiz.klock.KlockLocale
import com.soywiz.klock.parse
import java.text.DecimalFormat
import kotlin.math.floor

@BindingAdapter("bindYear")
fun MaterialTextView.bindYear(date: String?) {
    if (date == null || date.isBlank()) {
        this.text = "????"
    } else {
        val dateFormat = DateFormat("yyyy-MM-dd")
        this.text = dateFormat.parse(date).year.year.toString()
    }
}

@BindingAdapter("bindDate")
fun MaterialTextView.bindDate(date: String?) {
    if (date == null || date.isBlank()) {
        this.text = "????"
    } else {
        val dateFormat = DateFormat("yyyy-MM-dd")
        val locale = KlockLocale.english
        val finalDate = dateFormat.parse(date)
        this.text = locale.formatDateLong.format(finalDate)
    }
}

@BindingAdapter("bindProductionCompany")
fun MaterialTextView.bindProductionCompany(list: List<ProductionCompany>?) {
    if (list == null || list.isEmpty()) {
        this.text = "-"
    } else {
        val stringBuilder = StringBuilder()
        list.forEachIndexed { index, p ->
            stringBuilder.append(p.name)
            if (index != list.lastIndex) {
                stringBuilder.appendLine()
            }
        }
        this.text = stringBuilder.toString()
    }
}

@BindingAdapter("bindProductionCountry")
fun MaterialTextView.bindProductionCountry(list: List<ProductionCountry>?) {
    if (list == null || list.isEmpty()) {
        this.text = "-"
    } else {
        val stringBuilder = StringBuilder()
        list.forEachIndexed { index, p ->
            stringBuilder.append(p.name)
            if (index != list.lastIndex) {
                stringBuilder.appendLine()
            }
        }
        this.text = stringBuilder.toString()
    }
}

@BindingAdapter("bindStringList")
fun MaterialTextView.bindStringList(list: List<String>?) {
    if (list == null || list.isEmpty()) {
        this.text = "-"
    } else {
        val stringBuilder = StringBuilder()
        list.forEachIndexed { index, s ->
            stringBuilder.append(s)
            if (index != list.lastIndex) {
                stringBuilder.appendLine()
            }
        }
        this.text = stringBuilder.toString()
    }
}

@BindingAdapter("bindRuntime")
fun MaterialTextView.bindRuntime(minute: Int?) {
    if (minute == null || minute <= 0) {
        this.text = "-"
    } else {
        val hours = floor(minute / 60.0).toInt()
        val stringHours =
            resources.getQuantityString(R.plurals.hours, hours, hours)

        val minutes = minute % 60
        val stringMinutes =
            resources.getQuantityString(R.plurals.minutes, minutes, minutes)

        this.text = resources.getString(
            R.string.runtime_mask,
            stringHours,
            stringMinutes
        )
    }
}

@BindingAdapter("bindTvRuntime")
fun MaterialTextView.bindTvRuntime(list: List<Int>?) {
    if (list == null || list.isEmpty()) {
        this.text = "-"
    } else {
        val stringBuilder = StringBuilder()
        list.forEachIndexed { index, i ->
            stringBuilder.append(i).append(" m")
            if (index != list.lastIndex) {
                stringBuilder.append(", ")
            }
        }

        this.text = stringBuilder.toString()
    }
}

@BindingAdapter("bindBudget")
fun MaterialTextView.bindBudget(money: Int?) {
    if (money == null || money <= 0) {
        this.text = "-"
    } else {
        val numFormat = DecimalFormat("#,###,###")

        this.text = resources.getString(R.string.money_mask, numFormat.format(money))
    }
}

@BindingAdapter("bindRevenue")
fun MaterialTextView.bindRevenue(money: Long?) {
    if (money == null || money <= 0) {
        this.text = "-"
    } else {
        val numFormat = DecimalFormat("#,###,###")

        this.text = resources.getString(R.string.money_mask, numFormat.format(money))
    }
}