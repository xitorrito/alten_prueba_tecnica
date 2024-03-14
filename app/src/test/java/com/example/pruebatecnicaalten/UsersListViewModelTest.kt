package com.example.pruebatecnicaalten

import com.example.pruebatecnicaalten.domain.model.Id
import com.example.pruebatecnicaalten.domain.model.User
import com.example.pruebatecnicaalten.domain.model.UsersResult
import com.example.pruebatecnicaalten.domain.usecases.GetUsersUseCase
import com.example.pruebatecnicaalten.presentation.ui.userslist.UsersListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class UsersListViewModelTest {

    @RelaxedMockK
    private lateinit var getUsersUseCase: GetUsersUseCase
    private lateinit var usersListViewModel: UsersListViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        val userResult = UsersResult(
            results = listOf(
                User(id = Id(value = "1")),
                User(id = Id(value = "2")),
                User(id = Id(value = "2")),
            )
        )
        coEvery { getUsersUseCase(any(), any()) } returns userResult
        usersListViewModel = UsersListViewModel(getUsersUseCase)

    }


    @Test
    fun `GetUser is called on Init`() = runTest {
        coVerify { getUsersUseCase(any(), any()) }
    }

    @Test
    fun `Get next page when list scrolled to bottom`() = runTest {
        usersListViewModel.onScrollEnd()
        usersListViewModel.onScrollEnd()

        coVerifyOrder {
            getUsersUseCase(1, any())
            getUsersUseCase(2, any())
            getUsersUseCase(3, any())
        }
    }


    @Test
    fun `Avoid duplicate items`() = runTest {
        //Mock returns 3 users but 2 have same id
        assertEquals(2, usersListViewModel.usersStateFlow.value.size)
    }

}