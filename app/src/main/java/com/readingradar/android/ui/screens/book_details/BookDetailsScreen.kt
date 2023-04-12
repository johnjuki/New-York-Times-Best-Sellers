package com.readingradar.android.ui.screens.book_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.readingradar.android.R

@Composable
fun BookDetailsRoute(
    isbn: String,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookDetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getBook(isbn)
    }
    val uiState = viewModel.uiState
    BookDetailsScreen(uiState, navigateUp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    uiState: BookDetailsUiState,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {

    if (uiState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val context = LocalContext.current
        val book = uiState.book
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = navigateUp) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    },
                    title = { Text(text = "") }
                )
            },
            modifier = modifier.fillMaxSize(),
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp)
                        .verticalScroll(rememberScrollState()),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Card(
                            elevation = CardDefaults.cardElevation(3.dp),
                            shape = RoundedCornerShape(0.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(width = 200.dp, height = 300.dp),
                                model = ImageRequest.Builder(context)
                                    .data(book.bookImage)
                                    .placeholder(R.drawable.placeholder)
                                    .crossfade(true)
                                    .build(),
                                error = painterResource(R.drawable.placeholder),
                                contentScale = ContentScale.FillBounds,
                                contentDescription = null,
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = book.title,
                            color = colorResource(R.color.titleColor),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                        Text(
                            text = book.contributor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = stringResource(R.string.overview), fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = uiState.description, modifier = Modifier.padding(bottom = 16.dp))
                }
            }
        }
    }
}
