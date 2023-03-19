package com.example.avivgroup.home.remote

import com.example.avivgroup.ApiAbstract
import com.example.avivgroup.StringGenerator
import com.example.avivgroup.TestCoroutineRule
import com.example.avivgroup.data.remote.ApiInterface
import com.example.avivgroup.data.remote.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ApiServiceTest : ApiAbstract<ApiInterface>() {

    private lateinit var apiService: ApiInterface

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        apiService = createService(ApiInterface::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun `given ApiInterface when the loadPhotos is triggered tehn it returns the photos`() = runBlocking {
        // Given
        enqueueResponse("photos_response_200.json")

        // Invoke
        val response = apiService.loadProperties(StringGenerator.getRandomString(20))
        val responseBody = requireNotNull((response as ApiResponse.ApiSuccessResponse).data)
        mockWebServer.takeRequest()

        // Then
        assertThat(responseBody.hits?.get(0)?.id, `is`(5352510))
        assertThat(responseBody.hits?.get(0)?.previewURL, `is`("https://cdn.pixabay.com/photo/2020/06/29/12/11/snail-5352510_150.jpg"))
        assertThat(responseBody.hits?.get(0)?.downloads, `is`(27100))
        assertThat(responseBody.hits?.get(0)?.views, `is`(30236))
        assertThat(responseBody.hits?.get(0)?.largeImageURL, `is`("https://pixabay.com/get/gf5528b88e1e5d56623b80f8249a24ef95939d08a2893fb71069a158def9954072cb9614c2e4a42bd01ef5c086c2e7edfb71e2e5d5a636710845b1e1e15557b26_1280.jpg"))
    }
}
