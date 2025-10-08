package com.example.conectividadmovil.ui.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.conectividadmovil.data.remote.dto.ProductRequest
import com.example.conectividadmovil.domain.model.Product


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(vm: ProductViewModel) {
    val state by vm.uiState.collectAsStateWithLifecycle()

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var searchText by remember { mutableStateOf("") }

    // Carga inicial
    LaunchedEffect(Unit) { vm.loadAll() }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Products") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            // Barra de búsqueda
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    vm.searchByName(it)
                },
                label = { Text("Buscar por nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // Formulario crear producto
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    val p = price.toDoubleOrNull() ?: 0.0
                    vm.create(ProductRequest(name, p, category.ifBlank { null }))
                    name = ""; price = ""; category = ""
                },
                enabled = name.isNotBlank() && price.toDoubleOrNull() != null
            ) {
                Text("Crear producto")
            }

            Spacer(Modifier.height(16.dp))

            if (state.loading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            state.message?.let { msg ->
                Text(text = msg, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(8.dp))

            // Lista de productos
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(state.items) { product ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(Modifier.padding(8.dp)) {
                            Text(product.name, style = MaterialTheme.typography.titleMedium)
                            Text("Precio: \$${product.price}")
                            Text("Categoría: ${product.category ?: "-"}")
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                TextButton(onClick = {
                                    val newPrice = (product.price + 1.0)
                                    vm.update(product.id ?: return@TextButton,
                                        ProductRequest(product.name, newPrice, product.category))
                                }) {
                                    Text("+1.00")
                                }
                                TextButton(onClick = {
                                    product.id?.let { vm.delete(it) }
                                }) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}