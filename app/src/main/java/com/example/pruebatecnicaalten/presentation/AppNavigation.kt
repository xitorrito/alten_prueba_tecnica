package com.example.pruebatecnicaalten.presentation

enum class Screen {
    USER_LIST,
    USER_DETAIL
}

sealed class NavItem(val route: String) {
    data object UserList : NavItem(Screen.USER_LIST.name)
    data object UserDetail : NavItem(Screen.USER_DETAIL.name)
}
