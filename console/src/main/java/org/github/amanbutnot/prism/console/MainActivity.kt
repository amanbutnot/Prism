package org.github.amanbutnot.prism.console

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.github.amanbutnot.prism.console.data.PrismServiceDiscovery
import org.github.amanbutnot.prism.console.data.PrismServiceInfo
import org.github.amanbutnot.prism.console.ui.theme.PrismTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrismTheme {
                val discService = PrismServiceDiscovery(packageManager)
                var discList by remember { mutableStateOf<List<PrismServiceInfo>>(emptyList()) }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Button(onClick = {
                        discList = discService.discover()
                    }) {
                        Text("Discover")
                    }
                    if (discList.isEmpty()) {
                        Text("No discovered packages search again")
                    } else {

                        LazyColumn {

                            items(discList) {
                                Column() {
                                    Text(it.packageName)
                                    Text(it.serviceClassName)
                                    HorizontalDivider(modifier = Modifier.padding(12.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}