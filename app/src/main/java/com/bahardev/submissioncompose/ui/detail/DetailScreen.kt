package com.bahardev.submissioncompose.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.bahardev.submissioncompose.di.Injection
import com.bahardev.submissioncompose.model.Anime
import com.bahardev.submissioncompose.ui.ViewModelFactory
import com.bahardev.submissioncompose.ui.common.UiState

@ExperimentalMaterial3Api
@Composable
fun DetailScreen(
    id: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToHome: () -> Unit
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getHeroById(id)
            }
            is UiState.Success -> {
                DetailContent(data = uiState.data, modifier = modifier, navigateToHome = navigateToHome)
            }
            is UiState.Error -> {}
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    data: Anime,
    navigateToHome: () -> Unit
) {
    Scaffold(
        topBar = {
            Column(
                modifier = modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colorScheme.inversePrimary)
                    .clickable(onClick = navigateToHome)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = modifier
                            .size(20.dp)
                    )
                    Text(text = "Kembali", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
            }
        }
    ) {
        val padding = it

        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 52.dp)
                .padding(horizontal = 10.dp)
        ) {
            Column(
                modifier = modifier
                    .clip(RoundedCornerShape(12.dp))
            ){
                AsyncImage(
                    model = data.photoUrl,
                    contentDescription = null,
                    modifier = modifier
                        .height(380.dp)
                        .background(color = MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = modifier.padding(10.dp))
            Column {
                Text(
                    text = data.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }
            Spacer(modifier = modifier.padding(10.dp))
            Text(text = data.description)
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
@ExperimentalMaterial3Api
fun DetailContentPreview(){
    DetailContent(data = Anime("1", "BaharDev", ""), navigateToHome = {})
}