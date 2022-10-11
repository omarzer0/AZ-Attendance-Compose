package az.zero.azattendance.presentation.screens.auth.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import az.zero.azattendance.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        return setFragmentContent {
            TypeScreen(navController = findNavController())
        }
    }
}

@Composable
fun TypeScreen(
    navController: NavController,
) {
    TypeScreen(onOrgBtnClick = {
        navController.navigate(TypeFragmentDirections.actionTypeFragmentToOrgLoginFragment())
    }, onAttendantBtnClick = {
        navController.navigate(TypeFragmentDirections.actionTypeFragmentToAttendantLoginFragment())
    })
}

@Composable
fun TypeScreen(
    onOrgBtnClick: () -> Unit,
    onAttendantBtnClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            modifier = Modifier.width(250.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            onClick = onOrgBtnClick
        ) {
            Text(
                text = "Organization",
                style = MaterialTheme.typography.body1.copy(color = Color.White),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.width(250.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            onClick = onAttendantBtnClick
        ) {
            Text(
                text = "Attendant",
                style = MaterialTheme.typography.body1.copy(color = Color.White),
            )
        }
    }
}