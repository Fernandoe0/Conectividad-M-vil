package com.example.conectividadmovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.conectividadmovil.di.ServiceLocator
import com.example.conectividadmovil.ui.product.ProductViewModel
import com.example.conectividadmovil.ui.product.ProductsScreen
import com.example.conectividadmovil.ui.theme.ConectividadMovilTheme

class MainActivity : ComponentActivity() {
    // Inyectamos el repositorio desde el ServiceLocator usando un Factory
    private val vm: ProductViewModel by viewModels {
        ProductVMFactory(com.example.conectividadmovil.di.ServiceLocator.repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Si tienes tu Theme propio, cámbialo por el tuyo (ej. ConectividadMovilTheme)
            Surface(color = MaterialTheme.colorScheme.background) {
                ProductsScreen(vm = vm)
            }
        }
    }
}