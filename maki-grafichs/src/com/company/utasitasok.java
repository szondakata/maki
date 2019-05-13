package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class utasitasok {

    public com.company.Control control;
    com.company.Field lastcreated;
    ArrayList<com.company.Breakable> breakable_tiles= new ArrayList<>();
    ArrayList<String> tiles=new ArrayList<>();
    ArrayList<String> units= new ArrayList<>();
    ArrayList<com.company.Unit> unitobjects= new ArrayList<>();


    public utasitasok(){
        control=new com.company.Control();
    }

    public void crtunit(String[] args) {

        if (args.length < 3) {
            if (args.length == 2 && args[1].equals("help"))
                System.out.println("crtunit -unittype -place -unitID");

            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<crtunit -help> opciót");
            return;
        }


        Unit unit = null;

        switch (args[1]) {
            case "animal":
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
                System.out.println("Ismeretlen argumentum, lehetséges argomentumok: animal, panda, almos, ugralos, ijedos.");
                return;
        }


        if (args.length < 4) {  //ha nem adunk place argumentumot akkor utoljára létrehozott mezőre rakja
            unit.ID = args[2];
            if (lastcreated == null) {
                System.out.println("Még nem volt mező létrehozva!");
                return;
            }
            lastcreated.setContain(unit);
            unit.setIamon(lastcreated);
        } else {
            unit.ID = args[3];
            Field needed = null;
            for (Field field : control.items) {
                if (field.ID.equals(args[2]))
                    needed = field;
            }
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
            units.add(args[1]);

        }

        if (args[1].equals("animal"))
            control.addOrangutan((Orangutan) unit);
        else
            control.addPanda((Panda) unit);

        unitobjects.add(unit);
    }

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
            field = new Field();
            field.ID = args[1];
            control.items.add(field);
        } else {
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
                    break;
                default:
                    System.out.println("Ismeretlen argumentum, lehetséges argomentumok: entry, exit, tile, breakable, sofa, gamem, vendingm, wardrobe.");
                    return;

            }
            field.ID = args[2];
            control.items.add(field);
            lastcreated = field;
            units.add(args[1]);
        }
    }

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

        if(f1.getNei()!=null) {
            if (f1.getNei().contains(f2)) {
                System.out.println("A mezők már össze vannak kötve!");
                return;
            }
        }

        f1.addNei(f2);

    }

    public void linka(String[] args) {


        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("linka -unit1ID -unit2ID");
            return;
        }
        if (args.length < 4) {
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


        if ((u1 == control.orangutans.get(0) || u1 == control.orangutans.get(1)) && (u2 == control.orangutans.get(0) || u2 == control.orangutans.get(1))) {
            System.out.println("Orángután nem köthető orángutánhoz!");
            return;
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

    public void move(String[] args) {
        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("move -unitID -tileID");
            return;
        }
        if (args.length < 3) {
            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<move -help> opciót");
            return;
        }

        Unit unit = null;
        Field field = null;

        for (Unit u : control.pandas) {
            if (u.ID.equals(args[1]))
                unit = u;

        }

        for (Unit u : control.orangutans) {
            if (u.ID.equals(args[1]))
                unit = u;

        }

        for (Field f : control.items) {
            if (f.ID.equals(args[1]))
                field = f;
        }

        if (field == null || unit == null) {
            System.out.println("a megadott mező vagy unit nem létezik!");
            return;
        }

        if (!unit.move(field))
            System.out.println("A lépés nem lehetséges!");


    }

    public void destroy(String[] args) {
        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("move -unitID -tileID");
            return;
        }
        if (args.length < 2) {
            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<move -help> opciót");
            return;
        }

        Breakable breakable = null;
        for (Breakable b : breakable_tiles) {
            if (b.ID.equals(args[1]))
                breakable = b;
        }
        if (breakable == null) {
            System.out.println("A keresett mező nem létezik vagy a megadott mező nem törhető!");
            return;
        }

        breakable.setRemainLifetime(0);
        breakable.Update();


    }

    public void kill(String[] args) {
        for (Unit u : unitobjects) {
            if (u.ID.equals(args[1]))
                u.die();
        }
    }

    public void setweariness(String[] args) {


        if (args.length == 2 && args[1].equals("help")) {
            System.out.println("setweainess -life -tileID");
            return;
        }
        if (args.length < 3) {
            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<setweariness -help> opciót");
            return;
        }

        Breakable breakable = null;
        for (Breakable b : breakable_tiles) {
            if (b.ID.equals(args[2]))
                breakable = b;
        }
        if (breakable == null) {
            System.out.println("A keresett mező nem létezik vagy a megadott mező nem törhető!");
            return;
        }
        try {
            breakable.setRemainLifetime(Integer.parseInt(args[1]));
        } catch (NumberFormatException e) {
            System.out.println("A megadott érték nem szám!");
        }

    }

    public void save(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(args[1] + ".txt"), StandardCharsets.UTF_8))) {
            int i = 0;
            for (Field f : control.items) {
                writer.write("crttile -" + tiles.get(i) + "-" + control.items.get(i).ID);
                writer.newLine();
                i++;
            }
            i = 0;
            for (Unit u : unitobjects) {
                writer.write("crtunit -" + units.get(i) + "-" + u.getIamon().ID + "-" + unitobjects.get(i).ID);
                writer.newLine();
                i++;
            }
            for (Unit u : unitobjects) {
                writer.write("linka -" + u.getHand1().ID + "-" + u.ID);
                writer.newLine();
            }
            i = 0;
            for (Field f : control.items) {
                i = 0;
                for (Field nei : f.getNei()) {
                    writer.write("linkt -" + control.items.get(i).ID + "-" + control.items.get(i).ID);
                    writer.newLine();
                    i++;
                }


            }

        } catch (Exception e) {
            System.out.println("Hiba történt, mentés sikertelen!");
        }
    }

    public void load(String[] args) {

        try {

            File f = new File(args[1]);

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine;

            String[] arguments;
            while ((readLine = b.readLine()) != null) {
                arguments = readLine.split("-");
                for (int i = 0; arguments.length > i; i++) {
                    arguments[i] = arguments[i].trim();
                }
                switch (arguments[0]) {
                    case "crtunit":
                        crtunit(arguments);
                        break;
                    case "crtile":
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
                    case "listiles":
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

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void rand(String[] args) {
        if (args[1].equals("on"))
            control.isRandom = true;
        else
            control.isRandom = false;
    }

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

    public void fastforward(String[] args) {
        if (args[2].equals("on")) {
            for (int i = 0; i < Integer.parseInt(args[1]); i++){   control.movePandas();}
            control.updateItems();
        }
    }

    public void listpandas() {
        for (Unit u : control.pandas) {
            System.out.println(u.ID);
        }
    }

    public void listorangutans() {
        for (Unit u : control.orangutans) {
            System.out.println(u.ID);
        }
    }

    public void listtiles() {
        for (Field f : control.items) {
            System.out.println(f.ID);
        }
    }

    public void displaypoints() {
        System.out.println("Player1:" + control.p1.getPoints() + "\n" + "Player2:" + control.p2.getPoints());
    }

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
            System.out.println("A me gadott objektum nem létezik!");
        }
        if (unit != null) {
            System.out.println(unit.toString());
        }
        if (field != null) {
            System.out.println(field.toString());
        }
    }
    public void test (String[]args){
        System.out.println("Test succesful!"); //TODO rendesen implementálni
    }


}
