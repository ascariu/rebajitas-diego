package com.example.rebajitasdiego

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
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
import com.example.rebajitasdiego.db.RebajitasDatabase
import com.example.rebajitasdiego.db.entities.UserProfileEntity
import com.example.rebajitasdiego.mappers.isMale
import com.example.rebajitasdiego.mappers.toUserProfile
import com.example.rebajitasdiego.models.EatingData
import com.example.rebajitasdiego.models.FoodCategory
import com.example.rebajitasdiego.models.Gender
import com.example.rebajitasdiego.models.UserProfile
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

        val user by mainViewModel.userProfile.collectAsState(initial = null)
        val foodCategoriesData by mainViewModel.foodCategoriesData.collectAsState(emptyList())
        user?.let { user ->
            FoodMainContent(
                user.toUserProfile(),
                icons,
                foodCategoriesData,
                onChangeGender = { isMale ->
                    mainViewModel
                        .onChangeGender(user.copy(gender = if (isMale) Gender.Male else Gender.Female))
                },
                onClearAllData = { mainViewModel.onClearAllData() },
                onAddEating = { data -> mainViewModel.onAddEating(data) },
                onRemoveEating = { data -> mainViewModel.onRemoveEating(data) }
            )
        }

    }

    @Composable
    private fun FoodMainContent(
        userProfile: UserProfile,
        icons: List<Pair<Painter, String>>,
        foodCategoriesData: List<EatingData>,
        onChangeGender: (Boolean) -> Unit,
        onClearAllData: () -> Unit,
        onAddEating: (EatingData) -> Unit,
        onRemoveEating: (EatingData) -> Unit
    ) {
        val materialBlue700 = Color(0xFF1976D2)

        MaterialTheme() {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = { MyParagraph("Rebajitas ${userProfile.name}") },
                        backgroundColor = materialBlue700
                    )
                },
                drawerContent = {
                    Column() {
                        Row(modifier = Modifier.padding(start = 16.dp, top = 32.dp)) {
                            Text(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .defaultMinSize(minWidth = 64.dp),
                                text = "${userProfile.gender}"
                            )
                            Switch(checked = userProfile.isMale, onCheckedChange = onChangeGender)
                        }
                        Button(
                            modifier = Modifier.padding(top = 32.dp, start = 16.dp),
                            onClick = { -> onClearAllData() }) {
                            Text(text = "Limpiar datos")
                        }
                    }
                },
                content = {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(
                                rememberScrollState()
                            )
                            .padding(bottom = 64.dp)
                    ) {
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
                        Row(modifier = Modifier.padding(start = 16.dp, top = 32.dp)) {
                            Text(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .defaultMinSize(minWidth = 64.dp),
                                text = "${userProfile.gender}"
                            )
                            Switch(checked = userProfile.isMale, onCheckedChange = onChangeGender)
                        }
                        Button(
                            modifier = Modifier.padding(top = 32.dp, start = 16.dp),
                            onClick = { -> onClearAllData() }) {
                            Text(text = "Limpiar datos")
                        }
                    }

                },
                bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("Rebajitas Diego") } }
            )
        }

    }


    @Composable
    fun MyParagraph(text: String) {
        Text(text = "$text")
        Spacer(modifier = Modifier.height(16.dp))
    }

}

