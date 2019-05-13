package com.company;

public class Args {

    Control control;

    Field lastcreated;


    public void crtunit(String [] args){

        if(args.length<3){
            if(args.length==2&& args[1].equals("help"))
                System.out.println("crtunit -unittype -unitID");

            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<crtunit -help> opciót");
            return;
        }


        Unit unit=null;

        switch (args[1]){
            case "animal":
                unit=new Orangutan();
                break;
            case "panda":
                unit=new Panda();
                break;
            case "almos":
                unit=new Almos();
                break;
            case "ugralos":
                unit=new Ugralos();
                break;
            case "ijedos":
                unit=new Ijedos();
                break;
            default:
                System.out.println("Ismeretlen argumentum, lehetséges argomentumok: animal, panda, almos, ugralos, ijedos.");
                return;
        }



        if(args.length<4){  //ha nem adunk place argumentumot akkor utoljára létrehozott mezőre rakja
            unit.ID=args[3];
            if(lastcreated!=null){
                System.out.println("Még nem volt mező létrehozva!");
                return;
            }
                lastcreated.setContain(unit);
                unit.setIamon(lastcreated);
        }else{
            Field needed=null;
            for (Field field: control.items) {
                if(field.ID.equals(args[2]))
                    needed=field;
            }
            if(needed==null){
                System.out.println("Megadott mező nem létezik!");
                    return;
            }

            if(needed.getContain()!=null) {
                System.out.println("A mezőn már tartózkodik egy egység!");
                return;
            }
            needed.setContain(unit);
            unit.setIamon(needed);

        }

        if(args[1].equals("animal"))
            control.addOrangutan((Orangutan) unit);
        else
            control.addPanda((Panda)unit);





    }

    public void crttile  (String [] args){

        if(args.length<2){

                System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<crtunit -help> opciót");
            return;
        }
        if(args.length==2&& args[1].equals("help")) {
            System.out.println("crttile -tiletype -tileID");
            return;
        }

        Field field=null;
        if(args.length<3){
            field=new Field();
            field.ID=args[1];
            control.items.add(field);
        }else{
            switch (args[1]){
                case "entry":
                    field=Entry.getInstance() ;
                    control.e=(Entry)field;
                    break;
                case "exit":
                    field=new Exit();
                    break;
                case "tile":
                    field=new Field();
                    break;
                case "breakable":
                    field=new Breakable();
                    break;
                case "sofa":
                    field=new Fotel();
                    break;
                case "gamem":
                    field=new Jatek();
                    break;
                case "vendingm":
                    field=new Csoki();
                    break;
                case "wardrobe":
                    field=new Szekreny();
                    break;
                    default:
                        System.out.println("Ismeretlen argumentum, lehetséges argomentumok: entry, exit, tile, breakable, sofa, gamem, vendingm, wardrobe.");

            }
            field.ID=args[2];
            control.items.add(field);
            lastcreated=field;
        }


    }

    public void linkt (String [] args){

        if(args.length==2&& args[1].equals("help")) {
            System.out.println("linkt -tile1ID -tile2ID");
            return;
        }
        if(args.length<4){
            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<linkt -help> opciót");
            return;
        }


        Field f1=null;
        Field f2=null;

        if(args[1].equals(args[2])) {
            System.out.println("Mezőt nem lehet önmagához kötni!");
            return;
        }


        for (Field f:control.items) {
            if(f.ID.equals(args[1]))
                f1=f;
            if(f.ID.equals(args[2]))
                f2=f;

        }

        if(f1==null || f2==null) {
            System.out.println("A megadott mező/k nem taláható/k!");
            return;
        }

        f1.addNei(f2);

    }
    public void linka (String [] args){


        if(args.length==2&& args[1].equals("help")) {
            System.out.println("linka -unit1ID -unit2ID");
            return;
        }
        if(args.length<4){
            System.out.println("Nincs elegendő argomentum! A parancs formátumáért próbáld:<linka -help> opciót");
            return;
        }

        Unit u1=null;
        Unit u2=null;

        if(args[1].equals(args[2])) {
            System.out.println("Unitot nem lehet önmagához kötni!");
            return;
        }


        for (Unit u:control.pandas) {
            if(u.ID.equals(args[1]))
                u1=u;
            if(u.ID.equals(args[2]))
                u2=u;

        }

        for (Unit u:control.orangutans) {
            if(u.ID.equals(args[1]))
                u1=u;
            if(u.ID.equals(args[2]))
                u2=u;

        }


        if((u1==control.orangutans.get(0)||u1==control.orangutans.get(1))&&(u2==control.orangutans.get(0)||u2==control.orangutans.get(1)))
        {
            System.out.println("Orángután nem köthető orángutánhoz!");
            return;
        }

        if(u1==null || u2==null) {
            System.out.println("A megadott mező/k nem taláható/k!");
            return;
        }

       // f1.addNei(f2);
    }

    public void move (String [] args){

    }

    public void destroy (String [] args){

    }

    public void kill (String [] args){

    }

    public void setweariness (String [] args){

    }

    public void save (String [] args){

    }
    public void load (String [] args){

    }

    public void rand (String [] args){

    }

    public void action (String [] args){

    }

    public void fastforward (String [] args){

    }

    public void listpandas (String [] args){

    }

    public void listorangutans (String [] args){

    }

    public void listtiles (String [] args){

    }

    public void displaypoints (String [] args){

    }

    public void listnei (String [] args){

    }

    public void displaystatus (String [] args){

    }

    public void test (String [] args){

    }






















}
