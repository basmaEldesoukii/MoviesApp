package com.banquemisr.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Base class for [ViewModel] instances
 */
abstract class BaseViewModel<Intent : UiIntent, State : UiState> : ViewModel() {

    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState() : State

    val currentState: State
        get() = uiState.value

    private val _uiState : MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _intent : MutableSharedFlow<Intent> = MutableSharedFlow()
    val intent = _intent.asSharedFlow()


    init {
        subscribeIntents()
    }

    /**
     * Start listening to Intent
     */
    private fun subscribeIntents() {
        viewModelScope.launch {
            intent.collect {
                handleIntent(it)
            }
        }
    }

    /**
     * Handle each intent
     */
    abstract fun handleIntent(intent : Intent)


    /**
     * Set new Intent
     */
    fun setIntent(intent : Intent) {
        viewModelScope.launch { _intent.emit(intent) }
    }


    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

}