package Control;

import Constant.Navegador;

import static Constant.Navegador.*;

public class NavSelector {
    static Navegador nav;

    public static Navegador seleccionNavegador(String navegador) {
        // Usamos toLowerCase() para que no fallen las mayúsculas o minúsculas
        switch (navegador.trim().toLowerCase()) {
            case "chrome":
                nav = Chrome;
                return nav;
            case "edge":
                nav = Edge;
                return nav;
            case "firefox":
                nav = Firefox;
                return nav;
            case "browserstack": // ¡Aquí está el eslabón perdido!
                nav = BrowserStack;
                return nav;
            case "android":
            case "browserstack-android":
                nav = Android;
                return nav;
            case "ios":
            case "browserstack-ios":
                nav = IOS;
                return nav;
            default:
                System.out.println("Navegador no reconocido: " + navegador + ". Usando Chrome por defecto.");
                return Chrome;
        }
    }
}