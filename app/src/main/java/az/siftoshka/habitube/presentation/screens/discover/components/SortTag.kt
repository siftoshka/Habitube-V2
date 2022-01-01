package az.siftoshka.habitube.presentation.screens.discover.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import az.siftoshka.habitube.presentation.screens.discover.DiscoverViewModel
import az.siftoshka.habitube.presentation.screens.discover.items.DiscoverSortItem
import az.siftoshka.habitube.presentation.util.Padding

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SortTag(
    item: DiscoverSortItem,
    viewModel: DiscoverViewModel = hiltViewModel()
) {

    val backgroundColor = if (viewModel.sortCategory.value == item.category) MaterialTheme.colors.primary else MaterialTheme.colors.background
    val strokeColor = if (viewModel.sortCategory.value == item.category) MaterialTheme.colors.primary else MaterialTheme.colors.secondaryVariant.copy(alpha = 0.2f)
    val textColor = if (viewModel.sortCategory.value == item.category) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground

    Card(
        border = BorderStroke(1.dp, color = strokeColor),
        shape = RoundedCornerShape(100.dp),
        backgroundColor = backgroundColor,
        onClick = { viewModel.sortCategory.value = item.category }
    ) {
        Text(
            text = stringResource(id = item.text),
            color = textColor,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(Padding.Small)
        )
    }
}