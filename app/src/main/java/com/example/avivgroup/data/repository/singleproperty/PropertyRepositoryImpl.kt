package com.example.avivgroup.data.repository.singleproperty

import androidx.annotation.WorkerThread
import com.example.avivgroup.base.BaseRepository
import com.example.avivgroup.core.extensions.noNetworkErrorMessage
import com.example.avivgroup.core.extensions.somethingWentWrong
import com.example.avivgroup.data.DataState
import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.data.remote.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * This is an implementation of [PropertyRepository] to handle communication with [ApiInterface] server.
 * @author Malik Dawar
 */
class PropertyRepositoryImpl @Inject constructor(
    private val apiService: ApiInterface,
) : PropertyRepository, BaseRepository() {

    @WorkerThread
    override suspend fun loadProperty(propertyId: Int): Flow<DataState<PropertyModel>> {
        return flow {
            apiService.loadProperty(propertyId).apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it))
                    }
                }
                // handle the case when the API request gets an error response.
                // e.g. internal server error.
            }.onErrorSuspend {
                emit(DataState.error<PropertyModel>(message()))
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error<PropertyModel>(noNetworkErrorMessage()))
                } else {
                    emit(DataState.error<PropertyModel>(somethingWentWrong()))
                }
            }
        }
    }
}
