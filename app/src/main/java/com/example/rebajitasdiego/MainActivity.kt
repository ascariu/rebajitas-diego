package com.example.rebajitasdiego

import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants

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


        val foodCategoryConsumption = arrayOf(1, 2, 3, 5, 2, 6)
        val maxFoodCategoryConsumption = arrayOf(3, 3, 6, 5, 3, 3)
        val isHalf = arrayOf(false, true, true, true, false, false)

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
                                index % 2 == 0 -> FoodRow(
                                    icon = pair.first,
                                    description = pair.second,
                                    modifier = Modifier.background(Color.Gray),
                                    data = ConsumptionData(
                                        foodCategoryConsumption[index],
                                        maxFoodCategoryConsumption[index],
                                        isHalf[index]
                                    )
                                )
                                index % 2 != 0 -> FoodRow(
                                    icon = pair.first,
                                    description = pair.second,
                                    modifier = Modifier.background(Color.LightGray),
                                    data = ConsumptionData(
                                        foodCategoryConsumption[index],
                                        maxFoodCategoryConsumption[index],
                                        isHalf[index]
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

    @Composable
    fun FoodRow(icon: Painter, description: String, modifier: Modifier, data: ConsumptionData) {
        val maxValue = Math.max(data.consumptionValue, data.maxConsumptionValue)
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
                        GetIconForState(
                            data.getCircle(i)
                        )
                    }
                }
            }


            Row {
                Icon(
                    painter = removeIcon,
                    contentDescription = description,
                    modifier = Modifier.background(color = Color.Red, shape = CircleShape)
                )
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
                Icon(
                    painter = addIcon,
                    contentDescription = description,
                    modifier = Modifier.background(color = Color.Green, shape = CircleShape),
                )


            }
        }
    }

    enum class ConsumptionState {
        EMPTY,
        HALF,
        FULL;
    }

    @Composable
    fun GetIconForState(state: ConsumptionState) =
        when (state) {
            ConsumptionState.EMPTY -> EmptyCircle()
            ConsumptionState.HALF -> HalfFilledCircle()
            ConsumptionState.FULL -> FilledCircle()
        }

    data class ConsumptionData(
        val consumptionValue: Int,
        val maxConsumptionValue: Int,
        val isLastHalf: Boolean
    ) {

        fun getCircle(currentConsumptionValue: Int): ConsumptionState {
            return when {
                currentConsumptionValue <= maxConsumptionValue && currentConsumptionValue < consumptionValue -> ConsumptionState.FULL
                currentConsumptionValue <= maxConsumptionValue && currentConsumptionValue > consumptionValue -> ConsumptionState.EMPTY
                currentConsumptionValue > maxConsumptionValue && currentConsumptionValue < consumptionValue -> ConsumptionState.FULL
                currentConsumptionValue == currentConsumptionValue -> getLastCircle(
                    currentConsumptionValue
                )
                else -> getEmptyCircle()
            }
        }

        fun getLastCircle(currentConsumptionValue: Int): ConsumptionState {
            return when {
                currentConsumptionValue < maxConsumptionValue -> ConsumptionState.FULL
                currentConsumptionValue == maxConsumptionValue && isLastHalf -> ConsumptionState.HALF
                currentConsumptionValue == maxConsumptionValue && !isLastHalf -> ConsumptionState.FULL
                currentConsumptionValue > maxConsumptionValue -> ConsumptionState.EMPTY
                else -> error("Eso no possible ser")
            }
        }

        fun getEmptyCircle(): ConsumptionState {
            return ConsumptionState.EMPTY
        }
    }

    @Composable
    fun MyColumn(counter: Int, painter: Painter) {
        Image(painter = painter, contentDescription = "whatever")
        Column(
            modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var name by remember { mutableStateOf("") }
            MyParagraph("Counter of $name: $counter")
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })

        }
    }

    @Composable
    fun MyCanvas() {
        Canvas(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .padding(start = 16.dp)
        ) {
            drawCircle(
                color = Color.Black,
                radius = size.width,
                center = center,
            )
            drawArc(
                color = Color.Red,
                startAngle = 90f,
                sweepAngle = 180f,
                useCenter = true,
                size = Size(size.width, size.height)
            )
        }
    }


    @Composable
    fun EmptyCircle() {
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp)
                    .border(width = 1.dp, color = Color.White, shape = CircleShape)
            )
        }
    }


    @Composable
    fun FilledCircle() {
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp)
                    .background(color = Color.White, shape = CircleShape)
            )
        }
    }

    @Composable
    fun HalfFilledCircle() {
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp)
                    .border(width = 1.dp, color = Color.White, shape = CircleShape)
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(color = Color.White, shape = CircleShape)
                        .align(
                            Alignment.Center
                        )
                )
            }
        }
    }

}

