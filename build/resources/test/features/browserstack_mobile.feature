Feature: Pruebas móviles de BrowserStack

  @browserstack @mobile @android
  Scenario: Abrir Example Domain en Android
    Given El navegador móvil se conecta a BrowserStack en "Android" con la URL "https://example.com"
    Then La sesión móvil debe ejecutarse en "Android"
    And La página móvil debe mostrar el título "Example Domain"

  @browserstack @mobile @ios
  Scenario: Abrir Example Domain en iOS
    Given El navegador móvil se conecta a BrowserStack en "iOS" con la URL "https://example.com"
    Then La sesión móvil debe ejecutarse en "iOS"
    And La página móvil debe mostrar el título "Example Domain"

