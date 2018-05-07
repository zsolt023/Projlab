package main.movable;

import main.Game;
import main.ImagePanel;
import main.Util;
import main.field.Hole;
import main.field.HoneyPlain;
import main.field.Objective;
import main.field.OilPlain;
import main.field.Plain;
import main.field.Switch;
import main.field.Wall;


public class Worker extends Movable {

    private int score = 0;

    private int force = 20;

    public Worker() {
        //Default constructor
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    /**
     * Inkrementálja a munkás pontjait egy ponttal.
     */
    public void addPoint() {
        this.score++;
    }

    /**
     * Ez a függvény hívódik meg, ha az aktuális munkás objektumot megpróbálják eltolni vagy épp a felhasználó lépne vele.
     * Ezen metódus feladata, hogy tovább hívjon a lépés irányában lévő szomszéd accept metódusára
     * és ezen accept metódus visszatérési értéke határozza meg a move vissza térési értékét is.
     *
     * @return boolean
     */
    @Override
    public boolean move() {
        if (this.actualField.getNeigbour().accept(this)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Azon eset, amikor a munkás egy sima mezőre kerülne. Itt is első körben lekérjük a sima mező movable elemét
     * és tovább láncolunk, ha nem üres, akkor a visit hívásnak megfelelően térünk vissza.
     * Ha üres volt a sima mező meg kell vizsgálni, hogy a lánc eltolható-e a láncot toló munkás erejét
     * és a lánc súrlódási összegét vizsgálva és ennek megfelelően térünk vissza igazzal vagy hamissal,
     * de mindkét esetben először nullázzuk az actualChainFriction értékét.
     *
     * @param plain
     * @return boolean
     */
    @Override
    public boolean visit(Plain plain) {
        Movable movable = plain.getActualMovable();

        if (movable != null) {
            if (movable.visit(this)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (Game.getInstance().getActualMovingWorker().getForce() >= Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                return false;
            }
        }
    }

    /**
     * Azon eset, amikor a munkás egy mézes mezőre kerülne. Itt is első körben lekérjük a mézes mező movable elemét
     * és tovább láncolunk, ha nem üres, akkor a visit hívásnak megfelelően térünk vissza.
     * Ha üres volt a mézes mező meg kell vizsgálni, hogy a lánc eltolható-e a láncot toló munkás erejét
     * és a lánc súrlódási összegét vizsgálva és ennek megfelelően térünk vissza igazzal vagy hamissal,
     * de mindkét esetben először nullázzuk az actualChainFriction értékét.
     *
     * @param honeyPlain
     * @return boolean
     */
    @Override
    public boolean visit(HoneyPlain honeyPlain) {
        Movable movable = honeyPlain.getActualMovable();

        if (movable != null) {
            if (movable.visit(this)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (Game.getInstance().getActualMovingWorker().getForce() >= Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                return false;
            }
        }
    }

    /**
     * Azon eset, amikor a munkás egy olajos mezőre kerülne. Itt is első körben lekérjük az olajos mező movable elemét
     * és tovább láncolunk, ha nem üres, akkor a visit hívásnak megfelelően térünk vissza.
     * Ha üres volt az olajos mező meg kell vizsgálni, hogy a lánc eltolható-e a láncot toló munkás erejét
     * és a lánc súrlódási összegét vizsgálva és ennek megfelelően térünk vissza igazzal vagy hamissal,
     * de mindkét esetben először nullázzuk az actualChainFriction értékét.
     *
     * @param oilPlain
     * @return boolean
     */
    @Override
    public boolean visit(OilPlain oilPlain) {
        Movable movable = oilPlain.getActualMovable();

        if (movable != null) {
            if (movable.visit(this)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (Game.getInstance().getActualMovingWorker().getForce() >= Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                return false;
            }
        }
    }

    /**
     * Ennél a függvény hívásnál azt az esetet kell lekezelnünk, hogy a munkás egy falra kerülne.
     * Ebben az esetben mivel a munkást rá lehet tolni a falra, először lekérjük az aktuálisan lépő munkást,
     * és ha ezen munkás nem egyezik azzal a munkással akinél meghívódott ez a függvény,
     * akkor kill-eljük azon munkást akin meghívtuk ezt a függvényt. és a visszatérési érték igaz lesz,
     * ha megegyezett a két említett munkás, tehát a munkás közvetlenül falra szeretne lépni,
     * ekkor hamissal térünk vissza. De visszatérés előtt mindenképpen nullázzuk az actualChainFriction értékét,
     * mert ez után új lépés következik és innen nem mehet tovább a láncolás.
     *
     * @param wall
     * @return boolean
     */
    @Override
    public boolean visit(Wall wall) {
        Worker actualMovingWorker = Game.getInstance().getActualMovingWorker();

        if (!actualMovingWorker.getId().equals(this.getId())) {
            if (actualMovingWorker.getForce() >= Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                Game.getInstance().getTable().kill(this);
                return true;
            }
        }
        Game.getInstance().setActualChainFriction(0);
        return false;
    }

    /**
     * Azon eset, amikor is a munkás kerülne a cél mezőre. Ekkor meg kell néznünk,
     * hogy az adott cél-helyen van-e valamilyen movable objektum (ami egyébként csak munkás lehet, mert a doboz eltüntetésre kerülne, amikor is rátolják).
     * Ebben az esetben egy újabb visit hívást kezdeményezünk, ami a láncolás eltolásának ellenőrzésére szolgál.
     * A lánc sikeres eltolásának visszatérési értéke alapján, ha igazzal tért vissza,
     * akkor az adott munkást beállítjuk ezen cél mezőre. Ellenkező esetben hamissal térünk vissza.
     * Ha nem szerepelt a cél-helyen munkás, akkor itt dől el, hogy ez a lánc eltolható-e és vissza térünk ennek megfelelő értékkel,
     * amit a már említett módon vizsgálunk meg a súrlódási értékeknek megfelelően,
     * de előtte mindkettő esetben először nullázzuk a globális súrlódási együtthatót.
     * Ha tolható a lánc, akkor itt is az adott munkást beállítjuk ezen cél mezőre.
     * És végül tolás sikerességének megfelelően térünk vissza.
     *
     * @param objective
     * @return boolean
     */
    @Override
    public boolean visit(Objective objective) {
        Movable movable = objective.getActualMovable();

        if (movable != null) {
            if (movable.visit(this)) {
                objective.setActualMovable(this);
                return true;
            } else {
                return false;
            }
        } else {
            if (Game.getInstance().getActualMovingWorker().getForce() >= Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                objective.setActualMovable(this);
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                return false;
            }
        }
    }

    /**
     * Ennél a függvény hívásnál azt az esetet kell lekezelnünk, hogy a munkás egy kapcsolóra kerülne.
     * Mivel a doboz kapcsolhatja csak a kapcsolót, ezért ezt attól függően, hogy a kapcsolón áll-e más movable.
     * Ha igen és az egész lánc eltolható-e, amit egy tovább hívással rekurzívan a következő visit hívásával ellenőrizünk,
     * ha a lánc tolható és kapcsolón lévő movable éppen egy doboz, akkor ráléptetjük a munkást a switch-re,
     * át is kapcsoljuk és igazzal térünk vissza. Ha az egész láncról az derül ki, hogy nem tolható tovább,
     * akkor hamissal térünk vissza. Az utolsó eset pedig, ha a kapcsolón nem volt movable,
     * ebben az esetben is megvizsgáljuk a szokott módon a lánc tolása lehetséges-e. És ennek megfelelően,
     * ha lehetséges a tolás, akkor deaktiváljuk a kapcsolóhoz tartozó lyukat. és igazzal térünk vissza,
     * ellenkező esetben a lánc nem tolható és hamissal térünk vissza, , de mindkét esetben először nullázzuk az actualChainFriction értékét.
     *
     * @param s
     * @return boolean
     */
    @Override
    public boolean visit(Switch s) {
        Movable movable = s.getActualMovable();

        if (movable != null) {
            if (movable.visit(this)) {
                if (!(movable instanceof Box)) {
                    s.switchState();
                }
                return true;
            } else {
                return false;
            }
        } else {
            if (Game.getInstance().getActualMovingWorker().getForce() >= Game.getInstance().getActualChainFriction()) {
                s.switchState();
                Game.getInstance().setActualChainFriction(0);
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                return false;
            }
        }
    }

    /**
     * Ennél a függvény hívásnál azt az esetet kell lekezelnünk, hogy a munkás egy lyukra kerülne.
     * Ekkor első körben lekérjük, van-e a lyukon éppen movable, ha igen, akkor kiderült az is,
     * hogy a lyuk éppen nem volt aktív, ebben az esetben megnézzük, hogy a láncolatunk eltolható-e a visit hívás tovább láncolásával.
     * Ha igen, akkor csak visszatérünk igaz értékkel. ha nem eltolható, akkor pedig értelem szerűen hamissal térünk vissza.
     * Ha a lyukon nem volt movable, akkor a láncot toló munkás erejét és a lánc súrlódási összegét vizsgálva megnézzük,
     * hogy a lánc tolható-e és ennek megfelelően térünk vissza igazzal vagy hamissal,
     * de mindkét esetben először nullázzuk az actualChainFriction értékét.
     *
     * @param hole
     * @return boolean
     */
    @Override
    public boolean visit(Hole hole) {
        Movable movable = hole.getActualMovable();

        if (movable != null) {
            if (movable.visit(this)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (Game.getInstance().getActualMovingWorker().getForce() >= Game.getInstance().getActualChainFriction()) {
                Game.getInstance().setActualChainFriction(0);
                return true;
            } else {
                Game.getInstance().setActualChainFriction(0);
                return false;
            }
        }
    }

    /**
     * Munkás próbál eltolni munkást, paraméterben kapja azt a munkást, aki eltolná.
     * Ez a lépés minden esetben egy false-al tér vissza, mivel munkás munkást nem képes közvetlenül eltolni.
     *
     * @param worker
     * @return boolean
     */
    @Override
    public boolean visit(Worker worker) {
        Game.getInstance().setActualChainFriction(0);
        return false;
    }

    /**
     * Doboz próbál eltolni munkást, paraméterben kapja a dobozt. Ez a lépés minden esetben egy move() függvény hívást kezdeményez,
     * ami a további láncolást alapján jár el, a lánc sikeres eltolásának visszatérési értéke alapján tér vissza a függvény is,
     * igazzal ha a lánc eltolható, vagy hamissal, ha nem.
     *
     * @param box
     * @return boolean
     */
    @Override
    public boolean visit(Box box) {
        if (move()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw() {
        String[] idWithKoord = this.getId().split("_");
        ImagePanel imagePanel = new ImagePanel("code/res/obj/worker.png", Integer.parseInt(idWithKoord[1])* 30, Integer.parseInt(idWithKoord[2]) * 30);
        imagePanel.paintComponents(imagePanel.graphics);
        Util.frame.getContentPane().add(imagePanel);
    }
    
}