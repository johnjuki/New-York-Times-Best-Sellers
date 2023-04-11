package com.readingradar.android.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.readingradar.android.R

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onBookCoverClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val homeUiState by viewModel.uiState.collectAsState()

    HomeScreen(homeUiState, onBookCoverClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    onBookCoverClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.best_sellers)) },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                )
            )
        },
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            when (homeUiState) {
                is HomeUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = stringResource(R.string.loading))
                    }
                }
                is HomeUiState.Success -> {
                    val bestSellersList = homeUiState.bestSellersLists
                    LazyColumn {
                        items(bestSellersList) { lists ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = lists.displayName,
                                    fontSize = 16.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(start = 16.dp),
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = stringResource(R.string.see_all),
                                    fontSize = 15.sp,
                                    color = colorResource(R.color.titleColor),
                                    modifier = Modifier.padding(end = 16.dp),
                                )
                            }
                            LazyRow {
                                items(lists.books) {book ->
                                    AsyncImage(
                                        modifier = Modifier
                                            .size(width = 106.dp, height = 165.dp)
                                            .padding(
                                                start = 16.dp,
                                                top = 8.dp,
                                                bottom = 15.dp
                                            )
                                            .semantics {
                                                this.contentDescription = "Cover Image"
                                            }
                                            .clickable { onBookCoverClick(book.isbn) },
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
                            }
                        }
                    }
                }
            }
        }
    }
}
