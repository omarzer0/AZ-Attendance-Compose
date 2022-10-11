package az.zero.azattendance.domain.models

data class Attendant(
    val id: String = "",
    val name: String = "",
    val events: List<Event> = emptyList(),
)