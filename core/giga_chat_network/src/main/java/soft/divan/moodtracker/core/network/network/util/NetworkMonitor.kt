package soft.divan.moodtracker.core.network.network.util

import kotlinx.coroutines.flow.Flow

/** Утилита для отчетности статус подключения приложений */
interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}
