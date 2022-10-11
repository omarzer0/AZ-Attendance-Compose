package az.zero.azattendance.domain.models

data class Event(
    val id: String = "",
    val name: String = "",
    val sessions: List<Session> = emptyList(),
)
