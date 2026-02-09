package com.project.newsnow.presentation.onboarding

import androidx.annotation.DrawableRes
import com.project.newsnow.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Finance & Crypto News",
        description = "Stay up to date with the latest news on finance, investments, and cryptocurrencies.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Global News & Culture",
        description = "Explore international news, culture, and major events happening around the world.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Health & Lifestyle",
        description = "Read news about healthy living, nutrition, and habits that improve your well-being.",
        image = R.drawable.onboarding3
    )
)

