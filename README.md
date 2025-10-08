# 📱 Proyecto de Conectividad Móvil – Consumo de API REST

Este proyecto demuestra la conexión entre una aplicación móvil (Android Jetpack Compose) y un backend (Spring Boot / API REST), incluyendo la gestión de productos y pruebas de red locales con `http://10.0.2.2:8080/`.

---

## 🧩 Características principales

- 🔐 Autenticación con JWT (opcional según configuración del backend)
- 🗂️ CRUD completo de productos (crear, listar, actualizar, eliminar, buscar)
- 🌐 Conexión HTTP entre app y backend local
- ⚙️ Manejo de errores de red y mensajes en UI
- 🧪 Pruebas manuales y configuración para Android 9+

---

## ⚙️ Instalación y ejecución

### 🖥️ Backend (Spring Boot)
1. Clonar el repositorio del backend:
   ```bash
   git clone https://github.com/tu-usuario/backend-api.git
   cd backend-api
   ```
2. Ejecutar con Maven o IDE:
   ```bash
   mvn spring-boot:run
   ```
3. Verifica en el navegador:  
   👉 [http://localhost:8080/products](http://localhost:8080/products)

### 📱 Frontend (Android)
1. Clona o abre el proyecto Android:
   ```bash
   git clone https://github.com/tu-usuario/app-movil.git
   ```
2. Abre en **Android Studio**
3. En `ApiClient.kt` o `RetrofitInstance.kt`, confirma:
   ```kotlin
   const val BASE_URL = "http://10.0.2.2:8080/"
   ```
4. Ejecuta el emulador o conecta un dispositivo físico.

---

## 🧪 Pruebas manuales (QA checklist)

| Endpoint | Acción | Resultado esperado | En caso de error |
|-----------|--------|--------------------|------------------|
| **GET /products** | Cargar lista | Se muestran los productos | Snackbar “No hay conexión” |
| **POST /products** | Crear producto | Mensaje “Creado” y nuevo item visible | Snackbar “Error al crear” |
| **PUT /products/{id}** | Actualizar | Valor modificado en UI | “No se pudo actualizar” |
| **DELETE /products/{id}** | Eliminar | Item desaparece | “No se pudo eliminar” |
| **GET /products?name=...** | Buscar | Filtra resultados | “Error al buscar” |

📋 **Verificación adicional:**  
- Logcat muestra respuestas **2xx OK**.  
- En error de red: `UnknownHostException`, `timeout`, `HTTP 500`.

---

## 🧰 Solución al error `CLEARTEXT communication not permitted`

Android 9+ bloquea las conexiones HTTP sin cifrar.  
Sigue estos pasos para permitir tráfico local en desarrollo:

1️⃣ En `AndroidManifest.xml`, dentro de `<application>`:
```xml
<application
    android:usesCleartextTraffic="true"
    android:networkSecurityConfig="@xml/network_security_config"
    ... >
```

2️⃣ Crea `res/xml/network_security_config.xml`:
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="false">10.0.2.2</domain>
        <domain>localhost</domain>
    </domain-config>
</network-security-config>
```

3️⃣ Asegura permiso de internet:
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

✅ **Confirmación del fix:**
- Logcat ya no muestra `CLEARTEXT communication not permitted`.
- Los endpoints devuelven código `200/201`.
- Los productos cargan correctamente en UI.

---

## 🧠 Buenas prácticas de desarrollo

- Validar datos antes de enviar al backend.
- Mostrar mensajes claros en UI ante errores.
- Manejar estados (`loading`, `success`, `error`) con `StateFlow` o `ViewModel`.
- Usar `.gitignore` para excluir carpetas temporales (`/build`, `/idea`, `/gradle`).

---

## 📘 Documentación adicional

### 📄 Documento principal:
- [Conectividad Movil.docx](./docs/Conectividad%20Movil.docx)

> Este documento explica el flujo de conectividad móvil, el manejo del ID en el backend, y el diseño de la pantalla `ProductsScreen(vm)` en Jetpack Compose.

### 📁 Estructura recomendada del repositorio:
```bash
📂 mi-proyecto/
├─ 📁 app/
├─ 📁 data/
├─ 📁 ui/
├─ 📁 docs/
│  └─ Conectividad Movil.docx
└─ README.md
```

---

## 🧾 Licencia

Este proyecto se distribuye con fines educativos.  
Puedes usarlo, modificarlo y compartirlo libremente citando la fuente.

---

✉️ **Autor:** Fernando Equité  
📅 **Fecha:** Octubre 2025  
🏫 **Universidad:** Universidad Mariano Gálvez de Guatemala
