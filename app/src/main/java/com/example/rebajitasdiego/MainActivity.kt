package com.example.rebajitasdiego

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rebajitasdiego.components.FoodCategoryContent
import com.example.rebajitasdiego.models.EatingData
import com.example.rebajitasdiego.models.FoodCategory
import com.example.rebajitasdiego.viewModel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodMainScreen("Diego")
        }
    }

    @Composable
    private fun FoodMainScreen(name: String) {

        val vegetable = painterResource(id = R.drawable.ic_carrot)
        val bread = painterResource(id = R.drawable.ic_bread)
        val fruit = painterResource(id = R.drawable.ic_apple)
        val protein = painterResource(id = R.drawable.ic_egg)
        val dairy = painterResource(id = R.drawable.ic_milk)
        val oil = painterResource(id = R.drawable.ic_oil)

        val icons = listOf(
            dairy to "milk",
            oil to "oil",
            protein to "protein",
            bread to "bread",
            fruit to "fruit",
            vegetable to "vegetable",
        )
        val mainViewModel = viewModel<MainViewModel>()

        val foodCategoriesData by mainViewModel.foodCategoriesData.collectAsState()
        FoodMainContent(
            name,
            icons,
            foodCategoriesData,
            onAddEating = { mainViewModel.onAddEating(it) },
            onRemoveEating = { mainViewModel.onRemoveEating(it) })
    }

    @Composable
    private fun FoodMainContent(
        name: String,
        icons: List<Pair<Painter, String>>,
        foodCategoriesData: List<EatingData>,
        onAddEating: (FoodCategory) -> Unit,
        onRemoveEating: (FoodCategory) -> Unit
    ) {
        val materialBlue700 = Color(0xFF1976D2)
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        var counter by remember { mutableStateOf(0) }

        MaterialTheme() {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { MyParagraph("Rebajitas $name") },
                        backgroundColor = materialBlue700
                    )
                },
                drawerContent = { Text(text = "drawerContent") },
                content = {
                    Column(Modifier.fillMaxWidth()) {
                        foodCategoriesData.forEachIndexed { index, foodCategory ->
                            when {
                                index % 2 == 0 -> FoodCategoryContent(
                                    icon = icons[index].first,
                                    description = icons[index].second,
                                    modifier = Modifier.background(Color.Gray),
                                    data = foodCategory,
                                    onAddEating = { onAddEating(it) },
                                    onRemoveEating = { onRemoveEating(it) }
                                )
                                index % 2 != 0 -> FoodCategoryContent(
                                    icon = icons[index].first,
                                    description = icons[index].second,
                                    modifier = Modifier.background(Color.LightGray),
                                    data = foodCategory,
                                    onAddEating = { onAddEating(it) },
                                    onRemoveEating = { onRemoveEating(it) }
                                )
                            }
                        }
                    }
                },
                bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("Rebajitas Diego") } }
            )
        }

    }

    @Preview
    @Composable
    fun PreviewPersonalData() {
        val mainViewModel = viewModel<MainViewModel>()
        val vegetable = painterResource(id = R.drawable.ic_carrot)
        val bread = painterResource(id = R.drawable.ic_bread)
        val fruit = painterResource(id = R.drawable.ic_apple)
        val protein = painterResource(id = R.drawable.ic_egg)
        val dairy = painterResource(id = R.drawable.ic_milk)
        val oil = painterResource(id = R.drawable.ic_oil)

        val icons = listOf(
            dairy to "milk",
            oil to "oil",
            protein to "protein",
            bread to "bread",
            fruit to "fruit",
            vegetable to "vegetable",
        )
        val foodCategoriesData by mainViewModel.foodCategoriesData.collectAsState()
        FoodMainContent(
            "Diego",
            icons,
            foodCategoriesData,
            onAddEating = { mainViewModel.onAddEating(it) },
            onRemoveEating = { mainViewModel.onAddEating(it) })
    }

    @Composable
    fun MyParagraph(text: String) {
        Text(text = "$text")
        Spacer(modifier = Modifier.height(16.dp))
    }

}

