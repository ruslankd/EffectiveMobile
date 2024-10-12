package ru.kabirov.effectivemobile.search

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import ru.kabirov.effectivemobile.R
import ru.kabirov.effectivemobile.ui.BlueButton
import ru.kabirov.effectivemobile.ui.theme.Blue
import ru.kabirov.effectivemobile.ui.theme.ButtonText1
import ru.kabirov.effectivemobile.ui.theme.Text1
import ru.kabirov.effectivemobile.ui.theme.Title2
import ru.kabirov.effectivemobile.ui.theme.White

@Composable
fun SearchScreen(
    onNavigateToVacancyDetail: (String) -> Unit,
    vm: SearchViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        vm.events.collectLatest {
            when (it) {
                is SearchEvents.ToBrowser -> {
                    val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(it.link) }
                    context.startActivity(intent)
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchTopAppBar(
            searchHint = stringResource(R.string.search_hint),
            searchString = vm.searchString.collectAsState().value,
            searchStringValueChange = { vm.searchStringValueChange(it) },
            onLeadingIconClick = { vm.onLeadingIconClick() },
            vm.isAllVacancies.collectAsState().value
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (vm.isAllVacancies.collectAsState().value) {
            VacanciesByCompliance(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                vm.getVacanciesString()
            )
        } else {
            OffersRow(vm.offersState.value, onItemClick = {
                vm.onOffersClick(it)
            })
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.vacancies_for_you),
                style = Title2,
                color = White
            )
        }
        VacanciesColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            vacancies = vm.vacancies.collectAsStateWithLifecycle().value,
            vacanciesCount = vm.getVacanciesCount(),
            currentlyViewingString = { vm.getCurrentlyViewingString(it) },
            publishedDate = { vm.getDateFormat(it) },
            onNavigateToVacancyDetail = onNavigateToVacancyDetail,
            onFavoriteClick = { vm.onFavoriteClick(it) }
        )
        if (!vm.isAllVacancies.collectAsState().value) {
            BlueButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                onClick = {
                    vm.onExpandBtnClick()
                }) {
                Text(
                    text = stringResource(
                        R.string.remaining_vacancies,
                        vm.getVacanciesString()
                    ),
                    style = ButtonText1
                )
            }
        }
    }
}

@Composable
fun VacanciesByCompliance(modifier: Modifier = Modifier, vacanciesString: String) {
    Row(modifier = modifier) {
        Text(text = vacanciesString, style = Text1, color = White)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(R.string.by_comliance), style = Text1, color = Blue)
        Spacer(modifier = Modifier.width(6.dp))
        Column(modifier = Modifier.height(16.dp)) {
            Icon(
                painter = painterResource(R.drawable.up_by_compliance),
                tint = Blue,
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.down_by_compliance),
                tint = Blue,
                contentDescription = null
            )
        }
    }
}