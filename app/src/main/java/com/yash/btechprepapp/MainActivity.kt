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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BTechPrepApp() {

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
                        .clickable { },
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