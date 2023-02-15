package flowtest

import ApiException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import java.time.Instant

class ObserveAppointmentsUseCase(
    private val appointmentsRepository: AppointmentsRepository
) {
    // should observe only updated
    // should ignore repeating values
    // should retry API exception
    // should not retry API exception with incorrect code
    // should not retry non-API exceptions
    fun observeAppointments(): Flow<List<Appointment>> = appointmentsRepository
        .observeAppointments()
        .filterIsInstance<AppointmentsUpdate>()
        .map { it.appointments }
        .distinctUntilChanged()
        .retry { it is ApiException && it.code in 500..599 }
}

interface AppointmentsRepository {
    fun observeAppointments(): Flow<AppointmentsEvent>
}

sealed class AppointmentsEvent
data class AppointmentsUpdate(val appointments: List<Appointment>): AppointmentsEvent()
object AppointmentsConfirmed: AppointmentsEvent()
data class Appointment(val title: String, val time: Instant)