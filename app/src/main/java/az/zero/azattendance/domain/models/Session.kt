package az.zero.azattendance.domain.models

import java.sql.Timestamp

data class Session(
    val id: String = "",
    val name: String = "",
    val date: Timestamp? = null,
    val attendees: List<Attendant> = emptyList(),
)
