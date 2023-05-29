import java.util.Scanner;
import java.util.Locale;

abstract class AbstractKalkulator implements Kalkulator {
    private final String nazwaKalkulatora;

    public AbstractKalkulator(String nazwaKalkulatora) {
        this.nazwaKalkulatora = nazwaKalkulatora;
    }

    public String getNazwaKalkulatora() {
        return nazwaKalkulatora;
    }
}

enum Odpowiedzi {
    TAK, NIE
}

public class SzansaNaWylysienie extends AbstractKalkulator {
    private int wiek;

    public SzansaNaWylysienie(String nazwaKalkulatora) {
        super(nazwaKalkulatora);
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        if (wiek <= 0) {
            throw new IllegalArgumentException("Wiek nie może być mniejszy lub równy 0");
        }
        this.wiek = wiek;
    }

    public static void main(String[] args) {
        Kalkulator kalkulator = new SzansaNaWylysienie("Kalkulator Szansy na Wyłysienie");
        kalkulator.wykonajObliczenia();
    }

    @Override
    public void wykonajObliczenia() {
        Scanner skaner = new Scanner(System.in);
        System.out.println("Witaj w " + getNazwaKalkulatora() + "!");

        int wiek = pobierzWiekOdUzytkownika(skaner);
        setWiek(wiek);

        int szanse = 0;
        szanse += przeliczSzanse(pobierzOdp(skaner, "Czy pijesz Alkohol? (tak/nie): "));
        szanse += przeliczSzanse(pobierzOdp(skaner, "Czy pijesz kawe? (tak/nie): "));
        szanse += przeliczSzanse(pobierzOdp(skaner, "Czy twoi krewni lysieja w przedwczesnym wieku? (tak/nie):"));
        szanse += przeliczSzanse(pobierzOdp(skaner, "Czy często odczuwasz stres? (tak/nie): "));
        szanse += przeliczSzanse(pobierzOdp(skaner, "Czy utrzymujesz zdrową dietę? (tak/nie): "), true);
        szanse += przeliczSzanse(pobierzOdp(skaner, "Czy regularnie uprawiasz sport? (tak/nie): "), true);
        szanse += przeliczSzanse(pobierzOdp(skaner, "Czy palisz? (tak/nie): "));
        szanse += przeliczSzanse(pobierzOdp(skaner, "Czy regularnie się wysypiasz? (tak/nie): "), true);
        szanse += przeliczSzanse(pobierzOdp(skaner, "Czy przyjmujesz jakieś leki? (tak/nie): "));
        szanse += przeliczSzanse(pobierzOdp(skaner, "Czy pracujesz w stresującym środowisku? (tak/nie): "));
        szanse += przeliczSzanse(getWiek());

        String wynik;
        if (szanse >= 6) {
            wynik = "Twoje szanse na wyłysienie są duże, ale to nie znaczy, że musisz wyłysieć. Zastanów się nad swoją dietą i stylem życia, żeby do tego nie doprowadzić.";
        } else {
            wynik = "Twoje szanse na wyłysienie są adekwatne do wieku i nie masz czym się martwić.";
        }

        System.out.println(wynik);
    }

    private int pobierzWiekOdUzytkownika(Scanner skaner) {
        System.out.println("Podaj swój wiek: ");
        while (!skaner.hasNextInt()) {
            System.out.println("To nie jest poprawny wiek. Wprowadź wiek jako liczbę:");
            skaner.next();
        }
        int wiek = skaner.nextInt();
        skaner.nextLine();
        return wiek;
    }

    private Odpowiedzi pobierzOdp(Scanner skaner, String pytanie) {
        System.out.println(pytanie);
        String odpowiedz;
        do {
            odpowiedz = skaner.nextLine().trim().toUpperCase(Locale.ROOT);
            if (!(odpowiedz.equals("TAK") || odpowiedz.equals("NIE"))) {
                System.out.println("Niepoprawna odpowiedź. Wprowadź 'tak' lub 'nie':");
            }
        } while (!(odpowiedz.equals("TAK") || odpowiedz.equals("NIE")));
        return Odpowiedzi.valueOf(odpowiedz);
    }

    private int przeliczSzanse(Odpowiedzi odpowiedz) {
        return przeliczSzanse(odpowiedz, false);
    }

    private int przeliczSzanse(Odpowiedzi odpowiedz, boolean odwroc) {
        if (odwroc) {
            return (odpowiedz == Odpowiedzi.TAK) ? 0 : 1;
        } else {
            return (odpowiedz == Odpowiedzi.TAK) ? 1 : 0;
        }
    }

    private int przeliczSzanse(int wiek) {
        if (wiek >= 50) {
            return 2;
        } else if (wiek >= 30) {
            return 1;
        } else {
            return 0;
        }
    }
}
