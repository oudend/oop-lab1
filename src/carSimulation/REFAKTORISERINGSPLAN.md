1. Skapa en DrawablePositionable interface med en getImage(void): bufferedImage metod och en getPosition(void): Point2D metod.
2. Skapa DrawableCar och DrawableWorkshop som implementerar DrawablePositionable och har en Car respektive workshop.
3. Skapa DrawableCarFactory med metoder för att skapa bilar sammansatt med deras sprites och en DrawableWorkshopFactory med metoder för att skapa workshops tillsammans med deras sprites. 
4. Ta bort alla instansvariabler i DrawPanel och byt ut mot panels: ArrayList<DrawablePositionable>, ta även bort moveit metoden.
5. Lägg till en addDrawable(DrawablePositionable drawable) metod i DrawPanel
6. Vi introducerar en omorganiserad MVC struktur:
i vår carSimulation modul har vi en App-klass som är vår apps entry point. Den ansvarar för att organisera MVC-strukturen och innehåller därför en controller och flera views.
CarController ansvarar nu för 100% av app-logiken. Den används av App och CarView, där CarView kallar på CarController:s metoder i händelse av olika events, såsom knapptryck.

En översiktlig analys av skillnader mellan den gamla och den nya strukturen ger två saker:
Vi har fler klasser och fler gränssnitt, men varje sådant har ny ett mycket tydligare och enskilt ansvarsområde.
Gränssnitten har färre och mer genomtänkta beroenden. En förändring i användargränssnittet påverkar inte appens logik. En ny biltyp innebär inte längre att befintlig rörelselogik behöver uppdateras.
