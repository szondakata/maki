package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


/**
 * A parancsok helyes logikaval valo osszekoteset gyujto osztaly
 */
public class utasitasok {

    public Control control;
    Field lastcreated;
    ArrayList<Breakable> breakable_tiles = new ArrayList<>();
    ArrayList<Unit> unitobjects = new ArrayList<>();
    ArrayList<Szekreny> narnia = new ArrayList<>();


    public utasitasok() {
        control = new Control();
    }

    private boolean checkNameExists(String id) {
        for (Unit u : unitobjects) {
            if (u.ID.equals(id)) {
                System.out.println("A választott név már létezik! Adjon meg másik nevet!");
                return true;
            }
        }
        for (Field f : control.items) {
            if (f.ID.equals(id)) {
                System.out.println("A választott név már létezik! Adjon meg másik nevet!");
                return true;
            }
        }
        return false;
    }
    /** A fuggveny a parameterben kapott tipusu egyseget a parameterben kapott
     *  (meg nem foglalt) ID-val letrehozza
     * @param args -az egyseg tipusa -az egyseg ID-ja
     */
    public void crtunit(String[] args) {

        if (args.length < 3) {
            if (args.length == 2 && args[1].equals("help"))
                System.out.println("crtunit -unittype -fieldID -unitID");

            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<crtunit -help> opciót");
            return;
        }

        Unit unit = null;

        switch (args[1]) {
            case "orangutan":
                unit = new Orangutan();
                break;
            case "panda":
                unit = new Panda();
                break;
            case "almos":
                unit = new Almos();
                break;
            case "ugralos":
                unit = new Ugralos();
                break;
            case "ijedos":
                unit = new Ijedos();
                break;
            default:
                System.out.println("Ismeretlen argumentum, lehetséges argomentumok: orangutan, panda, almos, ugralos, ijedos.");
                return;
        }

        if (args.length < 4) {  //ha nem adunk place argumentumot akkor utoljára létrehozott mezőre rakja
            unit.ID = args[2];

            if (lastcreated == null) {
                System.out.println("Még nem volt mező létrehozva!");
                return;
            }
            if (checkNameExists(unit.ID)) {

                return;
            }
            lastcreated.setContain(unit);
            unit.setIamon(lastcreated);
        } else {
            unit.ID = args[3];
            Field needed = null;
            Field fake = new Field();
            fake.ID = "fake";
            for (Field field : control.items) {
                if (field.ID.equals(args[2]))
                    needed = field;
            }
            if (args[2].equals("fake")) {needed = fake;}
            if (needed == null) {
                System.out.println("Megadott mező nem létezik!");
                return;
            }

            if (needed.getContain() != null) {
                System.out.println("A mezőn már tartózkodik egy egység!");
                return;
            }
            needed.setContain(unit);
            unit.setIamon(needed);
        }

        if (args[1].equals("orangutan")) {
            control.addOrangutan((Orangutan) unit);
            //TODO control.orangCount++;
        } else
            control.addPanda((Panda) unit);

        unitobjects.add(unit);
    }


    /** A fuggveny a parameterben kapott tipusu csempet a parameterben kapott
     *  (meg nem foglalt) ID-val letrehozza
     * @param args -a csempe tipusa -a csempe ID-ja
     */
    public void crttile(String[] args) {

        if (args.length < 2) {

            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<crtunit -help> opciót");
            return;
        }
        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("crttile -tiletype -tileID");
            return;
        }

