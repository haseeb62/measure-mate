package com.haseeb.measuremate.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.haseeb.measuremate.R
import com.haseeb.measuremate.ui.theme.customblue
import com.haseeb.measuremate.ui.theme.custompink

@Composable
fun ProfilePicPlaceholder(
    modifier: Modifier = Modifier,
    placeholderSize:Dp,
    borderWidth: Dp,
    padding: Dp,
    profilePictureUrl : String?
) {

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(profilePictureUrl).
        crossfade(true)
        .build()
    Box(
        modifier =
        modifier.size(placeholderSize).
        border(width =  borderWidth
            , brush = Brush.linearGradient
                (listOf( customblue, custompink)),
            shape = CircleShape).padding(padding)
        ,
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize().clip(CircleShape),
            model = imageRequest,
            contentDescription =  "Profile Picture Placeholder",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.app_logo),
            error = painterResource(id = R.drawable.app_logo)
            )
    }
}



@Preview
@Composable
private fun ProfilePicPlaceholderPreview(){
    ProfilePicPlaceholder(
        placeholderSize = 30.dp,
        borderWidth = 1.dp,
        padding = 3.dp,
        profilePictureUrl = null
    )
}