package ru.kabirov.effectivemobile.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kabirov.effectivemobile.R
import ru.kabirov.effectivemobile.ui.GreenButton
import ru.kabirov.effectivemobile.ui.theme.Blue
import ru.kabirov.effectivemobile.ui.theme.ButtonText2
import ru.kabirov.effectivemobile.ui.theme.Green
import ru.kabirov.effectivemobile.ui.theme.Grey1
import ru.kabirov.effectivemobile.ui.theme.Grey3
import ru.kabirov.effectivemobile.ui.theme.Grey4
import ru.kabirov.effectivemobile.ui.theme.Text1
import ru.kabirov.effectivemobile.ui.theme.Title3
import ru.kabirov.effectivemobile.ui.theme.White
import ru.kabirov.network.api.dto.Vacancies

@Composable
fun VacanciesColumn(
    modifier: Modifier = Modifier,
    vacancies: List<Vacancies>,
    vacanciesCount: Int,
    currentlyViewingString: (Int) -> String,
    publishedDate: (String?) -> (String),
    onNavigateToVacancyDetail: (String) -> Unit,
    onFavoriteClick: (Vacancies) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 24.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = vacancies.take(vacanciesCount), key = {vacancy -> vacancy.id!!}) {
            VacanciesItem(
                vacancies = it,
                currentlyViewingString = currentlyViewingString,
                publishedDate = publishedDate,
                onNavigateToVacancyDetail = onNavigateToVacancyDetail,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Composable
fun VacanciesItem(
    vacancies: Vacancies,
    currentlyViewingString: (Int) -> String,
    publishedDate: (String?) -> (String),
    onNavigateToVacancyDetail: (String) -> Unit,
    onFavoriteClick: (Vacancies) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Grey1, RoundedCornerShape(8.dp))
            .clickable {
                onNavigateToVacancyDetail(vacancies.title ?: "")
            }
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            vacancies.lookingNumber?.let {
                Text(
                    text = stringResource(R.string.currently_viewing, currentlyViewingString(it)),
                    style = Text1,
                    color = Green
                )
            }
            Text(text = vacancies.title ?: "", style = Title3, color = White)
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = vacancies.address?.town ?: "", style = Text1, color = White)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = vacancies.company ?: "", style = Text1, color = White)
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(R.drawable.check_mark),
                        contentDescription = null,
                        tint = Grey3
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(R.drawable.experience),
                    contentDescription = null,
                    tint = White
                )
                Text(text = vacancies.experience?.previewText ?: "", style = Text1, color = White)
            }
            Text(
                text = stringResource(R.string.published, publishedDate(vacancies.publishedDate)),
                style = Text1,
                color = Grey3
            )
            GreenButton(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 11.dp), onClick = {}) {
                Text(text = stringResource(R.string.reply), style = ButtonText2)
            }
        }
        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable {
                    onFavoriteClick(vacancies)
                },
            painter = painterResource(if (vacancies.isFavorite != null && vacancies.isFavorite!!) R.drawable.heart_active else R.drawable.heart),
            contentDescription = null,
            tint = if (vacancies.isFavorite != null && vacancies.isFavorite!!) Blue else Grey4
        )
    }
}