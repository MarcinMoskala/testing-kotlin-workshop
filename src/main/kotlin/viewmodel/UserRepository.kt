package viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import java.util.*

interface UserRepository {
    suspend fun getUser(): UserData
}

interface NewsRepository {
    suspend fun getNews(): List<News>
}

data class UserData(val name: String)
data class News(val date: Date)

interface LiveData<T> {
    val value: T?
}

class MutableLiveData<T> : LiveData<T> {
    override var value: T? = null
}

abstract class ViewModel()

class MainViewModel(
    private val userRepo: UserRepository,
    private val newsRepo: NewsRepository
) : BaseViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName
    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news

    fun onCreate() {
        viewModelScope.launch {
            val user = userRepo.getUser()
            _userName.value = user.name
        }
        viewModelScope.launch {
            _news.value = newsRepo.getNews().sortedByDescending { it.date }
        }
    }
}

abstract class BaseViewModel : ViewModel() {
    private val context = Dispatchers.Main.immediate + SupervisorJob()
    val viewModelScope = CoroutineScope(context)

    fun onDestroy() {
        context.cancelChildren()
    }
}