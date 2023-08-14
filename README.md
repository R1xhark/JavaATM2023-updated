# Java ATM-Banking Simulátor

Toto je jednoduchý simulátor bankovního ATM, který umožňuje uživatelům interagovat s databází klientů a provádět operace jako přidání klienta, výběr peněz a zjištění zůstatku.

## Funkce

- Přidání klienta do databáze
- Výběr peněz z účtu
- Zjištění aktuálního zůstatku na účtu

## Použití

1. Vytvořte databázi a upravte připojovací údaje k databázi v souboru `LibertyDatabaseConnector.java`.
2. Spusťte aplikaci spuštěním třídy `BackSim` ve vašem IDE nebo přes příkazovou řádku.

## Jak používat ATM simulátor

1. Spusťte aplikaci.
2. Vyberte jednu z nabízených možností:
   1. **Vytvoř klienta:**
      Tato volba spustí metodu pro přidání nového klienta do databáze.
   
   2. **Emuluj ATM:**
      Tato volba spustí emulaci bankovního ATM. Uživatel bude moci vybírat peníze, zjišťovat zůstatek a provádět další operace prostřednictvím konzolové aplikace.
      
4. Pokračujte podle pokynů na obrazovce a zadejte požadované informace.

## Objekty

1.** Liberty Database Connector** - Objekt obsahující metody pro práci s SQL databází ( připojení, vytvoření uživatele, výpis zůstatku na účtu ). Tento program také obsahuje metodu která když při spuštění nenajde korespondující tabulku v databázi tak jí scriptem vytvoří.

2.** atm ** - objekt který slouží jako samotný emulátor, výběr, vklad, kontrola pinu.
3.** NewClient ** - objekt který spouštíme abychom databázi nabily uživateli
4.** BankSim ** - Úvodní menu které používáme pro navigaci v programu

## Poznámka

Tato aplikace je pouze simulací a nemá spojení s reálnými bankovními účty nebo operacemi. Používejte ji pouze pro výukové účely.

## Licence

Tento projekt je licencován pod MIT licencí - více informací najdete v souboru [LICENSE](LICENSE).
