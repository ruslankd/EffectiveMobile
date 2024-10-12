package ru.kabirov.effectivemobile.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.kabirov.effectivemobile.R
import ru.kabirov.effectivemobile.ui.theme.Blue
import ru.kabirov.effectivemobile.ui.theme.DarkBlue
import ru.kabirov.effectivemobile.ui.theme.DarkGreen
import ru.kabirov.effectivemobile.ui.theme.Green
import ru.kabirov.effectivemobile.ui.theme.Grey1
import ru.kabirov.effectivemobile.ui.theme.Text1
import ru.kabirov.effectivemobile.ui.theme.Title4
import ru.kabirov.effectivemobile.ui.theme.White
import ru.kabirov.network.api.dto.Offers

@Composable
fun OffersRow(offers: List<Offers>, onItemClick: (String) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(offers) { item ->
            OffersItem(item, onItemClick)
        }
    }
}

@Composable
fun OffersItem(offers: Offers, onItemClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .size(132.dp, 120.dp)
            .background(color = Grey1, RoundedCornerShape(8.dp)).clickable {
                offers.link?.let(onItemClick)
            }
    ) {
        Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp)) {
            when {
                offers.id == "near_vacancies" -> {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(color = DarkBlue, shape = RoundedCornerShape(percent = 50)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.near_vacancies),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp), tint = Blue
                        )
                    }
                }

                offers.id == "level_up_resume" -> {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(
                                color = DarkGreen,
                                shape = RoundedCornerShape(percent = 50)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.level_up_resume),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp), tint = Green
                        )
                    }
                }

                offers.id == "temporary_job" -> {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(
                                color = DarkGreen,
                                shape = RoundedCornerShape(percent = 50)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.temporary_job),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp), tint = Green
                        )
                    }
                }

                else -> Spacer(modifier = Modifier.height(32.dp))
            }
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = offers.title ?: "",
                maxLines = if (offers.button == null) 3 else 2,
                overflow = TextOverflow.Clip,
                style = Title4,
                color = White
            )
            offers.button?.let {
                Text(text = it.text ?: "", style = Text1, color = Green)
            }
        }
    }
}