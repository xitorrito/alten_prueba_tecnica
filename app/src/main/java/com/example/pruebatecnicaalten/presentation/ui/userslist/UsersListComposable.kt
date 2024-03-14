package com.example.pruebatecnicaalten.presentation.ui.userslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.pruebatecnicaalten.R
import com.example.pruebatecnicaalten.domain.model.User
import com.example.pruebatecnicaalten.presentation.NavItem
import com.example.pruebatecnicaalten.presentation.ui.composables.OnBottomReached
import com.example.pruebatecnicaalten.presentation.ui.composables.UsersTopAppBar
import com.example.pruebatecnicaalten.presentation.ui.theme.DarkGray
import com.example.pruebatecnicaalten.presentation.ui.theme.LightGray
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun UsersListComposable(navController: NavHostController) {

    val usersListViewModel: UsersListViewModel = hiltViewModel()
    val usersList by usersListViewModel.usersStateFlow.collectAsState()
    val showSearchUsers by usersListViewModel.searchUserVisibleStateFlow.collectAsState()

    Scaffold(
        topBar = {
            UsersTopAppBar(
                title = stringResource(R.string.topbar_contacts),
                onBackPressed = { navController.navigateUp() },
                onSearchUserClicked = usersListViewModel::onSearchUsersClicked
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {

            if (showSearchUsers) {
                SearchUsers(usersListViewModel::searchUser)
            }

            UsersListColumn(
                usersList = usersList,
                onScrollEnd = {
                    usersListViewModel.onScrollEnd()
                },
                onUserClicked = { user ->
                    val userJson = URLEncoder.encode(Gson().toJson(user), "utf-8")
                    navController.navigate("${NavItem.UserDetail.route}/$userJson")
                }
            )
        }
    }
}

@Composable
fun UsersListColumn(
    usersList: List<User>,
    onScrollEnd: () -> Unit,
    onUserClicked: (user: User?) -> Unit
) {

    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 20.dp),
        state = lazyListState
    ) {
        itemsIndexed(usersList) { index, item ->
            UserListItem(item, onUserClicked)
        }
    }

    lazyListState.OnBottomReached {
        onScrollEnd()
    }
}

@Composable
fun UserListItem(user: User, onUserClicked: (user: User?) -> Unit) {
    Box(
        Modifier.clickable() {
            onUserClicked(user)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .padding(start = 16.dp)

        ) {

            AsyncImage(
                model = user.picture?.thumbnail,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column {

                Row(Modifier.padding(end = 20.dp)) {

                    Column(Modifier.weight(1f)) {

                        Text(
                            text = "${user.name?.first} ${user.name?.last}",
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = user.email ?: "",
                            overflow = TextOverflow.Ellipsis,
                            color = DarkGray
                        )
                    }

                    Icon(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        imageVector = ImageVector.vectorResource(id = R.drawable.icon_arrow),
                        contentDescription = null
                    )
                }

                Divider(thickness = 1.dp, color = LightGray, modifier = Modifier.padding(top = 20.dp))

            }
        }
    }
}

@Composable
fun SearchUsers(searchUser: (searchValue: String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        modifier = Modifier
            .padding(horizontal = 25.dp)
            .padding(bottom = 10.dp)
            .fillMaxWidth(),
        value = text,
        placeholder = {
            Text(stringResource(R.string.search))
        },
        maxLines = 1,
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "") },
        trailingIcon = {
            Icon(imageVector = Icons.Filled.Clear, contentDescription = "", modifier = Modifier.clickable {
                text = TextFieldValue("")
                searchUser(text.text)
            })
        },
        onValueChange = { newText ->
            text = newText
            searchUser(text.text)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                searchUser(text.text)
                keyboardController?.hide()
            }
        ),
    )
}
