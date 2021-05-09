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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rebajitasdiego.components.FoodCategory
import com.example.rebajitasdiego.models.EatingData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonalData("Diego")
        }
    }

    @Composable
    private fun PersonalData(name: String) {
        val materialBlue700 = Color(0xFF1976D2)
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        var counter by remember { mutableStateOf(0) }
        val vegetable = painterResource(id = R.drawable.ic_carrot)
        val bread = painterResource(id = R.drawable.ic_bread)
        val fruit = painterResource(id = R.drawable.ic_apple)
        val protein = painterResource(id = R.drawable.ic_egg)
        val dairy = painterResource(id = R.drawable.ic_milk)
        val oil = painterResource(id = R.drawable.ic_oil)


        val foodCategoryEating = arrayOf(1, 2, 3, 3, 2, 2)
        val maxFoodCategoryEating = arrayOf(3, 3, 6, 5, 3, 3)
        val isLastHalf = arrayOf(false, true, true, true, false, false)

        val icons = listOf(
            dairy to "milk",
            oil to "oil",
            protein to "protein",
            bread to "bread",
            fruit to "fruit",
            vegetable to "vegetable",
        )
        MaterialTheme() {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { MyParagraph("Rebajitas $name") },
                        backgroundColor = materialBlue700
                    )
                },
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    FloatingActionButton(onClick = { counter += 1 }) {
                        Text("+")
                    }
                },
                drawerContent = { Text(text = "drawerContent") },
                content = {
                    Column(Modifier.fillMaxWidth()) {
                        icons.forEachIndexed { index, pair ->
                            when {
                                index % 2 == 0 -> FoodCategory(
                                    icon = pair.first,
                                    description = pair.second,
                                    modifier = Modifier.background(Color.Gray),
                                    data = EatingData(
                                        foodCategoryEating[index],
                                        maxFoodCategoryEating[index],
                                        isLastHalf[index]
                                    )
                                )
                                index % 2 != 0 -> FoodCategory(
                                    icon = pair.first,
                                    description = pair.second,
                                    modifier = Modifier.background(Color.LightGray),
                                    data = EatingData(
                                        foodCategoryEating[index],
                                        maxFoodCategoryEating[index],
                                        isLastHalf[index]
                                    )
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
        PersonalData("Diego")
    }

    @Composable
    fun MyParagraph(text: String) {
        Text(text = "$text")
        Spacer(modifier = Modifier.height(16.dp))
    }

}

