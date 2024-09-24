package com.haseeb.measuremate.presentation.add_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haseeb.measuremate.domain.model.predefinedBodyParts
import com.haseeb.measuremate.presentation.component.Dialog


@Composable
fun AddItemScreen(
    onBackButtonClick: () -> Unit,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    var isAddNewItemDialogOpen by rememberSaveable { mutableStateOf(false) }

    Dialog(
        onDismissRequest = {
            isAddNewItemDialogOpen = false
        },
        onConfirm = {
            isAddNewItemDialogOpen = false
        },
        title = "Add New Item",
        confirmButtonText = "Save",
        body = {
            OutlinedTextField(value = "", onValueChange ={} )
        },
        isOpen = isAddNewItemDialogOpen
    )
    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        AddItemTopBar(
            onAddIconClick = { isAddNewItemDialogOpen = true },
            onBackButtonClick = {onBackButtonClick()}
        )
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 300.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            items(predefinedBodyParts){
                ItemCart(
                    name = it.name,
                    onClick = {},
                    checked = it.isActive,
                    onCheckedChange = { }
                )
            }
        }
    }
    
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AddItemTopBar(
    modifier: Modifier = Modifier,
    onAddIconClick : () -> Unit,
    onBackButtonClick : () -> Unit
){
    TopAppBar(
        modifier = modifier,
        windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        title = { Text(text = "Add Item") },
        navigationIcon = {
            IconButton(onClick = {onBackButtonClick()}) {
                Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Navigate back" )
            }

        },
        actions = {
            IconButton(onClick = {onAddIconClick()}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add New Item" )
            }

        }
    )
}

@Composable
private fun ItemCart(
    modifier: Modifier = Modifier,
    name : String,
    onClick : () -> Unit,
    checked : Boolean,
    onCheckedChange : () -> Unit
){
    Box(
        modifier = modifier.clickable { onClick() }
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier.weight(8f),
                text = name,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(checked = checked, onCheckedChange = { onCheckedChange() })
        }
    }
}





@Preview
@Composable
private fun AddItemScreenPreview() {
    AddItemScreen({}, PaddingValues(0.dp), SnackbarHostState())

}