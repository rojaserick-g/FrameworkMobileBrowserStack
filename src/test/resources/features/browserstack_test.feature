Feature: Test de Conexión a BrowserStack

  @browserstack @smoke
  Scenario: Verificar conexión exitosa a BrowserStack
    Given El navegador se conecta a BrowserStack exitosamente
    When Se ejecuta un test en la plataforma remota
    Then Se verifica que el test se ejecutó en BrowserStack

  @browserstack @smoke
  Scenario: Verificar información de sesión en BrowserStack
    Given El navegador se conecta a BrowserStack
    When Se obtiene la información de la sesión
    Then La sesión debe estar registrada en BrowserStack