        Field field = null;
        if (args.length < 3) {
            if (checkNameExists(args[1])) {
                return;
            }

            field = new Field();
            field.ID = args[1];
            control.items.add(field);
            lastcreated = field;
        } else {
            if (checkNameExists(args[2])) {
                return;
            }
            switch (args[1]) {
                case "entry":
                    field = Entry.getInstance();
                    control.e = (Entry) field;
                    break;
                case "exit":
                    field = new Exit();
                    break;
                case "tile":
                    field = new Field();
                    break;
                case "breakable":
                    field = new Breakable();
                    breakable_tiles.add((Breakable) field);
                    break;
                case "sofa":
                    field = new Fotel();
                    break;
                case "gamem":
                    field = new Jatek();
                    break;
                case "vendingm":
                    field = new Csoki();
                    break;
                case "wardrobe":
                    field = new Szekreny();
                    narnia.add((Szekreny) field);
                    break;
                default:
                    System.out.println("Ismeretlen argumentum, lehetséges argomentumok: entry, exit, tile, breakable, sofa, gamem, vendingm, wardrobe.");
                    return;

            }
            field.ID = args[2];
            control.items.add(field);
            lastcreated = field;
        }
    }

    /** A fuggveny a parameterben kapott ket csempet osszekoti
     * @param args -az egyik csempe ID-ja -a masik csempe ID-ja
     */
    public void linkt(String[] args) {

        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("linkt -tile1ID -tile2ID");
            return;
        }
        if (args.length < 3) {
            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<linkt -help> opciót");
            return;
        }

        Field f1 = null;
        Field f2 = null;

        if (args[1].equals(args[2])) {
            System.out.println("Mezőt nem lehet önmagához kötni!");
            return;
        }

        for (Field f : control.items) {
            if (f.ID.equals(args[1]))
                f1 = f;
            if (f.ID.equals(args[2]))
                f2 = f;

        }

        if (f1 == null || f2 == null) {
            System.out.println("A megadott mező/k nem taláható/k!");
            return;
        }

        if (f1.getNei() != null) {
            if (f1.getNei().contains(f2)) {
                System.out.println("A mezők már össze vannak kötve!");
                return;
            }
        }

        f1.addNei(f2);

    }


    /**A fuggveny a parameterben kapott elso egyseg hatso mancsat a
     * parameterben kapott masidok egyseg mancsaba teszi
     * @param args -az egyik egyseg ID-ja -a masik egyseg ID-ja
     */
    public void linka(String[] args) {

        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("linka -unit1ID -unit2ID");
            return;
        }
        if (args.length < 3) {
            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<linka -help> opciót");
            return;
        }

        Unit u1 = null;
        Unit u2 = null;

        if (args[1].equals(args[2])) {
            System.out.println("Unitot nem lehet önmagához kötni!");
            return;
        }

        for (Unit u : control.pandas) {
            if (u.ID.equals(args[1]))
                u1 = u;
            if (u.ID.equals(args[2]))
                u2 = u;

        }

        for (Unit u : control.orangutans) {
            if (u.ID.equals(args[1]))
                u1 = u;
            if (u.ID.equals(args[2]))
                u2 = u;

        }

        if (control.orangutans.size() == 2) {
            if ((u1 == control.orangutans.get(0) || u1 == control.orangutans.get(1)) && (u2 == control.orangutans.get(0) || u2 == control.orangutans.get(1))) {
                System.out.println("Orángután nem köthető orángutánhoz!");
                return;
            }
        }

        if (u1 == null || u2 == null) {
            System.out.println("A megadott mező/k nem taláható/k!");
            return;
        }

        if (u1.getHand1() != null && u2.getHand1() != null) {
            System.out.println("A kettő kijelölt unit már tagja egy láncnak!");
            return;
        }

        if (u1.getHand1() == u2 || u1.getHand2() == u2) {
            System.out.println("Az egységek már össze vannak kötve!");
            return;
        }

        if (u1.getHand1() == null)
            u2.grab(u1);
        else
            u1.grab(u2);
    }

    /** A fuggveny a parameterben kapott egyseget a parameterben kapott csempere mozgatja
     * @param args -az egyseg ID-ja -az csempe ID-ja
     */
    public void move(String[] args) {
        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("move -unitID -tileID");
            return;
        }
        if (args.length < 3) {
            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<move -help> opciót");
            return;
        }

        Unit oran = null;
        Unit panda = null;
        Field field = null;

        for (Unit u : control.orangutans) {
            if (u.ID.equals(args[1]))
                oran = u;

        }

        for (Unit u : control.pandas) {
            if (u.ID.equals(args[1]))
                panda = u;

        }

        for (Field f : control.items) {
            if (f.ID.equals(args[2]))
                field = f;
        }


        if (field == null) {
            System.out.println("a megadott mező nem létezik!");
            return;
        }

        if (panda == null && oran == null) {
            System.out.println("a megadott egység nem létezik!");
            return;
        }

        if (oran != null) {
            control.move((Orangutan) oran, field);
            return;
        }

        if (!panda.move(field))
            System.out.println("A lépés nem lehetséges!");
    }

    /**A fuggveny a parameterben kapott torekeny csempet osszetori
     * @param args az osszetorni kivant csempe ID-ja
     */
    public void destroy(String[] args) {
        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("destroy -tileID");
            return;
        }
        if (args.length < 2) {
            System.out.println("Nincs elegendő argumentum! A parancs formátumáért próbáld:<destroy -help> opciót");
            return;
        }

        Field needed = null;
        for (Field f : control.items) {
            if (f.ID.equals(args[1]))
                needed = f;
        }
        if (needed == null || !breakable_tiles.contains((Breakable) needed)) {
            System.out.println("A keresett mező nem létezik vagy a megadott mező nem törhető!");
            return;
        }

        Breakable breakable = (Breakable) needed;
        breakable.setRemainLifetime(0);
        breakable.Update();
        breakable_tiles.remove(breakable);
        control.items.remove(breakable);
    }

    /** A fuggveny a parameterben kapott pandat vagy orangutant megoli
     * @param args unitID - a megolni kivánt egyseg ID-ja
     */
    public void kill(String[] args) {
        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("kill -unitID");
            return;
        }
        if (args.length < 2) {
            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<kill -help> opciót");
            return;
        }
        Unit unit = null;
        for (Unit u : unitobjects) {
            if (u.ID.equals(args[1]))
                unit = u;
        }
        if (unit == null) {
            System.out.println("A megadott egység nem található!");
            return;
        }
        unit.die();
    }

    /** A fuggveny a parameterben kapott szamra allitja be a parameterben kapott csempe eletet
     * @param args -int a beallitani kivant elet -tileID a csempe, aminek beallitjuk az eletet
     */
    public void setweariness(String[] args) {


        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("setweainess -life -tileID");
            return;
        }
        if (args.length < 3) {
            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<setweariness -help> opciót");
            return;
        }

        Field needed = null;
        for (Field f : control.items) {
            if (f.ID.equals(args[2]))
                needed = f;
        }
        if(needed==null) {
            System.out.println("A megadott mező nem található");
            return;
        }
        if (!breakable_tiles.contains((Breakable) needed)) {
            System.out.println("A megadott mező nem törhető!");
            return;
        }
        Breakable breakable = (Breakable) needed;
        try {
            breakable.setRemainLifetime(Integer.parseInt(args[1]));
        } catch (NumberFormatException e) {
            System.out.println("A megadott érték nem szám!");
        }
    }

    /** A fuggven a standard outputra erkezo barmilyen karaktersorozatot egy parameterben kapott
     * fajlnevu fejlba iranyitja at
     * @param args -filename a megnyitni kivant fajl neve
     */
    public void save(String[] args) {
        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("save -filename");
            return;
        }
        if (args.length < 2) {
            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<save -help> opciót");
            return;
        }

        try {
            PrintStream original = System.out;
            PrintStream fileOut = new PrintStream("./" + args[1] + ".txt");
            System.setOut(fileOut);
            args[0] = "displaystatus";


            for (Unit u : unitobjects) {
                args[1] = u.ID;
                displaystatus(args);
            }

            for (Field f : control.items) {
                args[1] = f.ID;
                displaystatus(args);
            }

            System.setOut(original);
            fileOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       /* try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(args[1] + ".txt"), StandardCharsets.UTF_8))) {
        } catch (Exception e) {
            System.out.println("Hiba történt, mentés sikertelen!");
        }
        */
    }

    /** A fuggveny betolt egy parameterben kapott nevu txt-fajlbol egy utasitassorozatot
     * @param args -filename a betolteni kivant fajl neve
     */
    public void load(String[] args) {
        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("load -filename");
            return;
        }
        if (args.length < 2) {
            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<load -help> opciót");
            return;
        }

        try {

            File f = new File(args[1].contains(".txt") ? args[1] : args[1] + ".txt");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine;

            String[] arguments;
            while ((readLine = b.readLine()) != null) {
                arguments = readLine.split("-");
                for (int i = 0; arguments.length > i; i++) {
                    arguments[i] = arguments[i].trim();
                }
                for (String s : arguments) {
                    System.out.print(s + " ");
                }
                System.out.println();

                switch (arguments[0]) {
                    case "crtunit":
                        crtunit(arguments);
                        break;
                    case "crttile":
                        crttile(arguments);
                        break;
                    case "linka":
                        linka(arguments);
                        break;
                    case "linkt":
                        linkt(arguments);
                        break;
                    case "move":
                        move(arguments);
                        break;
                    case "destroy":
                        destroy(arguments);
                        break;
                    case "kill":
                        kill(arguments);
                        break;
                    case "setweariness":
                        setweariness(arguments);
                        break;
                    case "save":
                        save(arguments);
                        break;
                    case "load":
                        load(arguments);
                        break;
                    case "rand":
                        rand(arguments);
                        break;
                    case "action":
                        action(arguments);
                        break;
                    case "fastforward":
                        fastforward(arguments);
                        break;
                    case "listpandas":
                        listpandas();
                        break;
                    case "listorangutans":
                        listorangutans();
                        break;
                    case "listtiles":
                        listtiles();
                        break;
                    case "displaypoints":
                        displaypoints();
                        break;
                    case "listnei":
                        listnei(arguments);
                        break;
                    case "displaystatus":
                        displaystatus(arguments);
                        break;
                    case "test":
                        test(arguments);
                        break;
                }
            }
            flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /** A fuggveny baellirja, hogy rendes vagy random modban jatszunk
     * @param args -string ha "on", determinisztikus a jatek, ha egyeb, akkor nem
     */
    public void rand(String[] args) {
        if (args[1].equals("on"))
            control.isRandom = true;
        else
            control.isRandom = false;
    }

    /**A targyak koret megvalosito fuggveny
     * @param args nem kerul hasznalatra a parameter
     */
    public void action(String[] args) {
        Field field = null;
        for (Field f : control.items) {
            if (f.ID.equals(args[1]))
                field = f;
        }

        if (field == null) {
            System.out.println("This is not the field you are looking for!");
            return;
        }
        field.Update();
    }

    /**A fuggven hatasare eltelik valamennyi kor (csak a pandak mozognak)
     * @param args -? -hany kor jatszodjon le -on (bekapcsolva, minden mas esetben a fuggveny nem kerul kiertekelesre)
     */
    public void fastforward(String[] args) {
        if (args[2].equals("on")) {
            for (int i = 0; i < Integer.parseInt(args[1]); i++)
                control.movePandas();
            control.updateItems();
        }
    }

    /**A fugveny a standard outputra irja a pandak ID-it
     *
     */
    public void listpandas() {
        for (Unit u : control.pandas) {
            System.out.println("    " + u.ID);
        }
    }

    /**A fuggveny a standard outputra irja az orangutanok ID-it
     *
     */
    public void listorangutans() {
        for (Unit u : control.orangutans) {
            System.out.println("    " + u.ID);
        }
    }

    /**
     * A fuggveny a standard outputra irja a csempek ID-it
     */
    public void listtiles() {
        for (Field f : control.items) {
            System.out.println("    " + f.ID);
        }
    }

    /**
     * A fuggveny a standard outputra irja a jatekosok pontjainak szamat
     */
    public void displaypoints() {
        if (control.p1 == null || control.p2 == null){
            System.out.println("Players not found!");
            return;
        }
        System.out.println("Player1:" + control.p1.getPoints() + "\n" + "Player2:" + control.p2.getPoints());
    }


    /** A fuggveny a standard outputra irja a parameterben kapott csempe szomszedait
     * @param args a csempe ID-je
     */
    public void listnei(String[] args) {
        Field field = null;
        for (Field f : control.items) {
            if (f.ID.equals(args[1]))
                field = f;
        }

        if (field == null) {
            System.out.println("This is not the field you are looking for!");
            return;
        }

        for (Field f : field.getNei()) {
            System.out.println(f.ID);
        }
    }

    /** A fuggveny a standard outputra irja a parameterben kapott egyseg/csempe reszletes adatait
     * @param args a csempe/egyseg ID-ja
     */
    public void displaystatus(String[] args) {
        Field field = null;
        for (Field f : control.items) {
            if (f.ID.equals(args[1]))
                field = f;
        }

        Unit unit = null;
        for (Unit u : unitobjects) {
            if (u.ID.equals(args[1]))
                unit = u;
        }

        if (unit == null && field == null) {
            System.out.println("A megadott objektum nem létezik!");
        }/*
        if (unit != null) {
            ArrayList<String> s=unit.kiir();
            for(String string:s)
                System.out.print(string);
        }
        if (field != null) {
            ArrayList<String> s=field.kiir();
            for(String string:s)
                System.out.print(string);
        }*/
    }

    public void test(String[] args) {
        System.out.println("Test succesful!"); //TODO rendesen implementálni
    }

    public void player_init()
    {
        if (control.orangutans.size()!=2)
        {
            System.out.println("Nem megfelelő orángután mennyiség!");
        }
        else
        {
            Player p1 = new Player();
            Player p2 = new Player();
            p1.setMyOran(control.orangutans.get(0));
            p2.setMyOran(control.orangutans.get(1));
            control.setP1(p1);
            control.setP2(p2);
            control.orangutans.get(0).setMyPlayer(p1);
            control.orangutans.get(1).setMyPlayer(p2);
        }
    }

    /** A fuggveny a parameterben kapott ket szekrenyt osszekoti
     * @param args -az egyik szekreny ID-ja -a masik szekreny ID-ja
     */
    public void linkw(String[] args) {

        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("linkt -tile1ID -tile2ID");
            return;
        }
        if (args.length < 3) {
            System.out.println("Nincs elegendő argumentum! A parancs formátumáért próbáld:<linkt -help> opciót");
            return;
        }

        Field f1 = null;
        Field f2 = null;

        if (args[1].equals(args[2])) {
            System.out.println("Mezőt nem lehet önmagához kötni!");
            return;
        }


        for (Field f : control.items) {
            if (f.ID.equals(args[1]))
                f1 = f;
            if (f.ID.equals(args[2]))
                f2 = f;

        }
        if (f1 == null || f2 == null) {
            System.out.println("A megadott mező/k nem taláható/k!");
            return;
        }
        if (narnia == null || !narnia.contains(f1) || !narnia.contains(f2)) {
            System.out.println("A megadott mezo/k nem szekreny tipusu/ak!!");
            return;
        }
        Szekreny sz1 = (Szekreny) f1;
        Szekreny sz2 = (Szekreny) f2;

        sz1.setSzekrenypar(sz2);
        sz2.setSzekrenypar(sz1);
    }

    /**
     * A fuggveny torli az eddigieket es egy uj jatekot hoz letre
     */
    public void flush() {
        this.unitobjects.clear();
        this.narnia.clear();
        this.lastcreated = null;
        this.control = new Control();
        Entry.getInstance().setContain(null);
        Entry.getInstance().getNei().clear();

    }

}
