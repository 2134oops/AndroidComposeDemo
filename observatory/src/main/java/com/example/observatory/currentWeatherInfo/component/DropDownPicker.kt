package com.example.observatory.currentWeatherInfo.component

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rc.base.util.getCustomColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownPicker(
    modifier: Modifier = Modifier,
    valueList: List<String>,
    selectedValue: String,
    onSelect: (location: String) -> Unit
) {

    val isExpended = remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpended.value,
        onExpandedChange = { isExpended.value = it },
    ) {
        TextField(
            modifier = modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
            readOnly = true,
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = getCustomColor().observatoryGrey,
                unfocusedContainerColor = getCustomColor().observatoryGrey,
                focusedIndicatorColor = getCustomColor().observatoryGrey,
                unfocusedIndicatorColor = getCustomColor().observatoryGrey,
            ),
            value = selectedValue,
            trailingIcon = {
                val icons = if (isExpended.value) {
                    Icons.Default.KeyboardArrowUp
                } else {
                    Icons.Default.KeyboardArrowDown
                }
                Icon(icons, contentDescription = null, tint = Color.Black)
            },
            onValueChange = {
            },
        )
        ExposedDropdownMenu(
            containerColor = getCustomColor().observatoryGrey,
            expanded = isExpended.value,
            onDismissRequest = { isExpended.value = false }
        ) {
            valueList.forEach { value ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = value,
                            color = Color.Black
                        )
                    },
                    onClick = {
                        onSelect.invoke(value)
                        isExpended.value = false
                    }
                )
            }
        }
    }
}

