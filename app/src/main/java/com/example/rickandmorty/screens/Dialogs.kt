package com.example.rickandmorty.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.mmodel.CastMember

@Composable
fun DeleteCharacterDialog(
    character: CastMember?,
    onDismiss: () -> Unit,
    onConfirmDelete: (CastMember) -> Unit
) {
    if (character != null){
        AlertDialog(
            onDismissRequest = {onDismiss() },
            title = { Text (text = "Delete Character") },
            text = { Text (text = "Are you sure you want to send ${character.name} into the void?") },
            confirmButton = {
                TextButton( onClick = {onConfirmDelete(character) } ) {
                    Text( text = "Delete", color = Color.Red)
                }
            }
        )
    }
} // END DELETE CHARACTER DIALOG

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCharacterDialog(
    character: CastMember?,
    onDismiss: () -> Unit,
    onConfirmEdit: (name: String, species: String, gender: String) -> Unit // Pass new title & description as parameters
) {
    if (character != null) {
        var newName by remember { mutableStateOf(character.name) }
        var newSpecies by remember { mutableStateOf(character.species) }
        var newGender by remember { mutableStateOf(character.gender) }

        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Edit Character") },
            text = {
                Column {
                    Text(text = "Update character details:")
                    Spacer(modifier = Modifier.height(8.dp))

                    // name input field
                    OutlinedTextField(
                        value = newName,
                        onValueChange = { newName = it },
                        label = { Text(text = "Name") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // species input field
                    OutlinedTextField(
                        value = newSpecies,
                        onValueChange = { newSpecies = it },
                        label = { Text(text = "Species") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // gender input field
                    OutlinedTextField(
                        value = newGender,
                        onValueChange = { newGender = it },
                        label = { Text(text = "Gender") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmEdit( newName, newSpecies, newGender)
                    }
                ) {
                    Text("Save", color = Color.Green)
                }
            }
        ) // END EDIT CHARACTER DIALOG
    }
}