package io.github.edwinchang24.salvage.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.edwinchang24.salvage.ui.RootNavGraph
import io.github.edwinchang24.salvage.ui.animation.FullScreenDialogTransitions
import io.github.edwinchang24.salvage.ui.components.ColorPicker
import io.github.edwinchang24.salvage.ui.components.presetColors
import io.github.edwinchang24.salvage.ui.destinations.NewTagPageDestination

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph
@Destination(style = NewTagPageTransitions::class)
@Composable
fun NewTagPage(navigator: DestinationsNavigator, viewModel: NewTagViewModel = hiltViewModel()) {
    var name by rememberSaveable { mutableStateOf("") }
    var color by rememberSaveable { mutableIntStateOf(presetColors[0]) }
    var description by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New tag") },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
            )
            Text("Color", style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(horizontal = 16.dp))
            ColorPicker(
                colorInt = color,
                onColorIntChange = { color = it },
                rowPadding = PaddingValues(horizontal = 16.dp)
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                minLines = 3,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.End),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
            ) {
                OutlinedButton(onClick = { navigator.navigateUp() }) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        viewModel.createTag(
                            name = name,
                            color = color,
                            description = description.takeIf { it.isNotBlank() }
                        )
                        navigator.navigateUp()
                    },
                    enabled = name.isNotBlank()
                ) {
                    Text("Done")
                }
            }
        }
    }
}

object NewTagPageTransitions : FullScreenDialogTransitions(NewTagPageDestination)
