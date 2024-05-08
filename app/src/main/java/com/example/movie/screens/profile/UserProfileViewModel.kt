package com.example.movie.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.popular.User.GetAccountUseCase
import com.example.movie.domain.popular.User.GetSessionIdUseCase
import com.example.movie.domain.popular.User.GetUserTokenUseCase
import com.example.movie.model.UIState
import com.example.movie.model.UserAccount
import com.example.movie.model.UserTokenResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val  getSessionIdUseCase: GetSessionIdUseCase,
    private val getAccountUseCase: GetAccountUseCase
    ): ViewModel() {

        val userTokenState: MutableStateFlow<UIState<UserTokenResponse>> =
            MutableStateFlow(UIState.Loading())

       val userSessionState: MutableStateFlow<UIState<UserTokenResponse>> =
           MutableStateFlow(UIState.Loading())

       val userAccountState: MutableStateFlow<UIState<UserAccount>> =
        MutableStateFlow(UIState.Loading())

    fun getUserToken(){
        viewModelScope.launch {
            when( val response = getUserTokenUseCase.invoke()){
                is UIState.Success -> userTokenState.value = UIState.Success(response.data)
                is UIState.Error -> userTokenState.value = UIState.Error(response.error)
                is UIState.Empty -> userTokenState.value = UIState.Empty(title = response.title)
                is UIState.Loading -> userTokenState.value = UIState.Loading()

            }
        }
    }

    fun getSessionId(sessionId: String){
        viewModelScope.launch {
            when( val response = getSessionIdUseCase.invoke(sessionId)){
                is UIState.Success -> userSessionState.value = UIState.Success(response.data)
                is UIState.Error -> userSessionState.value = UIState.Error(response.error)
                is UIState.Empty -> userSessionState.value = UIState.Empty(title = response.title)
                is UIState.Loading -> userSessionState.value = UIState.Loading()

            }
        }
    }

    fun getUserAccount(requestToken: String){
        viewModelScope.launch {
            when( val response = getAccountUseCase.invoke(requestToken)){
                is UIState.Success -> userAccountState.value = UIState.Success(response.data)
                is UIState.Error -> userAccountState.value = UIState.Error(response.error)
                is UIState.Empty -> userAccountState.value = UIState.Empty(title = response.title)
                is UIState.Loading -> userAccountState.value = UIState.Loading()

            }
        }
    }

}