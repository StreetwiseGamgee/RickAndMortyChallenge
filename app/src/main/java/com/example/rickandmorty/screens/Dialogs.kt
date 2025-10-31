package com.example.rickandmorty.screens

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
}