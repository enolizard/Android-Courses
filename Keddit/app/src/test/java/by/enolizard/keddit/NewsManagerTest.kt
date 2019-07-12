package by.enolizard.keddit

import by.enolizard.keddit.api.*
import by.enolizard.keddit.commons.RedditNews
import by.enolizard.keddit.features.news.NewsManager
import io.reactivex.observers.TestObserver
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Call
import retrofit2.Response
import java.util.*

class NewsManagerTest {
    var testSub = TestObserver<RedditNews>()
    var apiMock = mock<NewsApi>()
    var callMock = mock<Call<RedditNewsResponse>>()

    @Before
    fun setup() {
        testSub = TestObserver()
        apiMock = mock()
        callMock = mock()
        `when`(apiMock.getNews(anyString(), anyString())).thenReturn(callMock)
    }

    @Test
    fun testSuccess_basic() {
        val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
        val response = Response.success(redditNewsResponse)
        `when`(callMock.execute()).thenReturn(response)

        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()
    }

    @Test
    fun testSuccess_checkOneNews() {
        val newsData = RedditNewsDataResponse(
            "author",
            "title",
            10,
            Date().time,
            "thumbnail",
            "url"
        )
        val newsResponse = RedditChildrenResponse(newsData)
        val reddiNewResponse = RedditNewsResponse(RedditDataResponse(listOf(newsResponse), null, null))
        val response = Response.success(reddiNewResponse)

        `when`(callMock.execute()).thenReturn(response)

        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()
//        assert(testSub.onNextEvents[0].news[0].author == newsData.author)
//        assert(testSub.onNextEvents[0].news[0].title == newsData.title)
    }

    @Test
    fun testError() {
        val responseError = Response.error<RedditNewsResponse>(
            500,
            ResponseBody.create(MediaType.parse("application/json"), "")
        )

        `when`(callMock.execute()).thenReturn(responseError)

        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        assert(testSub.errorCount() == 1)
    }
}

inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)
