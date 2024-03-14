package com.example.pruebatecnicaalten.presentation.ui.userslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnicaalten.domain.model.User
import com.example.pruebatecnicaalten.domain.usecases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _usersStateFlow = MutableStateFlow<List<User>>(mutableListOf())
    val usersStateFlow: StateFlow<List<User>> = _usersStateFlow.asStateFlow()
    private var usersList = listOf<User>() //list of original users without any filter

    private val _searchUserVisibleStateFlow = MutableStateFlow(false)
    val searchUserVisibleStateFlow: StateFlow<Boolean> = _searchUserVisibleStateFlow.asStateFlow()

    private val resultsCount = 20
    private var page = 1

    private var searchValue = ""

    init {
        getUsers(page)
    }

    private fun getUsers(page: Int) {
        viewModelScope.launch {
            val usersResult = getUsersUseCase(page = page, results = resultsCount)
            if (!usersResult?.results.isNullOrEmpty()) {
                usersList += usersResult!!.results
                usersList = usersList.distinctBy { it.id?.value }  //Remove duplicate users
                _usersStateFlow.value = usersList

                if(searchValue.isNotEmpty()){
                    searchUser(searchValue)
                }
            }
        }
    }

    fun onScrollEnd() {
        page++
        getUsers(page)
    }

    fun onSearchUsersClicked() {
        _searchUserVisibleStateFlow.value = !searchUserVisibleStateFlow.value
        if (!searchUserVisibleStateFlow.value){
            searchUser("")
        }
    }

    fun searchUser(searchValue: String) {
        this.searchValue = searchValue
        if (searchValue.isEmpty()) {
            _usersStateFlow.value = usersList
        } else {
            _usersStateFlow.value =
                usersList.filter {
                    "${it.name?.first} ${it.name?.last}".lowercase().contains(searchValue.lowercase())
                }
        }

    }
}