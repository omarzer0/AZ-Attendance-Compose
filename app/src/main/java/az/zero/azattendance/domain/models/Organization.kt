package az.zero.azattendance.domain.models

data class Organization(
    val id: String = "",
    val name: String = "",
    val events: List<Event> = emptyList(),
)