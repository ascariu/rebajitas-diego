package com.example.rebajitasdiego.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rebajitasdiego.R
import com.example.rebajitasdiego.models.EatingData
import com.example.rebajitasdiego.models.FoodCategory
import com.example.rebajitasdiego.utils.getCircleIconForEatingState
import com.example.rebajitasdiego.viewModel.MainViewModel

/*
@Composable
fun FoodCategoryScreen(mainViewModel: MainViewModel, category: FoodCategory) {
    val categoryEatingData by mainViewModel.getFoodCategoryData(category)
}
*/


@Composable
fun FoodCategoryContent(
    icon: Painter,
    description: String,
    modifier: Modifier,
    data: EatingData,
    onAddEating: (FoodCategory) -> Unit,
    onRemoveEating: (FoodCategory) -> Unit
) {
    val realEatingValue = if(data.isLastHalf) data.eatingValue + 1 else data.eatingValue
    val maxValue = Math.max(realEatingValue, data.maxEatingValue)
    val removeIcon = painterResource(id = R.drawable.ic_baseline_remove_circle_outline_24)
    val addIcon = painterResource(id = R.drawable.ic_baseline_add_circle_outline_24)
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = icon,
                    contentDescription = description,
                    modifier = Modifier
                        .height(48.dp)
                        .width(32.dp)
                )

            }
            Row(
                modifier = Modifier.padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                for (i in 1..maxValue) {
                    getCircleIconForEatingState(
                        data.getCircle(i)
                    )
                }
            }
        }


        Row {
            IconButton(onClick = { onRemoveEating(data.category) }) {
                Icon(
                    painter = removeIcon,
                    contentDescription = description,
                    modifier = Modifier.background(color = Color.Red, shape = CircleShape)
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
            )
            IconButton(onClick = { onAddEating(data.category) }) {
                Icon(
                    painter = addIcon,
                    contentDescription = description,
                    modifier = Modifier.background(color = Color.Green, shape = CircleShape)
                )
            }


        }
    }
}

