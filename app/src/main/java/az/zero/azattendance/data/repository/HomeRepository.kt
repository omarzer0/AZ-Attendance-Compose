package az.zero.azattendance.data.repository

import az.zero.azattendance.common.*
import az.zero.azattendance.domain.models.Attendant
import az.zero.azattendance.domain.models.Event
import az.zero.azattendance.domain.models.Organization
import az.zero.azattendance.domain.models.Session
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.sql.Timestamp
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
) {

    fun createOrganization(name: String) = executeFlow {
        val id = firestore.collection(ORGANIZATION_ID).document().id
        val organization = Organization(id = id, name = name)
        firestore.collection(ORGANIZATION_ID).document(id).set(organization)
    }

    fun getOrganizationById(id: String) = executeFlow {
        firestore.collection(ORGANIZATION_ID).document(id).get().await()
            .toObject(Organization::class.java)
    }

    fun createEvent(name: String) = executeFlow {
        val id = firestore.collection(Event_ID).document().id
        val event = Event(id = id, name = name)
        firestore.collection(Event_ID).document(id).set(event).await()
    }

    fun getEventById(id: String) = executeFlow {
        firestore.collection(Event_ID).document(id).get().await()
            .toObject(Event::class.java)
    }

    fun createSession(name: String, date: Date) = executeFlow {
        val id = firestore.collection(SESSION_ID).document().id
        val session = Session(id = id, name = name, date = Timestamp(date.time))
        firestore.collection(SESSION_ID).document(id).set(session).await()
    }

    fun getSessionById(id: String) = executeFlow {
        firestore.collection(SESSION_ID).document(id).get().await()
            .toObject(Session::class.java)
    }

    fun createAttendant(name: String) = executeFlow {
        val id = firestore.collection(ATTENDANT_ID).document().id
        val attendant = Attendant(id = id, name = name)
        firestore.collection(ATTENDANT_ID).document(id).set(attendant).await()
    }

    fun getAttendantById(id: String) = executeFlow {
        firestore.collection(ATTENDANT_ID).document(id).get().await()
            .toObject(Attendant::class.java)
    }


    fun createEvent2(name: String) = executeFlow {
        val id = firestore.collection(Event_ID).document().id
        val event = Event(id = id, name = name)
        firestore.collection(Event_ID).document(id).set(event)
    }

    fun x() = executeFlowCallback(firestore.collection(Event_ID))

}