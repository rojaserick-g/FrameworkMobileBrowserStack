# Integración BrowserStack

Esta guía explica cómo configurar y usar BrowserStack con este framework de automatización.

## Requisitos

- Cuenta activa en BrowserStack (https://www.browserstack.com/)
- Gradle instalado
- Java 17+

## 🚀 Setup Rápido (Recomendado)

### Opción 1: Script automático

```bash
# Dale permisos de ejecución
chmod +x setup-browserstack.sh

# Ejecuta el script
./setup-browserstack.sh

# Sigue las instrucciones interactivas
```

El script creará automáticamente `browserstack.properties` con tus credenciales.

### Opción 2: Manual

1. Ingresa a https://www.browserstack.com/accounts/settings
2. Copia tu **Username** y **Access Key**
3. Edita el archivo `browserstack.properties` en la raíz del proyecto:

```properties
browserstack.username=TU_USERNAME
browserstack.access_key=TU_ACCESS_KEY
browserstack.browser.name=Chrome
browserstack.browser.version=latest
browserstack.os.name=Windows
browserstack.os.version=11
browserstack.project.name=simpleTestAutomation
```

## 📋 Configuración

El archivo `browserstack.properties` contiene toda la configuración:

```properties
# Credenciales (obligatorios)
browserstack.username=YOUR_USERNAME
browserstack.access_key=YOUR_ACCESS_KEY

# Navegador
browserstack.browser.name=Chrome
browserstack.browser.version=latest

# Sistema Operativo
browserstack.os.name=Windows
browserstack.os.version=11

# Proyecto
browserstack.project.name=simpleTestAutomation
browserstack.build.name=Test Build
browserstack.session.name=Test Session

# Opciones avanzadas
browserstack.local=false
browserstack.debug=false
browserstack.network.logs=false
```

### Alternativa: Variables de Entorno

El sistema también respeta variables de entorno (con prioridad sobre el archivo):

```bash
export BROWSERSTACK_USERNAME="tu_username"
export BROWSERSTACK_ACCESS_KEY="tu_access_key"
export BROWSERSTACK_OS="Windows"
export BROWSERSTACK_OS_VERSION="11"
export BROWSERSTACK_BROWSER_VERSION="latest"
```

## ✅ Verificación

### Ver la configuración actual:

```bash
grep -v "^#" browserstack.properties | grep -v "^$"
```

### Probar conexión:

```bash
gradle test -i | grep -E "(BrowserStack|Configuration|Conectando)"
```

## 🎯 Uso en Tests

### Método 1: En tus Hooks

```java
import Control.BrowserStackCredentials;
import Control.DriverContext;
import Constant.Navegador;

@Before
public void setUp() {
    // Las credenciales se validan automáticamente
    BrowserStackCredentials.printConfig();
    
    // Usar BrowserStack
    DriverContext.setUp(Navegador.BrowserStack, "https://tuapp.com");
}
```

### Método 2: En Feature Files

```gherkin
@browserstack
Scenario: Test en BrowserStack
  Given El usuario está en la página de login
  When Ingresa credenciales
  Then Se autentica correctamente
```

Ejecutar solo tests BrowserStack:
```bash
gradle test -Dcucumber.filter.tags="@browserstack"
```

## 🔒 Seguridad

**IMPORTANTE:** El archivo `browserstack.properties` está en `.gitignore` y **NO se debe commitear**.

Esto protege tus credenciales. Cada desarrollador debe:
1. Ejecutar `./setup-browserstack.sh` 
2. O crear su propio `browserstack.properties`

## 📊 Sistemas Operativos Soportados

**Windows:**
- Windows 7, 8, 8.1, 10, 11

**Mac:**
- OS X Leopard hasta Sonoma

**Linux:**
- Para navegadores: No disponible directamente
- Usar BrowserStack Local si es necesario

## 🐛 Solución de Problemas

### ❌ "BrowserStack credentials are not properly configured"

**Soluciones:**

1. Verifica que `browserstack.properties` existe:
```bash
ls -la browserstack.properties
```

2. Verifica que no tiene placeholders:
```bash
grep "YOUR_" browserstack.properties
```

3. Regenera el archivo:
```bash
./setup-browserstack.sh
```

### ❌ "Could not find certificate validation"

**Solución:** Las credenciales son inválidas. Revisa en https://www.browserstack.com/accounts/settings

### ❌ Timeout en conexión

**Recomendaciones:**
- Verifica tu conexión a internet
- Aumenta timeouts en tu código
- Intenta con una versión específica del navegador

## 💡 Tips

1. **Reutiliza sesiones:** No crees una nueva para cada test pequeño
2. **Agrupa por plataforma:** Ejecuta tests similares en el mismo OS
3. **Monitorea costos:** Revisa https://www.browserstack.com/accounts/billing
4. **Usa tags:** Marca tests con `@browserstack` para ejecutarlos selectivamente
5. **BrowserStack Local:** Para testing de apps locales (requiere setup adicional)

## 📚 Documentación Oficial

- [BrowserStack Automate](https://www.browserstack.com/docs/automate)
- [Capabilities](https://www.browserstack.com/docs/automate/selenium/capabilities)
- [Local Testing](https://www.browserstack.com/local-testing)

## 🆘 Soporte

- Documentación: https://www.browserstack.com/docs
- Status: https://www.browserstack.com/status
- Support: https://www.browserstack.com/support
