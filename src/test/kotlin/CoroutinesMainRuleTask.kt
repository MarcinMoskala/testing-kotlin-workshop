@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.jupiter.api.extension.TestInstancePostProcessor
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ExampleViewModel {
    var loading = false
    var data: List<String> = emptyList()
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun start() {
        scope.launch {
            loading = true
            delay(1000)
            data = listOf("A", "B", "C")
            loading = false
        }
    }
}

//class MainCoroutineExtension1Test {
//
//    @JvmField
//    @RegisterExtension
//    var coroutineExtension = MainCoroutineExtension1()
//
//    lateinit var viewModel: ExampleViewModel
//
//    @BeforeEach
//    fun setup() {
//        viewModel = ExampleViewModel()
//    }
//
//    @Test
//    fun `should display loading`() {
//        viewModel.start()
//
//        assertFalse(viewModel.loading)
//        coroutineExtension.scheduler.runCurrent()
//        assertTrue(viewModel.loading)
//        coroutineExtension.scheduler.advanceTimeBy(1000)
//        assertTrue(viewModel.loading)
//        coroutineExtension.scheduler.runCurrent()
//        assertFalse(viewModel.loading)
//    }
//
//    @Test
//    fun `should load data`() {
//        viewModel.start()
//        assertEquals(listOf(), viewModel.data)
//        coroutineExtension.scheduler.advanceUntilIdle()
//        assertEquals(listOf("A", "B", "C"), viewModel.data)
//    }
//}
//
//class MainCoroutineExtension2Test: ExtendWithMainCoroutine {
//    override lateinit var scheduler: TestCoroutineScheduler
//    override lateinit var dispatcher: TestDispatcher
//
//    lateinit var viewModel: ExampleViewModel
//
//    @BeforeEach
//    fun setup() {
//        viewModel = ExampleViewModel()
//    }
//
//    @Test
//    fun `should display loading`() {
//        viewModel.start()
//
//        assertFalse(viewModel.loading)
//        scheduler.runCurrent()
//        assertTrue(viewModel.loading)
//        scheduler.advanceTimeBy(1000)
//        assertTrue(viewModel.loading)
//        scheduler.runCurrent()
//        assertFalse(viewModel.loading)
//    }
//
//    @Test
//    fun `should load data`() {
//        viewModel.start()
//        assertEquals(listOf(), viewModel.data)
//        scheduler.advanceUntilIdle()
//        assertEquals(listOf("A", "B", "C"), viewModel.data)
//    }
//}
//
//@ExtendWith(MainCoroutineExtension3::class)
//class MainCoroutineExtension3Test {
//
//    lateinit var viewModel: ExampleViewModel
//
//    @BeforeEach
//    fun setup() {
//        viewModel = ExampleViewModel()
//    }
//
//    @Test
//    fun `should display loading`(scheduler: TestCoroutineScheduler) {
//        viewModel.start()
//
//        assertFalse(viewModel.loading)
//        scheduler.runCurrent()
//        assertTrue(viewModel.loading)
//        scheduler.advanceTimeBy(1000)
//        assertTrue(viewModel.loading)
//        scheduler.runCurrent()
//        assertFalse(viewModel.loading)
//    }
//
//    @Test
//    fun `should load data`(scheduler: TestCoroutineScheduler) {
//        viewModel.start()
//        assertEquals(listOf(), viewModel.data)
//        scheduler.advanceUntilIdle()
//        assertEquals(listOf("A", "B", "C"), viewModel.data)
//    }
//}