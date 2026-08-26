#!/bin/bash

# Setup Script para BrowserStack Configuration
# Este script ayuda a configurar las credenciales de BrowserStack

CONFIG_FILE="browserstack.properties"

echo "🔧 Setup de BrowserStack para simpleTestAutomation"
echo "=================================================="

if [ -f "$CONFIG_FILE" ]; then
    echo "✅ Archivo $CONFIG_FILE ya existe"
    read -p "¿Deseas actualizarlo? (s/n): " update_config
    if [[ "$update_config" != "s" ]]; then
        echo "Usando configuración existente"
        exit 0
    fi
fi

echo ""
echo "Ingresa tus credenciales de BrowserStack"
echo "Obtén tu información en: https://www.browserstack.com/accounts/settings"
echo ""

read -p "Username: " username
read -sp "Access Key: " access_key
echo ""

read -p "Navegador (default: Chrome): " browser
browser=${browser:-Chrome}

read -p "Versión del navegador (default: latest): " browser_version
browser_version=${browser_version:-latest}

read -p "Sistema Operativo (default: Windows): " os_name
os_name=${os_name:-Windows}

read -p "Versión del SO (default: 11): " os_version
os_version=${os_version:-11}

read -p "Nombre del Proyecto (default: simpleTestAutomation): " project_name
project_name=${project_name:-simpleTestAutomation}

# Crear archivo de configuración
cat > "$CONFIG_FILE" << EOF
# BrowserStack Configuration Properties
# Generado por setup script

# Credenciales (obligatorios)
browserstack.username=$username
browserstack.access_key=$access_key

# Configuración de navegador
browserstack.browser.name=$browser
browserstack.browser.version=$browser_version
browserstack.os.name=$os_name
browserstack.os.version=$os_version

# Configuración del proyecto
browserstack.project.name=$project_name
browserstack.build.name=Test Build
browserstack.session.name=Test Session

# Opciones avanzadas
browserstack.local=false
browserstack.accept.ssl.certs=true
browserstack.debug=false
browserstack.network.logs=false
EOF

echo ""
echo "✅ Configuración guardada en $CONFIG_FILE"
echo "📋 Archivo creado:"
cat "$CONFIG_FILE" | grep -v "^#" | grep -v "^$"
echo ""
echo "¡Listo! Ya puedes ejecutar tus tests con BrowserStack"
echo "Comando: gradle test"
