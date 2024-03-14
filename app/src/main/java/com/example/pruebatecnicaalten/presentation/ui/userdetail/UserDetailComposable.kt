package com.example.pruebatecnicaalten.presentation.ui.userdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.pruebatecnicaalten.R
import com.example.pruebatecnicaalten.domain.model.Picture
import com.example.pruebatecnicaalten.domain.model.User
import com.example.pruebatecnicaalten.presentation.ui.composables.UsersTopAppBar
import com.example.pruebatecnicaalten.presentation.ui.theme.DarkGray
import com.example.pruebatecnicaalten.presentation.ui.theme.LightGray


@Composable
fun UserDetailComposable(navController: NavHostController, user: User) {


    Box {
        Column {
            Box() {
                Image(
                    painterResource(R.drawable.user_banner),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .height(190.dp)
                        .fillMaxWidth()
                )
                UserCircleImage(
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 17.dp),
                    picture = user.picture
                )
            }

            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(17.dp)
                    .fillMaxWidth()
            ) {

                EditIcons(Modifier.align(Alignment.End))

                Spacer(modifier = Modifier.height(30.dp))

                UserDataFields(user)
            }
        }

        UsersTopAppBar(
            title = "${user.name?.first} ${user.name?.last}",
            onBackPressed = { navController.navigateUp() },
            textColor = Color.White,
            containerColor = Color.Transparent
        )
    }
}

@Composable
fun UserDataFields(user: User) {

    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        DataField(
            iconResource = R.drawable.icon_user_name,
            fieldTitle = stringResource(R.string.user_field_name),
            fieldValue = "${user.name?.first} ${user.name?.last} "
        )
        DataField(
            iconResource = R.drawable.icon_user_email,
            fieldTitle = stringResource(R.string.user_field_email),
            fieldValue = user.email
        )
        DataField(
            iconResource = R.drawable.icon_user_genre,
            fieldTitle = stringResource(R.string.user_field_genre),
            fieldValue = user.gender
        )
        DataField(
            iconResource = R.drawable.icon_user_calendar,
            fieldTitle = stringResource(R.string.user_field_date),
            fieldValue = user.registered?.date
        )
        DataField(
            iconResource = R.drawable.icon_user_phone,
            fieldTitle = stringResource(R.string.user_field_phone),
            fieldValue = user.phone
        )
    }
}

@Composable
fun DataField(iconResource: Int, fieldTitle: String, fieldValue: String?) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .padding(start = 16.dp)

    ) {

        Icon(
            modifier = Modifier
                .size(19.dp)
                .align(Alignment.CenterVertically),
            imageVector = ImageVector.vectorResource(id = iconResource),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(20.dp))

        Column {

            Row(Modifier.padding(end = 20.dp)) {

                Column(Modifier.weight(1f)) {

                    Text(
                        text = fieldTitle,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = fieldValue ?: "",
                        overflow = TextOverflow.Ellipsis,
                        color = DarkGray
                    )
                }
            }

            Divider(thickness = 1.dp, color = LightGray, modifier = Modifier.padding(top = 10.dp))

        }
    }
}

@Composable
fun EditIcons(modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(30.dp)) {
        Icon(
            modifier = Modifier.size(17.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_camera),
            contentDescription = null
        )
        Icon(
            modifier = Modifier.size(17.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_edit),
            contentDescription = null
        )
    }
}

@Composable
fun UserCircleImage(modifier: Modifier, picture: Picture?) {
    val height = 80.dp
    AsyncImage(
        model = picture?.medium,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .absoluteOffset(y = (height.value * 0.5F).dp)
            .clip(CircleShape)
            .size(80.dp)
            .border(width = 2.dp, color = Color.White, CircleShape)

    )
}
