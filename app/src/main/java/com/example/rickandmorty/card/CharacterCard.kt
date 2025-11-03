package com.example.rickandmorty.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmorty.mmodel.CastMember
import com.example.rickandmorty.R
import com.example.rickandmorty.api.RickAndMortyManager
import com.example.rickandmorty.db.AppDataBase
import com.example.rickandmorty.screens.DeleteCharacterDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CharacterCard(
    characterItem: CastMember,
    navController: NavController,
    onCharacterDeleted: () -> Unit
) {
    val context = LocalContext.current
    var showDeleteDialog by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .border(1.dp, Color.Black, shape= RectangleShape)
            .padding(2.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            // the link is like that bc if you look at the JSON its formatted like this: https://rickandmortyapi.com/api/character/avatar/2.jpeg
            AsyncImage(
                model = ImageRequest.Builder(
                    LocalContext.current
                ).data("https://rickandmortyapi.com/api/character/avatar/${characterItem.id}.jpeg").build(),
                contentDescription = characterItem.name
            )

            // Another way to write modifer = Modifier is to pass it directly
            Column(Modifier.padding(20.dp)) {
                characterItem.name?.let {
                    Text(
                        color = Color.Black,
                        text = it,
                        style = TextStyle(fontSize=16.sp),
                        maxLines = 1
                    )
                }

                characterItem.species?.let {
                    Text(
                        color = Color.Black,
                        text = "Species: ${characterItem.species ?: ""}",
                        style = TextStyle(fontSize=16.sp),
                        maxLines = 1
                    )
                }

                characterItem.gender?.let {
                    Text(
                        text = "Gender: ${characterItem.gender ?: ""}",
                        color = Color.Black,
                        style = TextStyle(fontSize=16.sp),
                        maxLines = 1
                    )
                }

                characterItem.status?.let {
                    Text(
                        color = Color.Black,
                        text = "Status: ${characterItem.status ?: ""}",
                        style = TextStyle(fontSize=16.sp),
                        maxLines = 1
                    )

                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                   DeleteButton( onClick = {showDeleteDialog = true} )
                }
            }
            // Add spacer
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    if (showDeleteDialog) {
        DeleteCharacterDialog(
            character = characterItem,
            onDismiss = { showDeleteDialog = false },
            onConfirmDelete = {
                CoroutineScope(Dispatchers.IO).launch {
                    val db = AppDataBase.getInstance(context)
                    db.dao().purgeCharacter(characterItem.id)
                    onCharacterDeleted()
                }
                showDeleteDialog = false
            }
        )
    }

}
@Composable
fun DeleteButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_delete_icon),
            contentDescription = "Redact character from database."
        )
    }
}