package az.siftoshka.habitube.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import az.siftoshka.habitube.domain.util.Constants.IMAGE_URL
import az.siftoshka.habitube.presentation.util.Padding
import coil.compose.rememberImagePainter
import coil.request.CachePolicy

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LongImageCard(
    imageUrl: String?,
    title: String?,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier.width(320.dp),
        shape = MaterialTheme.shapes.large,
        elevation = 4.dp,
        onClick = onItemClick
    ) {
        Box(modifier = Modifier.height(180.dp)) {
            Image(
                painter = rememberImagePainter(
                    data = IMAGE_URL + imageUrl,
                    builder = {
                        crossfade(true)
                        memoryCachePolicy(CachePolicy.ENABLED)
                        diskCachePolicy(CachePolicy.DISABLED)
                        networkCachePolicy(CachePolicy.ENABLED)
                    }
                ),
                contentDescription = title,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Padding.Medium),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = title.orEmpty(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}