package ru.kabirov.effectivemobile.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.kabirov.effectivemobile.R
import ru.kabirov.effectivemobile.search.SearchViewModel
import ru.kabirov.effectivemobile.search.VacanciesColumn
import ru.kabirov.effectivemobile.ui.theme.Grey3
import ru.kabirov.effectivemobile.ui.theme.Text1
import ru.kabirov.effectivemobile.ui.theme.Title2
import ru.kabirov.effectivemobile.ui.theme.White

@Composable
fun FavoritesScreen(
    onNavigateToVacancyDetail: (String) -> Unit,
    vm: SearchViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(R.string.favorites),
            style = Title2,
            color = White
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = vm.getVacanciesString(vm.favoritesVacancies.collectAsStateWithLifecycle().value.size),
            style = Text1,
            color = Grey3
        )
        VacanciesColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            vacancies = vm.favoritesVacancies.collectAsStateWithLifecycle().value,
            vacanciesCount = vm.favoritesVacancies.collectAsStateWithLifecycle().value.size,
            currentlyViewingString = { vm.getCurrentlyViewingString(it) },
            publishedDate = { vm.getDateFormat(it) },
            onNavigateToVacancyDetail = onNavigateToVacancyDetail,
            onFavoriteClick = { vm.onFavoriteClick(it) }
        )
    }
}