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
![KlasseDiagram](/images/Klassediagram27_03.png)

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

## Prosjekt og prosjektstruktur
* Det har vært noen feilprioriteringer med tanke på hva som skal være minimum viable product. F. eks. burde animerte sprites ha blitt prioritert på et senere tidspunkt og testingen tidligere. I stedet har vi havnet på et sted hvor nesten alt av logikken er klar, og animasjon av playerToken er på plass, når det heller burde ha vært motsatt. I testingen oppstod det også problemer på hvordan man skulle gå frem, som vi som gruppe kunne ha løst mye tidligere. Ellers kunne vi prøvd å få til litt mer parprogrammering slik at alle igjen får litt mer oversikt over de ulike delene av programmet, spesielt nå som det begynner å bli en del deler.

* Gruppedynamikken fungerer fortsatt bra og det stilles spørsmål til det som gjøres av hver enkelt om noen er uenig i hvordan ting skal gjøres. Det er noen i gruppen som gjør mer enn andre, så vi skal passe på å få opp commits og koding (evt gjennom parprogrammering) som gjøres av de som kanskje har kodet minst.
* Kommunikasjonen gjennom Slack er også bra. Det har skjedd private ting hos en i gruppen som har gjort det vanskelig å komme i kontakt med vedkommende i en liten periode.

* Basert på det vi har hatt som mål føles det ut som om vi har nådd målene vi har satt oss. Det som ligger i backlog er ting som dukker opp som enten skal gjøres etter mvp eller før, men fra uke til uke har det gått fremover i todo-listen, bortsett fra noen få ting. Samtidig føler vi at vi har blitt flinkere til å oppdatere Trello boardet.

* Møtereferat finnes i Deliverables

#### Roller
* Vi har tildelt rollen som tester til Renze Stolte.

### Forbedringspunker
* Parprogrammering slik at flere forstår flere deler av koden.
* Pushe siste arbeid så fort som mulig slik at alle er ajour med koden de jobber med.
* Bedre på organiseringen av hva som skal bli gjort.
* Bedre på navngivning av metoder.

### Hvordan kjøre test
* Kjør JUnit testene i mappen scr/test/java
* Manuelle tester ligger i mappen src/test/java/.../app/ManuelleTester. Piltastene er implementert til å bevege seg rundt brettet uten bruk av kort. 'C' brukes til checkpoint-testing og 'B' til conveyorbelt-testing.

### Hvordan spille spillet
* Kjør main
* Trykk start for å starte spillet
* 1 - Bruk 1 til 9 på tastaturet for å velge ut 5 kort som spiller 1 skal bruke
* 2 - Trykk enter for å bekrefte
* 3 - Bruk nummer-tallene 1 til 9 for å velge ut 5 kort som spiller 2 skal bruke
* 4 - Trykk enter for å bekrefte
* 5 - Trykk space for å simulere første kort fra begge spillere
* 6 - Trykk space for å simulere andre kort fra begge spillere
* 7 - Trykk space for å simulere tredje kort fra begge spillere
* 8 - Trykk space for å simulere fjerde kort fra begge spillere
* 9 - Trykk space for å simulere femte kort fra begge spillere
* 10 - Trykk space for å simulere conveyer belts/rotasjonsfelt/checkpoint registrering
* 11 - Tilbake til 1
* Når en spiller har kommet innom alle checkpointene, så avsluttes spillet og navnet til spilleren som vant blir printet ut i konsollen.
