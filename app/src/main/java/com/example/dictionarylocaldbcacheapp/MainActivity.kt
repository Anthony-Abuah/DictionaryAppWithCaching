package com.example.dictionarylocaldbcacheapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionarylocaldbcacheapp.feature_dictionary.presentation.WordInfoItem
import com.example.dictionarylocaldbcacheapp.feature_dictionary.presentation.WordInfoViewModel
import com.example.dictionarylocaldbcacheapp.ui.theme.DictionaryLocalDBCacheAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryLocalDBCacheAppTheme {
                val viewModel: WordInfoViewModel = hiltViewModel()
                val state = viewModel.state.value
                val scaffoldState = rememberScaffoldState()

                LaunchedEffect(key1 = true){
                    viewModel.eventFlow.collectLatest {event->
                        when(event){
                            is WordInfoViewModel.UIEvent.ShowSnackBar -> {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }

                Scaffold(
                    scaffoldState = scaffoldState,
                ){paddingValues->
                    Box(modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .padding(paddingValues)) {
                        if (state.isLoading){
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            TextField(value = viewModel.searchQuery.value,
                                onValueChange = viewModel::onSearch,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Search...")
                            })
                            
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            LazyColumn(modifier = Modifier.fillMaxSize() ){
                                items(state.wordInfoItems.size){i->
                                    val wordInfo = state.wordInfoItems[i]

                                    if (i>0){
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }

                                    WordInfoItem(wordInfo = wordInfo)
                                    if (i < state.wordInfoItems.size - 1) {
                                        Divider()
                                    }

                                }

                            }

                        }
                    }
                }
            }
        }
    }
}
