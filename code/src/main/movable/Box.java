package main.movable;

import main.Game;
import main.field.Hole;
import main.field.HoneyPlain;
import main.field.Objective;
import main.field.OilPlain;
import main.field.Plain;
import main.field.Switch;
import main.field.Wall;


public class Box extends Movable {

    private int friction = 2;
            
    public Box() {
        //Default constructor
    }

    public int getFriction() {
        return friction;
    }

    public void setFriction(int friction) {
        this.friction = friction;
    }

    /**
     * Ez a függvény hívódik meg, ha az aktuális doboz objektumot megpróbálják eltolni. 
     * Ezen metódus feladata, hogy tovább hívjon a lépés irányában lévő szomszéd accept metódusára 
     * és ezen accept metódus visszatérési értéke határozza meg a move vissza térési értékét is.
     * @return boolean
     */
    @Override
    public boolean move() {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".move()");
        }
        Game.getInstance().tabs++;
        if (this.actualField.getNeigbour().accept(this)) {
            Game.getInstance().tabs--;
            Game.getInstance().printTabs();
            System.out.println("< true");
            return true;
        } else {
            Game.getInstance().tabs--;
            Game.getInstance().printTabs();
            System.out.println("< false");
            return false;
        }
    }

    /**
     * Azon eset, amikor a dobozt egy sima mezőre rátolják. Itt is első körben lekérjük a sima mező movable elemét 
     * és tovább láncolunk, ha nem üres, akkor a visit hívás előtt, még a láncolás kiszámításához szükséges súrlódást 
     * hozzáadjuk az eddigi súrlódási lánc értékéhez a Game globális változójához. És végül visszatérünk a visit hívásnak megfelelően. 
     * Ha üres volt a sima mező meg kell vizsgálni, hogy a lánc eltolható-e a láncot toló munkás erejét 
     * és a lánc súrlódási összegét vizsgálva és ennek megfelelően térünk vissza igazzal vagy hamissal, 
     * de mindkét esetben először nullázzuk az actualChainFriction értékét.
     * @param plain
     * @return boolean
     */
    @Override
    public boolean visit(Plain plain) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + plain.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = plain.getActualMovable();
        
        if (movable != null) {
            Game.getInstance().setActualChainFriction(
                            Game.getInstance().getActualChainFriction() 
                            + (this.getFriction() * plain.getFrictionMultiplier()));
            if (movable.visit(this)) {
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        } else {
            if (Game.getInstance().table.alternatives == 3) {
                Game.getInstance().printing = false;
                Game.getInstance().getTable().kill(this);
                Game.getInstance().printing = true;
            }
            
            if (Game.getInstance().getActualMovingWorker().getForce() > Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        }
    }

    /**
     * Azon eset, amikor a dobozt egy mézes mezőre rátolják. Itt is első körben lekérjük a mézes mező movable elemét 
     * és tovább láncolunk, akkor a visit hívás előtt, még a láncolás kiszámításához szükséges súrlódást 
     * hozzáadjuk az eddigi súrlódási lánc értékéhez a Game globális változójához. És végül visszatérünk a visit hívásnak megfelelően. 
     * Ha üres volt a mézes mező meg kell vizsgálni, hogy a lánc eltolható-e a láncot toló munkás erejét 
     * és a lánc súrlódási összegét vizsgálva és ennek megfelelően térünk vissza igazzal vagy hamissal, 
     * de mindkét esetben először nullázzuk az actualChainFriction értékét.
     * @param honeyPlain
     * @return boolaen
     */
    @Override
    public boolean visit(HoneyPlain honeyPlain) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + honeyPlain.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = honeyPlain.getActualMovable();
        
        if (movable != null) {
            Game.getInstance().setActualChainFriction(
                            Game.getInstance().getActualChainFriction() 
                            + (this.getFriction() * honeyPlain.getFrictionMultiplier()));            
            if (movable.visit(this)) {
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        } else {
            if (Game.getInstance().table.alternatives == 3) {
                Game.getInstance().printing = false;
                Game.getInstance().getTable().kill(this);
                Game.getInstance().printing = true;
            }
            
            if (Game.getInstance().getActualMovingWorker().getForce() > Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        }
    }
    /**
     * Azon eset, amikor a dobozt egy olajos mezőre rátolják. Itt is első körben lekérjük az olajos mező movable elemét 
     * és tovább láncolunk, akkor a visit hívás előtt, még a láncolás kiszámításához szükséges súrlódást 
     * hozzáadjuk az eddigi súrlódási lánc értékéhez a Game globális változójához. És végül visszatérünk a visit hívásnak megfelelően. 
     * Ha üres volt az olajos mező meg kell vizsgálni, hogy a lánc eltolható-e a láncot toló munkás erejét 
     * és a lánc súrlódási összegét vizsgálva és ennek megfelelően térünk vissza igazzal vagy hamissal, 
     * de mindkét esetben először nullázzuk az actualChainFriction értékét.
     * @param oilPlain
     * @return boolean
     */
    @Override
    public boolean visit(OilPlain oilPlain) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + oilPlain.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = oilPlain.getActualMovable();
        
        if (movable != null) {
            Game.getInstance().setActualChainFriction(
                            Game.getInstance().getActualChainFriction() 
                            + (this.getFriction() * oilPlain.getFrictionMultiplier()));
            if (movable.visit(this)) {
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        } else {
            if (Game.getInstance().table.alternatives == 3) {
                Game.getInstance().printing = false;
                Game.getInstance().getTable().kill(this);
                Game.getInstance().printing = true;
            }
            
            if (Game.getInstance().getActualMovingWorker().getForce() > Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        }
    }
    
    /**
     * Ebben az esetben mivel a dobozt nem lehet rátolni a falra, 
     * a visszatérési érték mindig false, de először nullázzuk az actualChainFriction.
     * @param wall
     * @return boolean
     */
    @Override
    public boolean visit(Wall wall) {
        if (Game.printing) {
            Game.printTabs();
            System.out.println("> " + this.getId() + ".visit(" + wall.getId() + ")");
            Game.getInstance().printTabs();
            System.out.println("< false");
        }
        return false;
    }

    /**
     * Azon eset, amikor is a doboz kerülne a cél-helyre. Ekkor meg kell néznünk, 
     * hogy az adott cél-helyen van-e valamilyen movable objektum (ami egyébként csak munkás lehet, 
     * mert a doboz eltüntetésre kerülne, amikor is rátolják). Ebben az esetben egy újabb visit hívást kezdeményezünk, 
     * ami a láncolás eltolásának ellenőrzésére szolgál. A lánc sikeres eltolásának visszatérési értéke alapján, 
     * ha igazzal tért vissza, akkor az adott dobozt kill-eljük. majd az aktuálisan toló munkásnak pontot adunk. 
     * Ellenkező esetben hamissal térünk vissza. Ha nem szerepelt a cél-helyen munkás, akkor itt dőlt el, 
     * hogy ez a lánc eltolható és vissza térünk ennek megfelelő értékkel, de előtte mindkettő esetben először nullázzuk 
     * a globális súrlódási együtthatót. Ha tolható a lánc, akkor itt is kill-eljük a dobozt, 
     * majd az aktuálisan toló munkásnak pontot adunk. És végül tolás sikerességének megfelelően térünk vissza.
     * @param objective
     * @return boolean
     */
    @Override
    public boolean visit(Objective objective) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + objective.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = objective.getActualMovable();
        
        if (movable != null) {
            if (movable.visit(this)) {
                Game.getInstance().getTable().kill(this);
                Game.getInstance().getActualMovingWorker().addPoint();
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        } else {
            Worker actualMovingWorker = Game.getInstance().getActualMovingWorker();
            if (actualMovingWorker.getForce() > Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().getTable().kill(this);
                actualMovingWorker.addPoint();
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        }
    }

    /**
     * Ennél a függvény hívásnál azt az esetet kell lekezelnünk, hogy a dobozt egy kapcsolóra rátolják. 
     * Mivel a doboz kapcsolhatja csak a kapcsolót, ezért ezt attól függően, hogy a kapcsolón áll-e más movable, 
     * és ha igen és az egész lánc eltolható-e, amit egy tovább hívással rekurzívan a következő visit hívásával ellenőrizünk, 
     * ha a lánc tolható és kapcsolón lévő movable éppen nem doboz, (mert ha az lenne nem kellene állapotot váltania a kapcsolónak, mivel doboz helyére doboz kerülne), 
     * akkor ráléptetjük a dobozt a switch-re, át is kapcsoljuk és igazzal térünk vissza. Ha az egész láncról az derül ki, 
     * hogy nem tolható tovább, akkor hamissal térünk vissza. Az utolsó eset pedig, ha a kapcsolón nem volt movable, 
     * ebben az esetben is megvizsgáljuk a a szokott módon a lánc tolása lehetséges-e. És ennek megfelelően, 
     * ha lehetséges aktiváljuk a kapcsolóhoz tartozó lyukat. és igazzal térünk vissza, ellenkező esetben a lánc nem tolható 
     * és hamissal térünk vissza, , de mindkét esetben először nullázzuk az actualChainFriction értékét.
     * @param s
     * @return boolean
     */
    @Override
    public boolean visit(Switch s) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + s.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = s.getActualMovable();
        Game.getInstance().tabs--;

        if (movable != null) {
            if (movable.visit(this)) {
                if (!(movable.getId().startsWith("bo"))) {
                    s.switchState();
                }
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        } else {
            Game.getInstance().tabs++;
            if (Game.getInstance().getActualMovingWorker().getForce() > Game.getInstance().getActualChainFriction()) {
                s.switchState();
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        }
    }

    /**
     * Ennél a függvény hívásnál azt az esetet kell lekezelnünk, hogy a dobozt egy lyukra tolják. 
     * Ekkor első körben lekérjük, van-e a lyukon éppen movable, ha igen, akkor kiderült az is, 
     * hogy a lyuk éppen nem volt aktív, ebben az esetben megnézzük, hogy a láncolatunk eltolható-e a visit hívás tovább láncolásával. 
     * Ha igen, akkor csak visszatérünk igaz értékkel. ha nem eltolható, akkor pedig értelem szerűen hamissal térünk vissza. 
     * Ha a lyukon nem volt movable, akkor a láncot toló munkás erejét és a lánc súrlódási összegét vizsgálva megnézzük, 
     * hogy a lánc tolható-e és ennek megfelelően térünk vissza igazzal vagy hamissal, 
     * de mindkét esetben először nullázzuk az actualChainFriction értékét.
     * @param hole
     * @return boolean
     */
    @Override
    public boolean visit(Hole hole) {
        if (Game.getInstance().printing) {
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + hole.getId() + ")");
        }
        Game.getInstance().tabs++;
        Movable movable = hole.getActualMovable();
        
        if (movable != null) {
            if (movable.visit(this)) {
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        } else {
            if (Game.getInstance().getActualMovingWorker().getForce() > Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< true");
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().tabs--;
                Game.getInstance().printTabs();
                System.out.println("< false");
                return false;
            }
        }
    }

    /**
     * Munkás próbál eltolni dobozt, paraméterben kapja azt a munkást, aki eltolná. 
     * Ez a lépés minden esetben egy move() függvény hívást kezdeményez, ami a további láncolást alapján jár el, 
     * a lánc sikeres eltolásának visszatérési értéke alapján tér vissza a függvény is, igazzal ha a lánc eltolható, 
     * vagy hamissal, ha nem.
     * @param worker
     * @return 
     */
    @Override
    public boolean visit(Worker worker) {
        if (Game.getInstance().printing){
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + worker.getId() + ")");
        }
        Game.getInstance().tabs++;
        
        if (move()) {
            Game.getInstance().tabs--;
            Game.getInstance().printTabs();
            System.out.println("< true");
            return true;
        } else {
            Game.getInstance().tabs--;
            Game.getInstance().printTabs();
            System.out.println("< false");
            return false;
        }
    }

    /**
     * Doboz próbál eltolni dobozt, paraméterben kapja az első dobozt. 
     * Ez a lépés minden esetben egy move() függvény hívást kezdeményez, ami a további láncolást alapján jár el, 
     * a lánc sikeres eltolásának visszatérési értéke alapján tér vissza a függvény is, igazzal ha a lánc eltolható, 
     * vagy hamissal, ha nem.
     * @param box
     * @return 
     */
    @Override
    public boolean visit(Box box) {
        if (Game.getInstance().printing){
            Game.getInstance().printTabs();
            System.out.println("> " + this.getId() + ".visit(" + box.getId() + ")");
        }
        Game.getInstance().tabs++;
        
        if (move()) {
            Game.getInstance().tabs--;
            Game.getInstance().printTabs();
            System.out.println("< true");
            return true;
        } else {
            Game.getInstance().tabs--;
            Game.getInstance().printTabs();
            System.out.println("< false");
            return false;
        }
    }

}