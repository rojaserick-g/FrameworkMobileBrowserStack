package Control;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Constant.Navegador;

public class DriverManager {
    private WebDriver driver;

    private String buildBrowserStackErrorMessage(Exception e) {
        String message = e.getMessage() == null ? "" : e.getMessage();

        if (message.toLowerCase().contains("automate testing time expired")) {
            return "BrowserStack rechazó la sesión porque el tiempo/cuota de Automate expiró. " +
                    "El código llegó bien al hub, pero la cuenta/plan de BrowserStack no permite crear más sesiones. " +
                    "Revisa la suscripción, el uso disponible o renueva el tiempo de Automate.";
        }

        return "Error conectando a BrowserStack: " + message;
    }

    protected void resolverDriver(Navegador nav, String url) {
        String os = System.getProperty("os.name").toLowerCase();
        String osVersion = System.getProperty("os.version").toLowerCase();
        System.out.println("\nSistema Operativo :" + os + ", " + osVersion);
        System.out.println("\nNavegador :" + nav);
        switch (nav) {
            case Chrome -> {
                System.out.println("Chrome seleccionado");
                ChromeOptions configuracionChrome = new ChromeOptions();
                if (os.contains("linux")) {
                    System.out.println(System.getProperty("user.name"));
                    configuracionChrome.addArguments("--disable-dev-shm-usage");
                    configuracionChrome.addArguments("--no-sandbox");
                    configuracionChrome.addArguments("--disable-gpu");
                    configuracionChrome.addArguments("--headless");
                    configuracionChrome.addArguments("--ignore-ssl-errors=yes");
                    configuracionChrome.addArguments("--windows-size=1920x1080");
                }
                // WebDriverManager.chromedriver().browserVersion("147").setup();
                configuracionChrome.addArguments("--remote-allow-origins=yes");
                System.setProperty("webdriver.manager.verbose", "true");
                this.driver = new ChromeDriver(configuracionChrome);
                this.driver.manage().deleteAllCookies();
            }
            case BrowserStack -> {
                System.out.println("BrowserStack seleccionado");
                if (!BrowserStackManager.isBrowserStackConfigured()) {
                    System.out.println("ERROR: BrowserStack no está configurado. " +
                            "Asegúrate de establecer BROWSERSTACK_USERNAME y BROWSERSTACK_ACCESS_KEY");
                    throw new RuntimeException("BrowserStack credentials not found");
                }
                try {
                    String browserVersion = System.getenv("BS_BROWSER_VERSION") != null ? 
                                           System.getenv("BS_BROWSER_VERSION") : "latest";
                    String bsOs = System.getenv("BS_OS") != null ? 
                                   System.getenv("BS_OS") : "Windows";
                    String bsOsVersion = System.getenv("BS_OS_VERSION") != null ? 
                                      System.getenv("BS_OS_VERSION") : "11";
                    
                    this.driver = BrowserStackManager.createBrowserStackDriver("Chrome", browserVersion, bsOs, bsOsVersion);
                } catch (Exception e) {
                    String errorMessage = buildBrowserStackErrorMessage(e);
                    System.out.println(errorMessage);
                    throw new RuntimeException(errorMessage, e);
                }
            }
            case Android -> {
                System.out.println("BrowserStack Android seleccionado");
                if (!BrowserStackManager.isBrowserStackConfigured()) {
                    System.out.println("ERROR: BrowserStack no está configurado. " +
                            "Asegúrate de establecer BROWSERSTACK_USERNAME y BROWSERSTACK_ACCESS_KEY");
                    throw new RuntimeException("BrowserStack credentials not found");
                }
                try {
                    this.driver = BrowserStackManager.createAndroidBrowserStackDriver();
                } catch (Exception e) {
                    String errorMessage = buildBrowserStackErrorMessage(e);
                    System.out.println(errorMessage);
                    throw new RuntimeException(errorMessage, e);
                }
            }
            case IOS -> {
                System.out.println("BrowserStack iOS seleccionado");
                if (!BrowserStackManager.isBrowserStackConfigured()) {
                    System.out.println("ERROR: BrowserStack no está configurado. " +
                            "Asegúrate de establecer BROWSERSTACK_USERNAME y BROWSERSTACK_ACCESS_KEY");
                    throw new RuntimeException("BrowserStack credentials not found");
                }
                try {
                    this.driver = BrowserStackManager.createIOSBrowserStackDriver();
                } catch (Exception e) {
                    String errorMessage = buildBrowserStackErrorMessage(e);
                    System.out.println(errorMessage);
                    throw new RuntimeException(errorMessage, e);
                }
            }
            default -> System.out.println("No es posible levantar el navegador " + nav);
        }
        if (this.driver != null) {
            if (nav == Navegador.Chrome || nav == Navegador.Edge || nav == Navegador.Firefox || nav == Navegador.Safari || nav == Navegador.BrowserStack) {
                this.driver.manage().window().setSize(new Dimension(1920, 1080));
            }
            this.driver.get(url);
        }
    }

    protected WebDriver getDriver(){
        if(driver == null){
            return null;
        }else{
            return driver;
        }
    }
}
