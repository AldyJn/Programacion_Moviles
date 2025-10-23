package com.montoya.sem10_servicios.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montoya.sem10_servicios.data.model.User
import com.montoya.sem10_servicios.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class UserUiState {
    object Loading : UserUiState()
    data class Success(val users: List<User>) : UserUiState()
    data class Error(val message: String) : UserUiState()
}
class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    private var allUsers: List<User> = emptyList()

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadUsers()
    }
    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading

            try {
                val users = repository.getUsers()
                allUsers = users

                if (_searchQuery.value.isBlank()) {
                    _uiState.value = UserUiState.Success(users)
                } else {
                    filterUsers(_searchQuery.value)
                }
            } catch (e: Exception) {
                _uiState.value = UserUiState.Error(
                    e.message ?: "Error desconocido al cargar usuarios"
                )
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        filterUsers(query)
    }

    private fun filterUsers(query: String) {
        if (allUsers.isEmpty()) {
            return
        }

        val filteredUsers = if (query.isBlank()) {
            allUsers
        } else {
            allUsers.filter { user ->
                        user.name.contains(query, ignoreCase = true) ||
                        user.username.contains(query, ignoreCase = true) ||
                        user.email.contains(query, ignoreCase = true) ||
                        user.phone.contains(query, ignoreCase = true) ||
                        user.website.contains(query, ignoreCase = true)
            }
        }

        _uiState.value = UserUiState.Success(filteredUsers)
    }

    fun clearSearch() {
        _searchQuery.value = ""
        _uiState.value = UserUiState.Success(allUsers)
    }
}