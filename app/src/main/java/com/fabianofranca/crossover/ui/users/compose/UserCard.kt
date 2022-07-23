package com.fabianofranca.crossover.ui.users.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fabianofranca.crossover.R
import com.fabianofranca.crossover.data.model.UserModel

@Composable
fun UserCard(user: UserModel, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .wrapContentHeight()
    ) {
        UserImage(user.picture)
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp)
        ) {
            UserCardTitle(user.fullName)
            UserCardSubTitle(
                text = user.email,
                modifier = Modifier.padding(vertical = 2.dp)
            )
            UserCardSubTitle(user.phone)
        }
    }
}

@Composable
fun UserImage(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        placeholder = painterResource(id = R.drawable.placeholder),
        contentDescription = null,
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .composed { modifier }
    )
}

@Composable
fun UserCardTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6,
        modifier = modifier
    )
}

@Composable
fun UserCardSubTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.subtitle2,
        color = Color(0xff6d6d6d),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun UseCardPreview() {
    UserCard(user = Templates.user)
}
