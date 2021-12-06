Przebieg gry:
    1. Serwer decyduje o tym ile graczy ma grać
    2. Potem następuje łączenie się graczy z serwerem
    3. Do graczy wysyłany jest ich ID
    4. Po połaczeniu wszystkich graczy rozpoczyna się rozgrywka
    5. Pobierane jest ANTE od wszystkich graczy
    6. Graczom rozdawane jest po 5 kart
    7. Następuje pierwsza tura licytacji
        Gracz może:
            -) podbić stawke
            -) zakończyć ture
            -) sprawdzić
    8. Następnie gracze wybierają które karty chcą wymienić
        Gracz może:
            -) wymienić karty (po wpisaniu ich numeru w formacie np: 0,1,3)
            -) nie wymieniać kart poprzez wpisanie <enter>
    9. Następuje 2 tura licytacji analogicznie jak w punkcie 7
    10. Po zakończeniu licytacji wybierany jest zwyciężca rundy i cała pula pieniędzy jest mu przekazywana
    11. Następuje kolejna runda

Zasady gry:
    -) starszeństwo rąk jak w standardowym pokerze
    -) kolor kart nie ma znaczenia
    -) jeśli 2 graczy ma tak samo mocną rękę to pula jest dzielona między nimi

Uruchomienie gry:
    -) należy uruchomić: mvn clear package
    -) należy uruchomić serwer za pomocą java -jar "ścieżka do pliku .jar servera"
    -) należy uruchomić clienta za pomocą java -jar "ścieżka do pliku .jar clienta"

Protokół:
    Serwer ma 2 sposoby komunikacji z klientem:
        -) wysłanie wiadomości i oczekiwanie na odpowiedź (pierwsza linia komunikatu to 1)
        -) wysłanie wiadomości i nie oczekiwania na odpowiedź (pierwsza linia to -1)

