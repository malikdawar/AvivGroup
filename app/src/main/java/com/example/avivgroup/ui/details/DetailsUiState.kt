package com.example.avivgroup.ui.details

sealed class DetailsUiState

object LoadingState : DetailsUiState()
object ContentState : DetailsUiState()
class ErrorState(val message: String) : DetailsUiState()
