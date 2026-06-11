package com.yash.btechprepapp

import android.os.Bundle
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

            SubjectScreen(subjectName)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val subjects = listOf(
        "Data Structures",
        "Operating Systems",
        "Database Management System",
        "Computer Networks",
        "Theory of Computation",
        "Software Engineering"
    )

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
                Text(
                    text = "Computer Science Subjects",
                    fontSize = 22.sp
                )
            }

            items(subjects) { subject ->

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
fun SubjectScreen(subjectName: String) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(subjectName)
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = subjectName,
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "📹 Videos",
                    modifier = Modifier.padding(20.dp),
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "📄 Notes",
                    modifier = Modifier.padding(20.dp),
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "❓ PYQs",
                    modifier = Modifier.padding(20.dp),
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "💬 Discussions",
                    modifier = Modifier.padding(20.dp),
                    fontSize = 18.sp
                )
            }
        }
    }
}