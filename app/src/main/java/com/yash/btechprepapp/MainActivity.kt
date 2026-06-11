package com.yash.btechprepapp

import android.os.Bundle
import androidx.compose.runtime.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.compose.material3.ExperimentalMaterial3Api

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BTechPrepApp()
        }
    }
}

@Composable
fun BTechPrepApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(navController)
        }

        composable("subject/{name}") { backStackEntry ->

            val subjectName =
                backStackEntry.arguments?.getString("name") ?: ""

            SubjectScreen(
                subjectName = subjectName,
                navController = navController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    var searchText by remember {
        mutableStateOf("")
    }

    val subjects = listOf(
        "Data Structures",
        "Operating Systems",
        "Database Management System",
        "Computer Networks",
        "Theory of Computation",
        "Software Engineering"
    )
    val filteredSubjects = subjects.filter {
        it.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("B.Tech Prep App")
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            item {

                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    label = {
                        Text("Search Subject")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
            item {
                Text(
                    text = "Computer Science Subjects",
                    fontSize = 22.sp
                )
            }

            items(filteredSubjects) { subject ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("subject/$subject")
                        },
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "📚",
                            fontSize = 28.sp
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = subject,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreen(
    subjectName: String,
    navController: NavHostController
) {
    var commentText by remember {
        mutableStateOf("")
    }

    var comments by remember {

        mutableStateOf(

            listOf(
                "Yash: Important topic for exam?",
                "Faculty: Focus on Unit 3 and Unit 4."
            )
        )
    }

    var showSolution1 by remember { mutableStateOf(false) }
    var showSolution2 by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(subjectName)
                },
                navigationIcon = {
                    TextButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Text("Back")
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {

                Text(
                    text = "📄 Notes",
                    fontSize = 24.sp
                )
            }

            item {

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text("Unit 1 Notes")

                        Text(
                            "Author: Faculty Notes",
                            fontSize = 12.sp
                        )

                        Button(
                            onClick = {
                                println("Downloading...")
                            }
                        ) {
                            Text("Download")
                        }
                    }
                }
            }

            item {

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text("Unit 2 Notes")

                        Text(
                            "Author: Faculty Notes",
                            fontSize = 12.sp
                        )

                        Button(
                            onClick = { }
                        ) {
                            Text("Download")
                        }
                    }
                }
            }

            item {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "❓ Previous Year Questions",
                    fontSize = 24.sp
                )
            }

            item {

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            "2024: Explain Binary Search Algorithm."
                        )

                        TextButton(
                            onClick = {
                                showSolution1 = !showSolution1
                            }
                        ) {
                            Text("View Solution")
                        }

                        if (showSolution1) {

                            Text(
                                "Binary Search works on sorted arrays and reduces search space by half in every iteration."
                            )
                        }
                    }
                }
            }


            item {

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            "2023: What is Time Complexity?"
                        )

                        TextButton(
                            onClick = {
                                showSolution2 = !showSolution2
                            }
                        ) {
                            Text("View Solution")
                        }

                        if (showSolution2) {

                            Text(
                                "Time Complexity measures the amount of time an algorithm takes relative to input size."
                            )
                        }
                    }
                }
            }
            item {

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "💬 Discussions",
                    fontSize = 24.sp
                )
            }
            items(comments) { comment ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = comment,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            item {

                OutlinedTextField(
                    value = commentText,
                    onValueChange = {
                        commentText = it
                    },
                    label = {
                        Text("Add Comment")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Button(
                    onClick = {

                        if (commentText.isNotBlank()) {

                            comments =
                                comments + "Student: $commentText"

                            commentText = ""
                        }
                    }
                ) {

                    Text("Send")
                }
            }
        }
    }
}