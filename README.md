# Techs
Group 5.3

## Gruppemedlemmer:

**Joacim Delfin Sveen** *(Teamleader)*
* Datateknologi
* Java fra INF 100, 101 og 102

Føler seg midt på treet når det gjelder programmering. Koder ikke noe særlig på fritiden utover arbeid relatert til studiet
og har ikke jobbet med et kodeprosjekt i en gruppe tidligere.

**Knut Mathias Gaard Storvestre**
* Datatryggleik, UiB
* Java
* INF 100, 101, 102, 142, 143

**Mathias Bøe** *(Ekstra ansvar for design og sprites)*
* Datatryggleik, UiB
* Git
* Java
* Processing

**Mathias Madslien** *(Kundekontakt)*
* IKT
* Java fra INF 100 og 101

**Renze Stolte**
* Datavitenskap
* Java fra INF 100, 101 og 102

## Verktøy
* Git
* IntelliJ
* Slack
* Trello

## Prosjektmetodikk
* Blanding av Kanban og Scrum
* Parprogrammering

## Oversikt over klasser
![KlasseDiagram](/images/Klassediagram02_05.png)

## Grafikk
![Green robot animation](/assets/AnimatedGreenRobot.gif)

![Sprite sheet](/assets/GreenRobotSpriteSheet.png)
![Sprite_sheet](/assets/BlueRobotSpriteSheet.png)
![Sprite sheet](/assets/RedRobotSpriteSheet.png)
![Sprite_sheet](/assets/YellowRobotSpriteSheet.png)

## Sluttprodukt:
Vi ønsker oss et velfungerende program som i første omgang er et minimum viable product. Basic med reglene som gjelder for å kunne spille.

## Krav for første delinnlevering
* Et spillebrett
* En brikke på brettet
* Bevegelse på brikke

## Krav for andre delinnlevering
* Få på plass scaling
* ProgramCard objects
* Sprites
* Skrive mer tester
* Basic UserInterface

## Krav for tredje delinnlevering
* Animerte sprites
* Fungerende conveyor belts
* Fungerende pitfalls
* Fungerende rotating gears
* Programkort som kan styre spiller
* Spillet avsluttes når spiller har vært innom alle checkpoints
* Skrive flere tester

## Krav for fjerne delinnlevering
* Laser fra spiller
* Laser fra brett
* Velge antall spillere
* Ha en dummy-AI
* Mulighet for LAN-multiplayer
* Gjøre om valg av kort
* Damagetokens og healthtokens for spillere
* Synlige informasjon om spillere på skjermen
* Håndtering av spillere som mister et liv
* Antall kort som deles ut basert på damage
* Låse kort når damage >= 5
* Spillet avsluttes dersom alle har mistet alle healthtokens

## Prosjekt og prosjektstruktur
* Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?
* Hvordan er gruppedynamikken?
* Hvordan fungerer kommunikasjonen for dere?

* Hva justerte dere underveis, og hvorfor? Ble det bedre?
* Hva har fungert best, og hvorfor? (Hva er dere mest fornøyde med?) 
* Hvis dere skulle fortsatt med prosjektet, hva ville dere justert?
* Hva er det viktigste dere har lært?

* Møtereferat finnes i Deliverables

### Lister over ferdigstilte krav
* Man kan spille en komplett runde
* Man kan vinne spillet ved å besøke siste flagg (fullføre et spill)
* Det er lasere på brettet
* Det er hull på brettet
* Spillere får riktig antall kort etter hvor mye damage de har
* Spillere sine kort låses også riktig i henhold til damage
* Skademekasnismer når spillere skyter laser eller hvis de står på laser på brett
* Fungerende samlebånd på brett (både enkle og doble)
* Fungerende gyroer på brett
* Spillet avsluttes dersom alle spillere har 0 health tokens.
* Spillere kan kjøre powerdown
* Det går an å spille mot AI (foreløpig kun implementert dersom man er 1 spiller)

### Hvordan kjøre test
* Kjør JUnit testene i mappen scr/test/java
* Manuelle tester ligger i mappen src/test/java/.../app/ManuelleTester. Piltastene er implementert til å bevege seg rundt brettet uten bruk av kort. 'C' brukes til checkpoint-testing og 'B' til conveyorbelt-testing.

### Hvordan spille spillet
Man velger kort enten ved å bruke 1-9 på tastaturet eller ved å klikke på kortene

* Kjør main
* Trykk start for å starte spillet
* 1 - Velg kort for spiller 1
* 2 - Trykk enter for å bekrefte
* 3 - Velg kort for spiller 2
* 4 - Trykk enter for å bekrefte
* 5 - Trykk space for å simulere første kort fra spiller med høyest prioritet
* 6 - Trykk space for å simulere første kort fra spiller med neste prioritet
* 7 - Repeter 5-6 fire ganger
* 10 - Trykk space for å simulere conveyer belts/rotasjonsfelt/checkpoint registrering
* 11 - Tilbake til 1

Dersom en spiller har kommet innom alle checkpointene, så avsluttes spillet og navnet til spilleren som vant blir printet ut i konsollen. Hvis alle spillere har mistet sine liv, avsluttes også spillet.
